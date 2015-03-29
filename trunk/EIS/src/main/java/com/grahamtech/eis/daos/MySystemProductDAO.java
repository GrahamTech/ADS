package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;

import com.grahamtech.eis.pojos.SystemProduct;

public class MySystemProductDAO extends
 AbstractDAO<SystemProduct> {

  public MySystemProductDAO(SessionFactory sessionFactory) {
    super("SystemProduct", "product_id", SystemProduct.class);
    setSessionFactory(sessionFactory);
  }
}

