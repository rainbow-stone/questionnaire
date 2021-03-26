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
@Table ( name ="business_questionnaire")
public class BusinessQuestionnaire  extends BaseEntity {

	private static final long serialVersionUID =  8331567100026335143L;

	/**
	 * 业务ID
	 */
	@Column(name = "business_id" )
	private Long businessId;

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

}
