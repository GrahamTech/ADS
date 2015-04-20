package com.grahamtech.eis.utilities;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.grahamtech.eis.pojos.NVDEntryMessage;
import com.grahamtech.eis.pojos.Project;
import com.grahamtech.eis.pojos.ProjectSystem;
import com.grahamtech.eis.pojos.RiskMetrics;
import com.grahamtech.eis.pojos.SystemProduct;
import com.grahamtech.eis.pojos.SystemVulnerability;
import com.grahamtech.eis.utilities.enums.DaysDiffEnum;
import com.grahamtech.eis.utilities.enums.RiskMetricsCalcEnum;

public final class RiskModule {
  private static final Logger logger = LoggerFactory
      .getLogger(RiskModule.class);
  
  public static final Map<RiskMetricsCalcEnum, Double> getWeightedScores_Risk(
      RiskMetrics myObj) {
    if (myObj == null) {
      return null;
    }
    Map<RiskMetricsCalcEnum, Double> calculationMap =
        new TreeMap<RiskMetricsCalcEnum, Double>();
    Double category_weighted_score = 0.0;
    Double category_score = 0.0;
    Double criteria_total = 0.0;
    Double criteria_weight_total = 0.0;
    Double last_modified_date_overdue_weighted_score = 0.0;
    Double availability_impact_weighted_score = 0.0;
    Double integrity_impact_weighted_score = 0.0;
    Double confidentiality_impact_weighted_score = 0.0;
    Double authentication_weighted_score = 0.0;
    Double access_complexity_weighted_score = 0.0;
    Double access_vector_weighted_score = 0.0;

    access_vector_weighted_score =
        myObj.getAccess_vector_weight().getIntCodeForEnum().doubleValue()
            * myObj.getAccess_vector().getIntCodeForEnum().doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.access_vector,
        access_vector_weighted_score);
    criteria_total = criteria_total + access_vector_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + myObj.getAccess_vector_weight().getIntCodeForEnum().doubleValue();

    access_complexity_weighted_score =
        myObj.getAccess_complexity().getIntCodeForEnum().doubleValue()
            * myObj.getAccess_complexity_weight().getIntCodeForEnum()
                .doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.access_complexity,
        access_complexity_weighted_score);
    criteria_total = criteria_total + access_complexity_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + myObj.getAccess_complexity().getIntCodeForEnum().doubleValue();

    authentication_weighted_score =
        myObj.getAuthentication().getIntCodeForEnum().doubleValue()
            * myObj.getAuthentication_weight().getIntCodeForEnum()
                .doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.authentication,
        authentication_weighted_score);
    criteria_total = criteria_total + authentication_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + myObj.getAuthentication().getIntCodeForEnum().doubleValue();

    confidentiality_impact_weighted_score =
        myObj.getConfidentiality_impact().getIntCodeForEnum().doubleValue()
            * myObj.getConfidentiality_impact_weight().getIntCodeForEnum()
                .doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.confidentiality_impact,
        confidentiality_impact_weighted_score);
    criteria_total = criteria_total + confidentiality_impact_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + myObj.getConfidentiality_impact().getIntCodeForEnum()
                .doubleValue();

    integrity_impact_weighted_score =
        myObj.getIntegrity_impact().getIntCodeForEnum().doubleValue()
            * myObj.getIntegrity_impact_weight().getIntCodeForEnum()
                .doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.integrity_impact,
        integrity_impact_weighted_score);
    criteria_total = criteria_total + integrity_impact_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + myObj.getIntegrity_impact().getIntCodeForEnum().doubleValue();

    availability_impact_weighted_score =
        myObj.getAvailability_impact().getIntCodeForEnum().doubleValue()
            * myObj.getAvailability_impact_weight().getIntCodeForEnum()
                .doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.availability_impact,
        availability_impact_weighted_score);
    criteria_total = criteria_total + availability_impact_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + myObj.getAvailability_impact().getIntCodeForEnum().doubleValue();

    long lastModifiedSinceDays =
        StringUtil.dateDaysDiff(myObj.getLast_modified_date());
    if (lastModifiedSinceDays < new Integer(
        DaysDiffEnum.VERY_RECENT.getEnumString())) { // 60
      last_modified_date_overdue_weighted_score = 0.0;
    } else if (lastModifiedSinceDays >= new Integer(
        DaysDiffEnum.VERY_RECENT.getEnumString()) // 60 to 80
        && lastModifiedSinceDays < new Integer(
            DaysDiffEnum.RECENT.getEnumString())) {
      last_modified_date_overdue_weighted_score =
          myObj.getLast_modified_date_weight().getIntCodeForEnum()
              .doubleValue()
              * new Integer(DaysDiffEnum.VERY_RECENT_SCORE.getEnumString());
    } else if (lastModifiedSinceDays >= new Integer(
        DaysDiffEnum.RECENT.getEnumString()) // 80 to 100
        && lastModifiedSinceDays < new Integer(
            DaysDiffEnum.LESS_RECENT.getEnumString())) {
      last_modified_date_overdue_weighted_score =
          myObj.getLast_modified_date_weight().getIntCodeForEnum()
              .doubleValue()
              * new Integer(DaysDiffEnum.RECENT_SCORE.getEnumString());
    } else if (lastModifiedSinceDays >= new Integer(
        DaysDiffEnum.LESS_RECENT.getEnumString()) // 100 to 120
        && lastModifiedSinceDays < new Integer(
            DaysDiffEnum.LONG_AGO.getEnumString())) {
      last_modified_date_overdue_weighted_score =
          myObj.getLast_modified_date_weight().getIntCodeForEnum()
              .doubleValue()
              * new Integer(DaysDiffEnum.LESS_RECENT_SCORE.getEnumString());
    } else if (lastModifiedSinceDays >= new Integer(
        DaysDiffEnum.LONG_AGO.getEnumString()) // 100 to 120
        && lastModifiedSinceDays < new Integer(
            DaysDiffEnum.VERY_LONG_AGO.getEnumString())) {
      last_modified_date_overdue_weighted_score =
          myObj.getLast_modified_date_weight().getIntCodeForEnum()
              .doubleValue()
              * new Integer(DaysDiffEnum.LONG_AGO_SCORE.getEnumString());
    } else if (lastModifiedSinceDays > new Integer(
        DaysDiffEnum.VERY_LONG_AGO.getEnumString())) {
      last_modified_date_overdue_weighted_score =
          myObj.getLast_modified_date_weight().getIntCodeForEnum()
              .doubleValue()
              * new Integer(DaysDiffEnum.VERY_LONG_AGO_SCORE.getEnumString());
    }
    calculationMap.put(RiskMetricsCalcEnum.last_modified_date_overdue,
        last_modified_date_overdue_weighted_score);
    criteria_total = criteria_total + last_modified_date_overdue_weighted_score;

    calculationMap.put(RiskMetricsCalcEnum.criteria_total, criteria_total);

    criteria_weight_total =
        criteria_weight_total
            + myObj.getLast_modified_date_weight().getIntCodeForEnum();

    calculationMap.put(RiskMetricsCalcEnum.criteria_weight_total,
        criteria_weight_total);

    category_score = criteria_total / criteria_weight_total;

    calculationMap.put(RiskMetricsCalcEnum.category_score, category_score);

    category_weighted_score =
        category_score
            * myObj.getScore_weight().getIntCodeForEnum().doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.category_weighted_score,
        category_weighted_score);

    try {
      myObj.setScore(StringUtil
          .formatBigDecimalFromDouble(category_weighted_score));
    } catch (NumberFormatException e) {
      logger.info("\nERROR - category_weighted_score: " + e.toString());
    }

    return calculationMap;

  } // end calculation Metrics

  public static final Map<RiskMetricsCalcEnum, Double> getWeightedScore_System(
      ProjectSystem myObj) {
    if (myObj == null) {
      return null;
    }

    Map<RiskMetricsCalcEnum, Double> calculationMap =
        new TreeMap<RiskMetricsCalcEnum, Double>();

    int riskCount = 0;
    Double system_categories_weighted_totals = 0.0;
    Double category_weight_totals_nvd = 0.0;
    Double category_weight_totals_vul = 0.0;
    Double category_weight_totals_prod = 0.0;
    Double category_score_totals = 0.0;
    Double category_weighted_score_totals = 0.0;

    for (NVDEntryMessage nvdEntryMessage : myObj.getSystemNVDEntryMessageSet()) {
      Map<RiskMetricsCalcEnum, Double> map =
          RiskModule.getWeightedScores_Risk(nvdEntryMessage);

      if (category_weight_totals_nvd == 0.0) {
        // will be the same so only do it once.
        category_weight_totals_nvd =
            map.get(RiskMetricsCalcEnum.criteria_weight_total);
      }

      category_score_totals =
          category_score_totals + map.get(RiskMetricsCalcEnum.criteria_total);

      category_weighted_score_totals =
          category_weighted_score_totals
              + map.get(RiskMetricsCalcEnum.category_weighted_score);

      riskCount++;
    }// end for each

    for (SystemProduct systemProduct : myObj.getSystemProductSet()) {
      Map<RiskMetricsCalcEnum, Double> map =
          RiskModule.getWeightedScores_Risk(systemProduct);

      if (category_weight_totals_prod == 0) {
        // will be the same so only do it once.
        category_weight_totals_prod =
            map.get(RiskMetricsCalcEnum.criteria_weight_total);
      }

      category_score_totals =
          category_score_totals + map.get(RiskMetricsCalcEnum.criteria_total);

      category_weighted_score_totals =
          category_weighted_score_totals
              + map.get(RiskMetricsCalcEnum.category_weighted_score);

      riskCount++;
    }// end for each

    for (SystemVulnerability systemVulnerability : myObj
        .getSystemVulnerabilitySet()) {
      Map<RiskMetricsCalcEnum, Double> map =
          RiskModule.getWeightedScores_Risk(systemVulnerability);

      if (category_weight_totals_vul == 0) {
        // will be the same so only do it once.
        category_weight_totals_vul =
            map.get(RiskMetricsCalcEnum.criteria_weight_total);
      }

      category_score_totals =
          category_score_totals + map.get(RiskMetricsCalcEnum.criteria_total);

      category_weighted_score_totals =
          category_weighted_score_totals
              + map.get(RiskMetricsCalcEnum.category_weighted_score);

      riskCount++;
    }// end for each

    system_categories_weighted_totals =
        category_weight_totals_nvd + category_weight_totals_prod
            + category_weight_totals_vul;
    calculationMap.put(RiskMetricsCalcEnum.system_categories_weighted_totals,
        system_categories_weighted_totals);

    calculationMap.put(RiskMetricsCalcEnum.category_score_totals,
        category_score_totals);

    calculationMap.put(RiskMetricsCalcEnum.category_weighted_score_totals,
        category_weighted_score_totals);

    Double system_weighted_score =
        category_weighted_score_totals * system_categories_weighted_totals;
    calculationMap.put(RiskMetricsCalcEnum.system_weighted_score,
        system_weighted_score);

    Double system_score = system_weighted_score / riskCount;
    calculationMap.put(RiskMetricsCalcEnum.system_score, system_score);

    try {
      myObj.setScore(StringUtil.formatBigDecimalFromDouble(system_score));
    } catch (NumberFormatException e) {
      logger.info("\nERROR - system_score: " + e.toString());
    }

    return calculationMap;
  } // end getWeightedScore System
  
  public static final Map<RiskMetricsCalcEnum, Double> getWeightedScore_Project(
      Project myObj) {
    if (myObj == null) {
      return null;
    }

    Map<RiskMetricsCalcEnum, Double> calculationMap =
        new TreeMap<RiskMetricsCalcEnum, Double>();

    Double system_score_totals = 0.0;
    for (ProjectSystem projectSystem : myObj.getProjectSystemSet()) {
      Map<RiskMetricsCalcEnum, Double> map =
          RiskModule.getWeightedScore_System(projectSystem);
      system_score_totals =
          system_score_totals + map.get(RiskMetricsCalcEnum.system_score);
    }
    calculationMap.put(RiskMetricsCalcEnum.system_score_totals,
        system_score_totals);

    Double project_criteria_weight_totals =
        myObj.getProjectDetail().getBudget_variance_weight()
            .getIntCodeForEnum().doubleValue()
            + myObj.getProjectDetail().getSchedule_variance_weight()
                .getIntCodeForEnum().doubleValue()
            + myObj.getProjectDetail()
                .getFte_utilization_rate_variance_weight().getIntCodeForEnum()
                .doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.project_criteria_weight_totals,
        project_criteria_weight_totals);

    Double project_criteria_score_totals =
        myObj.getProjectDetail().getBudget_variance().doubleValue()
            + myObj.getProjectDetail().getSchedule_variance().doubleValue()
            + myObj.getProjectDetail().getFte_utilization_rate_variance()
                .doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.project_criteria_score_totals,
        project_criteria_score_totals);

    Double project_criteria_weighted_score_totals =
        (myObj.getProjectDetail().getBudget_variance().doubleValue() * myObj
            .getProjectDetail()
            .getBudget_variance_weight().getIntCodeForEnum().doubleValue())
            + (myObj.getProjectDetail().getSchedule_variance().doubleValue() * myObj
                .getProjectDetail()
                .getSchedule_variance_weight().getIntCodeForEnum()
                .doubleValue())
            + (myObj.getProjectDetail().getFte_utilization_rate_variance()
                .doubleValue() * myObj.getProjectDetail()
                .getFte_utilization_rate_variance_weight().getIntCodeForEnum()
                .doubleValue());
    calculationMap.put(
        RiskMetricsCalcEnum.project_criteria_weighted_score_totals,
        project_criteria_weighted_score_totals);

    Double project_category_score =
        project_criteria_weighted_score_totals / project_criteria_weight_totals;
    calculationMap.put(RiskMetricsCalcEnum.project_category_score,
        project_category_score);

    Double project_project_category_weighted_score =
        project_category_score
            * myObj.getProjectDetail().getProject_weight().getIntCodeForEnum()
                .doubleValue();
    calculationMap.put(
        RiskMetricsCalcEnum.project_project_category_weighted_score,
        project_project_category_weighted_score);

    Double project_rollup_score =
        (project_project_category_weighted_score + system_score_totals) / 2;
    calculationMap.put(RiskMetricsCalcEnum.project_rollup_score,
        project_rollup_score);

    try {
      myObj.getProjectDetail().setRollup_score(
          StringUtil.formatBigDecimalFromDouble(project_rollup_score));
    } catch (NumberFormatException e) {
      logger.info("\nERROR - project_rollup_score: " + e.toString());
    }

    JSONPObject jsonPObject = new JSONPObject("calculationMap", calculationMap);

    return calculationMap;
  } // end getWeightedScore project
}
