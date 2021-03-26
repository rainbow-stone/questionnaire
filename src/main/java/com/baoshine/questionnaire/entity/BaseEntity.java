/*
package com.baoshine.questionnaire.entity;

import com.baoshine.common.constant.BaseConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity implements Serializable {

    */
/**
     * 主键ID
     *//*

    @Id
    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    */
/**
     *  创建时间
     *//*

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    */
/**
     *  创建用户
     *//*

    @CreatedBy
    @Column(name = "create_user")
    private Integer createUser;

    */
/**
     *  修改时间
     *//*

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    */
/**
     *  修改用户
     *//*

    @LastModifiedBy
    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "is_deleted")
    @ApiModelProperty(value = "是否删除 Y:是 N:否")
    private String isDeleted = BaseConstants.DELETE_N;
}
*/
