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

  public static Integer getIntCodeForEnum(PartialToCompleteEnum theEnum) {
    Integer code;

    switch (theEnum) {
    case UNKNOWN:
      code = new Integer(4);
      break;
    case COMPLETE:
      code = new Integer(3);
      break;
    case PARTIAL:
      code = new Integer(2);
      break;
    case NONE:
      code = new Integer(1);
      break;
    default:
      code = new Integer(0);
      break;
    }

    return code;
  }
}
