package com.baoshine.questionnaire.service.impl;

import com.baoshine.questionnaire.entity.Node;
import com.baoshine.questionnaire.entity.Question;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.service.NodeService;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.NodeVO;
import com.baoshine.questionnaire.vo.QuestionVO;
import com.baoshine.questionnaire.vo.QuestionnaireVO;
import org.springframework.stereotype.Service;

@Service
public class NodeServiceImpl implements NodeService {
    /**
     * VO转Entity
     *
     * @param nodeVO vo
     * @return entity
     */
    @Override
    public Node convertToEntity(NodeVO nodeVO) {
        Node node = ListBeanConvertUtil.convert(nodeVO, Node.class);
        assert node != null;
        node.setQuestionnaire(ListBeanConvertUtil.convert(nodeVO.getQuestionnaireVO(), Questionnaire.class));
        node.setQuestion(ListBeanConvertUtil.convert(nodeVO.getQuestionVO(), Question.class));
        return node;
    }

    /**
     * Entity转VO
     *
     * @param node entity
     * @return vo
     */
    @Override
    public NodeVO convertToVO(Node node) {
        NodeVO nodeVO = ListBeanConvertUtil.convert(node, NodeVO.class);
        assert nodeVO != null;
        nodeVO.setQuestionnaireVO(ListBeanConvertUtil.convert(node.getQuestionnaire(), QuestionnaireVO.class));
        nodeVO.setQuestionVO(ListBeanConvertUtil.convert(node.getQuestion(), QuestionVO.class));
        return nodeVO;
    }
}
