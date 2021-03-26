package com.ebao.questionnaire.tree;

import lombok.Data;

import java.util.List;

/**
 * 问卷信息
 */
@Data
public class Questionnaire1 {

    private static final long serialVersionUID = -4671845213460716575L;

    private Long id;

    /**
     * 问卷Code
     */
    private String code;

    /**
     * 问卷名称
     */
    private String name;

    /**
     * 问卷描述
     */
    private String desc;

    /**
     * 问卷节点
     */
    private List<Node1> nodes;

    /**
     * 节点链接
     */
    private List<Path1> paths;
}
