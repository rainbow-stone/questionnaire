package com.baoshine.questionnaire.entity;

import com.baoshine.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "product_questionnaire")
public class ProductQuestionnaire extends BaseEntity {

    /**
     * 问卷
     */
    @JsonBackReference
    @ManyToOne//(cascade = CascadeType.ALL)
    private Questionnaire questionnaire;

    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private Long productId;
}
