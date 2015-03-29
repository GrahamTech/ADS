package com.grahamtech.eis.pojos;

public enum NotificationFrequencyEnum {
  optout("opt_out"), daily("daily"), weekly("weekly"), biweekly("bi_weekly"), monthly(
      "monthly"), quarterly("quarterly"), yearly("yearly");

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
