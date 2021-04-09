package com.baoshine.questionnaire.entity;

import com.baoshine.common.constant.BaseConstants;
import io.swagger.annotations.Api;
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

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Api(value = "通用类")
public class UUIDEntity implements Serializable {

    private static final long serialVersionUID = 7014141090552078226L;

    /**
     * 如果ID为String，默认用前端UUID保存则无需其他处理逻辑
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time", updatable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @CreatedBy
    @ApiModelProperty(value = "创建人")
    @Column(name = "creator", updatable = false)
    private String creator;

    @LastModifiedBy
    @Column(name = "modifier")
    @ApiModelProperty(value = "修改人")
    private String modifier;

    @Column(name = "is_deleted")
    @ApiModelProperty(value = "是否删除 Y:是 N:否")
    private String isDeleted = BaseConstants.DELETE_N;
}
