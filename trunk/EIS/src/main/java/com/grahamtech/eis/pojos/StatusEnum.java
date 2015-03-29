package com.grahamtech.eis.pojos;

public enum StatusEnum {
  active("active"), inactive("inactive"), pending("pending"), preliminary_login(
      "preliminary_login");

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
