package com.baoshine.questionnaire.vo;

import com.baoshine.questionnaire.entity.AnswerOption;
import com.baoshine.questionnaire.entity.Questionnaire;
import lombok.Data;

import java.util.List;

@Data
public class PathVO {

    private Long id;

    /**
     * 问卷ID
     */
    private Questionnaire questionnaire;

    /**
     * 上级节点
     */
    private Long parentNodeId;

    /**
     * 下级节点
     */
    private Long childNodeId;

    private List<AnswerOption> answerOptions;

}
