package com.baoshine.questionnaire.service.impl;

import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.common.resp.PageResp;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.constant.ExcludeFields;
import com.baoshine.questionnaire.entity.AnswerOption;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.repository.GenericRepository;
import com.baoshine.questionnaire.service.AnswerOptionService;
import com.baoshine.questionnaire.specification.AnswerOptionSpecification;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.AnswerOptionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnswerOptionServiceImpl  implements AnswerOptionService {

    private final GenericRepository genericRepository;

    @Autowired
    public AnswerOptionServiceImpl(GenericRepository genericRepository){
        this.genericRepository = genericRepository;
    }

    /**
     * 查询答案列表
     * @param answerOptionVO 查询条件
     * @param pageable 分页信息
     * @return 查询结果
     */
    @Override
    public PageResp<AnswerOption> listAnswerOption(AnswerOptionVO answerOptionVO, Pageable pageable){
        Page<AnswerOption> options = genericRepository.findAllBy(AnswerOption.class, AnswerOptionSpecification.buildCondition(answerOptionVO.getCode(),answerOptionVO.getType(), answerOptionVO.getContent()), pageable);
        return PageResp.by(options);
    }

    /**
     * 保存答案信息
     * @param answerOptionVO 保存答案内容
     * @return 保存实体主键ID
     */
    @Override
    public Long saveAnswerOption(AnswerOptionVO answerOptionVO){
        AnswerOption answerOption = ListBeanConvertUtil.convert(answerOptionVO, AnswerOption.class);
        assert answerOption != null;
        return genericRepository.save(answerOption).getId();
    }

    /**
     * 修改答案
     * @param answerOptionVO 修改答案内容
     * @return 修改实体主键ID
     * @throws QuestionnaireException 修改报错
     */
    @Override
    public Long updateAnswerOption(AnswerOptionVO answerOptionVO) throws QuestionnaireException {
        AnswerOption answerOption = genericRepository.findById(AnswerOption.class, answerOptionVO.getId()).orElseThrow(() -> new QuestionnaireException(
                ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        BeanUtils.copyProperties(answerOptionVO, answerOption, ExcludeFields.excludeField);
        return genericRepository.save(answerOption).getId();
    }

    /**
     * 删除答案
     * @param id 删除答案ID
     * @return 删除执行结果
     * @throws EntityPersistenceException 删除报错
     */
    @Override
    public int deleteAnswerOption(Long id) throws QuestionnaireException {
        genericRepository.findById(AnswerOption.class, id).orElseThrow(() -> new QuestionnaireException(
                ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        return  genericRepository.deleteById(AnswerOption.class, id);
    }
}
