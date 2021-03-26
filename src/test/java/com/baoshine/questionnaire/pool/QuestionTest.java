package com.baoshine.questionnaire.pool;

import com.baoshine.questionnaire.repository.QuestionRepository;
import com.baoshine.questionnaire.entity.Question;
import com.baoshine.questionnaire.QuestionnaireApplication;
import lombok.extern.slf4j.Slf4j;
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
class QuestionTest {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionTest(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    @Test
    void saveQuestion(){
        /*List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setCode("Q1");
        question.setType(1L);
        question.setContent("请选择你的性别");
        questions.add(question);
        Question question1 = new Question();
        question1.setCode("Q2");
        question1.setType(1L);
        question1.setContent("请选择你的年龄段");
        questions.add(question1);
        questionRepository.saveAll(questions);*/
    }
}
