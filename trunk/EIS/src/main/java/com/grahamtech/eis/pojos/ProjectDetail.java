package com.grahamtech.eis.pojos;

//import java.util.Set;

//import javax.persistence.CascadeType;
//import javax.persistence.ElementCollection;
//import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.grahamtech.eis.utilities.ConstantsUtil;
import com.grahamtech.eis.utilities.StringUtil;
import com.grahamtech.eis.utilities.enums.CountriesEnum;
import com.grahamtech.eis.utilities.enums.HighToLowEnum;
import com.grahamtech.eis.utilities.enums.StrengthRatingEnum;
import com.grahamtech.eis.utilities.enums.UsStatesEnum;
import com.grahamtech.eis.utilities.enums.VeryHighToVeryLowEnum;

@Entity
@Table(name = "project_details")
public class ProjectDetail implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "project_details_id")
  private long project_details_id;
  private String org_details;
  @Enumerated(EnumType.STRING)
  private CountriesEnum country_code;
  @Enumerated(EnumType.STRING)
  private UsStatesEnum state_province;
  private BigDecimal rollup_score;
  private BigDecimal risk_score;
  @Enumerated(EnumType.STRING)
  private VeryHighToVeryLowEnum project_weight;
  private String lessons_learned;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum budget_variance;
  @Enumerated(EnumType.STRING)
  private VeryHighToVeryLowEnum budget_variance_weight;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum schedule_variance;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum fte_utilization_rate_variance;
  @Enumerated(EnumType.STRING)
  private VeryHighToVeryLowEnum fte_utilization_rate_variance_weight;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private int risk_context_plans_count;
  @Enumerated(EnumType.STRING)
  public StrengthRatingEnum risk_context_plans_rating;
  private int risk_identification_plans_count;
  @Enumerated(EnumType.STRING)
  public StrengthRatingEnum risk_identification_plans_rating;
  private int risk_analysis_plans_count;
  @Enumerated(EnumType.STRING)
  public StrengthRatingEnum risk_analysis_plans_rating;
  private int risk_plans_communicated_count;
  @Enumerated(EnumType.STRING)
  public StrengthRatingEnum risk_plans_communicated_rating;
  @Column(name = "last_modified_date", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date last_modified_date;

  public ProjectDetail() {
    // default constructor
  }

  public long getProject_details_id() {
    return project_details_id;
  }

  public void setProject_details_id(long project_details_id) {
    this.project_details_id = project_details_id;
  }

  public String getOrg_details() {
    return org_details;
  }

  public void setOrg_details(String org_details) {
    this.org_details = org_details;
  }

  public CountriesEnum getCountry_code() {
    return country_code;
  }

  public void setCountry_code(CountriesEnum country_code) {
    this.country_code = country_code;
  }

  public UsStatesEnum getState_province() {
    return state_province;
  }

  public void setState_province(UsStatesEnum state_province) {
    this.state_province = state_province;
  }

  public String getLessons_learned() {
    return lessons_learned;
  }

  public void setLessons_learned(String lessons_learned) {
    this.lessons_learned = lessons_learned;
  }

  public HighToLowEnum getBudget_variance() {
    return budget_variance;
  }

  public void setBudget_variance(HighToLowEnum budget_variance) {
    this.budget_variance = budget_variance;
  }

  public HighToLowEnum getSchedule_variance() {
    return schedule_variance;
  }

  public void setSchedule_variance(HighToLowEnum schedule_variance) {
    this.schedule_variance = schedule_variance;
  }

  public HighToLowEnum getFte_utilization_rate_variance() {
    return fte_utilization_rate_variance;
  }

  public void setFte_utilization_rate_variance(
      HighToLowEnum fte_utilization_rate_variance) {
    this.fte_utilization_rate_variance = fte_utilization_rate_variance;
  }

  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  public int getRisk_context_plans_count() {
    return risk_context_plans_count;
  }

  public void setRisk_context_plans_count(int risk_context_plans_count) {
    this.risk_context_plans_count = risk_context_plans_count;
  }

  public StrengthRatingEnum getRisk_context_plans_rating() {
    return risk_context_plans_rating;
  }

  public void setRisk_context_plans_rating(
      StrengthRatingEnum risk_context_plans_rating) {
    this.risk_context_plans_rating = risk_context_plans_rating;
  }

  public int getRisk_identification_plans_count() {
    return risk_identification_plans_count;
  }

  public void setRisk_identification_plans_count(
      int risk_identification_plans_count) {
    this.risk_identification_plans_count = risk_identification_plans_count;
  }

  public StrengthRatingEnum getRisk_identification_plans_rating() {
    return risk_identification_plans_rating;
  }

  public void setRisk_identification_plans_rating(
      StrengthRatingEnum risk_identification_plans_rating) {
    this.risk_identification_plans_rating = risk_identification_plans_rating;
  }

  public int getRisk_analysis_plans_count() {
    return risk_analysis_plans_count;
  }

  public void setRisk_analysis_plans_count(int risk_analysis_plans_count) {
    this.risk_analysis_plans_count = risk_analysis_plans_count;
  }

  public StrengthRatingEnum getRisk_analysis_plans_rating() {
    return risk_analysis_plans_rating;
  }

  public void setRisk_analysis_plans_rating(
      StrengthRatingEnum risk_analysis_plans_rating) {
    this.risk_analysis_plans_rating = risk_analysis_plans_rating;
  }

  public int getRisk_plans_communicated_count() {
    return risk_plans_communicated_count;
  }

  public void setRisk_plans_communicated_count(int risk_plans_communicated_count) {
    this.risk_plans_communicated_count = risk_plans_communicated_count;
  }

  public StrengthRatingEnum getRisk_plans_communicated_rating() {
    return risk_plans_communicated_rating;
  }

  public void setRisk_plans_communicated_rating(
      StrengthRatingEnum risk_plans_communicated_rating) {
    this.risk_plans_communicated_rating = risk_plans_communicated_rating;
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

  public String gettLast_modified_date_String() {
    return StringUtil.dateToString(this.getLast_modified_date(),
        ConstantsUtil.DATE_FORMAT);
  }

  public VeryHighToVeryLowEnum getProject_weight() {
    return project_weight;
  }

  public void setProject_weight(VeryHighToVeryLowEnum project_weight) {
    this.project_weight = project_weight;
  }

  public VeryHighToVeryLowEnum getBudget_variance_weight() {
    return budget_variance_weight;
  }

  public void setBudget_variance_weight(
      VeryHighToVeryLowEnum budget_variance_weight) {
    this.budget_variance_weight = budget_variance_weight;
  }

  public VeryHighToVeryLowEnum getFte_utilization_rate_variance_weight() {
    return fte_utilization_rate_variance_weight;
  }

  public void setFte_utilization_rate_variance_weight(
      VeryHighToVeryLowEnum fte_utilization_rate_variance_weight) {
    this.fte_utilization_rate_variance_weight =
        fte_utilization_rate_variance_weight;
  }

  public BigDecimal getRollup_score() {
    return rollup_score;
  }

  public void setRollup_score(BigDecimal rollup_score) {
    this.rollup_score = rollup_score;
  }

  public BigDecimal getRisk_score() {
    return risk_score;
  }

  public void setRisk_score(BigDecimal risk_score) {
    this.risk_score = risk_score;
  }

}
