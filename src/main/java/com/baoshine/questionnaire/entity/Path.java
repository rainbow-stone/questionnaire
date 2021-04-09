package com.baoshine.questionnaire.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "path")
public class Path extends UUIDEntity {

    private static final long serialVersionUID = -4840647055351909617L;

    /**
     * 问卷ID
     */
    @JsonBackReference
    @ManyToOne//(cascade = CascadeType.ALL)
    private Questionnaire questionnaire;

    /**
     * 上级节点
     */
    @Column(name = "parent_node_id")
    private Long parentNodeId;

    /**
     * 下级节点
     */
    @Column(name = "child_node_id")
    private Long childNodeId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "path_option", joinColumns = {@JoinColumn(name = "option_id")}, inverseJoinColumns = {
            @JoinColumn(name = "path_id")})
    private List<AnswerOption> answerOptions;

    @Override
    public String toString() {
        return "Path{" +
                "questionnaire=" + questionnaire +
                ", parentNodeId=" + parentNodeId +
                ", childNodeId=" + childNodeId +
                '}';
    }
}
