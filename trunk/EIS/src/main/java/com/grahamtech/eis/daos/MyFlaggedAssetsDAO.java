package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;

import com.grahamtech.eis.pojos.FlaggedAsset;

public class MyFlaggedAssetsDAO extends AbstractDAO<FlaggedAsset> {

  public MyFlaggedAssetsDAO(SessionFactory sessionFactory) {
    super("FlaggedAsset", "flagged_id", FlaggedAsset.class);
    setSessionFactory(sessionFactory);
  }
}

