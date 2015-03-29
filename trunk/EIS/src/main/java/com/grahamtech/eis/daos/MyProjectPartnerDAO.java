package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;

import com.grahamtech.eis.pojos.ProjectPartner;

public class MyProjectPartnerDAO extends AbstractDAO<ProjectPartner> {

  public MyProjectPartnerDAO(SessionFactory sessionFactory) {
    super("ProjectPartner", "project_partner_id", ProjectPartner.class);
    setSessionFactory(sessionFactory);
  }
}

