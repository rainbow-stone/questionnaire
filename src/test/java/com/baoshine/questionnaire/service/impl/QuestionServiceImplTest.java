package com.baoshine.questionnaire.service.impl;

import com.baoshine.questionnaire.QuestionnaireApplication;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuestionnaireApplication.class})
@Slf4j
class QuestionServiceImplTest {

    private final QuestionService questionService;

    @Autowired
    public QuestionServiceImplTest(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Test
    void listQuestion() {
    }

    @Test
    void saveQuestion() {
    }

    @Test
    void updateQuestion() {
    }

    @Test
    void deleteQuestion() {
    }

    @Test
    void loadQuestion() {
    }

    @Test
        //@Transactional
    void addAnswerOptionConfig() throws QuestionnaireException {
        questionService.addAnswerOptionConfig(21L, 1L);
        questionService.addAnswerOptionConfig(21L, 2L);
    }

    @Test
    void deleteAnswerOptionConfig() throws QuestionnaireException {
        questionService.deleteAnswerOptionConfig(21L, 1L);
        questionService.deleteAnswerOptionConfig(21L, 2L);
    }

    @Test
    void convertToVO() {
    }

    @Test
    void convertToEntity() {
    }
}