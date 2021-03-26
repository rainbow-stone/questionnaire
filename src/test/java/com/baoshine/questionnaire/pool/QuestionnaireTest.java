package com.baoshine.questionnaire.pool;

import com.baoshine.questionnaire.QuestionnaireApplication;
import com.baoshine.questionnaire.entity.Node;
import com.baoshine.questionnaire.entity.Path;
import com.baoshine.questionnaire.entity.PathOption;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuestionnaireApplication.class})
@Slf4j
class QuestionnaireTest {

    private final QuestionnaireRepository questionnaireRepository;

    private final NodeRepository nodeRepository;

    private final PathRepository pathRepository;

    private final QuestionRepository questionRepository;

    private final AnswerOptionRepository answerOptionRepository;

    private final QuestionnaireOptionRepository questionnaireOptionRepository;


    @Autowired
    public QuestionnaireTest(QuestionnaireRepository questionnaireRepository,
                             NodeRepository nodeRepository,
                             PathRepository pathRepository,
                             QuestionRepository questionRepository,
                             AnswerOptionRepository answerOptionRepository,
                             QuestionnaireOptionRepository questionnaireOptionRepository){
        this.questionnaireRepository = questionnaireRepository;
        this.nodeRepository = nodeRepository;
        this.pathRepository = pathRepository;
        this.questionRepository = questionRepository;
        this.answerOptionRepository = answerOptionRepository;
        this.questionnaireOptionRepository = questionnaireOptionRepository;
    }

    @Test
    void deleteQuestionnaire(){
        questionnaireRepository.deleteAll();
        nodeRepository.deleteAll();
        pathRepository.deleteAll();

    }

    /**
     * 单选分支
     */
    @Test
    void saveSingleChoiceQuestionnaire(){
        /*Questionnaire questionnaire = new Questionnaire();
        questionnaire.setCode("template");
        questionnaire.setName("问卷模板");
        questionnaire.setQuestionnaireDesc("测试用问卷模板");
        questionnaireRepository.save(questionnaire);
        //rootNode
        Node rootNode = new Node();
        rootNode.setQuestionId(5L);
        rootNode.setQuestionnaireId(questionnaire.getId());
        rootNode.setRootNodeIndi(true);
        nodeRepository.save(rootNode);

        //rootNode -> node1
        Node rootNode1 = new Node();
        rootNode1.setQuestionId(6L);
        rootNode1.setQuestionnaireId(questionnaire.getId());
        nodeRepository.save(rootNode1);
        Path path = new Path();
        path.setParentNodeId(rootNode.getId());
        path.setChildNodeId(rootNode1.getId());
        path.setQuestionnaireId(questionnaire.getId());
        pathRepository.save(path);
        PathOption pathOption = new PathOption();
        pathOption.setPathId(path.getId());
        pathOption.setOptionId(3L);
        questionnaireOptionRepository.save(pathOption);

        //rootNode -> node2
        Node rootNode2 = new Node();
        rootNode2.setQuestionId(6L);
        rootNode2.setQuestionnaireId(questionnaire.getId());
        nodeRepository.save(rootNode2);
        Path path1 = new Path();
        path1.setParentNodeId(rootNode.getId());
        path1.setChildNodeId(rootNode2.getId());
        path1.setQuestionnaireId(questionnaire.getId());
        pathRepository.save(path1);
        PathOption pathOption1 = new PathOption();
        pathOption1.setPathId(path1.getId());
        pathOption1.setOptionId(4L);
        questionnaireOptionRepository.save(pathOption1);

        //node1 -> endNode1
        Node endNode1 = new Node();
        endNode1.setQuestionId(7L);
        endNode1.setQuestionnaireId(questionnaire.getId());
        nodeRepository.save(endNode1);
        Path path2 = new Path();
        path2.setParentNodeId(rootNode1.getId());
        path2.setChildNodeId(endNode1.getId());
        path2.setQuestionnaireId(questionnaire.getId());
        pathRepository.save(path2);
        PathOption pathOption2 = new PathOption();
        pathOption2.setPathId(path2.getId());
        pathOption2.setOptionId(5L);
        questionnaireOptionRepository.save(pathOption2);

        //node1 -> endNode2
        Node endNode2 = new Node();
        endNode2.setQuestionId(7L);
        endNode2.setQuestionnaireId(questionnaire.getId());
        nodeRepository.save(endNode2);
        Path path3 = new Path();
        path3.setParentNodeId(rootNode1.getId());
        path3.setChildNodeId(endNode2.getId());
        path3.setQuestionnaireId(questionnaire.getId());
        pathRepository.save(path3);
        PathOption pathOption3 = new PathOption();
        pathOption3.setPathId(path3.getId());
        pathOption3.setOptionId(6L);
        questionnaireOptionRepository.save(pathOption3);


        //node1 -> endNode3
        Node endNode3 = new Node();
        endNode3.setQuestionId(7L);
        endNode3.setQuestionnaireId(questionnaire.getId());
        nodeRepository.save(endNode3);
        Path path4 = new Path();
        path4.setParentNodeId(rootNode1.getId());
        path4.setChildNodeId(endNode3.getId());
        path4.setQuestionnaireId(questionnaire.getId());
        pathRepository.save(path4);
        PathOption pathOption4 = new PathOption();
        pathOption4.setPathId(path4.getId());
        pathOption4.setOptionId(7L);
        questionnaireOptionRepository.save(pathOption4);

        //node2 -> endNode4
        Node endNode4 = new Node();
        endNode4.setQuestionId(7L);
        endNode4.setQuestionnaireId(questionnaire.getId());
        nodeRepository.save(endNode4);
        Path path5 = new Path();
        path5.setParentNodeId(rootNode2.getId());
        path5.setChildNodeId(endNode4.getId());
        path5.setQuestionnaireId(questionnaire.getId());
        pathRepository.save(path5);
        PathOption pathOption5 = new PathOption();
        pathOption5.setPathId(path5.getId());
        pathOption5.setOptionId(5L);
        questionnaireOptionRepository.save(pathOption5);

        //node2 -> endNode5
        Node endNode5 = new Node();
        endNode5.setQuestionId(7L);
        endNode5.setQuestionnaireId(questionnaire.getId());
        nodeRepository.save(endNode5);
        Path path6 = new Path();
        path6.setParentNodeId(rootNode2.getId());
        path6.setChildNodeId(endNode5.getId());
        path6.setQuestionnaireId(questionnaire.getId());
        pathRepository.save(path6);
        PathOption pathOption6 = new PathOption();
        pathOption6.setPathId(path6.getId());
        pathOption6.setOptionId(6L);
        questionnaireOptionRepository.save(pathOption6);

        //node2 -> endNode6
        Node endNode6 = new Node();
        endNode6.setQuestionId(7L);
        endNode6.setQuestionnaireId(questionnaire.getId());
        nodeRepository.save(endNode6);
        Path path7 = new Path();
        path7.setParentNodeId(rootNode2.getId());
        path7.setChildNodeId(endNode6.getId());
        path7.setQuestionnaireId(questionnaire.getId());
        pathRepository.save(path7);
        PathOption pathOption7 = new PathOption();
        pathOption7.setPathId(path7.getId());
        pathOption7.setOptionId(5L);
        questionnaireOptionRepository.save(pathOption7);*/

    }

    @Test
    void saveMultipleChoiceQuestionnaire(){

    }
    //问题为单选、文本、上传文件可用此种方式
    //TODO 问题为多选，判断条件为多选如何解决： 添加path-options匹配关系，一个条件中存在多个option关系
    //TODO 如果条件为需要跟踪历史条件，
    public void outputChildNode(List<Path> paths, Node rootNode, int num){
        String s = "   ";
        for(int i = 0; i < num; i++){
            s = s.concat(s);
        }
        num++;
        System.out.println(s.concat("question "+questionRepository.findById(rootNode.getQuestionId()).get().getContent()));
        List<Path> pathList = paths.stream().filter(path -> path.getParentNodeId() != null && path.getParentNodeId().equals(rootNode.getId())).collect(
                Collectors.toList());

        for(Path path : pathList){
            List<PathOption> options = questionnaireOptionRepository.findByPathId(path.getId());
            for (PathOption option : options) {
                System.out.println(s.concat("answer "+answerOptionRepository.findById(option.getOptionId()).get().getContent()));
            }

            outputChildNode(paths, nodeRepository.findById(path.getChildNodeId()).get(), num);
        }
    }

    @Test
    void outPutTree(){
        Questionnaire questionnaire = questionnaireRepository.findAll().get(0);
        List<Node> nodes = nodeRepository.findAll();
        List<Path> paths = pathRepository.findAll();
        Node rootNode = nodes.stream().filter(Node::isRootNodeIndi).collect(Collectors.toList()).get(0);
        int num = 0;
        outputChildNode(paths, rootNode, num);
    }
}
