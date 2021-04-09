package com.baoshine.questionnaire.service;

import com.baoshine.questionnaire.entity.AnswerOption;
import com.baoshine.questionnaire.entity.Question;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.enums.HealthQuestionType;
import com.baoshine.questionnaire.enums.PresentationType;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.pool.utils.TestUtils;
import com.baoshine.questionnaire.repository.GenericRepository;
import com.baoshine.questionnaire.repository.NodeRepository;
import com.baoshine.questionnaire.repository.PathRepository;
import com.baoshine.questionnaire.repository.QuestionnaireRepository;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@TestComponent
public class TestQuestionnaireGenerate {

    private final QuestionService questionService;

    private final AnswerOptionService answerOptionService;

    private final QuestionnaireService questionnaireService;


    @Autowired
    public TestQuestionnaireGenerate(QuestionService questionService,
                             AnswerOptionService answerOptionService,
                             QuestionnaireService questionnaireService
                             ) {
        this.questionService = questionService;
        this.answerOptionService = answerOptionService;
        this.questionnaireService = questionnaireService;
    }
    
    void saveGenderOption() {
        AnswerOptionVO answerOptionVO = new AnswerOptionVO();
        answerOptionVO.setCode("A1");
        answerOptionVO.setType(1L);
        answerOptionVO.setContent("男");
        answerOptionService.saveAnswerOption(answerOptionVO);
        AnswerOptionVO answerOptionVO1 = new AnswerOptionVO();
        answerOptionVO1.setCode("A2");
        answerOptionVO1.setType(1L);
        answerOptionVO1.setContent("女");
        answerOptionService.saveAnswerOption(answerOptionVO1);
    }

    void saveGenderQuestion(){
        List<AnswerOptionVO> answerOptionVOS = new ArrayList<>();
        AnswerOptionVO answerOptionVO = new AnswerOptionVO();
        answerOptionVO.setCode("A1");
        AnswerOption answerOption = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVO.setCode("A2");
        AnswerOption answerOption1 = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption, AnswerOptionVO.class));
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption1, AnswerOptionVO.class));
        QuestionVO question = new QuestionVO();
        question.setCode("Q1");
        question.setQuestionType(HealthQuestionType.NON_ILLNESS.getKey());
        question.setPresentationType(PresentationType.SINGLE_CHOICE.getKey());
        question.setContent("请选择你的性别");
        question.setAnswerOptionVOS(answerOptionVOS);
        questionService.saveQuestion(question);
    }

    void saveAgeOption() {
        AnswerOptionVO answerOption = new AnswerOptionVO();
        answerOption.setCode("A3");
        answerOption.setType(1L);
        answerOption.setContent("0-20");
        answerOptionService.saveAnswerOption(answerOption);
        AnswerOptionVO answerOption1 = new AnswerOptionVO();
        answerOption1.setCode("A4");
        answerOption1.setType(1L);
        answerOption1.setContent("21-50");
        answerOptionService.saveAnswerOption(answerOption1);
        AnswerOptionVO answerOption2 = new AnswerOptionVO();
        answerOption2.setCode("A5");
        answerOption2.setType(1L);
        answerOption2.setContent("50-100");
        answerOptionService.saveAnswerOption(answerOption2);
    }

    void saveAgeQuestion(){
        List<AnswerOptionVO> answerOptionVOS = new ArrayList<>();
        AnswerOptionVO answerOptionVO = new AnswerOptionVO();
        answerOptionVO.setCode("A3");
        AnswerOption answerOption = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVO.setCode("A4");
        AnswerOption answerOption1 = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVO.setCode("A5");
        AnswerOption answerOption2 = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption, AnswerOptionVO.class));
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption1, AnswerOptionVO.class));
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption2, AnswerOptionVO.class));
        QuestionVO question = new QuestionVO();
        question.setCode("Q2");
        question.setQuestionType(HealthQuestionType.NON_ILLNESS.getKey());
        question.setPresentationType(PresentationType.SINGLE_CHOICE.getKey());
        question.setContent("请选择你的年龄区间");
        question.setAnswerOptionVOS(answerOptionVOS);
        questionService.saveQuestion(question);
    }

    void saveNationalityOption() {
        AnswerOptionVO answerOption = new AnswerOptionVO();
        answerOption.setCode("A6");
        answerOption.setType(1L);
        answerOption.setContent("中国");
        answerOptionService.saveAnswerOption(answerOption);
        AnswerOptionVO answerOption1 = new AnswerOptionVO();
        answerOption1.setCode("A7");
        answerOption1.setType(1L);
        answerOption1.setContent("美国");
        answerOptionService.saveAnswerOption(answerOption1);
    }

    void saveNationalityQuestion(){
        List<AnswerOptionVO> answerOptionVOS = new ArrayList<>();
        AnswerOptionVO answerOptionVO = new AnswerOptionVO();
        answerOptionVO.setCode("A6");
        AnswerOption answerOption = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVO.setCode("A7");
        AnswerOption answerOption1 = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption, AnswerOptionVO.class));
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption1, AnswerOptionVO.class));
        QuestionVO question = new QuestionVO();
        question.setCode("Q3");
        question.setQuestionType(HealthQuestionType.NON_ILLNESS.getKey());
        question.setPresentationType(PresentationType.SINGLE_CHOICE.getKey());
        question.setContent("请选择你的国籍");
        question.setAnswerOptionVOS(answerOptionVOS);
        questionService.saveQuestion(question);
    }

    void saveOccupationOption() {
        AnswerOptionVO answerOption = new AnswerOptionVO();
        answerOption.setCode("A8");
        answerOption.setType(1L);
        answerOption.setContent("老师");
        answerOptionService.saveAnswerOption(answerOption);
        AnswerOptionVO answerOption1 = new AnswerOptionVO();
        answerOption1.setCode("A9");
        answerOption1.setType(1L);
        answerOption1.setContent("工人");
        answerOptionService.saveAnswerOption(answerOption1);
    }

    void saveOccupationQuestion(){
        List<AnswerOptionVO> answerOptionVOS = new ArrayList<>();
        AnswerOptionVO answerOptionVO = new AnswerOptionVO();
        answerOptionVO.setCode("A8");
        AnswerOption answerOption = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVO.setCode("A9");
        AnswerOption answerOption1 = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption, AnswerOptionVO.class));
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption1, AnswerOptionVO.class));
        QuestionVO question = new QuestionVO();
        question.setCode("Q4");
        question.setQuestionType(HealthQuestionType.NON_ILLNESS.getKey());
        question.setPresentationType(PresentationType.SINGLE_CHOICE.getKey());
        question.setContent("请选择你的职业");
        question.setAnswerOptionVOS(answerOptionVOS);
        questionService.saveQuestion(question);
    }

    void saveEndQuestion(){
        QuestionVO question = new QuestionVO();
        question.setCode("Q5");
        question.setQuestionType(HealthQuestionType.NON_ILLNESS.getKey());
        question.setPresentationType(PresentationType.END_RESULT.getKey());
        question.setContent("结束");
        questionService.saveQuestion(question);
    }

    void deleteQuestionnaire(){
        Page<Questionnaire>
                questionnaires = questionnaireService.listQuestionnaire(new QuestionnaireVO(), PageRequest.of(0, 1000));
        List<Questionnaire> questionnaireList;
        if(questionnaires.hasContent()){
            questionnaireList = questionnaires.getContent();
            questionnaireList.forEach(q -> {
                try {
                    questionnaireService.deleteQuestionnaire(q.getId());
                } catch (QuestionnaireException e) {
                    log.error(e.getLocalizedMessage());
                }
            });
        }
    }

    void deleteAnswerOption(){
        Page<AnswerOption> answerOptions = answerOptionService.listAnswerOption(new AnswerOptionVO(), PageRequest.of(0, 1000));
        List<AnswerOption> answerOptionList;
        if(answerOptions.hasContent()){
            answerOptionList = answerOptions.getContent();
            answerOptionList.forEach(q -> {
                try {
                    answerOptionService.deleteAnswerOption(q.getId());
                } catch (QuestionnaireException e) {
                    log.error(e.getLocalizedMessage());
                }
            });
        }
    }

    void deleteQuestion(){
        Page<Question> questions = questionService.listQuestion(new QuestionVO(), PageRequest.of(0, 1000));
        List<Question> questionList;
        if(questions.hasContent()){
            questionList = questions.getContent();
            questionList.forEach(q -> {
                try {
                    questionService.deleteQuestion(q.getId());
                } catch (QuestionnaireException e) {
                    log.error(e.getLocalizedMessage());
                }
            });
        }
    }

    void saveSingleChoiceQuestionnaireByUUID() throws QuestionnaireException {
        List<NodeVO> nodes = new ArrayList<>();
        List<PathVO> paths = new ArrayList<>();
        QuestionnaireVO questionnaire = new QuestionnaireVO();
        questionnaire.setCode("template");
        questionnaire.setName("问卷模板" + new Date().getTime());
        questionnaire.setQuestionnaireDesc("测试用问卷模板");
        Long id = questionnaireService.saveQuestionnaire(questionnaire);
        QuestionnaireVO questionnaireVO = ListBeanConvertUtil.convert(questionnaireService.loadQuestionnaire(id), QuestionnaireVO.class);
        QuestionVO questionVO = new QuestionVO();
        questionVO.setCode("Q1");
        QuestionVO question1 = ListBeanConvertUtil.convert(questionService.listQuestion(questionVO, PageRequest.of(0, 1000)).getContent().get(0), QuestionVO.class);
        questionVO.setCode("Q2");
        QuestionVO question2 = ListBeanConvertUtil.convert(questionService.listQuestion(questionVO, PageRequest.of(0, 1000)).getContent().get(0), QuestionVO.class);
        questionVO.setCode("Q5");
        QuestionVO endQuestion = ListBeanConvertUtil.convert(questionService.listQuestion(questionVO, PageRequest.of(0, 1000)).getContent().get(0), QuestionVO.class);

        List<AnswerOptionVO> answerOptionVOS = new ArrayList<>();
        AnswerOptionVO answerOptionVO = new AnswerOptionVO();
        answerOptionVO.setCode("A1");
        AnswerOption answerOption1 = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVO.setCode("A2");
        AnswerOption answerOption2 = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVO.setCode("A3");
        AnswerOption answerOption3 = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVO.setCode("A4");
        AnswerOption answerOption4 = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);
        answerOptionVO.setCode("A5");
        AnswerOption answerOption5 = answerOptionService.listAnswerOption(answerOptionVO, PageRequest.of(0,1000)).getContent().get(0);

        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption2, AnswerOptionVO.class));

        //rootNode
        NodeVO rootNode = new NodeVO(10000L, questionnaireVO, question1, true, false);
        NodeVO rootNode1 = new NodeVO(10001L, questionnaireVO, question2, false, false);
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption1, AnswerOptionVO.class));
        paths.add(new PathVO(10001L, questionnaireVO, rootNode.getId(), rootNode1.getId(), answerOptionVOS));
        NodeVO rootNode2 = new NodeVO(10002L, questionnaireVO, question2, false, false);
        answerOptionVOS.clear();
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption2, AnswerOptionVO.class));
        paths.add(new PathVO(10002L, questionnaireVO, rootNode.getId(), rootNode2.getId(), answerOptionVOS));
        NodeVO endNode1 = new NodeVO(10003L, questionnaireVO, endQuestion, false, false);
        answerOptionVOS.clear();
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption3, AnswerOptionVO.class));
        paths.add(new PathVO(10003L, questionnaireVO, rootNode1.getId(), endNode1.getId(), answerOptionVOS));
        NodeVO endNode2 = new NodeVO(10004L, questionnaireVO, endQuestion, false, false);
        answerOptionVOS.clear();
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption4, AnswerOptionVO.class));
        paths.add(new PathVO(10004L, questionnaireVO, rootNode1.getId(), endNode2.getId(), answerOptionVOS));
        NodeVO endNode3 = new NodeVO(10005L, questionnaireVO, endQuestion, false, false);
        answerOptionVOS.clear();
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption5, AnswerOptionVO.class));
        paths.add(new PathVO(10005L, questionnaireVO, rootNode1.getId(), endNode3.getId(), answerOptionVOS));
        NodeVO endNode4 = new NodeVO(10006L, questionnaireVO, endQuestion, false, false);
        answerOptionVOS.clear();
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption3, AnswerOptionVO.class));
        paths.add(new PathVO(10006L, questionnaireVO, rootNode2.getId(), endNode4.getId(), answerOptionVOS));
        NodeVO endNode5 = new NodeVO(10007L, questionnaireVO, endQuestion, false, false);
        answerOptionVOS.clear();
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption5, AnswerOptionVO.class));
        paths.add(new PathVO(10007L, questionnaireVO, rootNode2.getId(), endNode5.getId(), answerOptionVOS));
        NodeVO endNode6 = new NodeVO(10008L, questionnaireVO, endQuestion, false, false);
        answerOptionVOS.clear();
        answerOptionVOS.add(ListBeanConvertUtil.convert(answerOption5, AnswerOptionVO.class));
        paths.add(new PathVO(10008L, questionnaireVO, rootNode2.getId(), endNode6.getId(), answerOptionVOS));
        nodes.add(rootNode);
        nodes.add(rootNode1);
        nodes.add(rootNode2);
        nodes.add(endNode1);
        nodes.add(endNode2);
        nodes.add(endNode3);
        nodes.add(endNode4);
        nodes.add(endNode5);
        nodes.add(endNode6);

        QuestionnaireDetailVO questionnaireDetailVO = new QuestionnaireDetailVO(questionnaireVO, nodes, paths);

        questionnaireService.saveQuestionnaireDetail(questionnaireDetailVO);
    }

    public void generateQuestionnaire() throws QuestionnaireException {
        saveGenderOption();
        saveGenderQuestion();
        saveAgeOption();
        saveAgeQuestion();
        saveNationalityOption();
        saveNationalityQuestion();
        saveOccupationOption();
        saveOccupationQuestion();
        saveEndQuestion();
        saveSingleChoiceQuestionnaireByUUID();
    }
    
    public void deleteAllQuestionnaire(){
        deleteQuestionnaire();
        deleteQuestion();
        deleteAnswerOption();
    }
}
