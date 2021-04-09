package com.baoshine.questionnaire.vo;

import com.baoshine.questionnaire.vo.request.SearchRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionnaireVO extends SearchRequest {

    private Long id;

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

    private List<ProductQuestionnaireVO> productQuestionnaireVOS;

}
