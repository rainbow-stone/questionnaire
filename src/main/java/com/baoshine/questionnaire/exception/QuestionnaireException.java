package com.baoshine.questionnaire.exception;

import com.baoshine.common.exception.ResultCodeEnum;

public class QuestionnaireException extends Exception {

    private static final long serialVersionUID = -4752057773527651053L;

    private ResultCodeEnum resultCodeEnum;

    public QuestionnaireException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getDesc());

        this.resultCodeEnum = resultCodeEnum;
    }

    public QuestionnaireException(ResultCodeEnum resultCodeEnum, Throwable throwable) {
        super(resultCodeEnum.getDesc(), throwable);
        this.resultCodeEnum = resultCodeEnum;
    }

    public QuestionnaireException(ResultCodeEnum resultCodeEnum, String errorMessage) {
        super(errorMessage);
        this.resultCodeEnum = resultCodeEnum;
    }

    public QuestionnaireException(ResultCodeEnum resultCodeEnum, String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
        this.resultCodeEnum = resultCodeEnum;
    }

    public ResultCodeEnum getResultCodeEnum() {
        return resultCodeEnum;
    }

}