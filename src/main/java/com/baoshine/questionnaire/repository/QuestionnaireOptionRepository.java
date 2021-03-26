package com.baoshine.questionnaire.repository;

import com.baoshine.questionnaire.entity.PathOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireOptionRepository extends JpaRepository<PathOption, Long> {

    List<PathOption> findByPathId(Long pathId);
}
