package com.baoshine.questionnaire.controller;

import com.baoshine.common.resp.PageResp;
import com.baoshine.common.resp.ResultResp;
import com.baoshine.questionnaire.vo.QuestionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/question")
@Api(value = "问题")
public class QuestionController {

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询问题列表")
    public ResultResp<PageResp<QuestionVO>> listQuestion(QuestionVO questionVO){
        return null;
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增问题")
    public ResultResp<String> addQuestion(@RequestBody QuestionVO questionVO){
        return null;
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "修改问题")
    public ResultResp<String> updateQuestion(@RequestBody QuestionVO questionVO){
        return null;
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除问题")
    public ResultResp<String> deleteQuestion(@RequestParam(value = "id") Long id){
        return null;
    }

    /*@GetMapping(value = "/config/list")
    @ApiOperation(value = "问题答案配置查询")
    public ResultResp<List<QuestionConfig>> listQuestionConfig(@RequestParam(value = "questionId") Long questionId){
        return null;
    }

    @PostMapping(value = "/config/add")
    @ApiOperation(value = "新增问题答案配置")
    public ResultResp<String> addQuestionConfig(@RequestBody QuestionConfigVO questionConfigVO){
        return null;
    }

    @PutMapping(value = "/config/update")
    @ApiOperation(value = "更新问题答案配置")
    public ResultResp<String> updateQuestionConfig(@RequestBody QuestionConfigVO questionConfigVO){
        return null;
    }

    @DeleteMapping(value = "/config/delete")
    @ApiOperation(value = "删除问题答案配置")
    public ResultResp<String> deleteQuestionConfig(@RequestParam(value = "questionConfigId") Long id){
        return null;
    }*/
}
