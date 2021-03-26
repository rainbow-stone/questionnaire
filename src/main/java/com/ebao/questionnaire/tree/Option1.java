package com.ebao.questionnaire.tree;

import lombok.Data;

/**
 * 问题答案选项
 */
@Data
public class Option1 {

    private static final long serialVersionUID = -980688710475982195L;

    private Long id;

    /**
     * 答案代码
     */
    private String code;

    /**
     * 答案内容
     */
    private String content;

}
