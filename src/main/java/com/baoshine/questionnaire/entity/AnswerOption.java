package com.baoshine.questionnaire.entity;

import com.baoshine.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "answer_option")
public class AnswerOption extends BaseEntity {

    private static final long serialVersionUID = 8280437613399247498L;

    /**
     * 答案类型（选项，输入，上传文件，其他项）
     */
    @Column(name = "type")
    private Long type;

    /**
     * 答案代码
     */
    @Column(name = "code")
    private String code;

    /**
     * 答案内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 答案对应问题List
     */
	/*@ManyToMany(mappedBy = "answerOptions")
	private List<Question> questions;*/

    /**
     * 答案对应节点链接List
     */
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="path_option",joinColumns={@JoinColumn(name="option_id")},inverseJoinColumns={@JoinColumn(name="path_id")})
	private List<Path> paths;*/
    @Override
    public String toString() {
        return "AnswerOption{" +
                "type=" + type +
                ", code='" + code + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
