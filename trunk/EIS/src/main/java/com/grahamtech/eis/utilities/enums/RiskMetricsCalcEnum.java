package com.grahamtech.eis.utilities.enums;

public enum RiskMetricsCalcEnum {
  // CATEGORY
  access_vector("access_vector"), access_complexity("﻿access_complexity"), authentication(
      "authentication"), confidentiality_impact(
      "﻿confidentiality_impact"), integrity_impact("﻿integrity_impact"), availability_impact(
      "﻿﻿availability_impact"), last_modified_date_overdue(
      "﻿last_modified_date_overdue"),
 criteria_total("﻿criteria_total"), criteria_weight_total(
      "﻿criteria_weight_total"), category_score(
      "category_score"), category_weighted_score(
      "category_weighted_score"),
  // SYSTEM
  system_categories_weighted_totals("﻿system_categories_weighted_totals"), category_score_totals(
      "category_score_totals"), category_weighted_score_totals(
      "category_weighted_score_totals"), system_weighted_score(
      "system_weighted_score"), system_score("system_score"),
  // PROJECT
  project_criteria_weighted_score_totals(
      "project_criteria_weighted_score_totals"), project_criteria_score_totals(
      "project_criteria_score_totals"), project_criteria_weight_totals(
      "project_criteria_weight_totals"), system_score_totals(
      "system_score_totals"), project_category_score(
      "project_category_score"), project_project_category_weighted_score(
      "project_project_category_weighted_score"), project_rollup_score(
      "project_rollup_score");

  private String myEnum;

  RiskMetricsCalcEnum(String myEnum) {
    this.myEnum = myEnum;
  }

  public String getEnumString() {
    return myEnum;
  }

  public static RiskMetricsCalcEnum fromString(String myEnum) {
    if (myEnum != null) {
      for (RiskMetricsCalcEnum anEnum : RiskMetricsCalcEnum.values()) {
        if (myEnum.equalsIgnoreCase(anEnum.getEnumString())) {
          return anEnum;
        }
      }
    }
    return null;
  }
}
