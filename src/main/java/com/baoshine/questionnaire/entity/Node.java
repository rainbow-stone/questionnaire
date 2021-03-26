package com.baoshine.questionnaire.entity;

import javax.naming.Name;
import javax.persistence.*;

import com.baoshine.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Table ( name ="node")
public class Node  extends BaseEntity {

	private static final long serialVersionUID =  8844626761974750780L;

	/**
	 * 问卷ID
	 */
	@JsonBackReference
	@ManyToOne
	private Questionnaire questionnaire;

	/**
	 * 问题ID
	 */
	@Column(name = "question_id" )
	private Long questionId;

	/**
	 * 是否根节点
	 */
	@Column(name = "root_node_indi" )
	private boolean rootNodeIndi;

	/**
	 * 是否终节点
	 */
	@Column(name = "end_node_indi")
	private boolean endNodeIndi;

}
