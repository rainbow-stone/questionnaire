package com.baoshine.questionnaire.config.jpa.dto;

import com.baoshine.questionnaire.config.jpa.util.JpaUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the OR condition.
 */
public class OrCondition implements QueryCondition {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -835334430567295850L;

    /**
     * All the conditions which should be joined by AND operator.
     */
    private final List<QueryCondition> conditions;

    /**
     * Empty constructor.
     */
    public OrCondition() {
        conditions = new ArrayList<>();
    }

    /**
     * constructor.
     *
     * @param conditions the conditions to assign
     */
    public OrCondition(List<QueryCondition> conditions) {
        this.conditions = conditions;
    }

    /**
     * constructor.
     *
     * @param conditions the condition array.
     */
    public OrCondition(QueryCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    /**
     * This method is used to add condition to list.
     *
     * @param condition the condition to add
     */
    public void addCondition(QueryCondition condition) {
        conditions.add(condition);
    }

    /**
     * Generates the condition expression for this and condition.
     *
     * @param parameters the list of parameters
     * @return the generated SQL String
     */
    @Override
    public String toSQL(List<Parameter> parameters) {
        if (conditions == null || conditions.isEmpty()) {
            return "";
        }
        final StringBuilder stringBuilder = new StringBuilder();
        for (final QueryCondition condition : conditions) {
            final String sql = condition.toSQL(parameters);
            if (StringUtils.isEmpty(sql)) {
                continue;
            }
            stringBuilder.append(JpaUtil.SQL_OR).append(sql);
        }
        final String result = stringBuilder.toString().replaceFirst("^" + JpaUtil.SQL_OR, "").trim();

        return result.isEmpty() ? "" : " ( " + result + " ) ";
    }

}
