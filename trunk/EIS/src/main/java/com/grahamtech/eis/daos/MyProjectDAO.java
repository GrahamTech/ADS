package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;

import com.grahamtech.eis.pojos.Project;

public class MyProjectDAO extends AbstractDAO<Project> {

  public MyProjectDAO(SessionFactory sessionFactory) {
    super("Project", "project_id", Project.class);
    setSessionFactory(sessionFactory);
  }
}

