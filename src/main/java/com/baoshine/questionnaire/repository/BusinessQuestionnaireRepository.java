package com.baoshine.questionnaire.repository;

import com.baoshine.questionnaire.entity.BusinessQuestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessQuestionnaireRepository extends JpaRepository<BusinessQuestionnaire, Long> {
}
