package com.baoshine.questionnaire.vo;

import com.baoshine.questionnaire.vo.request.SearchRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PathVO extends SearchRequest {

    private Long id;

    /**
     * 问卷ID
     */
    private QuestionnaireVO questionnaireVO;

    /**
     * 上级节点
     */
    private Long parentNodeId;

    /**
     * 下级节点
     */
    private Long childNodeId;

    private List<AnswerOptionVO> answerOptionVOS;

    public PathVO(Long id, QuestionnaireVO questionnaireVO, Long parentNodeId, Long childNodeId,
                  List<AnswerOptionVO> answerOptionVOS) {
        this.id = id;
        this.questionnaireVO = questionnaireVO;
        this.parentNodeId = parentNodeId;
        this.childNodeId = childNodeId;
        this.answerOptionVOS = answerOptionVOS;
    }
}
