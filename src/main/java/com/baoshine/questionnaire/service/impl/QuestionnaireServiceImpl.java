package com.baoshine.questionnaire.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.questionnaire.constant.ExcludeFields;
import com.baoshine.questionnaire.entity.*;
import com.baoshine.questionnaire.enums.QuestionnaireStatus;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.repository.GenericRepository;
import com.baoshine.questionnaire.service.NodeService;
import com.baoshine.questionnaire.service.PathService;
import com.baoshine.questionnaire.service.QuestionnaireService;
import com.baoshine.questionnaire.specification.QuestionnaireSpecification;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final GenericRepository genericRepository;

    private final PathService pathService;

    private final NodeService nodeService;

    @Autowired
    public QuestionnaireServiceImpl(GenericRepository genericRepository,
                                    PathService pathService,
                                    NodeService nodeService) {
        this.genericRepository = genericRepository;
        this.nodeService = nodeService;
        this.pathService = pathService;
    }

    /**
     * 查询问卷信息
     *
     * @param questionnaireId
     * @return
     */
    @Override
    public Questionnaire loadQuestionnaire(Long questionnaireId) throws QuestionnaireException {
        return genericRepository.findById(Questionnaire.class, questionnaireId)
                .orElseThrow(() -> new QuestionnaireException(ResultCodeEnum.CMN_QUERY_RESULT_NULL));
    }

    /**
     * 查询问卷列表
     *
     * @param questionnaireVO 问卷查询条件
     * @param pageable        分页信息
     * @return 查询结果
     */
    @Override
    public Page<Questionnaire> listQuestionnaire(QuestionnaireVO questionnaireVO, Pageable pageable) {
        return genericRepository.findAllBy(Questionnaire.class, QuestionnaireSpecification
                .buildCondition(questionnaireVO.getCode(), questionnaireVO.getName(),
                        questionnaireVO.getQuestionnaireDesc()), pageable);
    }

    /**
     * 查询下属问题
     *
     * @param questionnaireId
     * @param nodeId
     * @return
     */
    @Override
    public List<Node> nextQuestions(Long questionnaireId, Long nodeId) throws QuestionnaireException {
        List<Node> result;
        Questionnaire questionnaire = genericRepository.findById(Questionnaire.class, questionnaireId)
                .orElseThrow(() -> new QuestionnaireException(ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        List<Node> nodes = questionnaire.getNodeList();
        List<Path> paths = questionnaire.getPathList();
        if (CollectionUtils.isEmpty(nodes)) {
            return null;
        }
        Map<Long, Node> nodeMap =
                nodes.stream().collect(Collectors.toMap(UUIDEntity::getId, node -> node, (o, n) -> n));
        result = listChildNode(nodeMap, paths, nodeId);
        return result;
    }

    /**
     * 筛选所有下级问题节点
     * @param nodeMap
     * @param paths
     * @param nodeId
     * @return
     */
    public List<Node> listChildNode(Map<Long, Node> nodeMap, List<Path> paths, Long nodeId){
        List<Node> result = new ArrayList<>();
        boolean hasOptionParent = false;
        List<Path> childPaths =
                paths.stream().filter(path -> path.getParentNodeId().equals(nodeId)).collect(Collectors.toList());
        for (Path path : childPaths) {
            //子节点没有条件判定
            if (CollectionUtils.isEmpty(path.getAnswerOptions())) {
                List<Path> parentPaths =
                        paths.stream().filter(path1 -> path1.getChildNodeId().equals(path.getChildNodeId()))
                                .collect(Collectors.toList());
                for (Path parentPath : parentPaths) {
                    if (!CollectionUtils.isEmpty(parentPath.getAnswerOptions())) {
                        hasOptionParent = true;
                    }
                }
                //子节点同时没有包含条件判定的其他父节点
                if (!hasOptionParent) {
                    result.add(nodeMap.get(path.getChildNodeId()));
                }
                result.addAll(listChildNode(nodeMap, paths, path.getChildNodeId()));
            }
        }
        return result;
    }

    /**
     * 显示问卷详情
     *
     * @param id 问卷id
     * @return 问卷详情
     */
    @Override
    public QuestionnaireDetailVO detailQuestionnaire(Long id) throws QuestionnaireException {

        Questionnaire questionnaire = genericRepository.findById(Questionnaire.class, id)
                .orElseThrow(() -> new QuestionnaireException(ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        return detailConvertToVO(questionnaire);
    }

    /**
     * 保存问卷信息
     *
     * @param questionnaireVO 保存问卷内容
     * @return 保存实体主键ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveQuestionnaire(QuestionnaireVO questionnaireVO) {
        Questionnaire questionnaire = convertToEntity(questionnaireVO);
        assert questionnaire != null;
        return genericRepository.save(questionnaire).getId();
    }

    /**
     * 保存问卷详情
     *
     * @param questionnaireDetailVO 问卷详细信息
     * @return 保存实体主键ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveQuestionnaireDetail(QuestionnaireDetailVO questionnaireDetailVO) {
        Questionnaire questionnaire = detailConvertToEntity(questionnaireDetailVO);
        return genericRepository.save(questionnaire).getId();
    }

    /**
     * 更新问卷信息
     *
     * @param questionnaireVO 更新问卷内容
     * @return 更新实体主键ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateQuestionnaire(QuestionnaireVO questionnaireVO) throws QuestionnaireException {
        Questionnaire questionnaire = genericRepository.findById(Questionnaire.class, questionnaireVO.getId())
                .orElseThrow(() -> new QuestionnaireException(
                        ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        BeanUtil.copyProperties(questionnaireVO, questionnaire,
                CopyOptions.create().setIgnoreNullValue(true).setIgnoreProperties(ExcludeFields.excludeField));
        return genericRepository.save(questionnaire).getId();
    }

    /**
     * 删除问卷
     *
     * @param id 删除问卷ID
     * @return 删除执行结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteQuestionnaire(Long id) throws QuestionnaireException {
        Questionnaire questionnaire =
                genericRepository.findById(Questionnaire.class, id).orElseThrow(() -> new QuestionnaireException(
                        ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        questionnaire.setIsDeleted("Y");
        return genericRepository.save(questionnaire).getId();
    }

    /**
     * 改变问卷模板状态
     *
     * @param questionnaireId     问卷ID
     * @param questionnaireStatus 问卷状态
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void effectiveQuestionnaire(Long questionnaireId, QuestionnaireStatus questionnaireStatus)
            throws QuestionnaireException {
        Questionnaire questionnaire = genericRepository.findById(Questionnaire.class, questionnaireId)
                .orElseThrow(() -> new QuestionnaireException(
                        ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        questionnaire.setStatus(questionnaireStatus.getKey());
        genericRepository.save(questionnaire);
    }

    /**
     * 查询配置当前问卷的产品
     *
     * @param questionnaireId 问卷ID
     * @return
     */
    @Override
    public Page<ProductQuestionnaire> listProductQuestionnaire(Long questionnaireId, Pageable pageable) {
        List<ProductQuestionnaire> productQuestionnaires =
                genericRepository.findAllBy(ProductQuestionnaire.class, "questionnaire_id", questionnaireId);
        return new PageImpl<>(productQuestionnaires, pageable, productQuestionnaires.size());
    }

    public Questionnaire convertToEntity(QuestionnaireVO questionnaireVO) {
        Questionnaire questionnaire = ListBeanConvertUtil.convert(questionnaireVO, Questionnaire.class);
        assert questionnaire != null;
        questionnaire.setProductConfigs(
                ListBeanConvertUtil.convert(questionnaireVO.getProductQuestionnaireVOS(), ProductQuestionnaire.class));
        return questionnaire;
    }

    public Questionnaire detailConvertToEntity(QuestionnaireDetailVO questionnaireDetailVO) {
        Questionnaire questionnaire =
                ListBeanConvertUtil.convert(questionnaireDetailVO.getQuestionnaireVO(), Questionnaire.class);
        assert questionnaire != null;
        List<Path> paths = questionnaireDetailVO.getPathVOS().stream().map(pathService::convertToEntity).collect(
                Collectors.toList());
        List<Node> nodes = questionnaireDetailVO.getNodeVOS().stream().map(nodeService::convertToEntity).collect(
                Collectors.toList());
        questionnaire.getNodeList().addAll(nodes);
        questionnaire.getPathList().addAll(paths);
        return questionnaire;
    }

    public QuestionnaireVO convertToVO(Questionnaire questionnaire) {
        QuestionnaireVO questionnaireVO = ListBeanConvertUtil.convert(questionnaire, QuestionnaireVO.class);
        assert questionnaireVO != null;
        questionnaireVO.setProductQuestionnaireVOS(
                ListBeanConvertUtil.convert(questionnaire.getProductConfigs(), ProductQuestionnaireVO.class));
        return questionnaireVO;
    }

    public QuestionnaireDetailVO detailConvertToVO(Questionnaire questionnaire) {
        QuestionnaireDetailVO questionnaireDetailVO = new QuestionnaireDetailVO();
        questionnaireDetailVO.setQuestionnaireVO(convertToVO(questionnaire));
        List<PathVO> pathVOS = questionnaire.getPathList().stream().map(pathService::convertToVO).collect(
                Collectors.toList());
        List<NodeVO> nodeVOS = questionnaire.getNodeList().stream().map(nodeService::convertToVO).collect(
                Collectors.toList());
        questionnaireDetailVO.setPathVOS(pathVOS);
        questionnaireDetailVO.setNodeVOS(nodeVOS);
        return questionnaireDetailVO;
    }


}
