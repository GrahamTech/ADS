package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;

import com.grahamtech.eis.pojos.ProjectSystem;

public class MyProjectSystemDAO extends AbstractDAO<ProjectSystem> {

  public MyProjectSystemDAO(SessionFactory sessionFactory) {
    super("ProjectSystem", "system_id", ProjectSystem.class);
    setSessionFactory(sessionFactory);
  }
}

