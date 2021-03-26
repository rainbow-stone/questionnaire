package com.baoshine.questionnaire.entity;

import javax.persistence.*;

import com.baoshine.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Description  
 * @Author  
 * @Date 2021-03-23 17:54:00 
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table ( name ="answer_option")
public class AnswerOption extends BaseEntity {

	private static final long serialVersionUID =  8280437613399247498L;

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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="question_answer_option",joinColumns={@JoinColumn(name="option_id")},inverseJoinColumns={@JoinColumn(name="qeustion_id")})
	private List<Question> questions;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="path_option",joinColumns={@JoinColumn(name="option_id")},inverseJoinColumns={@JoinColumn(name="path_id")})
	private List<Path> paths;

}
