package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;

import com.grahamtech.eis.pojos.AdversDrugEventResultFlattened;

public class MyAdverseDrugEventDAO extends
	AbstractDAO<AdversDrugEventResultFlattened> {

  public MyAdverseDrugEventDAO(SessionFactory sessionFactory) {
	super("AdversDrugEventResultFlattened", "event_id",
		AdversDrugEventResultFlattened.class);
    setSessionFactory(sessionFactory);
  }
}

