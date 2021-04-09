package com.baoshine.questionnaire.service;

import com.baoshine.questionnaire.entity.BusinessQuestionnaire;
import com.baoshine.questionnaire.entity.Node;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.vo.BusinessNodeVO;
import com.baoshine.questionnaire.vo.BusinessQuestionnaireVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BusinessQuestionnaireService {

    /**
     * 保存客户填写问卷
     *
     * @param businessQuestionnaireVO
     */
    BusinessQuestionnaire saveBusinessQuestionnaire(BusinessQuestionnaireVO businessQuestionnaireVO);

    /**
     * 查询所有问卷
     *
     * @param businessId
     * @param businessType
     * @return
     */
    List<BusinessQuestionnaire> queryAllQuestionnaire(Long businessId, Long businessType);

    /**
     * 展示问卷内所有问题
     *
     * @param businessQuestionnaireId
     * @return
     */
    BusinessQuestionnaire displayAllQuestion(Long businessQuestionnaireId) throws QuestionnaireException;

    /**
     * 展示下一个问题
     *
     * @param nodeId
     * @param questionnaireId
     * @return
     */
    List<Node> displayNextQuestion(Long questionnaireId, Long nodeId) throws QuestionnaireException;

    /**
     * 保存当前问题
     *
     * @param businessNodeVO
     */
    List<Node> saveQuestionStep(BusinessNodeVO businessNodeVO) throws QuestionnaireException;

    BusinessQuestionnaireVO convertToVO(BusinessQuestionnaire businessQuestionnaire) throws QuestionnaireException;

    BusinessQuestionnaireVO initialQuestionnaire(BusinessQuestionnaireVO businessQuestionnaireVO) throws QuestionnaireException;

    BusinessNodeVO convertToBusiness(Node node);
}
