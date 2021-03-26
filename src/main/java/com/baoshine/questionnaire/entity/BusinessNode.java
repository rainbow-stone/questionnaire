package com.baoshine.questionnaire.entity;

import javax.persistence.*;

import com.baoshine.common.entity.BaseEntity;
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
@Table ( name ="business_node")
public class BusinessNode  extends BaseEntity {

	private static final long serialVersionUID =  9204686747109024686L;

	/**
	 * 问卷ID
	 */
	@Column(name = "questionnaire_id" )
	private Long questionnaireId;

	/**
	 * 问题ID
	 */
	@Column(name = "question_id" )
	private Long questionId;

	/**
	 * 问题类型
	 */
	@Column(name = "question_type" )
	private Long questionType;

	/**
	 * 问题类型（单选，多选，输入框，上传框）
	 */
	@Column(name = "presentation_type" )
	private Long presentationType;


	/**
	 * 问题代码
	 */
	@Column(name = "code" )
	private String code;

	/**
	 * 问题内容
	 */
	@Column(name = "content" )
	private String content;

}
