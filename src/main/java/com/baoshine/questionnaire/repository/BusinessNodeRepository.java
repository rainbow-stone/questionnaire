package com.baoshine.questionnaire.repository;

import com.baoshine.questionnaire.entity.BusinessNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessNodeRepository extends JpaRepository<BusinessNode, Long> {

    @Query(value ="select * from business_node where business_questionnaire_id = ?1", nativeQuery = true)
    List<BusinessNode> findByBusinessQuestionnaire(Long businessQuestionnaireId);
}
