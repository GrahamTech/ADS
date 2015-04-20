package com.grahamtech.eis.pojos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.grahamtech.eis.utilities.ConstantsUtil;
import com.grahamtech.eis.utilities.StringUtil;
import com.grahamtech.eis.utilities.enums.HighToLowEnum;
import com.grahamtech.eis.utilities.enums.StatusEnum;

@Entity
@Table(name = "risk_preferences")
public class RiskPreference extends RiskMetrics implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "risk_preference_id")
  private long risk_preference_id;
  @Column(name = "risk_preference_name", nullable = false)
  private String risk_preference_name;
  @Enumerated(EnumType.STRING)
  private StatusEnum preference_status;
  private BigDecimal overall_project_risk_tolerance;
  private BigDecimal overall_project_risk_weight;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum overall_project_risk_priority;
  private BigDecimal project_budget_variance_tolerance;
  private BigDecimal project_budget_variance_weight;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum project_budget_variance_priority;
  private BigDecimal project_schedule_variance_tolerance;
  private BigDecimal project_schedule_variance_weight;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum project_schedule_variance_priority;
  private BigDecimal project_fte_utilization_variance_tolerance;
  private BigDecimal project_fte_utilization_variance_weight;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum project_fte_utilization_variance_priority;
  private BigDecimal project_partner_risk_tolerance;
  private BigDecimal project_partner_risk_weight;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum project_partner_risk_priority;
  private BigDecimal project_product_risk_tolerance;
  private BigDecimal project_product_risk_weight;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum project_product_risk_priority;

  public long getRisk_preference_id() {
    return risk_preference_id;
  }

  public void setRisk_preference_id(long risk_preference_id) {
    this.risk_preference_id = risk_preference_id;
  }

  public String getRisk_preference_name() {
    return risk_preference_name;
  }

  public void setRisk_preference_name(String risk_preference_name) {
    this.risk_preference_name = risk_preference_name;
  }

  public StatusEnum getPreference_status() {
    return preference_status;
  }

  public void setPreference_status(StatusEnum preference_status) {
    this.preference_status = preference_status;
  }

  public BigDecimal getOverall_project_risk_tolerance() {
    return overall_project_risk_tolerance;
  }

  public void setOverall_project_risk_tolerance(
      BigDecimal overall_project_risk_tolerance) {
    this.overall_project_risk_tolerance = overall_project_risk_tolerance;
  }

  public BigDecimal getOverall_project_risk_weight() {
    return overall_project_risk_weight;
  }

  public void setOverall_project_risk_weight(
      BigDecimal overall_project_risk_weight) {
    this.overall_project_risk_weight = overall_project_risk_weight;
  }

  public HighToLowEnum getOverall_project_risk_priority() {
    return overall_project_risk_priority;
  }

  public void setOverall_project_risk_priority(
      HighToLowEnum overall_project_risk_priority) {
    this.overall_project_risk_priority = overall_project_risk_priority;
  }

  public BigDecimal getProject_budget_variance_tolerance() {
    return project_budget_variance_tolerance;
  }

  public void setProject_budget_variance_tolerance(
      BigDecimal project_budget_variance_tolerance) {
    this.project_budget_variance_tolerance = project_budget_variance_tolerance;
  }

  public BigDecimal getProject_budget_variance_weight() {
    return project_budget_variance_weight;
  }

  public void setProject_budget_variance_weight(
      BigDecimal project_budget_variance_weight) {
    this.project_budget_variance_weight = project_budget_variance_weight;
  }

  public HighToLowEnum getProject_budget_variance_priority() {
    return project_budget_variance_priority;
  }

  public void setProject_budget_variance_priority(
      HighToLowEnum project_budget_variance_priority) {
    this.project_budget_variance_priority = project_budget_variance_priority;
  }

  public BigDecimal getProject_schedule_variance_tolerance() {
    return project_schedule_variance_tolerance;
  }

  public void setProject_schedule_variance_tolerance(
      BigDecimal project_schedule_variance_tolerance) {
    this.project_schedule_variance_tolerance =
        project_schedule_variance_tolerance;
  }

  public BigDecimal getProject_schedule_variance_weight() {
    return project_schedule_variance_weight;
  }

  public void setProject_schedule_variance_weight(
      BigDecimal project_schedule_variance_weight) {
    this.project_schedule_variance_weight = project_schedule_variance_weight;
  }

  public HighToLowEnum getProject_schedule_variance_priority() {
    return project_schedule_variance_priority;
  }

  public void setProject_schedule_variance_priority(
      HighToLowEnum project_schedule_variance_priority) {
    this.project_schedule_variance_priority =
        project_schedule_variance_priority;
  }

  public BigDecimal getProject_fte_utilization_variance_tolerance() {
    return project_fte_utilization_variance_tolerance;
  }

  public void setProject_fte_utilization_variance_tolerance(
      BigDecimal project_fte_utilization_variance_tolerance) {
    this.project_fte_utilization_variance_tolerance =
        project_fte_utilization_variance_tolerance;
  }

  public BigDecimal getProject_fte_utilization_variance_weight() {
    return project_fte_utilization_variance_weight;
  }

  public void setProject_fte_utilization_variance_weight(
      BigDecimal project_fte_utilization_variance_weight) {
    this.project_fte_utilization_variance_weight =
        project_fte_utilization_variance_weight;
  }

  public HighToLowEnum getProject_fte_utilization_variance_priority() {
    return project_fte_utilization_variance_priority;
  }

  public void setProject_fte_utilization_variance_priority(
      HighToLowEnum project_fte_utilization_variance_priority) {
    this.project_fte_utilization_variance_priority =
        project_fte_utilization_variance_priority;
  }

  public BigDecimal getProject_partner_risk_tolerance() {
    return project_partner_risk_tolerance;
  }

  public void setProject_partner_risk_tolerance(
      BigDecimal project_partner_risk_tolerance) {
    this.project_partner_risk_tolerance = project_partner_risk_tolerance;
  }

  public BigDecimal getProject_partner_risk_weight() {
    return project_partner_risk_weight;
  }

  public void setProject_partner_risk_weight(
      BigDecimal project_partner_risk_weight) {
    this.project_partner_risk_weight = project_partner_risk_weight;
  }

  public HighToLowEnum getProject_partner_risk_priority() {
    return project_partner_risk_priority;
  }

  public void setProject_partner_risk_priority(
      HighToLowEnum project_partner_risk_priority) {
    this.project_partner_risk_priority = project_partner_risk_priority;
  }

  public BigDecimal getProject_product_risk_tolerance() {
    return project_product_risk_tolerance;
  }

  public void setProject_product_risk_tolerance(
      BigDecimal project_product_risk_tolerance) {
    this.project_product_risk_tolerance = project_product_risk_tolerance;
  }

  public BigDecimal getProject_product_risk_weight() {
    return project_product_risk_weight;
  }

  public void setProject_product_risk_weight(
      BigDecimal project_product_risk_weight) {
    this.project_product_risk_weight = project_product_risk_weight;
  }

  public HighToLowEnum getProject_product_risk_priority() {
    return project_product_risk_priority;
  }

  public void setProject_product_risk_priority(
      HighToLowEnum project_product_risk_priority) {
    this.project_product_risk_priority = project_product_risk_priority;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getLast_modified_date_String() {
    return StringUtil.dateToString(this.getLast_modified_date(),
        ConstantsUtil.DATE_FORMAT);
  }
}
