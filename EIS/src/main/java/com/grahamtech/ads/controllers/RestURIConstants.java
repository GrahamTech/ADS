package com.grahamtech.ads.controllers;

public class RestURIConstants {

    public static final String CONTROLLER_PREFIX = "/gt";
    public static final String GET_DRUG_EVENTS_CALL_WITH_API_KEY = "/get/drug/events/apikey/{rowLimit}";
    public static final String GET_DRUG_EVENTS_AND_STORE_CALL_WITH_API_KEY = "/get/drug/events/and/store/apikey/{rowLimit}";
  public static final String ADVERSE_DRUG_EVENT_REPORTS_EXTERNAL_URL =
      "https://api.fda.gov/drug/event.json";
    public static final String ADVERSE_DRUG_EVENT_REPORTS_API_KEY_HEADER = "api_key";
  public static final String ADVERSE_DRUG_EVENT_REPORTS_API_KEY =
      "YQG9X0JzsVSlL7WE8vx9KNiXM3jpyZz1jbhqpTGu";

    public static final String CREATE_DB_EVENT = "/create/db/event";
    public static final String READ_DB_EVENTS = "/read/db/events";
    public static final String READ_DB_EVENT_BY_ID = "/read/db/event/{id}";
    public static final String UPDATE_DB_EVENT_BY_ID = "/update/db/event/{id}";
    public static final String UPDATE_DB_EVENT_BY_ID_AND_PARAMS = "/update/db/event/params/{id}";
    //public static final String DELETE_DB_EVENT_BY_ID = "/delete/db/event/{id}";
    public static final String DELETE_DB_EVENT_BY_ID = "/delete/db/event";

    public static final String INDEX = "/index";
}
