package com.baoshine.questionnaire.service;

import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.entity.Question;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.vo.QuestionVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {

    /**
     * 查询问题列表
     *
     * @param questionVO 查询条件
     * @param pageable   分页条件
     * @return 查询结果
     */
    Page<Question> listQuestion(QuestionVO questionVO, Pageable pageable);

    /**
     * 保存问题
     *
     * @param questionVO 保存问题内容
     * @return 保存实体主键ID
     */
    Long saveQuestion(QuestionVO questionVO);

    /**
     * 更新问题
     *
     * @param questionVO 修改问题内容
     * @return 更新实体主键ID
     * @throws QuestionnaireException 修改报错
     */
    Long updateQuestion(QuestionVO questionVO) throws QuestionnaireException;

    /**
     * 删除问题
     *
     * @param id 删除问题主键ID
     * @return 删除执行结果
     * @throws EntityPersistenceException 删除报错
     */
    Long deleteQuestion(Long id) throws QuestionnaireException;

    /**
     * 加载问题
     *
     * @param id 问题ID
     * @return 问题
     * @throws QuestionnaireException 未找到数据
     */
    Question loadQuestion(Long id) throws QuestionnaireException;

    /**
     * 添加问题答案配置关系
     *
     * @param questionId 问题ID
     * @param optionId   答案ID
     */
    void addAnswerOptionConfig(Long questionId, Long optionId) throws QuestionnaireException;

    /**
     * 删除问题答案配置关系
     *
     * @param questionId 问题ID
     * @param optionId   答案ID
     */
    void deleteAnswerOptionConfig(Long questionId, Long optionId) throws QuestionnaireException;

}
