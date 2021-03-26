package com.baoshine.questionnaire.vo;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class AnswerOptionVO{

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
    @Column(name = "content" )
    private String content;

    /**
     * 答案对应问题List
     */
    private List<QuestionVO> questionVOS;

    /**
     * 答案对应节点链接List
     */
    private List<PathVO> pathVOS;

}
