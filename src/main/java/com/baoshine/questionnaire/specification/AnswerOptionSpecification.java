package com.baoshine.questionnaire.specification;

import com.baoshine.questionnaire.entity.AnswerOption;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnswerOptionSpecification {

    public static Specification<AnswerOption> buildCondition(String code, Long type, String content) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(code).ifPresent(item -> predicates.add(builder.equal(root.get("code"), item)));
            Optional.ofNullable(type).ifPresent(item -> predicates.add(builder.equal(root.get("type"), item)));
            Optional.ofNullable(content).ifPresent(
                    item -> predicates.add(builder.like(root.get("content"), StringUtils.join("%", item, "%"))));
            predicates.add(builder.equal(root.get("isDeleted"), "N"));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
