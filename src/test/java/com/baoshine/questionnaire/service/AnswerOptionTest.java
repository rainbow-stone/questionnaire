package com.baoshine.questionnaire.service;

import com.baoshine.questionnaire.QuestionnaireApplication;
import com.baoshine.questionnaire.entity.AnswerOption;
import com.baoshine.questionnaire.repository.AnswerOptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuestionnaireApplication.class})
@Slf4j
class AnswerOptionTest {


    private final AnswerOptionRepository answerOptionRepository;

    @Autowired
    public AnswerOptionTest(AnswerOptionRepository answerOptionRepository) {
        this.answerOptionRepository = answerOptionRepository;
    }

    @Test
    void saveGenderOption() {
        List<AnswerOption> answerOptions = new ArrayList<>();
        AnswerOption answerOption = new AnswerOption();
        answerOption.setCode("A1");
        answerOption.setType(1L);
        answerOption.setContent("男");
        answerOptions.add(answerOption);
        AnswerOption answerOption1 = new AnswerOption();
        answerOption1.setCode("A2");
        answerOption1.setType(1L);
        answerOption1.setContent("女");
        answerOptions.add(answerOption1);
        answerOptionRepository.saveAll(answerOptions);
    }

    @Test
    void saveNationalityOption() {
        List<AnswerOption> answerOptions = new ArrayList<>();
        AnswerOption answerOption = new AnswerOption();
        answerOption.setCode("A8");
        answerOption.setType(1L);
        answerOption.setContent("中国");
        answerOptions.add(answerOption);
        AnswerOption answerOption1 = new AnswerOption();
        answerOption1.setCode("A9");
        answerOption1.setType(1L);
        answerOption1.setContent("美国");
        answerOptions.add(answerOption1);
        answerOptionRepository.saveAll(answerOptions);
    }

    @Test
    void saveOccupationOption() {
        List<AnswerOption> answerOptions = new ArrayList<>();
        AnswerOption answerOption = new AnswerOption();
        answerOption.setCode("A10");
        answerOption.setType(1L);
        answerOption.setContent("老师");
        answerOptions.add(answerOption);
        AnswerOption answerOption1 = new AnswerOption();
        answerOption1.setCode("A11");
        answerOption1.setType(1L);
        answerOption1.setContent("工人");
        answerOptions.add(answerOption1);
        answerOptionRepository.saveAll(answerOptions);
    }

    @Test
    void saveAgeOption() {
        List<AnswerOption> answerOptions = new ArrayList<>();
        AnswerOption answerOption = new AnswerOption();
        answerOption.setCode("A3");
        answerOption.setType(1L);
        answerOption.setContent("0-20");
        answerOptions.add(answerOption);
        AnswerOption answerOption1 = new AnswerOption();
        answerOption1.setCode("A4");
        answerOption1.setType(1L);
        answerOption1.setContent("21-50");
        answerOptions.add(answerOption1);
        AnswerOption answerOption2 = new AnswerOption();
        answerOption2.setCode("A5");
        answerOption2.setType(1L);
        answerOption2.setContent("50-100");
        answerOptions.add(answerOption2);
        answerOptionRepository.saveAll(answerOptions);
    }

    @Test
    void setIdSave() {
        AnswerOption answerOption = new AnswerOption();
        answerOption.setId(22L);
        answerOption.setCode("A3");
        answerOption.setType(1L);
        answerOption.setContent("0-20");
        answerOptionRepository.save(answerOption);
    }
}
