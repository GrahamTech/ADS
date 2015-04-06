package com.grahamtech.eis.utilities.enums;

public enum StrengthRatingEnum {
  GAP("GAP"), VULNERABILITY("VULNERABILITY"), STRENGTH("STRENGTH");

  private String myEnum;

  StrengthRatingEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static StrengthRatingEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (StrengthRatingEnum anEnum : StrengthRatingEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}
