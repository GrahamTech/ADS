package com.grahamtech.eis.pojos;

public enum HighToLowEnum {
  very_high("very_high"), high("high"), medium("medium"), low("low"), very_low(
      "very_low"), unknown("unknown");

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
