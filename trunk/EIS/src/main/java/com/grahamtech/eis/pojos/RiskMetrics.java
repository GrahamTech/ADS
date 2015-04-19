package com.grahamtech.eis.pojos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.grahamtech.eis.utilities.ConstantsUtil;
import com.grahamtech.eis.utilities.StringUtil;
import com.grahamtech.eis.utilities.enums.AccessVectorEnum;
import com.grahamtech.eis.utilities.enums.DaysDiffEnum;
import com.grahamtech.eis.utilities.enums.HighToLowEnum;
import com.grahamtech.eis.utilities.enums.InstanceCountEnum;
import com.grahamtech.eis.utilities.enums.PartialToCompleteEnum;
import com.grahamtech.eis.utilities.enums.RiskMetricsCalcEnum;
import com.grahamtech.eis.utilities.enums.VeryHighToVeryLowEnum;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class RiskMetrics implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  // START RISK METRICS
  @Column(name = "summary")
  private String summary;

  @Column(name = "score")
  private BigDecimal score;

  @Enumerated(EnumType.STRING)
  private VeryHighToVeryLowEnum score_weight;

  @Column(name = "access_vector")
  @Enumerated(EnumType.STRING)
  private AccessVectorEnum access_vector; // access-vector

  @Enumerated(EnumType.STRING)
  private VeryHighToVeryLowEnum access_vector_weight;

  @Column(name = "access_complexity")
  @Enumerated(EnumType.STRING)
  private HighToLowEnum access_complexity; // access-complexity

  @Enumerated(EnumType.STRING)
  private VeryHighToVeryLowEnum access_complexity_weight;

  @Column(name = "authentication")
  @Enumerated(EnumType.STRING)
  private InstanceCountEnum authentication; // authentication

  @Enumerated(EnumType.STRING)
  private VeryHighToVeryLowEnum authentication_weight;

  @Column(name = "confidentiality_impact")
  @Enumerated(EnumType.STRING)
  private PartialToCompleteEnum confidentiality_impact; // confidentiality-impact

  @Enumerated(EnumType.STRING)
  private VeryHighToVeryLowEnum confidentiality_impact_weight;

  @Column(name = "integrity_impact")
  @Enumerated(EnumType.STRING)
  private PartialToCompleteEnum integrity_impact; // integrity-impact

  @Enumerated(EnumType.STRING)
  private VeryHighToVeryLowEnum integrity_impact_weight;

  @Column(name = "availability_impact")
  @Enumerated(EnumType.STRING)
  private PartialToCompleteEnum availability_impact; // availability-impact

  @Enumerated(EnumType.STRING)
  private VeryHighToVeryLowEnum availability_impact_weight;

  @Column(name = "last_modified_date", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date last_modified_date; // last-modified-datetime

  @Enumerated(EnumType.STRING)
  private VeryHighToVeryLowEnum last_modified_date_weight;

  public Map<RiskMetricsCalcEnum, Double> getWeightedScores() {
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
        this.getAccess_vector_weight().getIntCodeForEnum().doubleValue()
            * this.getAccess_vector().getIntCodeForEnum().doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.access_vector,
        access_vector_weighted_score);
    criteria_total = criteria_total + access_vector_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + this.getAccess_vector_weight().getIntCodeForEnum().doubleValue();

    access_complexity_weighted_score =
        this.getAccess_complexity().getIntCodeForEnum().doubleValue()
            * this.getAccess_complexity_weight().getIntCodeForEnum()
                .doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.access_complexity,
        access_complexity_weighted_score);
    criteria_total = criteria_total + access_complexity_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + this.getAccess_complexity().getIntCodeForEnum().doubleValue();

    authentication_weighted_score =
        this.getAuthentication().getIntCodeForEnum().doubleValue()
            * this.getAuthentication_weight().getIntCodeForEnum().doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.authentication,
        authentication_weighted_score);
    criteria_total = criteria_total + authentication_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + this.getAuthentication().getIntCodeForEnum().doubleValue();

    confidentiality_impact_weighted_score =
        this.getConfidentiality_impact().getIntCodeForEnum().doubleValue()
            * this.getConfidentiality_impact_weight().getIntCodeForEnum()
                .doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.confidentiality_impact,
        confidentiality_impact_weighted_score);
    criteria_total = criteria_total + confidentiality_impact_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + this.getConfidentiality_impact().getIntCodeForEnum()
                .doubleValue();

    integrity_impact_weighted_score =
        this.getIntegrity_impact().getIntCodeForEnum().doubleValue()
            * this.getIntegrity_impact_weight().getIntCodeForEnum()
                .doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.integrity_impact,
        integrity_impact_weighted_score);
    criteria_total = criteria_total + integrity_impact_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + this.getIntegrity_impact().getIntCodeForEnum().doubleValue();

    availability_impact_weighted_score =
        this.getAvailability_impact().getIntCodeForEnum().doubleValue()
            * this.getAvailability_impact_weight().getIntCodeForEnum()
                .doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.availability_impact,
        availability_impact_weighted_score);
    criteria_total = criteria_total + availability_impact_weighted_score;
    criteria_weight_total =
        criteria_weight_total
            + this.getAvailability_impact().getIntCodeForEnum().doubleValue();

    long lastModifiedSinceDays =
        StringUtil.dateDaysDiff(this.getLast_modified_date());
    if (lastModifiedSinceDays < new Integer(
        DaysDiffEnum.VERY_RECENT.getEnumString())) { // 60
      last_modified_date_overdue_weighted_score = 0.0;
    } else if (lastModifiedSinceDays >= new Integer(
        DaysDiffEnum.VERY_RECENT.getEnumString()) // 60 to 80
        && lastModifiedSinceDays < new Integer(
            DaysDiffEnum.RECENT.getEnumString())) {
      last_modified_date_overdue_weighted_score =
          this.getLast_modified_date_weight().getIntCodeForEnum().doubleValue()
              * new Integer(DaysDiffEnum.VERY_RECENT_SCORE.getEnumString());
    } else if (lastModifiedSinceDays >= new Integer(
        DaysDiffEnum.RECENT.getEnumString()) // 80 to 100
        && lastModifiedSinceDays < new Integer(
            DaysDiffEnum.LESS_RECENT.getEnumString())) {
      last_modified_date_overdue_weighted_score =
          this.getLast_modified_date_weight().getIntCodeForEnum().doubleValue()
              * new Integer(DaysDiffEnum.RECENT_SCORE.getEnumString());
    } else if (lastModifiedSinceDays >= new Integer(
        DaysDiffEnum.LESS_RECENT.getEnumString()) // 100 to 120
        && lastModifiedSinceDays < new Integer(
            DaysDiffEnum.LONG_AGO.getEnumString())) {
      last_modified_date_overdue_weighted_score =
          this.getLast_modified_date_weight().getIntCodeForEnum().doubleValue()
              * new Integer(DaysDiffEnum.LESS_RECENT_SCORE.getEnumString());
    } else if (lastModifiedSinceDays >= new Integer(
        DaysDiffEnum.LONG_AGO.getEnumString()) // 100 to 120
        && lastModifiedSinceDays < new Integer(
            DaysDiffEnum.VERY_LONG_AGO.getEnumString())) {
      last_modified_date_overdue_weighted_score =
          this.getLast_modified_date_weight().getIntCodeForEnum().doubleValue()
              * new Integer(DaysDiffEnum.LONG_AGO_SCORE.getEnumString());
    } else if (lastModifiedSinceDays > new Integer(
        DaysDiffEnum.VERY_LONG_AGO.getEnumString())) {
      last_modified_date_overdue_weighted_score =
          this.getLast_modified_date_weight().getIntCodeForEnum().doubleValue()
              * new Integer(DaysDiffEnum.VERY_LONG_AGO_SCORE.getEnumString());
    }
    calculationMap.put(RiskMetricsCalcEnum.last_modified_date_overdue,
        last_modified_date_overdue_weighted_score);
    criteria_total = criteria_total + last_modified_date_overdue_weighted_score;

    calculationMap.put(RiskMetricsCalcEnum.criteria_total, criteria_total);

    criteria_weight_total =
        criteria_weight_total
            + this.getLast_modified_date_weight().getIntCodeForEnum();

    calculationMap.put(RiskMetricsCalcEnum.criteria_weight_total,
        criteria_weight_total);

    category_score = criteria_total / criteria_weight_total;

    calculationMap.put(RiskMetricsCalcEnum.category_score, category_score);

    category_weighted_score =
        category_score
            * this.getScore_weight().getIntCodeForEnum().doubleValue();
    calculationMap.put(RiskMetricsCalcEnum.category_weighted_score,
        category_weighted_score);

    this.setScore(new BigDecimal(category_weighted_score));

    return calculationMap;

  } // end calculation

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public BigDecimal getScore() {
    return score;
  }

  public void setScore(BigDecimal score) {
    this.score = score;
  }

  public AccessVectorEnum getAccess_vector() {
    return access_vector;
  }

  public void setAccess_vector(AccessVectorEnum access_vector) {
    this.access_vector = access_vector;
  }

  public HighToLowEnum getAccess_complexity() {
    return access_complexity;
  }

  public void setAccess_complexity(HighToLowEnum access_complexity) {
    this.access_complexity = access_complexity;
  }

  public InstanceCountEnum getAuthentication() {
    return authentication;
  }

  public void setAuthentication(InstanceCountEnum authentication) {
    this.authentication = authentication;
  }

  public PartialToCompleteEnum getConfidentiality_impact() {
    return confidentiality_impact;
  }

  public void setConfidentiality_impact(
      PartialToCompleteEnum confidentiality_impact) {
    this.confidentiality_impact = confidentiality_impact;
  }

  public PartialToCompleteEnum getIntegrity_impact() {
    return integrity_impact;
  }

  public void setIntegrity_impact(PartialToCompleteEnum integrity_impact) {
    this.integrity_impact = integrity_impact;
  }

  public PartialToCompleteEnum getAvailability_impact() {
    return availability_impact;
  }

  public void setAvailability_impact(PartialToCompleteEnum availability_impact) {
    this.availability_impact = availability_impact;
  }

  public Date getLast_modified_date() {
    return last_modified_date;
  }

  public void setLast_modified_date(Date last_modified_date) {
    this.last_modified_date = last_modified_date;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getLast_modified_date_String() {
    return StringUtil.dateToString(this.getLast_modified_date(),
        ConstantsUtil.DATE_FORMAT);
  }

  public VeryHighToVeryLowEnum getScore_weight() {
    return score_weight;
  }

  public void setScore_weight(VeryHighToVeryLowEnum score_weight) {
    this.score_weight = score_weight;
  }

  public VeryHighToVeryLowEnum getAccess_vector_weight() {
    return access_vector_weight;
  }

  public void setAccess_vector_weight(VeryHighToVeryLowEnum access_vector_weight) {
    this.access_vector_weight = access_vector_weight;
  }

  public VeryHighToVeryLowEnum getAccess_complexity_weight() {
    return access_complexity_weight;
  }

  public void setAccess_complexity_weight(
      VeryHighToVeryLowEnum access_complexity_weight) {
    this.access_complexity_weight = access_complexity_weight;
  }

  public VeryHighToVeryLowEnum getAuthentication_weight() {
    return authentication_weight;
  }

  public void setAuthentication_weight(
      VeryHighToVeryLowEnum authentication_weight) {
    this.authentication_weight = authentication_weight;
  }

  public VeryHighToVeryLowEnum getConfidentiality_impact_weight() {
    return confidentiality_impact_weight;
  }

  public void setConfidentiality_impact_weight(
      VeryHighToVeryLowEnum confidentiality_impact_weight) {
    this.confidentiality_impact_weight = confidentiality_impact_weight;
  }

  public VeryHighToVeryLowEnum getIntegrity_impact_weight() {
    return integrity_impact_weight;
  }

  public void setIntegrity_impact_weight(
      VeryHighToVeryLowEnum integrity_impact_weight) {
    this.integrity_impact_weight = integrity_impact_weight;
  }

  public VeryHighToVeryLowEnum getAvailability_impact_weight() {
    return availability_impact_weight;
  }

  public void setAvailability_impact_weight(
      VeryHighToVeryLowEnum availability_impact_weight) {
    this.availability_impact_weight = availability_impact_weight;
  }

  public VeryHighToVeryLowEnum getLast_modified_date_weight() {
    return last_modified_date_weight;
  }

  public void setLast_modified_date_weight(
      VeryHighToVeryLowEnum last_modified_date_weight) {
    this.last_modified_date_weight = last_modified_date_weight;
  }

}
