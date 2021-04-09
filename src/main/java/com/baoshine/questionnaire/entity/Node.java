package com.baoshine.questionnaire.entity;

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
@Table(name = "node")
public class Node extends UUIDEntity {

    private static final long serialVersionUID = 8844626761974750780L;

    /**
     * 问卷ID
     */
    @JsonBackReference
    @ManyToOne//(cascade = CascadeType.ALL)
    private Questionnaire questionnaire;

    /**
     * 问题ID
     */
    @JsonBackReference
    @ManyToOne//(cascade = CascadeType.ALL)
    private Question question;

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

    @Override
    public String toString() {
        return "Node{" +
                "id=" + super.getId() +
                "questionnaire=" + questionnaire +
                ", question=" + question +
                ", rootNodeIndi=" + rootNodeIndi +
                ", endNodeIndi=" + endNodeIndi +
                '}';
    }
}
