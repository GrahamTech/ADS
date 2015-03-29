package com.grahamtech.eis.pojos;

public enum CountriesEnum {
  USA("USA");

  private String myEnum;

  CountriesEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static CountriesEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (CountriesEnum anEnum : CountriesEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}
