package com.baoshine.questionnaire.config.jpa.dto;

import com.baoshine.questionnaire.config.jpa.util.JpaUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AndCondition.
 */
public class AndCondition implements QueryCondition {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1407841071397595424L;

	/**
	 * All the conditions which should be joined by AND operator.
	 */
	private final List<QueryCondition> conditions;

	/**
	 * Empty constructor.
	 */
	public AndCondition() {
		conditions = new ArrayList<>();
	}

	/**
	 * constructor.
	 *
	 * @param conditions
	 *            the conditions to assign
	 */
	public AndCondition(List<QueryCondition> conditions) {
		this.conditions = conditions;
	}

	/**
	 * constructor.
	 *
	 * @param conditions
	 *            the condition array.
	 */
	public AndCondition(QueryCondition... conditions) {
		this.conditions = Arrays.asList(conditions);
	}

	/**
	 * This method is used to add condition to list.
	 *
	 * @param condition
	 *            the condition to add
	 */
	public void addCondition(QueryCondition condition) {
		conditions.add(condition);
	}

	/**
	 * Generates the condition expression for this and condition.
	 *
	 * @param parameters
	 *            a list of the parameters
	 * @return the SQL script
	 */
	@Override
	public String toSQL(List<Parameter> parameters) {
		return toSQL(parameters, true);
	}

	/**
	 * Generates the condition expression for this and condition.
	 *
	 * @param parameters
	 *            a list of the parameters
	 * @return the SQL script
	 */
	public String toSQLWithoutBrackets(List<Parameter> parameters) {
		return toSQL(parameters, false);
	}

	/**
	 * Generates the condition expression for this and condition.
	 *
	 * @param includeBrackets
	 *            the flag to indicate if include the brackets.
	 * @param parameters
	 *            a list of the parameters
	 * @return the SQL script
	 */
	public String toSQL(List<Parameter> parameters, boolean includeBrackets) {
		if (conditions == null || conditions.isEmpty()) {
			return "";
		}
		final StringBuilder stringBuilder = new StringBuilder();
		for (final QueryCondition condition : conditions) {
			final String sql = condition.toSQL(parameters);
			if (StringUtils.isEmpty(sql)) {
				continue;
			}
			stringBuilder.append(JpaUtil.SQL_AND).append(sql);
		}
		final String result = stringBuilder.toString().replaceFirst("^" + JpaUtil.SQL_AND, "").trim();
		if (result.isEmpty()) {
			return "";
		}
		return includeBrackets ? " ( " + result + " ) " : result;
	}

}
