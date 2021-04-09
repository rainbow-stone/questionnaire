package com.baoshine.questionnaire.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description
 * @Author
 * @Date 2021-03-23 17:54:00
 */

@Data
public class BusinessAnswerOptionVO {

    private Long id;

    private Long answerOptionId;

    /**
     * 节点ID
     */
    private BusinessNodeVO businessNodeVO;

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

    private boolean selected;

    @Override
    public String toString() {
        return "BusinessAnswerOptionVO{" +
                "id=" + id +
                ", answerOptionId=" + answerOptionId +
                ", type=" + type +
                ", code='" + code + '\'' +
                ", content='" + content + '\'' +
                ", selected=" + selected +
                '}';
    }
}
