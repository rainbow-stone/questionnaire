package com.baoshine.questionnaire.config.jpa.dto;

/**
 * Represents the JPQL operator.
 */
public enum Operator {

	/**
	 * equal operator.
	 */
	equal(" = "),

	/**
	 * not equal operator.
	 */
	notEqual(" <> "),

	/**
	 * is null operator.
	 */
	isNull(" IS NULL"),

	/**
	 * is null operator.
	 */
	isNotNull(" IS NOT NULL"),

	/**
	 * is empty operator.
	 */
	isEmpty(" IS EMPTY"),

	/**
	 * is noe empty operator.
	 */
	isNotEmpty(" IS NOT EMPTY"),

	/**
	 * in operator.
	 */
	in(" IN "),

	/**
	 * not in operator.
	 */
	notIn(" NOT IN "),

	/**
	 * like operator.
	 */
	like(" LIKE "),

	/**
	 * contain operator.Use like to implement.(%word%).
	 */
	contain(" LIKE "),

	/**
	 * greater than operator.
	 */
	greaterThan(" > "),

	/**
	 * greater than or equal to operator.
	 */
	greaterThanOrEqual(" >= "),

	/**
	 * less than operator.
	 */
	lessThan(" < "),

	/**
	 * less than or equal to operator.
	 */
	lessThanOrEqual(" <= "),

	/**
	 * RexExp
	 */
	rexExp(" REGEXP ");

	/**
	 * The enum value.
	 */
	private final String value;

	/**
	 * private constructor.
	 *
	 * @param value
	 *            the operator value
	 */
	private Operator(String value) {
		this.value = value;
	}

	/**
	 * Returns the enum value.
	 *
	 * @return the value
	 */
	@Override
	public String toString() {
		return value;
	}

}
