package com.grahamtech.eis.utilities.enums;

public enum HighToLowEnum {
  UNKNOWN("UNKNOWN"), HIGH("HIGH"), MEDIUM("MEDIUM"), LOW("LOW");

  private String myEnum;

  HighToLowEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static HighToLowEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (HighToLowEnum anEnum : HighToLowEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}