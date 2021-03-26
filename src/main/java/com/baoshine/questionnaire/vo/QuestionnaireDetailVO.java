package com.baoshine.questionnaire.vo;

import com.baoshine.questionnaire.entity.Questionnaire;

import java.util.List;

public class QuestionnaireDetailVO extends Questionnaire {

    /**
     * 问卷节点
     */
    private List<NodeVO> nodeVOS;

    /**
     * 节点路径
     */
    private List<PathVO> pathVOS;

}
