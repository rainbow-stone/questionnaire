package com.baoshine.questionnaire.controller;

import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.common.resp.PageResp;
import com.baoshine.common.resp.ResultResp;
import com.baoshine.questionnaire.entity.ProductQuestionnaire;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.enums.QuestionnaireStatus;
import com.baoshine.questionnaire.service.QuestionnaireService;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.ProductQuestionnaireVO;
import com.baoshine.questionnaire.vo.QuestionnaireDetailVO;
import com.baoshine.questionnaire.vo.QuestionnaireVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/questionnaire")
@Slf4j
@Api(value = "问卷")
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询问卷信息列表")
    public ResultResp<PageResp<QuestionnaireVO>> listQuestionnaire(QuestionnaireVO questionnaireVO) {
        Page<Questionnaire> questionnaires = questionnaireService.listQuestionnaire(questionnaireVO,
                PageRequest.of(questionnaireVO.getPage(), questionnaireVO.getSize()));
        Page<QuestionnaireVO> questionnaireVOS = ListBeanConvertUtil.convertPage(questionnaires, QuestionnaireVO.class);
        return ResultResp.success(PageResp.by(questionnaireVOS));
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "显示问卷详情")
    public ResultResp<QuestionnaireDetailVO> questionnaireDetail(
            @RequestParam(value = "questionnaireId") Long questionnaireId) {
        try {
            QuestionnaireDetailVO questionnaireDetailVO = questionnaireService.detailQuestionnaire(questionnaireId);
            return ResultResp.success(questionnaireDetailVO);
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_QUERY_RESULT_NULL.getCode(), e.getLocalizedMessage());
        }
    }

    @PostMapping(value = "/detail/add")
    @ApiOperation(value = "保存问卷详情")
    public ResultResp<String> saveQuestionnaireDetail(@RequestBody QuestionnaireDetailVO questionnaireDetailVO) {
        questionnaireService.saveQuestionnaireDetail(questionnaireDetailVO);
        return ResultResp.success("保存问卷详情成功");
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增问卷")
    public ResultResp<String> addQuestionnaire(@RequestBody QuestionnaireVO questionnaireVO) {
        try {
            Long questionnaireId = questionnaireService.saveQuestionnaire(questionnaireVO);
            if (questionnaireId == null || questionnaireId.intValue() <= 0) {
                return ResultResp.error(ResultCodeEnum.CMN_REPOSITORY_ERROR.getCode(), "新增失败");
            }
            return ResultResp.success("新增成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "更新问卷")
    public ResultResp<String> updateQuestionnaire(@RequestBody QuestionnaireVO questionnaireVO) {
        try {
            Long saveId = questionnaireService.updateQuestionnaire(questionnaireVO);
            if (saveId == null || saveId.intValue() <= 0) {
                return ResultResp.error(ResultCodeEnum.CMN_REPOSITORY_ERROR.getCode(), "修改失败");
            }
            return ResultResp.success("修改成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除问卷")
    public ResultResp<String> deleteQuestionnaire(@RequestParam(value = "questionnaireId") Long id) {
        try {
            Long deleteId = questionnaireService.deleteQuestionnaire(id);
            if (deleteId == null || deleteId.intValue() <= 0) {
                return ResultResp.error(ResultCodeEnum.CMN_REPOSITORY_ERROR.getCode(), "删除失败");
            }
            return ResultResp.success("删除成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    @PutMapping(value = "/effective")
    @ApiOperation(value = "生效问卷")
    public ResultResp<String> effectiveQuestionnaire(@RequestParam(value = "questionnaireId") Long id) {
        try {
            questionnaireService.effectiveQuestionnaire(id, QuestionnaireStatus.EFFECTIVE);
            return ResultResp.success("生效成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/product/list")
    @ApiOperation(value = "展示产品问卷配置")
    public ResultResp<PageResp<ProductQuestionnaireVO>> listProductQuestionnaire(
            @RequestParam(value = "questionnaireId") Long questionnaireId, @RequestParam(value = "page") int page,
            @RequestParam(value = "siz") int size) {
        Page<ProductQuestionnaire> productQuestionnaires =
                questionnaireService.listProductQuestionnaire(questionnaireId, PageRequest.of(page, size));
        Page<ProductQuestionnaireVO> productQuestionnaireVOS =
                ListBeanConvertUtil.convertPage(productQuestionnaires, ProductQuestionnaireVO.class);
        return ResultResp.success(PageResp.by(productQuestionnaireVOS));
    }

}
