package com.baoshine.questionnaire.service;

import com.baoshine.common.resp.PageResp;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.entity.Question;
import com.baoshine.questionnaire.exception.QuestionnaireException;
import com.baoshine.questionnaire.vo.QuestionVO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {

    /**
     * 查询问题列表
     * @param questionVO 查询条件
     * @param pageable 分页条件
     * @return 查询结果
     */
    public PageResp<Question> listQuestion(QuestionVO questionVO, Pageable pageable);

    /**
     * 保存问题
     * @param questionVO 保存问题内容
     * @return 保存实体主键ID
     */
    public Long saveQuestion(QuestionVO questionVO);

    /**
     * 更新问题
     * @param questionVO 修改问题内容
     * @return 更新实体主键ID
     * @throws QuestionnaireException 修改报错
     */
    public Long updateQuestion(QuestionVO questionVO) throws QuestionnaireException;

    /**
     * 删除问题
     * @param id 删除问题主键ID
     * @return 删除执行结果
     * @throws EntityPersistenceException 删除报错
     */
    public int deleteQuestion(Long id) throws QuestionnaireException;


}
