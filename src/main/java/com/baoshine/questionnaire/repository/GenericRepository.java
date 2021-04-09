package com.baoshine.questionnaire.repository;

import com.baoshine.common.entity.BaseEntity;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.config.jpa.util.JpaUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

@Service
public class GenericRepository {

    @PersistenceContext(name = "posEntityManager")
    private EntityManager entityManager;

    public <T> List<T> createQuery(String qlString, Class<T> resultClass) {
        TypedQuery<T> typedQuery = entityManager.createQuery(qlString, resultClass);
        return typedQuery.getResultList();
    }

    public int executeUpdate(String qlString, List<?> parameters) throws EntityPersistenceException {
        Query query = JpaUtil.createParametersQuery(null, entityManager, qlString, parameters);
        return query.executeUpdate();
    }

    public int deleteById(Class<?> entityType, Long... entityIds) throws EntityPersistenceException {
        return JpaUtil.delete(entityManager, entityType, entityIds);
    }

    public <T> T getOne(Class<T> entityClass, long id) {
        return entityManager.find(entityClass, id);
    }

    public <T> Optional<T> findById(Class<T> entityClass, long id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    public <T> Optional<T> findById(Class<T> entityClass, @Nullable Specification<T> spec) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        applySpecification(entityClass, spec, query, builder);
        try {
            T result = entityManager.createQuery(query).getSingleResult();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public <T> T findBy(Class<T> entityClass, String field, Object value) {
        List<T> result = findAllBy(entityClass, field, value);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);
    }

    public <T> Long countBy(Class<T> entityClass, String field, Object value) {
        return countBy(entityClass, (root, query, criteriaBuilder) -> {
            query.distinct(true);
            return criteriaBuilder.equal(root.get(field), value);
        });
    }

    public <T> long countBy(Class<T> entityClass, @Nullable Specification<T> spec) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<T> root = query.from(entityClass);
        if (spec != null) {
            Predicate predicate = spec.toPredicate(root, query, builder);
            if (predicate != null) {
                query.where(predicate);
            }
        }
        if (query.isDistinct()) {
            query.select(builder.countDistinct(root));
        } else {
            query.select(builder.count(root));
        }
        // Remove all Orders the Specifications might have applied
        query.orderBy(Collections.emptyList());
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        List<Long> totals = typedQuery.getResultList();
        long total = 0L;
        for (Long element : totals) {
            total += element == null ? 0 : element;
        }
        return total;
    }

    public <T> List<T> findAllById(Class<T> entityClass, Collection<Long> ids) {
        return findAllBy(entityClass, (Specification<T>) (root, query, criteriaBuilder) ->
                root.get("id").in(ids));
    }

    public <T> List<T> findAllBy(Class<T> entityClass, String field, Object value) {
        return findAllBy(entityClass, (Specification<T>) (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(field), value));
    }

    public <T> List<T> findAllBy(Class<T> entityClass, @Nullable Specification<T> spec) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        applySpecification(entityClass, spec, query, builder);
        return entityManager.createQuery(query).getResultList();
    }

    public <T> Page<T> findAllBy(Class<T> entityClass, @Nullable Specification<T> spec, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> root = applySpecification(entityClass, spec, query, builder);

        Sort sort = pageable.isPaged() ? pageable.getSort() : Sort.unsorted();
        if (sort.isSorted()) {
            query.orderBy(toOrders(sort, root, builder));
        }
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        return pageable.isUnpaged() ? new PageImpl<T>(typedQuery.getResultList())
                : readPage(typedQuery, entityClass, pageable, spec);
    }

    @Transactional
    public <S extends BaseEntity> S save(S entity) {
        if (entity.getId() == null || entity.getId().equals(0L)) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    @Transactional
    public <S extends BaseEntity> S saveAndFlush(S entity) {
        S result = save(entity);
        entityManager.flush();
        return result;
    }

    @Transactional
    public <S extends BaseEntity> List<S> saveAll(Iterable<S> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        List<S> result = new ArrayList<S>();
        for (S entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    private <T> Root<T> applySpecification(Class<T> entityClass, @Nullable Specification<T> spec,
                                           CriteriaQuery<T> query, CriteriaBuilder builder) {
        Root<T> root = query.from(entityClass);
        if (spec != null) {
            Predicate predicate = spec.toPredicate(root, query, builder);
            if (predicate != null) {
                query.where(predicate);
            }
        }
        query.select(root);
        return root;
    }

    private <T> Page<T> readPage(TypedQuery<T> query, final Class<T> domainClass, Pageable pageable,
                                 @Nullable Specification<T> spec) {
        if (pageable.isPaged()) {
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        return PageableExecutionUtils.getPage(query.getResultList(), pageable,
                () -> countBy(domainClass, spec));
    }

}
