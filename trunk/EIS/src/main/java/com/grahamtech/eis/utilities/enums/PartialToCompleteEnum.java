package com.grahamtech.eis.utilities.enums;

public enum PartialToCompleteEnum {
  UNKNOWN("UNKNOWN"), NONE("NONE"), PARTIAL("PARTIAL"), COMPLETE("COMPLETE");

  private String myEnum;

  PartialToCompleteEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static PartialToCompleteEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (PartialToCompleteEnum anEnum : PartialToCompleteEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}
