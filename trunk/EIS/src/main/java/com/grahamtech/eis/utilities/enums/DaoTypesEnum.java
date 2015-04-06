package com.grahamtech.eis.utilities.enums;

public enum DaoTypesEnum {
  myRolesDAO("myRolesDAO"), myUserProfileDAO("myUserProfileDAO");

  private String myEnum;

  DaoTypesEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static DaoTypesEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (DaoTypesEnum anEnum : DaoTypesEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}
