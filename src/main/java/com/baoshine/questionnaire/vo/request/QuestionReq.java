package com.baoshine.questionnaire.vo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionReq extends SearchRequest {

    private Long questionType;

    private String code;

    private String content;

    private Long presentationType;
}
