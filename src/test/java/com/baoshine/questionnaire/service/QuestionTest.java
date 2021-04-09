package com.baoshine.questionnaire.service;

import com.baoshine.questionnaire.QuestionnaireApplication;
import com.baoshine.questionnaire.entity.AnswerOption;
import com.baoshine.questionnaire.entity.Question;
import com.baoshine.questionnaire.enums.HealthQuestionType;
import com.baoshine.questionnaire.enums.PresentationType;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.pool.utils.TestUtils;
import com.baoshine.questionnaire.repository.GenericRepository;
import com.baoshine.questionnaire.repository.QuestionRepository;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.AnswerOptionVO;
import com.baoshine.questionnaire.vo.QuestionVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuestionnaireApplication.class})
@Slf4j
class QuestionTest {

    private final QuestionRepository questionRepository;

    private final QuestionService questionService;

    private final GenericRepository genericRepository;

    @Autowired
    public QuestionTest(QuestionRepository questionRepository,
                        QuestionService questionService,
                        GenericRepository genericRepository) {
        this.questionRepository = questionRepository;
        this.questionService = questionService;
        this.genericRepository = genericRepository;
    }

    @Test
    void listQuestion() throws IOException {
        QuestionVO questionVO = new QuestionVO();
        Page<Question> questions = questionService.listQuestion(questionVO, PageRequest.of(1, 10));
        String filePath = "D:" + File.separator + "list_question.json";
        TestUtils.prettyPrintJson(questions, filePath);
    }

    @Test
        //@Transactional
    void saveQuestion() throws IOException {
        List<QuestionVO> questions = new ArrayList<>();
        List<AnswerOptionVO> answerOptionVOS = new ArrayList<>();
        AnswerOptionVO answerOption1 = ListBeanConvertUtil
                .convert(genericRepository.findById(AnswerOption.class, 9L).get(), AnswerOptionVO.class);
        AnswerOptionVO answerOption2 = ListBeanConvertUtil
                .convert(genericRepository.findById(AnswerOption.class, 10L).get(), AnswerOptionVO.class);
        AnswerOptionVO answerOption3 = ListBeanConvertUtil
                .convert(genericRepository.findById(AnswerOption.class, 11).get(), AnswerOptionVO.class);
        AnswerOptionVO answerOption4 = ListBeanConvertUtil
                .convert(genericRepository.findById(AnswerOption.class, 12).get(), AnswerOptionVO.class);
        answerOptionVOS.add(answerOption1);
        answerOptionVOS.add(answerOption2);
        QuestionVO question = new QuestionVO();
        question.setCode("Q4");
        question.setQuestionType(HealthQuestionType.NON_ILLNESS.getKey());
        question.setPresentationType(PresentationType.SINGLE_CHOICE.getKey());
        question.setContent("请选择你的国籍");
        question.setAnswerOptionVOS(answerOptionVOS);
        questionService.saveQuestion(question);
        QuestionVO question1 = new QuestionVO();
        question1.setCode("Q5");
        question1.setQuestionType(HealthQuestionType.NON_ILLNESS.getKey());
        question1.setPresentationType(PresentationType.SINGLE_CHOICE.getKey());
        question1.setContent("请选择你的工作");
        answerOptionVOS.clear();
        answerOptionVOS.add(answerOption3);
        answerOptionVOS.add(answerOption4);
        Long id = questionService.saveQuestion(question1);
        /*QuestionVO q1 = ListBeanConvertUtil.convert(questionService.loadQuestion(id), QuestionVO.class);
        q1.setAnswerOptionVOS(answerOptionVOS);
        questionService.saveQuestion(q1);*/
        Page<Question> questions1 = questionService.listQuestion(question, PageRequest.of(0, 10));
        TestUtils.prettyPrintJson(questions1, "D:" + File.separator + "list_save_question_1.json");
        Page<Question> questions2 = questionService.listQuestion(question1, PageRequest.of(0, 10));
        TestUtils.prettyPrintJson(questions2, "D:" + File.separator + "list_save_question_2.json");
    }
}
