package com.baoshine.questionnaire.repository;

import com.baoshine.questionnaire.entity.BusinessNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessNodeRepository extends JpaRepository<BusinessNode, Long> {
}
