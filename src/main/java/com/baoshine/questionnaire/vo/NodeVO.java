package com.baoshine.questionnaire.vo;

import lombok.Data;

@Data
public class NodeVO {

    private Long id;

    /**
     * 问卷
     */
    private QuestionnaireVO questionnaireVO;

    /**
     * 问题ID
     */
    private Long questionId;

    /**
     * 是否根节点
     */
    private boolean rootNodeIndi;

    /**
     * 是否终节点
     */
    private boolean endNodeIndi;

}
