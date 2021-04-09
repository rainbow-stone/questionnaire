package com.baoshine.questionnaire.vo;

import com.baoshine.questionnaire.vo.request.SearchRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AnswerOptionVO extends SearchRequest {

    private Long id;
    /**
     * 答案类型（选项，输入，上传文件，其他项）
     */
    private Long type;

    /**
     * 答案代码
     */
    private String code;

    /**
     * 答案内容
     */
    private String content;

}
