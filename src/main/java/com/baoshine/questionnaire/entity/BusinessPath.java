/*
package com.baoshine.questionnaire.entity;

import com.baoshine.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

*/
/**
 * @Description
 * @Author
 * @Date 2021-03-23 17:54:00
 *//*


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "business_path")
public class BusinessPath extends BaseEntity {

    private static final long serialVersionUID = 4022931023623111486L;

    @Column(name = "path_id")
    private Long pathId;

    */
/**
     * 问卷ID
     *//*

    @JsonBackReference
    @ManyToOne
    private BusinessQuestionnaire businessQuestionnaire;

    */
/**
     * 上级节点
     *//*

    @Column(name = "parent_node_id")
    private Long parentNodeId;

    */
/**
     * 下级节点
     *//*

    @Column(name = "child_node_id")
    private Long childNodeId;

}
*/
