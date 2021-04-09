package com.baoshine.questionnaire.exception;

import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.common.resp.ResultResp;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalDefultExceptionHandler {
    /**
     * 处理参数异常，一般用于校验body参数
     *
     * @param e MethodArgumentNotValidException
     * @return ResultResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResp<Void> handleValidationBodyException(MethodArgumentNotValidException e) {
        for (ObjectError s : e.getBindingResult().getAllErrors()) {
            return ResultResp
                    .error(ResultCodeEnum.CMN_ILLEGAL_ARG.getCode(), s.getObjectName() + ": " + s.getDefaultMessage());
        }
        return ResultResp.error(ResultCodeEnum.CMN_ILLEGAL_ARG.getCode(), "Unknown parameter");
    }

    /**
     * 主动throw的异常
     *
     * @param e        PosProviderException
     * @param response response
     * @return ResultResponse
     */
    @ExceptionHandler(QuestionnaireException.class)
    public ResultResp<Void> handleFormulaProviderException(QuestionnaireException e, HttpServletResponse response) {
        response.setStatus(e.getResultCodeEnum().getCode());
        return ResultResp.error(e.getResultCodeEnum().getCode(), e.getMessage());
    }
}