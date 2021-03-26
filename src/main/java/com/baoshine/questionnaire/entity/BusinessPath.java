package com.baoshine.questionnaire.entity;

import javax.persistence.*;

import com.baoshine.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description  
 * @Author  
 * @Date 2021-03-23 17:54:00 
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table ( name ="business_path")
public class BusinessPath  extends BaseEntity {

	private static final long serialVersionUID =  4022931023623111486L;

	/**
	 * 问卷ID
	 */
	@Column(name = "questionnaire_id" )
	private Long questionnaireId;

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

}
