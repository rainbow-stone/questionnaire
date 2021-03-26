package com.baoshine.questionnaire.entity;

import javax.persistence.*;

import com.baoshine.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description  
 * @Author  
 * @Date 2021-03-24 15:31:40 
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table ( name ="questionnaire_option")
public class PathOption extends BaseEntity {

	private static final long serialVersionUID =  7505743984903713537L;

	/**
	 * 问题条件ID
	 */
	@Column(name = "path_id" )
	private Long pathId;

	/**
	 * 答案ID
	 */
	@Column(name = "option_id" )
	private Long optionId;

}
