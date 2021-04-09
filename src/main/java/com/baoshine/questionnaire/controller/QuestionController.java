package com.baoshine.questionnaire.controller;

import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.common.resp.PageResp;
import com.baoshine.common.resp.ResultResp;
import com.baoshine.questionnaire.entity.AnswerOption;
import com.baoshine.questionnaire.entity.Question;
import com.baoshine.questionnaire.service.AnswerOptionService;
import com.baoshine.questionnaire.service.QuestionService;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.AnswerOptionVO;
import com.baoshine.questionnaire.vo.QuestionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/question")
@Api(value = "问题")
public class QuestionController {

    private final QuestionService questionService;

    private final AnswerOptionService answerOptionService;

    @Autowired
    public QuestionController(QuestionService questionService,
                              AnswerOptionService answerOptionService) {
        this.questionService = questionService;
        this.answerOptionService = answerOptionService;
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询问题列表")
    public ResultResp<PageResp<QuestionVO>> listQuestion(QuestionVO questionVO) {
        Page<Question> questions =
                questionService.listQuestion(questionVO, PageRequest.of(questionVO.getPage(), questionVO.getSize()));
        Page<QuestionVO> questionVOS = ListBeanConvertUtil.convertPage(questions, QuestionVO.class);
        return ResultResp.success(PageResp.by(questionVOS));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增问题")
    public ResultResp<String> addQuestion(@RequestBody QuestionVO questionVO) {
        try {
            Long saveId = questionService.saveQuestion(questionVO);
            if (saveId == null || saveId.intValue() <= 0) {
                return ResultResp.error(ResultCodeEnum.CMN_REPOSITORY_ERROR.getCode(), "新增失败");
            }
            return ResultResp.success("新增成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "修改问题")
    public ResultResp<String> updateQuestion(@RequestBody QuestionVO questionVO) {
        try {
            Long saveId = questionService.updateQuestion(questionVO);
            if (saveId == null || saveId.intValue() <= 0) {
                return ResultResp.error(ResultCodeEnum.CMN_REPOSITORY_ERROR.getCode(), "新增失败");
            }
            return ResultResp.success("修改成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除问题")
    public ResultResp<String> deleteQuestion(@RequestParam(value = "id") Long id) {
        try {
            Long deleteId = questionService.deleteQuestion(id);
            if (deleteId == null || deleteId.intValue() <= 0) {
                return ResultResp.error(ResultCodeEnum.CMN_REPOSITORY_ERROR.getCode(), "删除失败");
            }
            return ResultResp.success("删除成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/config/list")
    @ApiOperation(value = "问题答案配置查询")
    public ResultResp<List<AnswerOptionVO>> listQuestionConfig(@RequestParam(value = "questionId") Long questionId) {
        try {
            Question question = questionService.loadQuestion(questionId);
            List<AnswerOption> answerOptions = question.getAnswerOptions();
            List<AnswerOptionVO> answerOptionVOS = ListBeanConvertUtil.convert(answerOptions, AnswerOptionVO.class);
            return ResultResp.success(answerOptionVOS);
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    @PostMapping(value = "/config/add")
    @ApiOperation(value = "新增问题答案配置")
    public ResultResp<String> addQuestionConfig(@RequestParam(value = "questionId", required = true) Long questionId,
                                                @RequestParam(value = "optionId", required = true) Long optionId) {
        try {
            questionService.addAnswerOptionConfig(questionId, optionId);
            return ResultResp.success("添加答案配置成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }


    @DeleteMapping(value = "/config/delete")
    @ApiOperation(value = "删除问题答案配置")
    public ResultResp<String> deleteQuestionConfig(@RequestParam(value = "questionId", required = true) Long questionId,
                                                   @RequestParam(value = "optionId", required = true) Long optionId) {
        try {
            questionService.deleteAnswerOptionConfig(questionId, optionId);
            return ResultResp.success("删除答案配置成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }

    }
}
