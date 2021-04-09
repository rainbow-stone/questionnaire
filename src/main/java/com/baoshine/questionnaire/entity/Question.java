package com.baoshine.questionnaire.entity;

import com.baoshine.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

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
@Table(name = "question")
public class Question extends BaseEntity {

    private static final long serialVersionUID = 4436397574683972436L;

    /**
     * 问题业务类型（疾病相关，非疾病相关）
     */
    @Column(name = "question_type")
    private Long questionType;

    /**
     * 问题代码
     */
    @Column(name = "code")
    private String code;

    /**
     * 问题内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 问题类型（单选，多选，输入框，上传框）
     */
    @Column(name = "presentation_type")
    private Long presentationType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "question_answer_option", joinColumns = {@JoinColumn(name = "option_id")}, inverseJoinColumns = {
            @JoinColumn(name = "question_id")})
    private List<AnswerOption> answerOptions;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "question")
    private List<Node> nodes;

    @Override
    public String toString() {
        return "Question{" +
                "questionType=" + questionType +
                ", code='" + code + '\'' +
                ", content='" + content + '\'' +
                ", presentationType=" + presentationType +
                '}';
    }
}
