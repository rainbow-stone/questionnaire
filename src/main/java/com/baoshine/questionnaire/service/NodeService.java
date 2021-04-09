package com.baoshine.questionnaire.service;

import com.baoshine.questionnaire.entity.Node;
import com.baoshine.questionnaire.vo.NodeVO;

public interface NodeService {

    /**
     * VO转Entity
     *
     * @param nodeVO vo
     * @return entity
     */
    Node convertToEntity(NodeVO nodeVO);

    /**
     * Entity转VO
     *
     * @param node entity
     * @return vo
     */
    NodeVO convertToVO(Node node);
}
