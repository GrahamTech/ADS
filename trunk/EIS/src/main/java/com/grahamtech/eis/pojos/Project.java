package com.grahamtech.eis.pojos;

//import java.math.BigDecimal;
//import java.util.Map;
import java.util.Set;
//import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.persistence.Transient;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.databind.util.JSONPObject;
//import com.grahamtech.eis.utilities.RiskModule;
//import com.grahamtech.eis.utilities.enums.RiskMetricsCalcEnum;
import com.grahamtech.eis.utilities.enums.StatusEnum;
@Entity
@Table(name = "projects")
public class Project implements java.io.Serializable {
  // private static final Logger logger =
  // LoggerFactory.getLogger(Project.class);

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "project_id")
  private long role_id;
  @Column(name = "project_name")
  private String project_name;
  @Column(name = "project_status")
  private String project_status;

  @ManyToOne
  @JoinColumn(name = "project_details_fk")
  private ProjectDetail projectDetail;

  @ManyToOne
  @JoinColumn(name = "user_profile_fk", insertable = false, updatable = false)
  @JsonBackReference
  private UserProfile userProfileAttribute;

  @OneToMany(mappedBy = "projectAttribute", fetch = FetchType.EAGER)
  @JsonManagedReference
  private Set<ProjectSystem> projectSystemSet;

  @OneToMany(mappedBy = "projectAttribute", fetch = FetchType.EAGER)
  @JsonManagedReference
  private Set<ProjectPartner> projectPartnerSet;

  @ManyToOne
  @JoinColumn(name = "flagged_fk_project")
  @JsonBackReference
  private FlaggedAsset flaggedAsset;
  
  // @Transient
  // private JSONPObject jsonPObject;

  // public JSONPObject getJsonPObject() {
  // return jsonPObject;
  // }
  //
  // public void setJsonPObject(JSONPObject jsonPObject) {
  // this.jsonPObject = jsonPObject;
  // }

  public Project() {
    // default constructor
  }

  // public Map<RiskMetricsCalcEnum, Double> getWeightedScore() {
  // Map<RiskMetricsCalcEnum, Double> calculationMap =
  // new TreeMap<RiskMetricsCalcEnum, Double>();
  //
  // Double system_score_totals = 0.0;
  // for (ProjectSystem projectSystem : this.getProjectSystemSet()) {
  // Map<RiskMetricsCalcEnum, Double> map =
  // RiskModule.getWeightedScore_System(projectSystem);
  // system_score_totals =
  // system_score_totals + map.get(RiskMetricsCalcEnum.system_score);
  // }
  // calculationMap.put(RiskMetricsCalcEnum.system_score_totals,
  // system_score_totals);
  //
  // Double project_criteria_weight_totals =
  // this.getProjectDetail().getBudget_variance_weight().getIntCodeForEnum()
  // .doubleValue()
  // + this.getProjectDetail().getSchedule_variance_weight()
  // .getIntCodeForEnum().doubleValue()
  // + this.getProjectDetail().getFte_utilization_rate_variance_weight()
  // .getIntCodeForEnum().doubleValue();
  // calculationMap.put(RiskMetricsCalcEnum.project_criteria_weight_totals,
  // project_criteria_weight_totals);
  //
  // Double project_criteria_score_totals =
  // this.getProjectDetail().getBudget_variance().getIntCodeForEnum()
  // .doubleValue()
  // + this.getProjectDetail().getSchedule_variance()
  // .getIntCodeForEnum().doubleValue()
  // + this.getProjectDetail().getFte_utilization_rate_variance()
  // .getIntCodeForEnum().doubleValue();
  // calculationMap.put(RiskMetricsCalcEnum.project_criteria_score_totals,
  // project_criteria_score_totals);
  //
  // Double project_criteria_weighted_score_totals =
  // (this.getProjectDetail().getBudget_variance().getIntCodeForEnum()
  // .doubleValue() * this.getProjectDetail()
  // .getBudget_variance_weight().getIntCodeForEnum().doubleValue())
  // + (this.getProjectDetail().getSchedule_variance()
  // .getIntCodeForEnum().doubleValue() * this.getProjectDetail()
  // .getSchedule_variance_weight().getIntCodeForEnum()
  // .doubleValue())
  // + (this.getProjectDetail().getFte_utilization_rate_variance()
  // .getIntCodeForEnum().doubleValue() * this.getProjectDetail()
  // .getFte_utilization_rate_variance_weight().getIntCodeForEnum()
  // .doubleValue());
  // calculationMap.put(
  // RiskMetricsCalcEnum.project_criteria_weighted_score_totals,
  // project_criteria_weighted_score_totals);
  //
  // Double project_category_score =
  // project_criteria_weighted_score_totals / project_criteria_weight_totals;
  // calculationMap.put(RiskMetricsCalcEnum.project_category_score,
  // project_category_score);
  //
  // Double project_project_category_weighted_score =
  // project_category_score
  // * this.getProjectDetail().getProject_weight().getIntCodeForEnum()
  // .doubleValue();
  // calculationMap.put(
  // RiskMetricsCalcEnum.project_project_category_weighted_score,
  // project_project_category_weighted_score);
  //
  // Double project_rollup_score =
  // (project_project_category_weighted_score + system_score_totals) / 2;
  // calculationMap.put(RiskMetricsCalcEnum.project_rollup_score,
  // project_rollup_score);
  //
  // try {
  // this.getProjectDetail().setRollup_score(
  // new BigDecimal(project_rollup_score));
  // } catch (NumberFormatException e) {
  // logger.info("\nERROR - project_rollup_score: " + e.toString());
  // }
  //
  // JSONPObject jsonPObject = new JSONPObject("calculationMap",
  // calculationMap);
  //
  // return calculationMap;
  // } //end getWeightedScore project

  public Project(String project_name, StatusEnum project_status) {
    this.project_name = project_name;
    this.project_status = project_status.getEnumString();
  }

  public long getRole_id() {
    return role_id;
  }

  public void setRole_id(long role_id) {
    this.role_id = role_id;
  }

  public String getProject_name() {
    return project_name;
  }

  public void setProject_name(String project_name) {
    this.project_name = project_name;
  }

  public String getProject_status() {
    return project_status;
  }

  public void setProject_status(String project_status) {
    this.project_status = project_status;
  }

  public ProjectDetail getProjectDetail() {
    return projectDetail;
  }

  public void setProjectDetail(ProjectDetail projectDetail) {
    this.projectDetail = projectDetail;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Set<ProjectSystem> getProjectSystemSet() {
    return projectSystemSet;
  }

  public void setProjectSystemSet(Set<ProjectSystem> projectSystemSet) {
    for (ProjectSystem obj : projectSystemSet) {
      this.getProjectSystemSet().add(obj);
      obj.setProjectAttribute(this);
    }
  }

  public Set<ProjectPartner> getProjectPartnerSet() {
    return projectPartnerSet;
  }

  public void setProjectPartnerSet(Set<ProjectPartner> projectPartnerSet) {
    for (ProjectPartner obj : projectPartnerSet) {
      this.getProjectPartnerSet().add(obj);
      obj.setProjectAttribute(this);
    }
  }

  public FlaggedAsset getFlaggedAsset() {
    return flaggedAsset;
  }

  public void setFlaggedAsset(FlaggedAsset flaggedAsset) {
    this.flaggedAsset = flaggedAsset;
  }

  public UserProfile getUserProfileAttribute() {
    return userProfileAttribute;
  }

  public void setUserProfileAttribute(UserProfile userProfileAttribute) {
    this.userProfileAttribute = userProfileAttribute;
  }

}
