package com.grahamtech.eis.utilities.enums;

public enum AccessVectorEnum {
  UNKNOWN("UNKNOWN"), LOCAL("LOCAL"), NETWORK("NETWORK"), ADJACENT_NETWORK(
      "ADJACENT_NETWORK");

  private String myEnum;

  AccessVectorEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static AccessVectorEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (AccessVectorEnum anEnum : AccessVectorEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}
