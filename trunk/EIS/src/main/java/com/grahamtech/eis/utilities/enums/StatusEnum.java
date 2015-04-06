package com.grahamtech.eis.utilities.enums;

public enum StatusEnum {
  ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), PENDING("PENDING"), PRELIMINARY_LOGIN(
      "PRELIMINARY_LOGIN");

  private String myEnum;

  StatusEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static StatusEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (StatusEnum anEnum : StatusEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}
