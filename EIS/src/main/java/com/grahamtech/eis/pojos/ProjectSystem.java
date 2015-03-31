package com.grahamtech.eis.pojos;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Entity
@Table(name = "project_systems")
public class ProjectSystem implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "system_id")
  private long system_id;
  private String system_name;
  private String description;
  @Column(name = "last_modified_date", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date last_modified_date;

  @ManyToOne
  @JoinColumn(name = "project_fk_systems", insertable = false, updatable = false)
  @JsonBackReference
  private Project projectAttribute;

  @ManyToOne
  @JoinColumn(name = "last_modified_by_fk_systems")
  @JsonBackReference
  private UserProfile last_modified_by_fk_systems;

  @OneToMany(mappedBy = "projectSystemAttribute", fetch = FetchType.EAGER, targetEntity = SystemVulnerability.class)
  @JsonManagedReference
  private Set<SystemVulnerability> systemVulnerabilitySet;

  @OneToMany(mappedBy = "projectSystemAttribute", fetch = FetchType.EAGER, targetEntity = SystemProduct.class)
  @JsonManagedReference
  private Set<SystemProduct> systemProductSet;

  @ManyToOne
  @JoinColumn(name = "flagged_by_fk_systems")
  @JsonBackReference
  private FlaggedAsset flaggedAsset;

  public ProjectSystem() {
    // default constructor
  }

  public ProjectSystem(String system_name) {
    this.system_name = system_name;
  }

  public long getSystem_id() {
    return system_id;
  }

  public void setSystem_id(long system_id) {
    this.system_id = system_id;
  }

  public String getSystem_name() {
    return system_name;
  }

  public void setSystem_name(String system_name) {
    this.system_name = system_name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getLast_modified_date() {
    return last_modified_date;
  }

  public void setLast_modified_date(Date last_modified_date) {
    this.last_modified_date = last_modified_date;
  }

  // public Project getProject_fk_systems() {
  // return project_fk_systems;
  // }
  //
  // public void setProject_fk_systems(Project project_fk_systems) {
  // this.project_fk_systems = project_fk_systems;
  // }

  public UserProfile getLast_modified_by_fk_systems() {
    return last_modified_by_fk_systems;
  }

  public void setLast_modified_by_fk_systems(
      UserProfile last_modified_by_fk_systems) {
    this.last_modified_by_fk_systems = last_modified_by_fk_systems;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Set<SystemVulnerability> getSystemVulnerabilitySet() {
    return systemVulnerabilitySet;
  }

  public void setSystemVulnerabilitySet(
      Set<SystemVulnerability> systemVulnerabilitySet) {
    this.systemVulnerabilitySet = systemVulnerabilitySet;
  }

  public Project getProjectAttribute() {
    return projectAttribute;
  }

  public void setProjectAttribute(Project projectAttribute) {
    this.projectAttribute = projectAttribute;
  }

  public Set<SystemProduct> getSystemProductSet() {
    return systemProductSet;
  }

  public void setSystemProductSet(Set<SystemProduct> systemProductSet) {
    this.systemProductSet = systemProductSet;
  }

  public FlaggedAsset getFlaggedAsset() {
    return flaggedAsset;
  }

  public void setFlaggedAsset(FlaggedAsset flaggedAsset) {
    this.flaggedAsset = flaggedAsset;
  }

}