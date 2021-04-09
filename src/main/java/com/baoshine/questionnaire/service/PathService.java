package com.baoshine.questionnaire.service;

import com.baoshine.questionnaire.entity.Path;
import com.baoshine.questionnaire.vo.PathVO;

public interface PathService {

    /**
     * entity转VO
     *
     * @param path entity
     * @return vo
     */
    PathVO convertToVO(Path path);

    /**
     * VO转Entity
     *
     * @param pathVO vo
     * @return entity
     */
    Path convertToEntity(PathVO pathVO);
}
