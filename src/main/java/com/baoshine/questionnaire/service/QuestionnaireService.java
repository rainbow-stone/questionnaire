package com.baoshine.questionnaire.service;

import com.baoshine.questionnaire.entity.Node;
import com.baoshine.questionnaire.entity.ProductQuestionnaire;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.enums.QuestionnaireStatus;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.vo.QuestionnaireDetailVO;
import com.baoshine.questionnaire.vo.QuestionnaireVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionnaireService {

    /**
     * 查询问卷信息
     *
     * @param questionnaireId
     * @return
     */
    Questionnaire loadQuestionnaire(Long questionnaireId) throws QuestionnaireException;

    /**
     * 查询问卷列表
     *
     * @param questionnaireVO 问卷查询条件
     * @param pageable        分页信息
     * @return 查询结果
     */
    Page<Questionnaire> listQuestionnaire(QuestionnaireVO questionnaireVO, Pageable pageable);

    /**
     * 查询下属问题
     *
     * @param questionnaireId
     * @param nodeId
     * @return
     */
    List<Node> nextQuestions(Long questionnaireId, Long nodeId) throws QuestionnaireException;

    /**
     * 显示问卷详情
     *
     * @param id 问卷id
     * @return 问卷详情
     */
    QuestionnaireDetailVO detailQuestionnaire(Long id) throws QuestionnaireException;

    /**
     * 保存问卷信息
     *
     * @param questionnaireVO 保存问卷内容
     * @return 保存实体主键ID
     */
    Long saveQuestionnaire(QuestionnaireVO questionnaireVO);

    /**
     * 保存问卷详情
     *
     * @param questionnaireDetailVO 问卷详细信息
     * @return 保存实体主键ID
     */
    Long saveQuestionnaireDetail(QuestionnaireDetailVO questionnaireDetailVO);

    /**
     * 更新问卷信息
     *
     * @param questionnaireVO 更新问卷内容
     * @return 更新实体主键ID
     */
    Long updateQuestionnaire(QuestionnaireVO questionnaireVO) throws QuestionnaireException;

    /**
     * 删除问卷
     *
     * @param id 删除问卷ID
     * @return 删除执行结果
     */
    Long deleteQuestionnaire(Long id) throws QuestionnaireException;

    /**
     * 改变问卷模板状态
     *
     * @param questionnaireId     问卷ID
     * @param questionnaireStatus 问卷状态
     */
    void effectiveQuestionnaire(Long questionnaireId, QuestionnaireStatus questionnaireStatus)
            throws QuestionnaireException;

    /**
     * 查询配置当前问卷的产品
     *
     * @param questionnaireId 问卷ID
     * @return
     */
    Page<ProductQuestionnaire> listProductQuestionnaire(Long questionnaireId, Pageable pageable);
}
