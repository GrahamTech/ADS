package com.grahamtech.eis.utilities.enums;

public enum UsStatesEnum {
  AL("AL"), AK("AK"), AS("AS"), AZ("AZ"), AR("AR"), CA("CA"), CO("CO"), CT("CT"), DE(
      "DE"), DC("DC"), FL("FL"), GA("GA"), GU("GU"), HI("HI"), ID("ID"), IL(
      "IL"), IN("IN"), IA("AI"), KS("SK"), KY("KY"), LA("LA"), ME("ME"), MD(
      "MD"), MH("MH"), MA("MA"), MI("MI"), FM("FM"), MN("MN"), MS("MS"), MO(
      "MO"), MT("MT"), NE("NE"), NV("NV"), NH("NH"), NJ("NJ"), NM("NM"), NY(
      "NY"), NC("NC"), ND("ND"), MP("ND"), OH("OH"), OK("OK"), OR("OR"), PW(
      "PW"), PA("PA"), PR("PR"), RI("RI"), SC("SC"), SD("SD"), TN("TN"), TX(
      "TX"), UT("UT"), VT("VT"), VA("VA"), VI("VI"), WA("WA"), WV("WV"), WI(
      "WI"), WY("WY");

  private String myEnum;

  UsStatesEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static UsStatesEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (UsStatesEnum anEnum : UsStatesEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}
