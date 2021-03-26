package com.baoshine.questionnaire.config.jpa.dto;

import java.io.Serializable;

/**
 * Represents the JPA fragments tuple.
 *
 * @author Luke
 */
public class JpaFragmentsTuple implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 770960695500464211L;

	/**
	 * Where clause.
	 */
	private WhereClause whereClause;

	/**
	 * Order by clause.
	 */
	private OrderClause orderByClause;

	/**
	 * Default empty constructor.
	 */
	public JpaFragmentsTuple() {
		// empty
	}

	/**
	 * The getter for the whereClause instance variable.
	 *
	 * @return the whereClause
	 */
	public WhereClause getWhereClause() {
		return whereClause;
	}

	/**
	 * The setter for the whereClause instance variable.
	 *
	 * @param whereClause
	 *            the whereClause to set
	 */
	public void setWhereClause(WhereClause whereClause) {
		this.whereClause = whereClause;
	}

	/**
	 * The getter for the orderByClause instance variable.
	 *
	 * @return the orderByClause
	 */
	public OrderClause getOrderByClause() {
		return orderByClause;
	}

	/**
	 * The setter for the orderByClause instance variable.
	 *
	 * @param orderByClause
	 *            the orderByClause to set
	 */
	public void setOrderByClause(OrderClause orderByClause) {
		this.orderByClause = orderByClause;
	}

}