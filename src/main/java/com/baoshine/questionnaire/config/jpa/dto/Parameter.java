package com.baoshine.questionnaire.config.jpa.dto;

/**
 * Parameter.
 */
public class Parameter {

    /**
     * The parameter name.
     */
    private String name;

    /**
     * The parameter value.
     */
    private Object value;

    /**
     * The constructor of Parameter.
     *
     * @param name  the name parameter
     * @param value the value parameter
     */
    public Parameter(String name, Object value) {
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
     * @param name the name to set
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
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }
}
