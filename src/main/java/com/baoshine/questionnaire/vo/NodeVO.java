package com.baoshine.questionnaire.vo;

import com.baoshine.questionnaire.vo.request.SearchRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NodeVO extends SearchRequest {

    private static final long serialVersionUID = -3752534617416141615L;

    private Long id;

    /**
     * 问卷
     */
    private QuestionnaireVO questionnaireVO;

    /**
     * 问题ID
     */
    private QuestionVO questionVO;

    /**
     * 是否根节点
     */
    private boolean rootNodeIndi;

    /**
     * 是否终节点
     */
    private boolean endNodeIndi;

    public NodeVO(Long id, QuestionnaireVO questionnaireVO, QuestionVO questionVO, boolean rootNodeIndi,
                  boolean endNodeIndi) {
        this.id = id;
        this.questionnaireVO = questionnaireVO;
        this.questionVO = questionVO;
        this.rootNodeIndi = rootNodeIndi;
        this.endNodeIndi = endNodeIndi;
    }
}
