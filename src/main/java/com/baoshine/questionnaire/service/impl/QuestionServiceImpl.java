package com.baoshine.questionnaire.service.impl;

import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.common.resp.PageResp;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.constant.ExcludeFields;
import com.baoshine.questionnaire.entity.Question;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.repository.GenericRepository;
import com.baoshine.questionnaire.service.QuestionService;
import com.baoshine.questionnaire.specification.QuestionSpecification;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.QuestionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class QuestionServiceImpl implements QuestionService {

    private final GenericRepository genericRepository;

    @Autowired
    public QuestionServiceImpl(GenericRepository genericRepository){
        this.genericRepository = genericRepository;
    }

    /**
     * 查询问题列表
     * @param questionVO 查询条件
     * @param pageable 分页条件
     * @return 查询结果
     */
    @Override
    public PageResp<Question> listQuestion(QuestionVO questionVO, Pageable pageable) {
        Page<Question> questions = genericRepository.findAllBy(Question.class, QuestionSpecification.buildCondition(questionVO.getCode(), questionVO.getQuestionType(), questionVO.getPresentationType(), questionVO.getContent()),
                pageable);
        return PageResp.by(questions);
    }

    /**
     * 保存问题
     * @param questionVO 保存问题内容
     * @return 保存实体主键ID
     */
    @Override
    public Long saveQuestion(QuestionVO questionVO) {
        Question question = ListBeanConvertUtil.convert(questionVO, Question.class);
        assert question != null;
        return genericRepository.save(question).getId();
    }

    /**
     * 更新问题
     * @param questionVO 修改问题内容
     * @return 更新实体主键ID
     * @throws QuestionnaireException 修改报错
     */
    @Override
    public Long updateQuestion(QuestionVO questionVO) throws QuestionnaireException {
        Question question = genericRepository.findById(Question.class, questionVO.getId()).orElseThrow(() -> new QuestionnaireException(
                ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        BeanUtils.copyProperties(questionVO, question, ExcludeFields.excludeField);
        return genericRepository.save(question).getId();
    }

    /**
     * 删除问题
     * @param id 删除问题主键ID
     * @return 删除执行结果
     * @throws EntityPersistenceException 删除报错
     */
    @Override
    public int deleteQuestion(Long id) throws QuestionnaireException {
        genericRepository.findById(Question.class, id).orElseThrow(() -> new QuestionnaireException(
                ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        return genericRepository.deleteById(Questionnaire.class, id);
    }
}