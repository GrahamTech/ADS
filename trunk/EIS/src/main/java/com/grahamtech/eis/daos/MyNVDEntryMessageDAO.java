package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;

import com.grahamtech.eis.pojos.NVDEntryMessage;

public class MyNVDEntryMessageDAO extends AbstractDAO<NVDEntryMessage> {

  public MyNVDEntryMessageDAO(SessionFactory sessionFactory) {
    super("NVDEntryMessage", "entry_message_id", NVDEntryMessage.class);
    setSessionFactory(sessionFactory);
  }
}

