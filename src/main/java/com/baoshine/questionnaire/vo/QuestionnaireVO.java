package com.baoshine.questionnaire.vo;

import com.baoshine.questionnaire.entity.Node;
import com.baoshine.questionnaire.entity.Path;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class QuestionnaireVO {

    private Long id;

    /**
     * 问卷代码
     */
    private String code;

    /**
     * 问卷名称
     */
    private String name;

    /**
     * 问卷描述
     */
    private String questionnaireDesc;

}
