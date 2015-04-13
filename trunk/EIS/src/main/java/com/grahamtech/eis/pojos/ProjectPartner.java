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
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.grahamtech.eis.utilities.enums.HighToLowEnum;
import com.grahamtech.eis.utilities.enums.StatusEnum;

@Entity
@Table(name = "project_partners")
public class ProjectPartner implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "project_partner_id")
  private long project_partner_id;
  private String project_partner_name;
  private String project_partner_details;
  @Enumerated(EnumType.STRING)
  private StatusEnum on_site;
  @Enumerated(EnumType.STRING)
  private StatusEnum project_participation_status;
  private String lessons_learned;
  @Column(name = "last_modified_date", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date last_modified_date;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum corp_leadership_history_rating;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum financial_viability_rating;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum market_and_labeling_rating;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum physical_security_rating;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum cyber_security_rating;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum insider_threat_rating;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum regional_stability_rating;
  private BigDecimal score;
  
  @ManyToOne
  @JoinColumn(name = "last_modified_by_fk_partners")
  @JsonBackReference
  private UserProfile last_modified_by_fk_partners;

  @ManyToOne
  @JoinColumn(name = "project_fk_partners", insertable = false, updatable = false)
  @JsonBackReference
  private Project projectAttribute;
  
  @ManyToOne
  @JoinColumn(name = "flagged_by_fk_partners")
  @JsonBackReference
  private FlaggedAsset flaggedAsset;
  
  public ProjectPartner() {
    // default constructor
  }

  public ProjectPartner(String project_partner_name) {
    this.project_partner_name = project_partner_name;
  }

  public long getProject_partner_id() {
    return project_partner_id;
  }

  public void setProject_partner_id(long project_partner_id) {
    this.project_partner_id = project_partner_id;
  }

  public String getProject_partner_name() {
    return project_partner_name;
  }

  public void setProject_partner_name(String project_partner_name) {
    this.project_partner_name = project_partner_name;
  }

  public String getProject_partner_details() {
    return project_partner_details;
  }

  public void setProject_partner_details(String project_partner_details) {
    this.project_partner_details = project_partner_details;
  }

  public StatusEnum getOn_site() {
    return on_site;
  }

  public void setOn_site(StatusEnum on_site) {
    this.on_site = on_site;
  }

  public StatusEnum getProject_participation_status() {
    return project_participation_status;
  }

  public void setProject_participation_status(
      StatusEnum project_participation_status) {
    this.project_participation_status = project_participation_status;
  }

  public String getLessons_learned() {
    return lessons_learned;
  }

  public void setLessons_learned(String lessons_learned) {
    this.lessons_learned = lessons_learned;
  }

  public Date getLast_modified_date() {
    return last_modified_date;
  }

  public void setLast_modified_date(Date last_modified_date) {
    this.last_modified_date = last_modified_date;
  }

  public HighToLowEnum getCorp_leadership_history_rating() {
    return corp_leadership_history_rating;
  }

  public void setCorp_leadership_history_rating(
      HighToLowEnum corp_leadership_history_rating) {
    this.corp_leadership_history_rating = corp_leadership_history_rating;
  }

  public HighToLowEnum getFinancial_viability_rating() {
    return financial_viability_rating;
  }

  public void setFinancial_viability_rating(
      HighToLowEnum financial_viability_rating) {
    this.financial_viability_rating = financial_viability_rating;
  }

  public HighToLowEnum getMarket_and_labeling_rating() {
    return market_and_labeling_rating;
  }

  public void setMarket_and_labeling_rating(
      HighToLowEnum market_and_labeling_rating) {
    this.market_and_labeling_rating = market_and_labeling_rating;
  }

  public HighToLowEnum getPhysical_security_rating() {
    return physical_security_rating;
  }

  public void setPhysical_security_rating(HighToLowEnum physical_security_rating) {
    this.physical_security_rating = physical_security_rating;
  }

  public HighToLowEnum getCyber_security_rating() {
    return cyber_security_rating;
  }

  public void setCyber_security_rating(HighToLowEnum cyber_security_rating) {
    this.cyber_security_rating = cyber_security_rating;
  }

  public HighToLowEnum getInsider_threat_rating() {
    return insider_threat_rating;
  }

  public void setInsider_threat_rating(HighToLowEnum insider_threat_rating) {
    this.insider_threat_rating = insider_threat_rating;
  }

  public HighToLowEnum getRegional_stability_rating() {
    return regional_stability_rating;
  }

  public void setRegional_stability_rating(
      HighToLowEnum regional_stability_rating) {
    this.regional_stability_rating = regional_stability_rating;
  }

  public UserProfile getLast_modified_by_fk_partners() {
    return last_modified_by_fk_partners;
  }

  public void setLast_modified_by_fk_partners(
      UserProfile last_modified_by_fk_partners) {
    this.last_modified_by_fk_partners = last_modified_by_fk_partners;
  }

  public Project getProjectAttribute() {
    return projectAttribute;
  }

  public void setProjectAttribute(Project projectAttribute) {
    this.projectAttribute = projectAttribute;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public FlaggedAsset getFlaggedAsset() {
    return flaggedAsset;
  }

  public void setFlaggedAsset(FlaggedAsset flaggedAsset) {
    this.flaggedAsset = flaggedAsset;
  }

  public BigDecimal getScore() {
    return score;
  }

  public void setScore(BigDecimal score) {
    this.score = score;
  }

}
