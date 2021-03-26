package com.baoshine.questionnaire.config.jpa.dto;

import java.io.Serializable;

/**
 * Represents the JPA query hint.
 */
public class JpaQueryHint implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -4054890071411547534L;

	/**
	 * Hint name.
	 */
	private String name;

	/**
	 * Hint value.
	 */
	private Object value;

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            the hint name
	 * @param value
	 *            the hint value
	 */
	public JpaQueryHint(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * The getter for the name instance variable.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * The setter for the name instance variable.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The getter for the value instance variable.
	 * 
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * The setter for the value instance variable.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
