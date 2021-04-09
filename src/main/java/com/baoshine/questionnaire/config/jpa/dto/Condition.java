package com.baoshine.questionnaire.config.jpa.dto;

import com.baoshine.questionnaire.config.jpa.util.JpaUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Condition class.
 */
public class Condition implements QueryCondition {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -5579805453130021355L;

    /**
     * The path expression.the default root "e".
     */
    private String pathExpression;

    /**
     * The parameter value.
     */
    private Object value;

    /**
     * The operator.
     */
    private Operator operator;

    /**
     * The SQL condition.
     */
    private String condition;

    /**
     * Constructor with condition parameter. It's the direct condition.
     *
     * @param condition the condition to append
     */
    public Condition(String condition) {
        // e.g. SELECT E FROM Merchant m, * Metadata md WHERE m = md.merchant
        this.condition = condition;
    }

    /**
     * constructor.
     *
     * @param pathExpression the path expression
     * @param operator       the operator
     * @param value          the condition value
     */
    public Condition(String pathExpression, Operator operator, Object value) {
        this.pathExpression = pathExpression;
        this.operator = operator;
        this.value = value;
    }

    /**
     * Constructor,Use equal to link the pathExpression and value.
     *
     * @param pathExpression the path expression
     * @param value          the condition value
     */
    public Condition(String pathExpression, Object value) {
        this.pathExpression = pathExpression;
        this.value = value;
        this.operator = Operator.equal;
    }

    /**
     * This method is used to generate the SQL script for this condition.
     *
     * @param parameters the passed-in parameters.
     * @return the generated SQL script
     */
    @Override
    public String toSQL(List<Parameter> parameters) {
        if (StringUtils.isNotEmpty(condition)) {
            // return the condition directly.
            return condition;
        }

        if (((operator == Operator.isNull || operator == Operator.isNotNull) && value == null)
                || ((operator == Operator.isEmpty || operator == Operator.isNotEmpty) && value == null)) {
            final StringBuilder conditionSQL = new StringBuilder();
            conditionSQL.append(pathExpression);
            conditionSQL.append(operator);
            return conditionSQL.toString();
        }

        if (value == null || (value instanceof String && StringUtils.isEmpty((String) value))) {
            return "";
        }
        final StringBuilder conditionSQL = new StringBuilder();
        final String paramName = "jpaparam" + (parameters.size() + 1);
        conditionSQL.append(pathExpression);
        conditionSQL.append(operator);
        conditionSQL.append(JpaUtil.NAMED_PARAMETER_INDICATOR);
        conditionSQL.append(paramName);
        Object actualValue = value;
        if (operator == Operator.contain && value instanceof String) {
            actualValue = "%" + value + "%";
        }
        parameters.add(new Parameter(paramName, actualValue));
        return conditionSQL.toString();
    }
}