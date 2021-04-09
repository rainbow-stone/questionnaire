package com.baoshine.questionnaire.config.jpa.dto;

import com.baoshine.questionnaire.config.jpa.util.JpaUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * WhereClause.
 */
public class WhereClause implements JpaFragment {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 3641435245505455493L;

    /**
     * All the conditions in where clause.They will be joined by 'AND'.
     */
    private final List<QueryCondition> conditions;

    /**
     * Constructor.
     *
     * @param conditions the conditions argument
     */
    private WhereClause(List<QueryCondition> conditions) {
        this.conditions = conditions;
    }

    /**
     * Default constructor.
     *
     * @param conditions the conditions to set.
     */
    private WhereClause(QueryCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    /**
     * This method is used to create where clause by conditions.
     *
     * @param conditions the conditions to set
     * @return the where clause
     */
    public static WhereClause create(List<QueryCondition> conditions) {
        return conditions == null ? null : new WhereClause(conditions);
    }

    /**
     * This method is used to create where clause by conditions.
     *
     * @param conditions the conditions to set
     * @return the where clause
     */
    public static WhereClause create(QueryCondition... conditions) {
        return conditions == null ? null : new WhereClause(conditions);
    }

    /**
     * This method is used to generate the SQL script for where clause.
     *
     * @param parameters the passed parameters
     * @return the SQL script
     */
    public String toSQL(List<Parameter> parameters) {
        if (!CollectionUtils.isEmpty(parameters)) {
            return "";
        }
        final String jpql = new AndCondition(conditions).toSQLWithoutBrackets(parameters);
        return StringUtils.isEmpty(jpql) ? "" : JpaUtil.SQL_WHERE + jpql;
    }

}
