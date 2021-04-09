package com.baoshine.questionnaire.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.constant.ExcludeFields;
import com.baoshine.questionnaire.entity.AnswerOption;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.repository.GenericRepository;
import com.baoshine.questionnaire.service.AnswerOptionService;
import com.baoshine.questionnaire.specification.AnswerOptionSpecification;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.AnswerOptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {

    private final GenericRepository genericRepository;

    @Autowired
    public AnswerOptionServiceImpl(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    /**
     * 查询答案列表
     *
     * @param answerOptionVO 查询条件
     * @param pageable       分页信息
     * @return 查询结果
     */
    @Override
    public Page<AnswerOption> listAnswerOption(AnswerOptionVO answerOptionVO, Pageable pageable) {
        return genericRepository.findAllBy(AnswerOption.class, AnswerOptionSpecification
                        .buildCondition(answerOptionVO.getCode(), answerOptionVO.getType(), answerOptionVO.getContent()),
                pageable);
    }

    /**
     * 保存答案信息
     *
     * @param answerOptionVO 保存答案内容
     * @return 保存实体主键ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveAnswerOption(AnswerOptionVO answerOptionVO) {
        AnswerOption answerOption = ListBeanConvertUtil.convert(answerOptionVO, AnswerOption.class);
        assert answerOption != null;
        return genericRepository.save(answerOption).getId();
    }

    /**
     * 修改答案
     *
     * @param answerOptionVO 修改答案内容
     * @return 修改实体主键ID
     * @throws QuestionnaireException 修改报错
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateAnswerOption(AnswerOptionVO answerOptionVO) throws QuestionnaireException {
        AnswerOption answerOption = genericRepository.findById(AnswerOption.class, answerOptionVO.getId())
                .orElseThrow(() -> new QuestionnaireException(
                        ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        BeanUtil.copyProperties(answerOptionVO, answerOption,
                CopyOptions.create().setIgnoreNullValue(true).setIgnoreProperties(ExcludeFields.excludeField));
        return genericRepository.save(answerOption).getId();
    }

    /**
     * 删除答案
     *
     * @param id 删除答案ID
     * @return 删除执行结果
     * @throws EntityPersistenceException 删除报错
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteAnswerOption(Long id) throws QuestionnaireException {

        AnswerOption answerOption =
                genericRepository.findById(AnswerOption.class, id).orElseThrow(() -> new QuestionnaireException(
                        ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        answerOption.setIsDeleted("Y");
        return genericRepository.save(answerOption).getId();
    }

    @Override
    public AnswerOption loadAnswerOption(Long id) throws QuestionnaireException {
        return genericRepository.findById(AnswerOption.class, id).orElseThrow(() -> new QuestionnaireException(
                ResultCodeEnum.CMN_QUERY_RESULT_NULL));
    }

}
