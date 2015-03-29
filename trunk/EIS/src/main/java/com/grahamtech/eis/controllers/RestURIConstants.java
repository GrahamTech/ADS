package com.grahamtech.eis.controllers;

/* No  URI                Method   Details
 1   /users          GET     To get the list of all the Employees in the data store
 2   /user/{id}      GET     To get the Employee object based on the id
 3   /user/create    POST    To create the Employee object and store it
 4   /user/delete/{id} PUT   To delete the Employee object from the data storage based on the id
 */
public class RestURIConstants {

  // User Profile
  public static final String GET_ALL_EMP = "/users";
  public static final String GET_EMP = "/user/{id}";
  public static final String CREATE_EMP = "/user/create";
  public static final String DELETE_EMP = "/user/delete/{id}";
}
