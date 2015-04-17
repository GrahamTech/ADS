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

  public static Integer getIntCodeForEnum(HighToLowEnum theEnum) {
    Integer code;

    switch (theEnum) {
    case HIGH:
      code = new Integer(4);
      break;
    case MEDIUM:
      code = new Integer(3);
      break;
    case LOW:
      code = new Integer(2);
      break;
    case UNKNOWN:
      code = new Integer(0);
      break;
    default: // UNKNOWN
      code = new Integer(0);
      break;
    }

    return code;
  }
}
