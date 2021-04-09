package com.baoshine.questionnaire.entity;

import com.baoshine.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
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
@Table(name = "business_node")
public class BusinessNode extends BaseEntity {

    private static final long serialVersionUID = 9204686747109024686L;

    /**
     * 问卷节点ID
     */
    @Column(name = "node_id")
    private Long nodeId;

    /**
     * 问卷ID
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "business_questionnaire_id")
    private BusinessQuestionnaire businessQuestionnaire;

    /**
     * 问题ID
     */
    @Column(name = "question_id")
    private Long questionId;

    /**
     * 问题类型
     */
    @Column(name = "question_type")
    private Long questionType;

    /**
     * 问题类型（单选，多选，输入框，上传框）
     */
    @Column(name = "presentation_type")
    private Long presentationType;


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
     * 是否根节点
     */
    @Column(name = "root_node_indi")
    private boolean rootNodeIndi;

    /**
     * 是否终节点
     */
    @Column(name = "end_node_indi")
    private boolean endNodeIndi;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "businessNode")
    private List<BusinessAnswerOption> optionList = new ArrayList<>();

}
