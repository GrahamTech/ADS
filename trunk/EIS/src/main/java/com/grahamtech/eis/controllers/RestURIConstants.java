package com.grahamtech.eis.controllers;

public class RestURIConstants {

  // BaseController - User Profile
  /*
   * No URI Method Details 1 /users GET To get the list of all the Employees in
   * the data store 2 /user/{id} GET To get the Employee object based on the id
   * 3 /user/create POST To create the Employee object and store it 4
   * /user/update POST To update the Employee object in the store 4
   * /user/delete/{id} PUT To delete the Employee object from the data storage
   * based on the id
   */
  public static final String GET_PROFILES = "/get/profiles";
  public static final String GET_PROFILE = "/get/profile/{id}";
  public static final String UPDATE_PROFILE = "/update/profile/{id}";
  public static final String DELETE_PROFILE = "/delete/profile/{id}";
  public static final String CREATE_PROFILE = "/create/profile";

  public static final String GET_USERS = "/get/users";
  public static final String GET_USER = "/get/user/{id}";
  public static final String UPDATE_USER = "/update/user/{id}";
  public static final String UPDATE_USER_EMAIL =
      "/update/user/{id}/{userEmail}";
  public static final String DELETE_USER = "/delete/user/{id}";
  public static final String CREATE_USER = "/create/user";
  public static final String CREATE_USER_BASIC =
      "/create/user/{userEmail}/{primaryRole}";
  
  public static final String GET_SYSTEM_PRODUCTS = "/get/systemProducts";
  public static final String GET_SYSTEM_PRODUCT = "/get/systemProduct/{id}";
  public static final String UPDATE_SYSTEM_PRODUCT =
      "/update/systemProduct/{id}";
  public static final String DELETE_SYSTEM_PRODUCT =
      "/delete/systemProduct/{id}";
  public static final String CREATE_SYSTEM_PRODUCT =
 "/create/systemProduct";

  public static final String GET_SYSTEM_VULNERABILITIES =
      "/get/systemVulnerabilities";
  public static final String GET_SYSTEM_VULNERABILITY =
      "/get/systemVulnerability/{id}";
  public static final String UPDATE_SYSTEM_VULNERABILITY =
      "/update/systemVulnerability/{id}";
  public static final String DELETE_SYSTEM_VULNERABILITY =
      "/delete/systemVulnerability/{id}";
  public static final String CREATE_SYSTEM_VULNERABILITY =
      "/create/systemVulnerability";

  public static final String GET_ROLES = "/get/roles";
  public static final String GET_ROLE = "/get/role/{id}";
  public static final String UPDATE_ROLE = "/update/role/{id}";
  public static final String DELETE_ROLE = "/delete/role/{id}";
  public static final String CREATE_ROLE = "/create/role";

  public static final String GET_RISK_PREFERENCES = "/get/riskPreferences";
  public static final String GET_RISK_PREFERENCE = "/get/riskPreference/{id}";
  public static final String UPDATE_RISK_PREFERENCE =
      "/update/riskPreference/{id}";
  public static final String DELETE_RISK_PREFERENCE =
      "/delete/riskPreference/{id}";
  public static final String CREATE_RISK_PREFERENCE =
 "/create/riskPreference";

  public static final String GET_PROJECT_SYSTEMS = "/get/projectSystems";
  public static final String GET_PROJECT_SYSTEM = "/get/projectSystem/{id}";
  public static final String UPDATE_PROJECT_SYSTEM =
      "/update/projectSystem/{id}";
  public static final String DELETE_PROJECT_SYSTEM =
      "/delete/projectSystem/{id}";
  public static final String CREATE_PROJECT_SYSTEM =
 "/create/projectSystem";

  public static final String GET_PROJECT_PARTNERS = "/get/projectPartners";
  public static final String GET_PROJECT_PARTNER = "/get/projectPartner/{id}";
  public static final String UPDATE_PROJECT_PARTNER =
      "/update/projectPartner/{id}";
  public static final String DELETE_PROJECT_PARTNER =
      "/delete/projectPartner/{id}";
  public static final String CREATE_PROJECT_PARTNER =
 "/create/projectPartner";

  public static final String GET_PROJECT_DETAILS = "/get/projectDetails";
  public static final String GET_PROJECT_DETAIL = "/get/projectDetail/{id}";
  public static final String UPDATE_PROJECT_DETAIL =
      "/update/projectDetail/{id}";
  public static final String DELETE_PROJECT_DETAIL =
      "/delete/projectDetail/{id}";
  public static final String CREATE_PROJECT_DETAIL =
 "/create/projectDetail";

  public static final String GET_PROJECTS = "/get/projects";
  public static final String GET_PROJECT = "/get/projects/{id}";
  public static final String UPDATE_PROJECT = "/update/projects/{id}";
  public static final String DELETE_PROJECT = "/delete/projects/{id}";
  public static final String CREATE_PROJECT = "/create/projects";

  public static final String GET_NVD_ENTRY_MESSAGES = "/get/nvdEntryMessages";
  public static final String GET_NVD_ENTRY_MESSAGE =
      "/get/nvdEntryMessage/{id}";
  public static final String UPDATE_NVD_ENTRY_MESSAGE =
      "/update/nvdEntryMessage/{id}";
  public static final String DELETE_NVD_ENTRY_MESSAGE =
      "/delete/nvdEntryMessage/{id}";
  public static final String CREATE_NVD_ENTRY_MESSAGE =
      "/create/nvdEntryMessage";

  public static final String GET_FLAGGED_ASSETS = "/get/flaggedAssets";
  public static final String GET_FLAGGED_ASSET = "/get/flaggedAsset/{id}";
  public static final String UPDATE_FLAGGED_ASSET = "/update/flaggedAsset/{id}";
  public static final String DELETE_FLAGGED_ASSET = "/delete/flaggedAsset/{id}";
  public static final String CREATE_FLAGGED_ASSET = "/create/flaggedAsset";

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
