package com.grahamtech.ads.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.grahamtech.ads.pojos.AdverseDrugEventResultFlattened;

public class MyAdverseDrugEventDAO extends
	AbstractDAO<AdverseDrugEventResultFlattened> {

  public MyAdverseDrugEventDAO(SessionFactory sessionFactory) {
	super("AdverseDrugEventResultFlattened", "event_id",
		AdverseDrugEventResultFlattened.class);
    setSessionFactory(sessionFactory);
  }

    public List<AdverseDrugEventResultFlattened> findEventBySafetyReportId(
String id) {
	log.debug("About to find instance of " + entityClass
		+ " with Safety Report ID: "
		+ id);
	try {
	    return (List<AdverseDrugEventResultFlattened>) getHibernateTemplate()
		    .findByCriteria(
			    DetachedCriteria.forClass(
				    AdverseDrugEventResultFlattened.class).add(
				    Restrictions.eq("safetyreportid", id)));
	} catch (HibernateException he) {
	    log.error("Failed to find instance of " + entityClass
		    + " with safety report id: " + id, he);
	    throw he;
	} catch (final RuntimeException re) {
	    log.error("Failed to find instance of " + entityClass
		    + " with safety report id: " + id, re);
	    throw re;
	}
    }
}

