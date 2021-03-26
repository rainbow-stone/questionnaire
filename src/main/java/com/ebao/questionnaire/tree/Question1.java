package com.ebao.questionnaire.tree;

import lombok.Data;

/**
 * 问题信息
 */
@Data
public class Question1 {

    private static final long serialVersionUID = 8064287709544695707L;

    private Long id;

    /**
     * 问题类型：单选题，多选题，文本输入，文件上传
     */
    private String type;

    /**
     * 问题代码
     */
    private String code;

    /**
     * 问题内容描述
     */
    private String content;
}
