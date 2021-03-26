package com.baoshine.questionnaire.entity;

import javax.persistence.*;

import com.baoshine.common.entity.BaseEntity;
import lombok.Data;

/**
 * @Description  
 * @Author  
 * @Date 2021-03-24 15:31:40 
 */

@Entity
@Data
@Table ( name ="business_questionnaire_option")
public class BusinessPathOption extends BaseEntity {

	private static final long serialVersionUID =  611484556923110729L;

	@Column(name = "business_path_id" )
	private Long businessPathId;

	/**
	 * 答案ID
	 */
	@Column(name = "option_id" )
	private Long optionId;

	/**
	 * 答案类型（选项，输入，上传文件，其他项）
	 */
	@Column(name = "type" )
	private Long type;

	/**
	 * 答案代码
	 */
	@Column(name = "code" )
	private String code;

	/**
	 * 答案内容
	 */
	@Column(name = "content" )
	private String content;

}
