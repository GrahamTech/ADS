package com.grahamtech.eis.utilities.enums;

public enum InstanceCountEnum {
  UNKNOWN("UNKNOWN"), NONE("NONE"), SINGLE_INSTANCE("SINGLE_INSTANCE"), MULTIPLE_INSTANCE(
      "MULTIPLE_INSTANCE");

  private String myEnum;

  InstanceCountEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static InstanceCountEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (InstanceCountEnum anEnum : InstanceCountEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}
