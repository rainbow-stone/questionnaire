package com.baoshine.questionnaire.entity;

import com.baoshine.common.entity.BaseEntity;
import com.baoshine.questionnaire.enums.QuestionnaireStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author
 * @Date 2021-03-23 17:54:01
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "questionnaire")
public class Questionnaire extends BaseEntity {

    private static final long serialVersionUID = 6308925817606172434L;

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

    @Column(name = "status")
    private Long status = QuestionnaireStatus.DRAFT.getKey();

    /**
     * 问卷描述
     */
    @Column(name = "questionnaire_desc")
    private String questionnaireDesc;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questionnaire")
    private List<Node> nodeList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questionnaire")
    private List<Path> pathList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questionnaire")
    private List<ProductQuestionnaire> productConfigs = new ArrayList<>();

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id='" + super.getId() + '\'' +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", questionnaireDesc='" + questionnaireDesc + '\'' +
                '}';
    }
}
