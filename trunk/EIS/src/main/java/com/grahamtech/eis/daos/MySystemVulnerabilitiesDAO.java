package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;
import com.grahamtech.eis.pojos.SystemVulnerability;

public class MySystemVulnerabilitiesDAO extends
    AbstractDAO<SystemVulnerability> {

  public MySystemVulnerabilitiesDAO(SessionFactory sessionFactory) {
    super("SystemVulnerability", "vulnerability_id", SystemVulnerability.class);
    setSessionFactory(sessionFactory);
  }
}

