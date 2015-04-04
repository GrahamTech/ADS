package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;

import com.grahamtech.eis.pojos.NVDEntryVulnerableSoftware;

public class MyNVDEntryVulnerableSoftwareDAO extends
    AbstractDAO<NVDEntryVulnerableSoftware> {

  public MyNVDEntryVulnerableSoftwareDAO(SessionFactory sessionFactory) {
    super("NVDVEntryVulnerableSoftware", "vulnerable_software_id",
        NVDEntryVulnerableSoftware.class);
    setSessionFactory(sessionFactory);
  }
}

