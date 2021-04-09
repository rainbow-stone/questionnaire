/*
package com.baoshine.questionnaire.entity;

import com.baoshine.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

*/
/**
 * @Description
 * @Author
 * @Date 2021-03-24 15:31:40
 *//*


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "business_questionnaire_option")
public class BusinessPathOption extends BaseEntity {

    private static final long serialVersionUID = 611484556923110729L;

    @Column(name = "business_path_id")
    private Long businessPathId;

    */
/**
     * 答案ID
     *//*

    @Column(name = "option_id")
    private Long optionId;

    */
/**
     * 答案类型（选项，输入，上传文件，其他项）
     *//*

    @Column(name = "type")
    private Long type;

    */
/**
     * 答案代码
     *//*

    @Column(name = "code")
    private String code;

    */
/**
     * 答案内容
     *//*

    @Column(name = "content")
    private String content;

}
*/
