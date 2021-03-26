package com.baoshine.questionnaire.vo;

import com.baoshine.questionnaire.entity.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuestionVO extends Question {

    /**
     * 问题的可选答案
     */
    private List<AnswerOptionVO> optionVOS;

}
