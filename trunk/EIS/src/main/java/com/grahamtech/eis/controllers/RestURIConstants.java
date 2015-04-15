package com.grahamtech.eis.controllers;

public class RestURIConstants {

  // BaseController - User Profile
  /* No  URI                Method   Details
  1   /users          GET     To get the list of all the Employees in the data store
  2   /user/{id}      GET     To get the Employee object based on the id
  3   /user/create    POST    To create the Employee object and store it
  4   /user/delete/{id} PUT   To delete the Employee object from the data storage based on the id
  */
  public static final String GET_PROFILES = "/get/profiles";
  public static final String GET_PROFILE = "/get/profile/{id}";

  public static final String GET_USERS = "/get/users";
  public static final String CREATE_USER =
      "/create/user/{userEmail}/{primaryRole}";
  public static final String DELETE_USER = "/delete/user/{id}";
  public static final String UPDATE_USER_EMAIL =
      "/update/user/{id}/{userEmail}";
  
  public static final String GET_SYSTEM_PRODUCTS = "/get/systemProducts";
  public static final String GET_SYSTEM_VULNERABILITIES =
      "/get/systemVulnerabilities";
  public static final String GET_ROLES = "/get/roles";
  public static final String GET_RISK_PREFERENCES = "/get/riskPreferences";
  public static final String GET_PROJECT_SYSTEMS = "/get/projectSystems";
  public static final String GET_PROJECT_PARTNERS = "/get/projectPartners";
  public static final String GET_PROJECT_DETAILS = "/get/projectDetails";
  public static final String GET_PROJECTS = "/get/projects";
  public static final String GET_NVD_ENTRY_MESSAGES = "/get/nvdEntryMessages";
  public static final String GET_FLAGGED_ASSETS = "/get/flaggedAssets";

  public static final String LOCALIZATION = "/localization";
  public static final String OVERVIEW = "/overview";
  
  public static final String RSS_NVD_20_ALL = "/rss/nvd/all";
  public static final String RSS_NVD_CVE_20_2015_URL =
      "https://nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-2015.xml";
  public static final String RSS_NVD_CVE_20_2014_URL =
      "https://nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-2014.xml";
  public static final String RSS_NVD_CVE_20_2013_URL =
      "https://nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-2013.xml";

  public static final String RSS_NVD_20_MODIFIED = "/rss/nvd/modified";
  public static final String RSS_NVD_CVE_20_MODIFIED_URL =
      "http://nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-Modified.xml";

  public static final String RSS_NVD_20_RECENT = "/rss/nvd/recent";
  public static final String RSS_NVD_CVE_20_RECENT_URL =
      "https://nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-Recent.xml";

  // CallableController
  public static final String ASYNC_CALLABLE = "/async/callable";
  public static final String ASYNC_VIEW = "/view";
  public static final String ASYNC_EXCEPTION = "/exception";
  public static final String ASYNC_CUSTOM_TIMEOUT_HANDLING =
 "/custom-timeout-handling";
  public static final String ASYNC_RESPONSE_BODY =
 "/response-body";

  // FileUpload Controller
  public static final String FILE_UPLOAD = "/fileupload";
  
}
