package com.grahamtech.ads.controllers;

public class RestURIConstants {

    public static final String CONTROLLER_PREFIX = "/gt";
    public static final String GET_DRUG_EVENTS_CALL_WITH_API_KEY = "/get/drug/events/apikey/{rowLimit}";
    public static final String GET_DRUG_EVENTS_CALL_WITH_API_KEY_ROWLIMIT_PARAM = "/get/drug/events/apikey";

    public static final String GET_DRUG_EVENTS_AND_STORE_CALL_WITH_API_KEY = "/get/drug/events/and/store/apikey/{rowLimit}";
    public static final String GET_DRUG_EVENTS_AND_STORE_CALL_WITH_API_KEY_ROWLIMIT_PARAM = "/get/drug/events/and/store/apikey";

  public static final String ADVERSE_DRUG_EVENT_REPORTS_EXTERNAL_URL =
      "https://api.fda.gov/drug/event.json";
    public static final String ADVERSE_DRUG_EVENT_REPORTS_API_KEY_HEADER = "api_key";
  public static final String ADVERSE_DRUG_EVENT_REPORTS_API_KEY =
      "YQG9X0JzsVSlL7WE8vx9KNiXM3jpyZz1jbhqpTGu";

    public static final String READ_DB_EVENTS = "/read/db/events"; // GET
    public static final String READ_DB_EVENT_BY_ID = "/read/db/event"; // GET
    public static final String READ_DB_EVENT_BY_ID_BY_PARAM = "/read/db/event"; // GET

    public static final String CREATE_DB_EVENT = "/create/db/event"; // POST
    public static final String UPDATE_DB_EVENT = "/update/db/event"; // POST
    public static final String DELETE_DB_EVENT_BY_ID = "/delete/db/event";
    public static final String DELETE_DB_EVENT_BY_ID_PARAM = "/delete/db/event"; // GET
										// or
										// PUT

    public static final String STATUS_MESSAGE_SUCCESS = "Success";
    public static final String STATUS_MESSAGE_FAILURE = "Faiure";
    public static final String STATUS_MESSAGE_DETAILS = "Application Error: Please retry your request. If the problem persists, please contact your system administrator and reference error code: ";
    public static final String INDEX = "/index"; // TODO: remove this once
						 // Angular front-end is up.
}
