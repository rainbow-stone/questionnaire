package com.baoshine.questionnaire.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BusinessNodeVO{


    private static final long serialVersionUID = -9113670156085086223L;

    private Long id;

    /**
     * 问卷节点ID
     */
    private Long nodeId;

    /**
     * 问卷ID
     */
    private BusinessQuestionnaireVO businessQuestionnaireVO;

    /**
     * 问题ID
     */
    private Long questionId;

    /**
     * 问题类型
     */
    private Long questionType;

    /**
     * 问题类型（单选，多选，输入框，上传框）
     */
    private Long presentationType;


    /**
     * 问题代码
     */
    private String code;

    /**
     * 问题内容
     */
    private String content;

    /**
     * 是否根节点
     */
    private boolean rootNodeIndi;

    /**
     * 是否终节点
     */
    private boolean endNodeIndi;

    private List<BusinessAnswerOptionVO> optionVOS = new ArrayList<>();

    @Override
    public String toString() {
        return "BusinessNodeVO{" +
                "id=" + id +
                ", nodeId=" + nodeId +
                ", questionId=" + questionId +
                ", questionType=" + questionType +
                ", presentationType=" + presentationType +
                ", code='" + code + '\'' +
                ", content='" + content + '\'' +
                ", rootNodeIndi=" + rootNodeIndi +
                ", endNodeIndi=" + endNodeIndi +
                '}';
    }
}
