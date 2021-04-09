package com.baoshine.questionnaire.vo;

import lombok.Data;

import java.util.List;

@Data
public class BusinessQuestionnaireVO {

    private Long id;

    private Long questionnaireId;

    /**
     * 业务ID
     */
    private Long businessId;

    /**
     * 业务类型
     */
    private Long businessType;

    /**
     * 问卷代码
     */
    private String code;

    /**
     * 问卷名称
     */
    private String name;

    /**
     * 问卷描述
     */
    private String questionnaireDesc;

    private List<NodeVO> nodeVOS;

    private List<BusinessNodeVO> businessNodeVOS;

    private List<PathVO> pathVOS;
}
