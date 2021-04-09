package com.baoshine.questionnaire.config.jpa.util;

import com.baoshine.questionnaire.config.jpa.dto.*;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA list query utility.
 */
public abstract class JpaListQueryUtil extends BaseJpaUtil {

    /**
     * Empty constructor.
     */
    protected JpaListQueryUtil() {
        // Empty
    }

    /**
     * The general list query by named parameters array.
     *
     * @param <T>           the object type of the item in the returned list
     * @param queryHints    the query hints
     * @param entityManager the entity manager
     * @param jpql          the JPQL to run
     * @param itemType      the object type of the item in the returned list
     * @param parameters    the named parameters list
     * @return the list result
     * @throws EntityPersistenceException if any error occurred.
     */
    public static <T> List<T> listGeneralQuery(JpaQueryHints queryHints, EntityManager entityManager, Class<T> itemType,
                                               String jpql, List<?> parameters) throws EntityPersistenceException {
        try {
            return createParametersQuery(queryHints, entityManager, itemType, jpql, parameters).getResultList();
        } catch (final PersistenceException e) {
            throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
        }
    }

    /**
     * Query the entity list by conditions.The path expression of the condition should be started with prefix: 'e'.
     *
     * @param <T>           the entity type
     * @param queryHints    the query hints
     * @param entityManager the entity manager
     * @param entityClass   the entity type
     * @param jpaFragments  {@link WhereClause} and {@link OrderClause}
     * @return the list result
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static <T> List<T> listQuery(JpaQueryHints queryHints, EntityManager entityManager, Class<T> entityClass,
                                        JpaFragment... jpaFragments) throws EntityPersistenceException {
        try {
            final List<Parameter> parameters = new ArrayList<>();
            return listGeneralQuery(queryHints, entityManager, entityClass,
                    buildJpql(createSelectEntityClause(entityClass), parameters, jpaFragments), parameters);
        } catch (final PersistenceException e) {
            throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
        }
    }

    /**
     * Query the list result by conditions.
     *
     * @param <T>           the object type of the item in the returned list
     * @param queryHints    the query hints
     * @param baseJpql      it also can be a template JPQL that contains {@link BaseJpaUtil#WHERE_CLAUSE_TOKEN} and
     *                      {@link BaseJpaUtil#ORDER_BY_CLAUSE_TOKEN}
     * @param itemType      the object type of the item in the returned list
     * @param entityManager the entity manager
     * @param jpaFragments  {@link WhereClause} and {@link OrderClause}
     * @return the list result
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static <T> List<T> listQuery(JpaQueryHints queryHints, EntityManager entityManager, Class<T> itemType,
                                        String baseJpql, JpaFragment... jpaFragments)
            throws EntityPersistenceException {
        try {
            final List<Parameter> parameters = new ArrayList<>();
            return listGeneralQuery(queryHints, entityManager, itemType, buildJpql(baseJpql, parameters, jpaFragments),
                    parameters);
        } catch (final PersistenceException e) {
            throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
        }
    }

    /**
     * The general list query by named parameters array and limit by the first result and the max number of results.
     *
     * @param <T>           the object type of the item in the returned list
     * @param queryHints    the query hints
     * @param entityManager the entity manager
     * @param jpql          the JPQL to run
     * @param itemType      the object type of the item in the returned list
     * @param firstResult   the index of the first result (starting from 0)
     * @param maxResults    the max number of results
     * @param parameters    the named parameters list
     * @return the list result
     * @throws EntityPersistenceException if any error occurred.
     */
    public static <T> List<T> listLimitGeneralQuery(JpaQueryHints queryHints, EntityManager entityManager,
                                                    Class<T> itemType, String jpql, int firstResult, int maxResults,
                                                    List<?> parameters)
            throws EntityPersistenceException {
        try {
            return createParametersQuery(queryHints, entityManager, itemType, jpql, parameters)
                    .setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
        } catch (final PersistenceException e) {
            throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
        }
    }

    /**
     * Query the entity list by conditions and limit by the first result and the max number of results. The path
     * expression of the condition should be started with prefix: 'e'.
     *
     * @param <T>           the entity type
     * @param queryHints    the query hints
     * @param entityManager the entity manager
     * @param entityClass   the entity type
     * @param firstResult   the index of the first result (starting from 0)
     * @param maxResults    the max number of results
     * @param jpaFragments  {@link WhereClause} and {@link OrderClause}
     * @return the list result
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static <T> List<T> listLimitQuery(JpaQueryHints queryHints, EntityManager entityManager,
                                             Class<T> entityClass, int firstResult, int maxResults,
                                             JpaFragment... jpaFragments)
            throws EntityPersistenceException {
        try {
            final List<Parameter> parameters = new ArrayList<>();
            return listLimitGeneralQuery(queryHints, entityManager, entityClass,
                    buildJpql(createSelectEntityClause(entityClass), parameters, jpaFragments), firstResult, maxResults,
                    parameters);
        } catch (final PersistenceException e) {
            throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
        }
    }

    /**
     * Query the list result by conditions limit by the first result and the max number of results.
     *
     * @param <T>           the object type of the item in the returned list
     * @param queryHints    the query hints
     * @param baseJpql      it also can be a template JPQL that contains {@link BaseJpaUtil#WHERE_CLAUSE_TOKEN} and
     *                      {@link BaseJpaUtil#ORDER_BY_CLAUSE_TOKEN}
     * @param itemType      the object type of the item in the returned list
     * @param entityManager the entity manager
     * @param firstResult   the index of the first result (starting from 0)
     * @param maxResults    the max number of results
     * @param jpaFragments  {@link WhereClause} and {@link OrderClause}
     * @return the list result
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static <T> List<T> listLimitQuery(JpaQueryHints queryHints, EntityManager entityManager, Class<T> itemType,
                                             String baseJpql, int firstResult, int maxResults,
                                             JpaFragment... jpaFragments)
            throws EntityPersistenceException {
        try {
            final List<Parameter> parameters = new ArrayList<>();
            return listLimitGeneralQuery(queryHints, entityManager, itemType,
                    buildJpql(baseJpql, parameters, jpaFragments), firstResult, maxResults, null);
        } catch (final PersistenceException e) {
            throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
        }
    }

}
