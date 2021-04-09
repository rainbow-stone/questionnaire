package com.baoshine.questionnaire.controller;

import com.baoshine.common.exception.ResultCodeEnum;
import com.baoshine.common.resp.PageResp;
import com.baoshine.common.resp.ResultResp;
import com.baoshine.questionnaire.entity.AnswerOption;
import com.baoshine.questionnaire.service.AnswerOptionService;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.AnswerOptionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(value = "/option", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(value = "答案")
public class OptionController {


    private final AnswerOptionService answerOptionService;

    @Autowired
    public OptionController(AnswerOptionService answerOptionService) {
        this.answerOptionService = answerOptionService;
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询答案列表")
    public ResultResp<PageResp<AnswerOptionVO>> listOption(AnswerOptionVO optionVO) {
        Page<AnswerOption> options =
                answerOptionService.listAnswerOption(optionVO, PageRequest.of(optionVO.getPage(), optionVO.getSize()));
        Page<AnswerOptionVO> optionVOS = ListBeanConvertUtil.convertPage(options, AnswerOptionVO.class);
        return ResultResp.success(PageResp.by(optionVOS));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增答案")
    public ResultResp<String> addOption(@RequestBody AnswerOptionVO optionVO) {
        try {
            Long saveId = answerOptionService.saveAnswerOption(optionVO);
            if (optionVO == null || saveId.intValue() <= 0) {
                return ResultResp.error(ResultCodeEnum.CMN_REPOSITORY_ERROR.getCode(), "新增失败");
            }
            return ResultResp.success("新增成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "修改答案")
    public ResultResp<String> updateOption(@RequestBody AnswerOptionVO optionVO) {
        try {
            Long saveId = answerOptionService.updateAnswerOption(optionVO);
            if (optionVO == null || saveId.intValue() <= 0) {
                return ResultResp.error(ResultCodeEnum.CMN_REPOSITORY_ERROR.getCode(), "新增失败");
            }
            return ResultResp.success("修改成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除答案")
    public ResultResp<String> deleteOption(@RequestParam(value = "optionId") Long id) {
        try {
            Long deleteId = answerOptionService.deleteAnswerOption(id);
            if (deleteId == null || deleteId.intValue() <= 0) {
                return ResultResp.error(ResultCodeEnum.CMN_REPOSITORY_ERROR.getCode(), "删除失败");
            }
            return ResultResp.success("删除成功");
        } catch (Exception e) {
            return ResultResp.error(ResultCodeEnum.CMN_P_BUZ_ERROR.getCode(), e.getLocalizedMessage());
        }
    }

}
