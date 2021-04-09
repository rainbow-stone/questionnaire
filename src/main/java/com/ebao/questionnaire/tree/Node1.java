package com.ebao.questionnaire.tree;

import lombok.Data;

/**
 * 问卷树节点
 */
@Data
public class Node1 {

    private static final long serialVersionUID = -3056096631520008040L;

    private Long id;

    /**
     * 问卷ID
     */
    private Long questionnaireId;
    //private String type; // question, question_set, widget, result

    /**
     * 问题
     */
    private Question1 questions;

    /**
     * type != question 或 question_set 时为空
     */
    //private List<Option> options;

    /**
     * type != widget 时为空
     */
    //private Widget widget;

    /**
     * type != result 时为空
     */
    private String result; // Success, Failed
}
