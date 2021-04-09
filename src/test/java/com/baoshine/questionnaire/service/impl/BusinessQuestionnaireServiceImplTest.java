package com.baoshine.questionnaire.service.impl;

import com.baoshine.questionnaire.QuestionnaireApplication;
import com.baoshine.questionnaire.constant.ExcludeFields;
import com.baoshine.questionnaire.entity.BusinessQuestionnaire;
import com.baoshine.questionnaire.entity.Node;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.pool.utils.TestUtils;
import com.baoshine.questionnaire.repository.GenericRepository;
import com.baoshine.questionnaire.service.BusinessQuestionnaireService;
import com.baoshine.questionnaire.service.QuestionnaireService;
import com.baoshine.questionnaire.service.TestQuestionnaireGenerate;
import com.baoshine.questionnaire.vo.BusinessAnswerOptionVO;
import com.baoshine.questionnaire.vo.BusinessNodeVO;
import com.baoshine.questionnaire.vo.BusinessQuestionnaireVO;
import com.baoshine.questionnaire.vo.QuestionnaireVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuestionnaireApplication.class, TestQuestionnaireGenerate.class})
@Slf4j
class BusinessQuestionnaireServiceImplTest {

    private final BusinessQuestionnaireService businessQuestionnaireService;

    private final QuestionnaireService questionnaireService;

    private final TestQuestionnaireGenerate testQuestionnaireGenerate;


    @Autowired
    public BusinessQuestionnaireServiceImplTest(BusinessQuestionnaireService businessQuestionnaireService,
                                                QuestionnaireService questionnaireService,
                                                TestQuestionnaireGenerate testQuestionnaireGenerate) {
        this.businessQuestionnaireService = businessQuestionnaireService;
        this.questionnaireService = questionnaireService;
        this.testQuestionnaireGenerate = testQuestionnaireGenerate;
    }

    @BeforeEach
    void setUp() throws QuestionnaireException {
        testQuestionnaireGenerate.generateQuestionnaire();
    }

    @AfterEach
    void tearDown() {
        testQuestionnaireGenerate.deleteAllQuestionnaire();
    }

    @Test
    @Transactional
    void saveBusinessQuestionnaire() throws QuestionnaireException, IOException {
    }

    @Test
    void queryAllQuestionnaire() throws IOException {
        List<BusinessQuestionnaire> businessQuestionnaires = businessQuestionnaireService.queryAllQuestionnaire(null, null);
        TestUtils.prettyPrintJson(businessQuestionnaires,
                "D:" + File.separator + "questionnaire" + File.separator + "all business questionnaire" +
                        new Date().getTime() + ".json");
    }

    @Test
    void displayAllQuestion() throws QuestionnaireException, IOException {
        List<BusinessQuestionnaire> businessQuestionnaires = businessQuestionnaireService.queryAllQuestionnaire(null, null);
        if(businessQuestionnaires == null || businessQuestionnaires.size() == 0){
            return;
        }
        BusinessQuestionnaire businessQuestionnaire = businessQuestionnaireService.displayAllQuestion(businessQuestionnaires.get(0).getQuestionnaireId());
        TestUtils.prettyPrintJson(businessQuestionnaire,
                "D:" + File.separator + "questionnaire" + File.separator + "detail business questionnaire" +
                        new Date().getTime() + ".json");
    }

    @Test
    @Transactional
    void displayNextQuestion() throws QuestionnaireException, IOException {
        Page<Questionnaire> questionnaires = questionnaireService.listQuestionnaire(new QuestionnaireVO(), PageRequest.of(0,1000));
        if(questionnaires == null || !questionnaires.hasContent()){
            return;
        }
        List<Node> nodes = businessQuestionnaireService.displayNextQuestion(questionnaires.getContent().get(0).getId(), questionnaires.getContent().get(0).getNodeList().get(0).getId());
        TestUtils.prettyPrintJson(nodes,
                "D:" + File.separator + "questionnaire" + File.separator + "next nodes" + new Date().getTime() +
                        ".json");
    }

    @Test
    @Transactional
    void saveQuestionStep() throws QuestionnaireException, IOException {
        BusinessQuestionnaireVO businessQuestionnaireVO = initialQuestionnaire();
        List<Node> nodes =
                questionnaireService.loadQuestionnaire(businessQuestionnaireVO.getQuestionnaireId()).getNodeList();
        Node node = nodes.stream().filter(Node::isRootNodeIndi).collect(Collectors.toList()).get(0);
        BusinessNodeVO businessNodeVO = businessQuestionnaireService.convertToBusiness(node);
        businessNodeVO.setBusinessQuestionnaireVO(businessQuestionnaireVO);
        List<BusinessAnswerOptionVO> optionVOS = businessNodeVO.getOptionVOS();
        for (BusinessAnswerOptionVO business : optionVOS) {
            if (business.getCode().equals("A1")) {
                business.setSelected(true);
            }
            business.setBusinessNodeVO(businessNodeVO);
        }
        businessNodeVO.setOptionVOS(optionVOS);
        List<Node> nextNodes = businessQuestionnaireService.saveQuestionStep(businessNodeVO);
        List<BusinessNodeVO> nodeVOS = nextNodes.stream().map(businessQuestionnaireService::convertToBusiness
        ).collect(Collectors.toList());
        nodeVOS.forEach(n -> n.setBusinessQuestionnaireVO(businessNodeVO.getBusinessQuestionnaireVO()));
        TestUtils.prettyPrintJson(nodeVOS,
                "D:" + File.separator + "questionnaire" + File.separator + "next questionnaire step" +
                        new Date().getTime() + ".json");
    }


    @Test
    void saveAnswerOption() {
        BusinessNodeVO businessNode = new BusinessNodeVO();
        List<BusinessAnswerOptionVO> list = new ArrayList<>();
        BusinessAnswerOptionVO answerOption = new BusinessAnswerOptionVO();
        answerOption.setCode("test");
        answerOption.setBusinessNodeVO(businessNode);
        list.add(answerOption);

        businessNode.setOptionVOS(list);
        //genericRepository.save(businessNode);
    }

    @Test
    void convertToEntity() {
    }

    @Test
    void convertToVO() {
    }

    @Test
    void convertToBusiness() {
    }

    @Test
    void testConvertToVO() {
    }

    /**
     * 初始化调查问卷，生成初始问题
     */
    @Test
    @Transactional
    BusinessQuestionnaireVO initialQuestionnaire() throws QuestionnaireException, IOException {
        BusinessQuestionnaireVO businessQuestionnaireVO = new BusinessQuestionnaireVO();
        businessQuestionnaireVO.setBusinessId(1L);
        businessQuestionnaireVO.setBusinessType(1L);
        QuestionnaireVO questionnaireVO = new QuestionnaireVO();
        questionnaireVO.setCode("template");
        Questionnaire questionnaire =
                questionnaireService.listQuestionnaire(questionnaireVO, PageRequest.of(0, 1000)).getContent().get(0);
        BeanUtils.copyProperties(questionnaire, businessQuestionnaireVO, ExcludeFields.excludeField);
        businessQuestionnaireVO.setQuestionnaireId(questionnaire.getId());
        //TestUtils.prettyPrintJson(result, "D:" + File.separator + "questionnaire"+File.separator+"initial questionnaire"+new Date().getTime() +".json");
        return businessQuestionnaireService.initialQuestionnaire(businessQuestionnaireVO);
    }
}