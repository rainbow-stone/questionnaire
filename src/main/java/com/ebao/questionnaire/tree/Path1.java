package com.ebao.questionnaire.tree;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 节点连接
 */
@Data
public class Path1 {

    private static final long serialVersionUID = 5043481277053354435L;

    private Long id;

    /**
     * 问卷ID
     */
    private Long questionnaireId;

    /**
     * 上级节点
     */
    private Long parentId;

    /**
     * 子节点
     */
    private Long childId;

    private List<Option1> options;

    /**
     * 可以为空
     * Key: 问题id
     * Value: 答案id
     */
    private Map<Long, Long> answers = Maps.newHashMap();
}
