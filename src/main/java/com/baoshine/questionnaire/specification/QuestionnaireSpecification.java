package com.baoshine.questionnaire.specification;

import com.baoshine.questionnaire.entity.Questionnaire;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionnaireSpecification {

    public static Specification<Questionnaire> buildCondition(String code, String name, String questionnaireDesc) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(code).ifPresent(item -> predicates.add(builder.equal(root.get("code"), item)));
            Optional.ofNullable(name).ifPresent(
                    item -> predicates.add(builder.like(root.get("name"), StringUtils.join("%", item, "%"))));
            Optional.ofNullable(questionnaireDesc).ifPresent(item -> predicates
                    .add(builder.like(root.get("questionnaireDesc"), StringUtils.join("%", item, "%"))));
            predicates.add(builder.equal(root.get("isDeleted"), "N"));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
