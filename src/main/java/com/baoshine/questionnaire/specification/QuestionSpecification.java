package com.baoshine.questionnaire.specification;

import com.baoshine.questionnaire.entity.Question;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionSpecification {

    public static Specification<Question> buildCondition(String code, Long questionType, Long presentationType, String content){
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(code).ifPresent(item -> predicates.add(builder.equal(root.get("code"), item)));
            Optional.ofNullable(questionType).ifPresent(item -> predicates.add(builder.equal(root.get("questionType"), item)));
            Optional.ofNullable(presentationType).ifPresent(item -> predicates.add(builder.equal(root.get("presentationType"), item)));
            Optional.ofNullable(content).ifPresent(item -> predicates.add(builder.like(root.get("content"), StringUtils.join("%", item, "%"))));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
