package com.grahamtech.eis.utilities.enums;

public enum VeryHighToVeryLowEnum {
  UNKNOWN("UNKNOWN"), VERY_HIGH("VERY_HIGH"), HIGH("HIGH"), MEDIUM("MEDIUM"), LOW(
      "LOW"), VERY_LOW("VERY_LOW");

  private String myEnum;

  VeryHighToVeryLowEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static VeryHighToVeryLowEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (VeryHighToVeryLowEnum anEnum : VeryHighToVeryLowEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
  
  public static Integer getIntCodeForEnum(VeryHighToVeryLowEnum theEnum) {
    Integer code;

    switch (theEnum) {
    case VERY_HIGH:
      code = new Integer(5);
      break;
    case HIGH:
      code = new Integer(4);
      break;
    case MEDIUM:
      code = new Integer(3);
      break;
    case LOW:
      code = new Integer(2);
      break;
    case VERY_LOW:
      code = new Integer(1);
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
