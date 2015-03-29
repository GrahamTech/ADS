package com.grahamtech.eis.pojos;

public enum RolesEnum {
  administrator("administrator"), executive("executive"), manager("manager"), readOnly(
      "read_only"), restricted("restricted"), internal_system("internal_system"), external_system(
      "external_system");

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
