package com.baoshine.questionnaire.config.jpa.util;

import com.baoshine.questionnaire.config.jpa.dto.JpaQueryHints;
import com.baoshine.questionnaire.config.jpa.dto.OrderClause;
import com.baoshine.questionnaire.config.jpa.dto.Parameter;
import com.baoshine.questionnaire.config.jpa.dto.WhereClause;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.vo.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA search query utility.
 */
public abstract class JpaSearchQueryUtil extends JpaSingleQueryUtil {

    /**
     * Empty constructor.
     */
    protected JpaSearchQueryUtil() {
        // Empty
    }

    /**
     * This method is used to search entities.
     *
     * @param <T>           the entity type
     * @param queryHints    the query hints
     * @param entityManager the entity manager
     * @param entityClass   the entity type
     * @param filter        the search filter
     * @param whereClause   the {@link WhereClause}
     * @return the search result.
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static <T> Page<T> search(JpaQueryHints queryHints, EntityManager entityManager,
                                     Class<T> entityClass, SearchRequest filter, WhereClause whereClause, OrderClause orderClause)
            throws EntityPersistenceException {
        try {
            if (filter.getPage() < 0 || filter.getSize() < 1) {
                final List<T> items = listQuery(queryHints, entityManager, entityClass, whereClause,
                        orderClause);
                return PageableExecutionUtils.getPage(items, filter.buildPageable(), items::size);
            }
            final int totalItems = countEntities(entityManager, entityClass, whereClause);
            if (totalItems == 0) {
                return Page.empty();
            }

            // fix the issue that if the page number > maximum page number
            filter.setPage((int) Math.min(filter.getPage(), Math.ceil((double) totalItems / (double) filter.getSize())));

            final List<Parameter> parameters = new ArrayList<>();
            final TypedQuery<T> query = createParametersQuery(queryHints, entityManager, entityClass,
                    buildJpql(createSelectEntityClause(entityClass), parameters, orderClause, whereClause),
                    parameters);
            query.setFirstResult(filter.getPage() * filter.getSize());
            query.setMaxResults(filter.getSize());
            return PageableExecutionUtils.getPage(query.getResultList(), filter.buildPageable(), () -> totalItems);
        } catch (final PersistenceException e) {
            throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
        }
    }

    /**
     * This method is used to search entities.
     *
     * @param <T>            the item type in items
     * @param queryHints     the query hints
     * @param entityManager  the entity manager
     * @param itemType       the item type in items
     * @param filter         the search filter
     * @param whereClause    the {@link WhereClause}
     * @param baseSelectJpql it also can be a template JPQL that contains {@link BaseJpaUtil#WHERE_CLAUSE_TOKEN} and
     *                       {@link BaseJpaUtil#ORDER_BY_CLAUSE_TOKEN}
     * @param baseCountJpql  it also can be a template JPQL that contains {@link BaseJpaUtil#WHERE_CLAUSE_TOKEN} and
     *                       {@link BaseJpaUtil#ORDER_BY_CLAUSE_TOKEN}
     * @return the search result.
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static <T> Page<T> search(JpaQueryHints queryHints, EntityManager entityManager, Class<T> itemType,
                                     SearchRequest filter, WhereClause whereClause, OrderClause orderClause,
                                     String baseSelectJpql, String baseCountJpql)
            throws EntityPersistenceException {
        try {
            if (filter.getPage() < 0 || filter.getSize() < 1) {
                final List<T> items = listQuery(queryHints, entityManager, itemType, baseSelectJpql, whereClause,
                        orderClause);
                return PageableExecutionUtils.getPage(items, filter.buildPageable(), items::size);
            }
            final int totalItems = countEntities(entityManager, baseCountJpql, whereClause);
            if (totalItems == 0) {
                return Page.empty();
            }

            // fix the issue that if the page number > maximum page number
            filter.setPage((int) Math.min(filter.getPage(), Math.ceil((double) totalItems / (double) filter.getSize())));

            final List<Parameter> parameters = new ArrayList<>();
            final TypedQuery<T> query = createParametersQuery(queryHints, entityManager, itemType,
                    buildJpql(baseSelectJpql, parameters, orderClause, whereClause),
                    parameters);
            query.setFirstResult(filter.getPage() * filter.getSize());
            query.setMaxResults(filter.getSize());
            return PageableExecutionUtils.getPage(query.getResultList(), filter.buildPageable(), () -> totalItems);
        } catch (final PersistenceException e) {
            throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
        }
    }

    /**
     * This method is used to search objects by search filter.
     *
     * @param <T>           the object type of the item in the returned list
     * @param queryHints    the query hints
     * @param entityManager the entity manager
     * @param countQuery    count query
     * @param selectQuery   select query
     * @param itemType      the object type of the item in the returned list
     * @param filter        the search filter
     * @param parameters    the parameters
     * @return the search result.
     * @throws EntityPersistenceException if any persistence error occurs
     */
    public static <T> Page<T> generalSearch(JpaQueryHints queryHints, EntityManager entityManager,
                                            String countQuery, String selectQuery, Class<T> itemType,
                                            SearchRequest filter, List<Parameter> parameters)
            throws EntityPersistenceException {
        try {
            if (filter.getPage() < 0 || filter.getSize() < 1) {
                final List<T> items = listGeneralQuery(queryHints, entityManager, itemType, selectQuery, parameters);
                return PageableExecutionUtils.getPage(items, filter.buildPageable(), items::size);
            }
            final Long countResult = singleGeneralQuery(queryHints, entityManager, Long.class, countQuery, parameters);
            if (countResult == null || countResult.intValue() == 0) {
                return Page.empty();
            }

            // fix the issue that if the page number > maximum page number
            filter.setPage((int) Math.min(filter.getPage(), Math.ceil(countResult.doubleValue() / (double) filter.getSize())));

            final int totalItems = countResult.intValue();
            final TypedQuery<T> query = createParametersQuery(queryHints, entityManager, itemType,
                    selectQuery, parameters);
            query.setFirstResult(filter.getPage() * filter.getSize());
            query.setMaxResults(filter.getSize());
            return PageableExecutionUtils.getPage(query.getResultList(), filter.buildPageable(), () -> totalItems);
        } catch (final PersistenceException e) {
            throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
        }
    }
}
