package com.baoshine.questionnaire.entity;

import javax.persistence.*;

import com.baoshine.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Table ( name ="path")
public class Path  extends BaseEntity {

	private static final long serialVersionUID =  3736569373192296112L;

	/**
	 * 问卷ID
	 */
	@JsonBackReference
	@ManyToOne
	private Questionnaire questionnaire;

	/**
	 * 上级节点
	 */
	@Column(name = "parent_node_id" )
	private Long parentNodeId;

	/**
	 * 下级节点
	 */
	@Column(name = "child_node_id" )
	private Long childNodeId;

	@ManyToMany(mappedBy = "paths")
	private List<AnswerOption> answerOptions;

}
