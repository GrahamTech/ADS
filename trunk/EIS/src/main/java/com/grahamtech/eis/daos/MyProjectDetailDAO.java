package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;

import com.grahamtech.eis.pojos.ProjectDetail;

public class MyProjectDetailDAO extends AbstractDAO<ProjectDetail> {

  public MyProjectDetailDAO(SessionFactory sessionFactory) {
    super("ProjectDetail", "project_detail_id", ProjectDetail.class);
    setSessionFactory(sessionFactory);
  }
}

