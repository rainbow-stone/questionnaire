package com.baoshine.questionnaire.config.jpa.util;

import com.baoshine.questionnaire.config.jpa.dto.JpaQueryHints;
import com.baoshine.questionnaire.config.jpa.dto.Parameter;
import com.baoshine.questionnaire.config.jpa.dto.WhereClause;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.config.jpa.exception.NonUniqueEntityException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA single query utility.
 */
public abstract class JpaSingleQueryUtil extends JpaListQueryUtil {

    /**
     * Empty constructor.
     */
    protected JpaSingleQueryUtil() {
        // Empty
    }

    /**
     * 查询唯一结果 - 带参数的JPQL
     *
     * @param <T>           the returned object type
     * @param queryHints    the query hints
     * @param entityManager the entity manager
     * @param jpql          the JPQL to run
     * @param returnedType  the returned object type
     * @param parameters    the named parameters list
     * @return the single result
     * @throws NonUniqueEntityException   if more than one entity is found
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static <T> T singleGeneralQuery(JpaQueryHints queryHints, EntityManager entityManager, Class<T> returnedType,
                                           String jpql, List<?> parameters) throws EntityPersistenceException {
        return retrieveSingleResult(
                createParametersQuery(queryHints, entityManager, returnedType, jpql, parameters));
    }

    /**
     * 查询唯一结果 - 指定的domain
     *
     * @param <T>           the returned entity type
     * @param queryHints    the query hints
     * @param entityManager the entity manager
     * @param entityClass   the returned entity type
     * @param whereClause   {@link WhereClause}
     * @return the single result
     * @throws NonUniqueEntityException   if more than one entity is found
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static <T> T singleQuery(JpaQueryHints queryHints, EntityManager entityManager, Class<T> entityClass,
                                    WhereClause whereClause) throws EntityPersistenceException {
        final List<Parameter> parameters = new ArrayList<>();
        return singleGeneralQuery(queryHints, entityManager, entityClass,
                buildJpql(createSelectEntityClause(entityClass), parameters, whereClause), parameters);
    }

    /**
     * 查询唯一结果 - 带参数的JPQL和Where条件
     *
     * @param <T>           the returned object type
     * @param queryHints    the query hints
     * @param baseJpql      it also can be a template JPQL that contains {@link BaseJpaUtil#WHERE_CLAUSE_TOKEN} and
     *                      {@link BaseJpaUtil#ORDER_BY_CLAUSE_TOKEN}
     * @param returnedType  the returned object type
     * @param entityManager the entity manager
     * @param whereClause   {@link WhereClause}
     * @return the list result
     * @throws NonUniqueEntityException   if more than one entity is found
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static <T> T singleQuery(JpaQueryHints queryHints, EntityManager entityManager, String baseJpql,
                                    Class<T> returnedType, WhereClause whereClause) throws EntityPersistenceException {
        final List<Parameter> parameters = new ArrayList<>();
        return singleGeneralQuery(queryHints, entityManager, returnedType, buildJpql(baseJpql, parameters, whereClause),
                parameters);
    }

    /**
     * This method is used to count entities.
     *
     * @param entityManager the entity manager
     * @param entityClass   the entity type to count
     * @return the entity count
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static int countEntities(EntityManager entityManager, Class<?> entityClass)
            throws EntityPersistenceException {
        return singleGeneralQuery(null, entityManager, Long.class, createSelectDomainCountClause(entityClass), null)
                .intValue();
    }

    /**
     * This method is used to count entities.
     *
     * @param entityManager the entity manager
     * @param entityClass   the entity type to count
     * @param whereClause   {@link WhereClause}
     * @return the entity count
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static int countEntities(EntityManager entityManager, Class<?> entityClass, WhereClause whereClause)
            throws EntityPersistenceException {
        final List<Parameter> parameters = new ArrayList<>();
        return singleGeneralQuery(null, entityManager, Long.class,
                buildJpql(createSelectDomainCountClause(entityClass), parameters, whereClause), parameters).intValue();
    }

    /**
     * This method is used to count entities.
     *
     * @param entityManager the entity manager
     * @param baseJpql      it also can be a template JPQL that contains {@link BaseJpaUtil#WHERE_CLAUSE_TOKEN} and
     *                      {@link BaseJpaUtil#ORDER_BY_CLAUSE_TOKEN}
     * @return the entity count
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static int countEntities(EntityManager entityManager, String baseJpql) throws EntityPersistenceException {
        return singleGeneralQuery(null, entityManager, Long.class, baseJpql, null).intValue();
    }

    /**
     * This method is used to count entities.
     *
     * @param entityManager the entity manager
     * @param baseJpql      the base JPQL
     * @param whereClause   {@link WhereClause}
     * @return the entity count
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static int countEntities(EntityManager entityManager, String baseJpql, WhereClause whereClause)
            throws EntityPersistenceException {
        final List<Parameter> parameters = new ArrayList<>();
        return singleGeneralQuery(null, entityManager, Long.class, buildJpql(baseJpql, parameters, whereClause),
                parameters).intValue();
    }

    /**
     * This method is used to retrieve single result.
     *
     * @param <T>   the return result type
     * @param query the query
     * @return the retrieve object
     * @throws NonUniqueEntityException   if more than one entity is found
     * @throws EntityPersistenceException if any persistence error occurs
     */
    private static <T> T retrieveSingleResult(TypedQuery<T> query) throws EntityPersistenceException {
        try {
            return query.getSingleResult();
        } catch (final NoResultException e) {
            return null;
        } catch (final NonUniqueResultException e) {
            throw new NonUniqueEntityException("It should be only one entity in database.", e);
        } catch (final PersistenceException e) {
            throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
        }
    }
}
