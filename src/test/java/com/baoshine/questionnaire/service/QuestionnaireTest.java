package com.baoshine.questionnaire.service;

import com.baoshine.questionnaire.QuestionnaireApplication;
import com.baoshine.questionnaire.entity.*;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuestionnaireApplication.class, TestQuestionnaireGenerate.class})
@Slf4j
class QuestionnaireTest {

    private final QuestionnaireRepository questionnaireRepository;

    private final NodeRepository nodeRepository;

    private final PathRepository pathRepository;

    private final QuestionService questionService;

    private final AnswerOptionService answerOptionService;

    private final QuestionnaireService questionnaireService;

    private final TestQuestionnaireGenerate testQuestionnaireGenerate;

    private final PathService pathService;

    private final NodeService nodeService;

    private final GenericRepository genericRepository;

    @Autowired
    public QuestionnaireTest(QuestionnaireRepository questionnaireRepository,
                             NodeRepository nodeRepository,
                             PathRepository pathRepository,
                             QuestionService questionService,
                             AnswerOptionService answerOptionService,
                             QuestionnaireService questionnaireService,
                             NodeService nodeService,
                             PathService pathService,
                             GenericRepository genericRepository,
                             TestQuestionnaireGenerate testQuestionnaireGenerate) {
        this.questionnaireRepository = questionnaireRepository;
        this.nodeRepository = nodeRepository;
        this.pathRepository = pathRepository;
        this.questionService = questionService;
        this.answerOptionService = answerOptionService;
        this.questionnaireService = questionnaireService;
        this.nodeService = nodeService;
        this.pathService = pathService;
        this.genericRepository = genericRepository;
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

    /**
     * 单选分支
     */
    @Test
    void saveSingleChoiceQuestionnaireByUUID() throws QuestionnaireException, IOException {
        Questionnaire result = questionnaireRepository.findAll().get(0);
        TestUtils.prettyPrintJson(result, "D:" + File.separator + "list_save_questionnaire.json");
        outPutTree();
    }


    @Test
    @Transactional
    void updateQuestionnaire() throws QuestionnaireException {
        Questionnaire questionnaire = questionnaireService.loadQuestionnaire(20L);
        QuestionnaireVO questionnaireVO = ListBeanConvertUtil.convert(questionnaire, QuestionnaireVO.class);
        questionnaireVO.setName("template1");
        questionnaireService.updateQuestionnaire(questionnaireVO);
    }

    //问题为单选、文本、上传文件可用此种方式
    //TODO 问题为多选，判断条件为多选如何解决： 添加path-options匹配关系，一个条件中存在多个option关系
    //TODO 如果条件为需要跟踪历史条件，
    public void outputChildNode(List<Path> paths, Map<Long, Node> nodeMap, Node rootNode, int num) {
        String s = "   ";
        for (int i = 0; i < num; i++) {
            s = s.concat(s);
        }
        num++;
        System.out.println(s.concat("question " + rootNode.getQuestion().getContent()));
        List<Path> pathList = paths.stream()
                .filter(path -> path.getParentNodeId() != null && path.getParentNodeId().equals(rootNode.getId()))
                .collect(
                        Collectors.toList());

        for (Path path : pathList) {
            List<AnswerOption> options = path.getAnswerOptions();
            for (AnswerOption option : options) {
                System.out.println(s.concat("answer " + option.getContent()));
            }

            outputChildNode(paths, nodeMap, nodeMap.get(path.getChildNodeId()), num);
        }
    }

    @Test
    void outPutTree() {
        Questionnaire questionnaire = questionnaireRepository.findAll().get(0);
        List<Node> nodes = nodeRepository.findAll();
        List<Path> paths = pathRepository.findAll();
        Node rootNode = nodes.stream().filter(Node::isRootNodeIndi).collect(Collectors.toList()).get(0);
        int num = 0;
        Map<Long, Node> nodeMap =
                nodes.stream().collect(Collectors.toMap(node -> node.getId(), node -> node, (o, n) -> n));
        outputChildNode(paths, nodeMap, rootNode, num);
    }


    /**
     * 循环逐级保存path
     *
     * @param paths
     * @param nodeMap
     * @param rootNode
     */
    public void saveChildNode(List<Path> paths, Map<Long, Node> nodeMap, Node rootNode) {
        List<Path> pathList = paths.stream()
                .filter(path -> path.getParentNodeId() != null && path.getParentNodeId().equals(rootNode.getId()))
                .collect(
                        Collectors.toList());
        for (Path path : pathList) {
            path.setParentNodeId(nodeMap.get(path.getParentNodeId()).getId());
            path.setChildNodeId(nodeMap.get(path.getChildNodeId()).getId());
            pathRepository.save(path);
            saveChildNode(paths, nodeMap, nodeMap.get(path.getChildNodeId()));
        }
    }

    /**
     * 保存问卷详细内容，仅用于主键为自增使用
     */
    @Test
    void saveNodeAndPath() {
        List<Node> nodeList = new ArrayList<>();
        List<Path> pathList = new ArrayList<>();
        Map<Long, Node> nodeMap =
                nodeList.stream().collect(Collectors.toMap(node -> node.getId(), node -> node, (o, n) -> n));
        Map<Long, Node> savedNodeMap = nodeMap.keySet().stream()
                .collect(Collectors.toMap(nodeId -> nodeId, nodeId -> nodeRepository.save(nodeMap.get(nodeId))));
        Node rootNode = nodeList.stream().filter(Node::isRootNodeIndi).collect(Collectors.toList()).get(0);
    }

}
