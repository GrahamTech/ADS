package com.grahamtech.eis.utilities.enums;

public enum RolesEnum {
  ADMINISTRATOR("ADMINISTRATOR"), EXECUTIVE("EXECUTIVE"), MANAGER("MANAGER"), READ_ONLY(
      "READ_ONLY"), RESTRICTED("RESTRICTED"), INTERNAL_SYSTEM("INTERNAL_SYSTEM"), EXTERNAL_SYSTEM(
      "EXTERNAL_SYSTEM");

  private String myEnum;

  RolesEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static RolesEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (RolesEnum anEnum : RolesEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}
