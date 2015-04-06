package com.grahamtech.eis.utilities.enums;

public enum NotificationFrequencyEnum {
  OPT_OUT("OPT_OUT"), DAILY("DAILY"), WEEKLY("WEEKLY"), BI_WEEKLY("BI_WEEKLY"), MONTHLY(
      "MONTHLY"), QUARTERLY("QUARTERLY"), YEARLY("YEARLY");

  private String myEnum;

  NotificationFrequencyEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static NotificationFrequencyEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (NotificationFrequencyEnum anEnum : NotificationFrequencyEnum
          .values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}
