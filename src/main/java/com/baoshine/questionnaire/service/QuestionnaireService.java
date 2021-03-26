package com.baoshine.questionnaire.service;

import com.baoshine.common.resp.PageResp;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.vo.QuestionnaireDetailVO;
import com.baoshine.questionnaire.vo.QuestionnaireVO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface QuestionnaireService {

    /**
     * 查询问卷列表
     * @param questionnaireVO 问卷查询条件
     * @param pageable 分页信息
     * @return 查询结果
     */
    public PageResp<Questionnaire> listQuestionnaire(QuestionnaireVO questionnaireVO, Pageable pageable);

    /**
     * 显示问卷详情
     * @param id 问卷id
     * @return 问卷详情
     */
    public QuestionnaireDetailVO detailQuestionnaire(Long id) throws QuestionnaireException;

    /**
     * 保存问卷信息
     * @param questionnaireVO 保存问卷内容
     * @return 保存实体主键ID
     */
    public Long saveQuestionnaire(QuestionnaireVO questionnaireVO);

    /**
     * 更新问卷信息
     * @param questionnaireVO 更新问卷内容
     * @return 更新实体主键ID
     */
    public Long updateQuestionnaire(QuestionnaireVO questionnaireVO) throws QuestionnaireException;

    /**
     * 删除问卷
     * @param id 删除问卷ID
     * @return 删除执行结果
     */
    public int deleteQuestionnaire(Long id) throws QuestionnaireException;
}
