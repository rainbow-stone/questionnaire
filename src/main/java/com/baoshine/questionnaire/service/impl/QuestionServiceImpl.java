package com.baoshine.questionnaire.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.constant.ExcludeFields;
import com.baoshine.questionnaire.entity.AnswerOption;
import com.baoshine.questionnaire.entity.Question;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.repository.GenericRepository;
import com.baoshine.questionnaire.service.QuestionService;
import com.baoshine.questionnaire.specification.QuestionSpecification;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.AnswerOptionVO;
import com.baoshine.questionnaire.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final GenericRepository genericRepository;

    @Autowired
    public QuestionServiceImpl(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    /**
     * 查询问题列表
     *
     * @param questionVO 查询条件
     * @param pageable   分页条件
     * @return 查询结果
     */
    @Override
    public Page<Question> listQuestion(QuestionVO questionVO, Pageable pageable) {
        return genericRepository.findAllBy(Question.class, QuestionSpecification
                        .buildCondition(questionVO.getCode(), questionVO.getQuestionType(), questionVO.getPresentationType(),
                                questionVO.getContent()),
                pageable);
    }

    /**
     * 保存问题
     *
     * @param questionVO 保存问题内容
     * @return 保存实体主键ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveQuestion(QuestionVO questionVO) {
        Question question = convertToEntity(questionVO);
        /*List<AnswerOption> list = question.getAnswerOptions().stream().map(answerOption -> genericRepository.findById(AnswerOption.class, answerOption.getId()).get()).collect(
                Collectors.toList());
        question.setAnswerOptions(list);*/
        assert question != null;
        return genericRepository.save(question).getId();
    }

    /**
     * 更新问题
     *
     * @param questionVO 修改问题内容
     * @return 更新实体主键ID
     * @throws QuestionnaireException 修改报错
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateQuestion(QuestionVO questionVO) throws QuestionnaireException {
        Question question = genericRepository.findById(Question.class, questionVO.getId())
                .orElseThrow(() -> new QuestionnaireException(
                        ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        BeanUtil.copyProperties(questionVO, question,
                CopyOptions.create().setIgnoreNullValue(true).setIgnoreProperties(ExcludeFields.excludeField));
        return genericRepository.save(question).getId();
    }

    /**
     * 删除问题
     *
     * @param id 删除问题主键ID
     * @return 删除执行结果
     * @throws EntityPersistenceException 删除报错
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteQuestion(Long id) throws QuestionnaireException {
        Question question = genericRepository.findById(Question.class, id).orElseThrow(() -> new QuestionnaireException(
                ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        question.setIsDeleted("Y");
        return genericRepository.save(question).getId();
    }

    /**
     * 加载
     *
     * @param id
     * @return
     * @throws QuestionnaireException
     */
    @Override
    public Question loadQuestion(Long id) throws QuestionnaireException {
        return genericRepository.findById(Question.class, id).orElseThrow(() -> new QuestionnaireException(
                ResultCodeEnum.CMN_QUERY_RESULT_NULL));
    }

    /**
     * 添加问题答案配置关系
     *
     * @param questionId 问题ID
     * @param optionId   答案ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAnswerOptionConfig(Long questionId, Long optionId) throws QuestionnaireException {
        Question question =
                genericRepository.findById(Question.class, questionId).orElseThrow(() -> new QuestionnaireException(
                        ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        AnswerOption answerOption =
                genericRepository.findById(AnswerOption.class, optionId).orElseThrow(() -> new QuestionnaireException(
                        ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        question.getAnswerOptions().add(answerOption);
        genericRepository.save(question);
    }

    /**
     * 删除问题答案配置关系
     *
     * @param questionId 问题ID
     * @param optionId   答案ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAnswerOptionConfig(Long questionId, Long optionId) throws QuestionnaireException {
        Question question =
                genericRepository.findById(Question.class, questionId).orElseThrow(() -> new QuestionnaireException(
                        ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        AnswerOption answerOption =
                genericRepository.findById(AnswerOption.class, optionId).orElseThrow(() -> new QuestionnaireException(
                        ResultCodeEnum.CMN_QUERY_RESULT_NULL));
        question.getAnswerOptions().remove(answerOption);
        genericRepository.save(question);
    }

    public QuestionVO convertToVO(Question question) {
        QuestionVO questionVO = ListBeanConvertUtil.convert(question, QuestionVO.class);
        assert questionVO != null;
        questionVO.setAnswerOptionVOS(ListBeanConvertUtil.convert(question.getAnswerOptions(), AnswerOptionVO.class));
        return questionVO;
    }

    public Question convertToEntity(QuestionVO questionVO) {
        Question question = ListBeanConvertUtil.convert(questionVO, Question.class);
        assert question != null;
        question.setAnswerOptions(ListBeanConvertUtil.convert(questionVO.getAnswerOptionVOS(), AnswerOption.class));
        return question;

    }

}