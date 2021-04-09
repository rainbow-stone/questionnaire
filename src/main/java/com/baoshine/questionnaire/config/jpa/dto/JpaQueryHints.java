package com.baoshine.questionnaire.config.jpa.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the query hints.
 */
public class JpaQueryHints implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -1061332534438805147L;

    /**
     * Query hints for JPA query.
     */
    private final List<JpaQueryHint> queryHints;

    /**
     * Default constructor.
     *
     * @param queryHints the query hints
     */
    public JpaQueryHints(JpaQueryHint... queryHints) {
        this.queryHints = Arrays.asList(queryHints);
    }

    /**
     * Default constructor.
     *
     * @param queryHints the query hints
     */
    public JpaQueryHints(List<JpaQueryHint> queryHints) {
        this.queryHints = queryHints;
    }

    /**
     * The getter for the queryHints instance variable.
     *
     * @return the queryHints
     */
    public List<JpaQueryHint> getQueryHints() {
        return queryHints;
    }

}
