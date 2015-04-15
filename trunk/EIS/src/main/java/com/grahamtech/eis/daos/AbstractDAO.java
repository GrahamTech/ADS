package com.grahamtech.eis.daos;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class AbstractDAO<T> extends HibernateDaoSupport implements IDAO<T> {
  protected final Log log = LogFactory.getLog(getClass());

  protected Class<T> entityClass;
  protected String tableName;
  protected String orderFieldName;

  public AbstractDAO(final String tableName, final String orderFieldName,
      final Class<T> entityClass) {
    this.tableName = tableName;
    this.orderFieldName = orderFieldName;
    this.entityClass = entityClass;
  }

  /** {@inheritDoc} */
  @Transactional
  public void save(final T transientInstance)
      throws ConstraintViolationException, RuntimeException {
    log.debug("About to save object of type " + entityClass);
    try {
      getHibernateTemplate().save(transientInstance);
      log.debug("Saved");
    } catch (ConstraintViolationException e) {
      throw e;
    } catch (final RuntimeException re) {
      log.error("Failed to save object of type " + entityClass, re);
      throw re;
    }
  }

  /** {@inheritDoc} */
  @Transactional
  public void delete(final T persistentInstance) throws RuntimeException {
    log.debug("About to delete instance of " + entityClass);
    try {
      getHibernateTemplate().delete(persistentInstance);
      log.debug("Delete successful");
    } catch (final RuntimeException re) {
      log.error("Failed to delete instance of " + entityClass, re);
      throw re;
    }
  }

  /** {@inheritDoc} */
  public T findById(final Long id) {
    log.debug("About to find instance of " + entityClass + " with id: " + id);
    try {
      final T instance = (T) getHibernateTemplate().get(this.entityClass, id);
      return instance;
    } catch (final RuntimeException re) {
      log.error(
          "Failed to find instance of " + entityClass + " with id: " + id, re);
      throw re;
    }
  }

  @SuppressWarnings("unchecked")
  /** {@inheritDoc} */
  public List<T> findAll() {
    log.debug("About to find all instances of " + entityClass);
    try {
      String queryString = "FROM " + tableName;
      // queryString += " ORDER BY " + orderFieldName;
      return (List<T>) getHibernateTemplate().find(queryString);
    } catch (final RuntimeException re) {
      log.error("Failed to find all instances of " + entityClass, re);
      throw re;
    }
  }

  @SuppressWarnings("unchecked")
  /** {@inheritDoc} */
  public int getRowCount() {
    log.debug("About to get a row count of table " + tableName);
    try {
      // final Criteria criteria =
      // this.getSession(false).createCriteria(this.entityClass);
      final Criteria criteria =
          this.getSessionFactory().openSession()
              .createCriteria(this.entityClass);

      criteria.setProjection(Projections.rowCount());
      final List<Integer> results = criteria.list();
      return results.get(0);
    } catch (final RuntimeException re) {
      log.error("Failed to get row count of table " + tableName, re);
      throw re;
    }
  }

  /** {@inheritDoc} */
  @Transactional
  public T merge(final T detachedInstance) throws RuntimeException {
    log.debug("Merging instance of type " + entityClass);
    try {
      final T result = (T) getHibernateTemplate().merge(detachedInstance);
      log.debug("Merged succesfully");
      return result;
    } catch (final RuntimeException re) {
      log.error("Merge of instance type " + entityClass + " failed", re);
      throw re;
    }
  }

  /** {@inheritDoc} */
  @Transactional
  public void deleteAll() throws RuntimeException {
    log.debug("About to delete all records from the " + tableName);
    try {
      // flush all changes to the database to avoid issues when deleting.
      this.getHibernateTemplate().flush();
      final int rows =
          this.getHibernateTemplate().bulkUpdate("delete from " + tableName);
      log.debug("successfully " + rows + " deleted");
      this.getHibernateTemplate().clear();
    } catch (final RuntimeException re) {
      log.error("Failed to delete all rows from " + tableName, re);
      throw re;
    }
  }

  /** {@inheritDoc} */
  public void flushAndClear() {
    try {
      flush();
      this.getHibernateTemplate().clear();
    } catch (final RuntimeException re) {
      log.error("Failed to flush and clear the cache", re);
      throw re;
    }
  }

  /** {@inheritDoc} */
  public void flush() {
    try {
      this.getHibernateTemplate().flush();
    } catch (final RuntimeException re) {
      log.error("Failed to flush the cache", re);
      throw re;
    }
  }

  /** {@inheritDoc} */
  @Transactional
  public T update(final T instance) {
    return merge(instance);
  }

  /**
   * Limits the number of results given the start position and the number of
   * results required.
   * 
   * @param criteria criteria object to apply limit to
   * @param start the start position of the limit, E.g. row 15 = 15
   * @param fetchSize the amount to fetch, E.g. 20 rows.
   */
  protected void limitCriteria(final Criteria criteria, final int start,
      final int fetchSize) {
    criteria.setFirstResult(start);
    criteria.setFetchSize(fetchSize);
    criteria.setMaxResults(fetchSize);
  }
}
