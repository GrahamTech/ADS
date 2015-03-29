package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;

import com.grahamtech.eis.pojos.Role;

public class MyRolesDAO extends AbstractDAO<Role> {

  public MyRolesDAO(SessionFactory sessionFactory) {
    super("Role", "role_id", Role.class);
    setSessionFactory(sessionFactory);
  }
}

