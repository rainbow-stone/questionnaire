package com.baoshine.questionnaire.service.impl;

import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.common.resp.PageResp;
import com.baoshine.questionnaire.constant.ExcludeFields;
import com.baoshine.questionnaire.entity.Node;
import com.baoshine.questionnaire.entity.Path;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.repository.GenericRepository;
import com.baoshine.questionnaire.service.QuestionnaireService;
import com.baoshine.questionnaire.specification.QuestionnaireSpecification;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.NodeVO;
import com.baoshine.questionnaire.vo.PathVO;
import com.baoshine.questionnaire.vo.QuestionnaireDetailVO;
import com.baoshine.questionnaire.vo.QuestionnaireVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final GenericRepository genericRepository;

    @Autowired
    public QuestionnaireServiceImpl(GenericRepository genericRepository){
        this.genericRepository = genericRepository;
    }

    /**
     * 查询问卷列表
     *
     * @param questionnaireVO 问卷查询条件
     * @param pageable 分页信息
     * @return 查询结果
     */
    @Override
    public PageResp<Questionnaire> listQuestionnaire(QuestionnaireVO questionnaireVO, Pageable pageable) {
        Page<Questionnaire> questionnaires = genericRepository.findAllBy(Questionnaire.class, QuestionnaireSpecification.buildCondition(questionnaireVO.getCode(), questionnaireVO.getName(), questionnaireVO.getQuestionnaireDesc()), pageable);
        return PageResp.by(questionnaires);
    }

    /**
     * 显示问卷详情
     *
     * @param id 问卷id
     * @return 问卷详情
     */
    @Override
    public QuestionnaireDetailVO detailQuestionnaire(Long id) throws QuestionnaireException {

        Questionnaire questionnaire = genericRepository.findById(Questionnaire.class, id).orElseThrow(() -> new QuestionnaireException(ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        QuestionnaireVO questionnaireVO = ListBeanConvertUtil.convert(questionnaire, QuestionnaireVO.class);
        List<Node> nodes = questionnaire.getNodeList();
        List<Path> paths = questionnaire.getPathList();
        if(nodes == null || nodes.size() == 0){
            return new QuestionnaireDetailVO(questionnaireVO, new ArrayList<>(), new ArrayList<>());
        }
        List<NodeVO> nodeVOS = ListBeanConvertUtil.convert(nodes, NodeVO.class);
        List<PathVO> pathVOS = ListBeanConvertUtil.convert(paths, PathVO.class);
        return new QuestionnaireDetailVO(questionnaireVO, nodeVOS, pathVOS);
    }

    /**
     * 保存问卷信息
     *
     * @param questionnaireVO 保存问卷内容
     * @return 保存实体主键ID
     */
    @Override
    public Long saveQuestionnaire(QuestionnaireVO questionnaireVO) {
        Questionnaire questionnaire = ListBeanConvertUtil.convert(questionnaireVO, Questionnaire.class);
        assert questionnaire != null;
        return genericRepository.save(questionnaire).getId();
    }

    /**
     * 更新问卷信息
     * @param questionnaireVO 更新问卷内容
     * @return 更新实体主键ID
     */
    @Override
    public Long updateQuestionnaire(QuestionnaireVO questionnaireVO) throws QuestionnaireException {
        Questionnaire questionnaire = genericRepository.findById(Questionnaire.class, questionnaireVO.getId()).orElseThrow(() -> new QuestionnaireException(
                ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        BeanUtils.copyProperties(questionnaireVO, questionnaire, ExcludeFields.excludeField);
        return genericRepository.save(questionnaire).getId();
    }

    /**
     * 删除问卷
     * @param id 删除问卷ID
     * @return 删除执行结果
     */
    @Override
    public int deleteQuestionnaire(Long id) throws QuestionnaireException {
        genericRepository.findById(Questionnaire.class, id).orElseThrow(() -> new QuestionnaireException(
                ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        return genericRepository.deleteById(Questionnaire.class, id);
    }

}
