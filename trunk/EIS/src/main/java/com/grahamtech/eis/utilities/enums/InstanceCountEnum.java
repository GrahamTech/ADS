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

  public static Integer getIntCodeForEnum(InstanceCountEnum theEnum) {
    Integer code;

    switch (theEnum) {
    case UNKNOWN:
      code = new Integer(4);
      break;
    case MULTIPLE_INSTANCE:
      code = new Integer(3);
      break;
    case SINGLE_INSTANCE:
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
