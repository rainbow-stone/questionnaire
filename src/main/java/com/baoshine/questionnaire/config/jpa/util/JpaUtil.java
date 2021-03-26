package com.baoshine.questionnaire.config.jpa.util;

import com.baoshine.common.entity.BaseEntity;
import com.baoshine.questionnaire.config.jpa.exception.EntityPersistenceException;
import com.baoshine.questionnaire.config.jpa.exception.NoEntityFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA utility.
 */
public final class JpaUtil extends JpaSearchQueryUtil {

	/**
	 * Private empty constructor.
	 */
	private JpaUtil() {
		// Hide constructor.
	}

	/**
	 * Flush the entity manager.
	 *
	 * @param entityManager
	 *            the entity manager
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static void flush(EntityManager entityManager) throws EntityPersistenceException {
		try {
			entityManager.flush();
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}

	/**
	 * Clear the entity manager.
	 *
	 * @param entityManager
	 *            the entity manager
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static void clear(EntityManager entityManager) throws EntityPersistenceException {
		try {
			entityManager.clear();
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}

	/**
	 * This method retrieves entity by id. Null is returned if none is found.
	 *
	 * @param <T>
	 *            the retrieved entity type
	 * @param entityManager
	 *            the entity manager
	 * @param entityType
	 *            the entity type
	 * @param id
	 *            the entity id
	 * @return the entity, or null if none is found
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static <T extends BaseEntity> T get(EntityManager entityManager, Class<T> entityType, int id)
			throws EntityPersistenceException {
		try {
			return entityManager.find(entityType, id);
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}

	/**
	 * This method merges entity.
	 *
	 * @param <T>
	 *            the merged entity type
	 * @param entityManager
	 *            the entity manager
	 * @param entity
	 *            the entity to merge
	 * @return the merged entity
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static <T extends BaseEntity> T merge(EntityManager entityManager, T entity)
			throws EntityPersistenceException {
		try {
			return entityManager.merge(entity);
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}

	/**
	 * This method creates the entity, and return created entity.
	 *
	 * @param <T>
	 *            created entity type
	 * @param entityManager
	 *            the entity manager
	 * @param entity
	 *            the entity to create
	 * @return the created entity
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static <T extends BaseEntity> T create(EntityManager entityManager, T entity)
			throws EntityPersistenceException {
		try {
			entityManager.persist(entity);
			return entity;
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}

	/**
	 * This method creates the entities, and return created entities.
	 *
	 * @param <T>
	 *            created entity type
	 * @param entityManager
	 *            the entity manager
	 * @param entities
	 *            the entities to create
	 * @return the created entities
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static <T extends BaseEntity> List<T> createList(EntityManager entityManager, List<T> entities)
			throws EntityPersistenceException {
		final List<T> result = new ArrayList<>();
		for (final T entity : entities) {
			result.add(create(entityManager, entity));
		}
		return result;
	}

	/**
	 * This method updates the entity.
	 *
	 * @param entityManager
	 *            the entity manager
	 * @param entity
	 *            the entity to update
	 * @throws EntityPersistenceException
	 *             if any error occurs
	 */
	public static void update(EntityManager entityManager, Object entity) throws EntityPersistenceException {
		try {
			entityManager.merge(entity);
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}

	/**
	 * This method updates the entities.
	 *
	 * @param entityManager
	 *            the entity manager
	 * @param entities
	 *            the entities to update
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static void updateList(EntityManager entityManager, List<?> entities) throws EntityPersistenceException {
		for (final Object entity : entities) {
			update(entityManager, entity);
		}
	}

	/**
	 * This method saves entity.
	 *
	 * @param <T>
	 *            the saved entity type
	 * @param entityManager
	 *            the entity manager
	 * @param entity
	 *            the entity save
	 * @return the saved entity
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static <T extends BaseEntity> T save(EntityManager entityManager, T entity)
			throws EntityPersistenceException {
		if (entity.getId() == null || entity.getId().equals(0L)) {
			return create(entityManager, entity);
		}
		return merge(entityManager, entity);
	}

	/**
	 * This method saves entities.
	 *
	 * @param <T>
	 *            the saved entities type
	 * @param entityManager
	 *            the entity manager
	 * @param entities
	 *            the entities save
	 * @return the saved entities
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static <T extends BaseEntity> List<T> saveList(EntityManager entityManager, List<T> entities)
			throws EntityPersistenceException {
		final List<T> result = new ArrayList<>();
		for (final T entity : entities) {
			result.add(save(entityManager, entity));
		}
		return result;
	}

	/**
	 * This method removes entity by id.
	 *
	 * @param <T>
	 *            the removed entity type
	 * @param entityManager
	 *            the entity manager
	 * @param entityType
	 *            the removed entity type
	 * @param id
	 *            the entity id
	 * @throws NoEntityFoundException
	 *             if entity with the given id is not found
	 * @throws EntityPersistenceException
	 *             if there is any other error
	 */
	public static <T extends BaseEntity> void remove(EntityManager entityManager, Class<?> entityType, int id)
			throws EntityPersistenceException {
		try {
			final Object entity = entityManager.find(entityType, id);
			if (entity == null) {
				throw new NoEntityFoundException(
						entityType.getSimpleName() + " entity with id " + id + " is not found.");
			}
			entityManager.remove(entity);
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}

	/**
	 * This method removes entity instance.
	 *
	 * @param <T>
	 *            the removed entity type
	 * @param entityManager
	 *            the entity manager
	 * @param entity
	 *            the entity to remove
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static <T extends BaseEntity> void remove(EntityManager entityManager, T entity)
			throws EntityPersistenceException {
		try {
			entityManager.remove(entity);
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}

	/**
	 * This method removes entities by ids.
	 *
	 * @param entityManager
	 *            the entity manager
	 * @param entityType
	 *            the removed entity type
	 * @param ids
	 *            the entity ids
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static void removeList(EntityManager entityManager, Class<?> entityType, List<Integer> ids)
			throws EntityPersistenceException {
		for (final int id : ids) {
			remove(entityManager, entityType, id);
		}
	}

	/**
	 * This method removes entities.
	 *
	 * @param <T>
	 *            the removed entity type
	 * @param entityManager
	 *            the entity manager
	 * @param entities
	 *            the entities to remove
	 * @throws EntityPersistenceException
	 *             if there is any error
	 */
	public static <T extends BaseEntity> void removeList(EntityManager entityManager, List<T> entities)
			throws EntityPersistenceException {
		for (final T entity : entities) {
			remove(entityManager, entity);
		}
	}

	/**
	 * Removes all the entities from persistence.
	 *
	 * @param entityType
	 *            the type of the entity to be deleted
	 * @param entityManager
	 *            the entity manager
	 * @throws EntityPersistenceException
	 *             if any persistence error occurs
	 */
	public static void removeAll(EntityManager entityManager, Class<?> entityType) throws EntityPersistenceException {
		try {
			final String entityTypeName = entityType.getSimpleName();

			final Query query = entityManager.createQuery("DELETE FROM " + entityTypeName);

			query.executeUpdate();
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}

	/**
	 * Deletes the entity with the given IDs from persistence.
	 *
	 * @param entityIds
	 *            the IDs of the entities to be deleted
	 * @param entityType
	 *            the type of the entity to be deleted
	 * @param entityManager
	 *            the entity manager
	 * @throws EntityPersistenceException
	 *             if any persistence error occurs
	 */
	public static int delete(EntityManager entityManager, Class<?> entityType, Long... entityIds)
			throws EntityPersistenceException {
		try {
			final String entityTypeName = entityType.getSimpleName();
			final StringBuilder jpql = new StringBuilder("DELETE FROM ");
			jpql.append(entityTypeName);
			jpql.append(" e WHERE e.id = ?1 ");

			if (entityIds.length > 1) {
				for (int i = 1; i < entityIds.length; i++) {
					jpql.append(" OR e.id = ?");
					jpql.append((i + 1));
				}
			}
			final Query query = entityManager.createQuery(jpql.toString());

			for (int i = 0; i < entityIds.length; i++) {
				query.setParameter(i + 1, entityIds[i]);
			}
			return query.executeUpdate();
		} catch (final PersistenceException e) {
			throw new EntityPersistenceException(PERSISTENCE_ERROR, e);
		}
	}
}
