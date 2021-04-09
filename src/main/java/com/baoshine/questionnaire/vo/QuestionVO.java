package com.baoshine.questionnaire.vo;

import com.baoshine.questionnaire.vo.request.SearchRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionVO extends SearchRequest {

    private static final long serialVersionUID = -3050624266947964543L;
    private Long id;

    /**
     * 问题业务类型（疾病相关，非疾病相关）
     */
    private Long questionType;

    /**
     * 问题代码
     */
    private String code;

    /**
     * 问题内容
     */
    private String content;

    /**
     * 问题类型（单选，多选，输入框，上传框）
     */
    private Long presentationType;

    private List<AnswerOptionVO> answerOptionVOS;

    private List<NodeVO> nodeVOS;

}
