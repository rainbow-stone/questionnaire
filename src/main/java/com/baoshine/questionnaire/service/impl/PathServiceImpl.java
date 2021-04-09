package com.baoshine.questionnaire.service.impl;

import com.baoshine.questionnaire.entity.AnswerOption;
import com.baoshine.questionnaire.entity.Path;
import com.baoshine.questionnaire.entity.Questionnaire;
import com.baoshine.questionnaire.service.PathService;
import com.baoshine.questionnaire.utils.ListBeanConvertUtil;
import com.baoshine.questionnaire.vo.AnswerOptionVO;
import com.baoshine.questionnaire.vo.PathVO;
import com.baoshine.questionnaire.vo.QuestionnaireVO;
import org.springframework.stereotype.Service;

@Service
public class PathServiceImpl implements PathService {
    /**
     * entity转VO
     *
     * @param path entity
     * @return vo
     */
    @Override
    public PathVO convertToVO(Path path) {
        PathVO pathVO = ListBeanConvertUtil.convert(path, PathVO.class);
        assert pathVO != null;
        pathVO.setAnswerOptionVOS(ListBeanConvertUtil.convert(path.getAnswerOptions(), AnswerOptionVO.class));
        pathVO.setQuestionnaireVO(ListBeanConvertUtil.convert(path.getQuestionnaire(), QuestionnaireVO.class));
        return pathVO;
    }

    /**
     * VO转Entity
     *
     * @param pathVO vo
     * @return entity
     */
    @Override
    public Path convertToEntity(PathVO pathVO) {
        Path path = ListBeanConvertUtil.convert(pathVO, Path.class);
        assert path != null;
        path.setAnswerOptions(ListBeanConvertUtil.convert(pathVO.getAnswerOptionVOS(), AnswerOption.class));
        path.setQuestionnaire(ListBeanConvertUtil.convert(pathVO.getQuestionnaireVO(), Questionnaire.class));
        return path;
    }
}
