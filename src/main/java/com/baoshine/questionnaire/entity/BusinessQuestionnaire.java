package com.baoshine.questionnaire.entity;

import com.baoshine.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;


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
@Table(name = "business_questionnaire")
public class BusinessQuestionnaire extends BaseEntity {

    private static final long serialVersionUID = 8331567100026335143L;

    @Column(name = "questionnaire_id")
    private Long questionnaireId;

    /**
     * 业务ID
     */
    @Column(name = "business_id")
    private Long businessId;

    /**
     * 业务类型
     */
    @Column(name = "business_type")
    private Long businessType;

    /**
     * 问卷代码
     */
    @Column(name = "code")
    private String code;

    /**
     * 问卷名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 问卷描述
     */
    @Column(name = "questionnaire_desc")
    private String questionnaireDesc;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "businessQuestionnaire")
    private List<BusinessNode> nodeList;

    /*@JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "businessQuestionnaire")
    private List<BusinessPath> pathList;*/

}
