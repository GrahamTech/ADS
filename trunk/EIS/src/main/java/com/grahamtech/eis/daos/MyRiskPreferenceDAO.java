package com.grahamtech.eis.daos;

import org.hibernate.SessionFactory;

import com.grahamtech.eis.pojos.RiskPreference;

public class MyRiskPreferenceDAO extends AbstractDAO<RiskPreference> {

  public MyRiskPreferenceDAO(SessionFactory sessionFactory) {
    super("RiskPreference", "risk_preference_id", RiskPreference.class);
    setSessionFactory(sessionFactory);
  }
}

