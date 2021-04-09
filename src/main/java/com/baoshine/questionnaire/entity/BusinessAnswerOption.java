package com.baoshine.questionnaire.entity;

import com.baoshine.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @Description
 * @Author
 * @Date 2021-03-23 17:54:00
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "business_answer_option")
public class BusinessAnswerOption extends BaseEntity {

    private static final long serialVersionUID = -6005801437167322497L;

    @Column(name = "answer_option_id")
    private Long answerOptionId;

    /**
     * 节点ID
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "business_node_id")
    private BusinessNode businessNode;

    /**
     * 答案类型（选项，输入，上传文件，其他项）
     */
    @Column(name = "type")
    private Long type;

    /**
     * 答案代码
     */
    @Column(name = "code")
    private String code;

    /**
     * 答案内容
     */
    @Column(name = "content")
    private String content;

    @Column(name = "selected")
    private boolean selected;

    @Override
    public String toString() {
        return "AnswerOption{" +
                "type=" + type +
                ", code='" + code + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
