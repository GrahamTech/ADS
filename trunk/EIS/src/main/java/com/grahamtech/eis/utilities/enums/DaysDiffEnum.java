package com.grahamtech.eis.utilities.enums;

public enum DaysDiffEnum {
  VERY_RECENT("60"), VERY_RECENT_SCORE("1"), RECENT("80"), RECENT_SCORE("2"), LESS_RECENT(
      "100"), LESS_RECENT_SCORE("3"), LONG_AGO("120"), LONG_AGO_SCORE("4"), VERY_LONG_AGO(
      "140"), VERY_LONG_AGO_SCORE("5");

  private String myEnum;

  DaysDiffEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static DaysDiffEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (DaysDiffEnum anEnum : DaysDiffEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }

}
