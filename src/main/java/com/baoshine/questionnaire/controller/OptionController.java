package com.baoshine.questionnaire.controller;

import com.baoshine.common.resp.PageResp;
import com.baoshine.common.resp.ResultResp;
import com.baoshine.questionnaire.vo.AnswerOptionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(value = "/option", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(value = "答案")
public class OptionController {

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询答案列表")
    public ResultResp<PageResp<AnswerOptionVO>> listOption(AnswerOptionVO optionVO){
        return null;
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增答案")
    public ResultResp<String> addOption(@RequestBody AnswerOptionVO optionVO){
        return null;
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "修改答案")
    public ResultResp<String> updateOption(@RequestBody AnswerOptionVO optionVO){
        return null;
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除答案")
    public ResultResp<String> deleteOption(@RequestParam(value = "id") Long id){
        return null;
    }

}
