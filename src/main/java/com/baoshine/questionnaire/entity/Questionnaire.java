package com.baoshine.questionnaire.entity;

import javax.persistence.*;

import com.baoshine.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

/**
 * @Description  
 * @Author  
 * @Date 2021-03-23 17:54:01 
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table ( name ="questionnaire")
public class Questionnaire  extends BaseEntity {

	private static final long serialVersionUID =  6308925817606172434L;

	/**
	 * 问卷代码
	 */
	@Column(name = "code" )
	private String code;

	/**
	 * 问卷名称
	 */
	@Column(name = "name" )
	private String name;

	/**
	 * 问卷描述
	 */
	@Column(name = "questionnaire_desc" )
	private String questionnaireDesc;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questionnaire")
	private List<Node> nodeList;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questionnaire")
	private List<Path> pathList;

}
