package com.grahamtech.eis.utilities.enums;

public enum AccessVectorEnum {
  UNKNOWN("UNKNOWN"), LOCAL("LOCAL"), NETWORK("NETWORK"), ADJACENT_NETWORK(
      "ADJACENT_NETWORK");

  private String myEnum;

  AccessVectorEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static AccessVectorEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (AccessVectorEnum anEnum : AccessVectorEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }

  public Integer getIntCodeForEnum() {
    Integer code;

    switch (this) {
    case UNKNOWN:
      code = new Integer(4);
      break;
    case ADJACENT_NETWORK:
      code = new Integer(3);
      break;
    case NETWORK:
      code = new Integer(2);
      break;
    case LOCAL:
      code = new Integer(1);
      break;
    default:
      code = new Integer(0);
      break;
    }

    return code;
  }
}
