package com.baoshine.questionnaire.service;

import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.entity.AnswerOption;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.vo.AnswerOptionVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerOptionService {
    /**
     * 查询答案列表
     *
     * @param answerOptionVO 查询条件
     * @param pageable       分页信息
     * @return 查询结果
     */
    Page<AnswerOption> listAnswerOption(AnswerOptionVO answerOptionVO, Pageable pageable);

    /**
     * 保存答案信息
     *
     * @param answerOptionVO 保存答案内容
     * @return 保存实体主键ID
     */
    Long saveAnswerOption(AnswerOptionVO answerOptionVO);

    /**
     * 修改答案
     *
     * @param answerOptionVO 修改答案内容
     * @return 修改实体主键ID
     * @throws QuestionnaireException 修改报错
     */
    Long updateAnswerOption(AnswerOptionVO answerOptionVO) throws QuestionnaireException;

    /**
     * 删除答案
     *
     * @param id 删除答案ID
     * @return 删除执行结果
     * @throws EntityPersistenceException 删除报错
     */
    Long deleteAnswerOption(Long id) throws QuestionnaireException;

    AnswerOption loadAnswerOption(Long id) throws QuestionnaireException;
}
