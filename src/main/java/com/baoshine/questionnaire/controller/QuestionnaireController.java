package com.baoshine.questionnaire.controller;

import com.baoshine.common.resp.PageResp;
import com.baoshine.common.resp.ResultResp;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.vo.QuestionnaireDetailVO;
import com.baoshine.questionnaire.vo.QuestionnaireVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiListing;

@RestController
@RequestMapping(value = "/questionnaire")
@Slf4j
@Api(value = "问卷")
public class QuestionnaireController {

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询问卷信息列表")
    public ResultResp<PageResp<QuestionnaireVO>> listQuestionnaire(QuestionnaireVO questionnaireVO){
        return null;
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "显示问卷详情")
    public ResultResp<QuestionnaireDetailVO> questionnaireDetail(QuestionnaireDetailVO questionnaireDetailVO){
        return null;
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增问卷")
    public ResultResp<String> addQuestionnaire(@RequestBody QuestionnaireVO questionnaireVO){
        return null;
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "更新问卷")
    public ResultResp<String> updateQuestionnaire(@RequestBody Questionnaire questionnaire){
        return null;
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除问卷")
    public ResultResp<String> deleteQuestionnaire(@RequestParam(value = "questionnaireId") Long id){
        return null;
    }
}
