package com.baoshine.questionnaire.config.jpa.util;

import com.baoshine.questionnaire.config.jpa.dto.*;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * BaseJpaUtil.
 */
public abstract class BaseJpaUtil {

	/**
	 * WHERE条件子句替换符
	 */
	public static final String WHERE_CLAUSE_TOKEN = "@WHERE_CLAUSE_TOKEN@";

	/**
	 * ORDER-BY条件子句替换符
	 */
	public static final String ORDER_BY_CLAUSE_TOKEN = "@ORDER_BY_CLAUSE_TOKEN@";

	/**
	 * 'AND'替换符
	 */
	public static final String SQL_AND = " AND ";

	/**
	 * 'OR'替换符
	 */
	public static final String SQL_OR = " OR ";

	/**
	 * 命名参数指代符
	 */
	public static final String NAMED_PARAMETER_INDICATOR = ":";

	/**
	 * 'WHERE'替换符
	 */
	public static final String SQL_WHERE = " WHERE ";

	/**
	 * 'WHERE'子句前缀：'WHERE' and 1=1.
	 */
	protected static final String SQL_WHERE_PREFIX = SQL_WHERE + "1=1 ";

	/**
	 * 持久层错误
	 */
	protected static final String PERSISTENCE_ERROR = "Persistence error occurred.";

	/**
	 * SELECT语句模板
	 */
	private static final String SELECT_ENTITY_CLAUSE = "SELECT e FROM  %1s e";

	/**
	 * COUNT语句模板
	 */
	private static final String SELECT_ENTITY_COUNT_CLAUSE = "SELECT COUNT(e) FROM  %1s e";

	/**
	 * Empty constructor.
	 */
	protected BaseJpaUtil() {
		// Empty
	}

	/**
	 *
	 * createSelectEntityClause: 创建SELECT语句 <br/>
	 *
	 * @author Luke
	 * @param domainClass
	 *            类型
	 * @return SELECT语句
	 */
	public static String createSelectEntityClause(Class<?> domainClass) {
		return String.format(SELECT_ENTITY_CLAUSE, domainClass.getSimpleName());
	}

	/**
	 *
	 * createSelectDomainCountClause: 创建COUNT语句 <br/>
	 *
	 * @author Luke
	 * @param domainClass
	 *            类型
	 * @return COUNT语句
	 */
	public static String createSelectDomainCountClause(Class<?> domainClass) {
		return String.format(SELECT_ENTITY_COUNT_CLAUSE, domainClass.getSimpleName());
	}

	/**
	 *
	 * createPositionalParametersQuery: 创建参数查询<br/>
	 *
	 * @author Luke
	 * @param <T>
	 *            类型
	 * @param queryHints
	 *            查询Hint
	 * @param entityManager
	 *            {@link EntityManager}
	 * @param returnedType
	 *            返回类型
	 * @param jpql
	 *            JPQL语句
	 * @param parameters
	 *            参数组
	 * @return 参数查询 {@link TypedQuery}
	 * @throws EntityPersistenceException
	 *             if any error occurs
	 */
	public static <T> TypedQuery<T> createParametersQuery(JpaQueryHints queryHints, EntityManager entityManager,
														  Class<T> returnedType, String jpql, List<?> parameters) throws
			EntityPersistenceException {
		try {
			final TypedQuery<T> query = entityManager.createQuery(jpql, returnedType);
			bindQueryHints(queryHints, query);
			int position = 1;
			if (!CollectionUtils.isEmpty(parameters)) {
				for (final Object parameter : parameters) {
					if (parameter instanceof Parameter) {
						final Parameter param = (Parameter) parameter;
						query.setParameter(param.getName(), param.getValue());
					} else {
						query.setParameter(position++, parameter);
					}
				}
			}
			return query;
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}

	/**
	 *
	 * createPositionalParametersQuery: 创建参数查询<br/>
	 *
	 * @author Luke
	 * @param queryHints
	 *            查询Hint
	 * @param entityManager
	 *            {@link EntityManager}
	 * @param jpql
	 *            JPQL语句
	 * @param parameters
	 *            参数组
	 * @return 参数查询 {@link TypedQuery}
	 * @throws EntityPersistenceException
	 *             if any error occurs
	 */
	public static Query createParametersQuery(JpaQueryHints queryHints, EntityManager entityManager,
														  String jpql, List<?> parameters)
			throws EntityPersistenceException {
		try {
			Query query = entityManager.createQuery(jpql);
			bindQueryHints(queryHints, query);
			int position = 1;
			if (!CollectionUtils.isEmpty(parameters)) {
				for (final Object parameter : parameters) {
					if (parameter instanceof Parameter) {
						final Parameter param = (Parameter) parameter;
						query.setParameter(param.getName(), param.getValue());
					} else {
						query.setParameter(position++, parameter);
					}
				}
			}
			return query;
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}

	/**
	 * <p>
	 * This method is used to create JPQL based on base JPQL.
	 * </p>
	 * <p>
	 * baseJpql argument also can be a template JPQL that contains {@link BaseJpaUtil#WHERE_CLAUSE_TOKEN} and
	 * {@link BaseJpaUtil#ORDER_BY_CLAUSE_TOKEN}
	 * </p>
	 *
	 * @param baseJpql
	 *            it can be a template JPQL that contains {@link BaseJpaUtil#WHERE_CLAUSE_TOKEN} and
	 *            {@link BaseJpaUtil#ORDER_BY_CLAUSE_TOKEN}
	 * @param parameters
	 *            the list of parameters
	 * @param jpaFragments
	 *            {@link WhereClause} and {@link OrderClause}
	 * @return the JPQL based on baseJpql
	 */
	protected static String buildJpql(String baseJpql, List<Parameter> parameters, JpaFragment... jpaFragments) {
		String jpql = baseJpql;
		final JpaFragmentsTuple jpaFragmentsTuple = createJpaFragmentsTuple(jpaFragments);
		final WhereClause whereClause = jpaFragmentsTuple.getWhereClause();
		final OrderClause orderByClause = jpaFragmentsTuple.getOrderByClause();

		if (!jpql.contains(WHERE_CLAUSE_TOKEN)) {
			if (whereClause != null) {
				jpql += whereClause.toSQL(parameters);
			}
		} else {
			jpql = jpql.replace(WHERE_CLAUSE_TOKEN, whereClause != null ? whereClause.toSQL(parameters) : "");
		}

		if (!jpql.contains(ORDER_BY_CLAUSE_TOKEN)) {
			if (orderByClause == null) {
				return jpql;
			}
			return jpql + orderByClause.toSQL();
		}
		return jpql.replace(ORDER_BY_CLAUSE_TOKEN, orderByClause != null ? orderByClause.toSQL() : "");
	}

	/**
	 * This method is used to create JpaFragmentsTuple.
	 *
	 * @param jpaFragments
	 *            the array of JpaFragment
	 * @return the JpaFragmentsTuple
	 */
	private static JpaFragmentsTuple createJpaFragmentsTuple(JpaFragment... jpaFragments) {
		final JpaFragmentsTuple result = new JpaFragmentsTuple();
		if (jpaFragments == null) {
			return result;
		}
		for (final JpaFragment jpaFragment : jpaFragments) {
			if (jpaFragment instanceof WhereClause) {
				result.setWhereClause((WhereClause) jpaFragment);
				continue;
			}
			if (jpaFragment instanceof OrderClause) {
				result.setOrderByClause((OrderClause) jpaFragment);
			}
		}
		return result;
	}

	/**
	 * This method is used to bind the query hints to query.
	 *
	 * @param queryHints
	 *            the query hints
	 * @param query
	 *            the query
	 */
	private static void bindQueryHints(JpaQueryHints queryHints, Query query) {
		if (queryHints == null) {
			return;
		}
		for (final JpaQueryHint queryHint : queryHints.getQueryHints()) {
			query.setHint(queryHint.getName(), queryHint.getValue());
		}
	}
}
