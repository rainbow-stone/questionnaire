package com.baoshine.questionnaire.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.common.resp.ResultResp;
import com.baoshine.questionnaire.constant.ExcludeFields;
import com.baoshine.questionnaire.entity.BusinessQuestionnaire;
import com.baoshine.questionnaire.entity.Node;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.service.BusinessQuestionnaireService;
import com.baoshine.questionnaire.service.QuestionnaireService;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.BusinessNodeVO;
import com.baoshine.questionnaire.vo.BusinessQuestionnaireVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/business")
@Slf4j
@Api(value = "问卷填写")
public class BusinessQuestionnaireController {

    private final BusinessQuestionnaireService businessQuestionnaireService;

    private final QuestionnaireService questionnaireService;

    @Autowired
    public BusinessQuestionnaireController(BusinessQuestionnaireService businessQuestionnaireService,
                                           QuestionnaireService questionnaireService) {
        this.businessQuestionnaireService = businessQuestionnaireService;
        this.questionnaireService = questionnaireService;
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询客户填写问卷列表")
    public ResultResp<List<BusinessQuestionnaireVO>> queryQuestionnaires(@RequestParam(value = "businessId", required = true) Long businessId, @RequestParam(value = "businessType", required = true) Long businessType) {
        List<BusinessQuestionnaire> businessQuestionnaires =
                businessQuestionnaireService.queryAllQuestionnaire(businessId, businessType);
        return ResultResp.success(ListBeanConvertUtil.convert(businessQuestionnaires, BusinessQuestionnaireVO.class));
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "显示问卷详情")
    public ResultResp<BusinessQuestionnaireVO> detailBusinessQuestionnaire(@RequestParam(value = "businessQuestionnaireId", required = true) Long businessQuestionnaireId) {
        try {
            BusinessQuestionnaire businessQuestionnaire =
                    businessQuestionnaireService.displayAllQuestion(businessQuestionnaireId);
            return ResultResp.success(businessQuestionnaireService.convertToVO(businessQuestionnaire));
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    /**
     * 问卷填写初始化
     */
    @PostMapping(value = "/initial")
    @ApiOperation(value = "初始化问卷")
    public ResultResp<BusinessQuestionnaireVO> initialQuestionnaire(@RequestBody BusinessQuestionnaireVO businessQuestionnaireVO) {
        try {
            return ResultResp.success(businessQuestionnaireService.initialQuestionnaire(businessQuestionnaireVO));
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    /**
     * 单步提交填写答案
     *
     * @return
     */
    @PostMapping(value = "/fill")
    @ApiOperation(value = "逐个提交问题")
    public ResultResp<List<BusinessNodeVO>> saveQuestionnaireStep(@RequestBody BusinessNodeVO businessNodeVO) {
        try {
            List<Node> nodes = businessQuestionnaireService.saveQuestionStep(businessNodeVO);
            List<BusinessNodeVO> nodeVOS = nodes.stream().map(businessQuestionnaireService::convertToBusiness
            ).collect(Collectors.toList());
            nodeVOS.forEach(node -> node.setBusinessQuestionnaireVO(businessNodeVO.getBusinessQuestionnaireVO()));
            return ResultResp.success(nodeVOS);
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

}
