package com.grahamtech.eis.pojos;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "project_systems")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
public class ProjectSystem extends RiskMetrics implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "system_id")
  private long system_id;
  private String system_name;
  private BigDecimal latitude;
  private BigDecimal longitude;

  // START RISK METRICS
  // @Column(name = "summary")
  // private String summary;
  // @Column(name = "score")
  // private BigDecimal score;
  // @Column(name = "access_vector")
  // @Enumerated(EnumType.STRING)
  // private AccessVectorEnum access_vector; // access-vector
  // @Column(name = "access_complexity")
  // @Enumerated(EnumType.STRING)
  // private HighToLowEnum access_complexity; // access-complexity
  // @Column(name = "authentication")
  // @Enumerated(EnumType.STRING)
  // private InstanceCountEnum authentication; // authentication
  // @Column(name = "confidentiality_impact")
  // @Enumerated(EnumType.STRING)
  // private PartialToCompleteEnum confidentiality_impact; //
  // confidentiality-impact
  // @Column(name = "integrity_impact")
  // @Enumerated(EnumType.STRING)
  // private PartialToCompleteEnum integrity_impact; // integrity-impact
  // @Column(name = "availability_impact")
  // @Enumerated(EnumType.STRING)
  // private PartialToCompleteEnum availability_impact; // availability-impact
  // @Column(name = "last_modified_date", columnDefinition = "DATETIME")
  // @Temporal(TemporalType.TIMESTAMP)
  // @JsonSerialize(using = DateSerializer.class)
  // private Date last_modified_date; // last-modified-datetime
  // END RISK METRICS

  @ManyToOne
  @JoinColumn(name = "project_fk_systems", insertable = false, updatable = false)
  @JsonBackReference
  private Project projectAttribute;

  @ManyToOne
  @JoinColumn(name = "last_modified_by_fk_systems")
  @JsonBackReference
  private UserProfile last_modified_by_fk_systems;

  @OneToMany(mappedBy = "projectSystemAttribute", fetch = FetchType.EAGER)
  @JsonManagedReference
  private Set<SystemVulnerability> systemVulnerabilitySet;

  @OneToMany(mappedBy = "projectSystemAttribute", fetch = FetchType.EAGER)
  @JsonManagedReference
  private Set<SystemProduct> systemProductSet;

  @OneToMany(mappedBy = "nvdEntrySystemAttribute", fetch = FetchType.EAGER)
  @JsonManagedReference
  private Set<NVDEntryMessage> systemNVDEntryMessageSet;

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
    for (SystemVulnerability obj : systemVulnerabilitySet) {
      this.getSystemVulnerabilitySet().add(obj);
      obj.setProjectSystemAttribute(this);
    }
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
    for (SystemProduct obj : systemProductSet) {
      this.getSystemProductSet().add(obj);
      obj.setProjectSystemAttribute(this);
    }
  }

  public FlaggedAsset getFlaggedAsset() {
    return flaggedAsset;
  }

  public void setFlaggedAsset(FlaggedAsset flaggedAsset) {
    this.flaggedAsset = flaggedAsset;
  }

  public Set<NVDEntryMessage> getSystemNVDEntryMessageSet() {
    return systemNVDEntryMessageSet;
  }

  public void setSystemNVDEntryMessageSet(
      Set<NVDEntryMessage> systemNVDEntryMessageSet) {
    this.systemNVDEntryMessageSet = systemNVDEntryMessageSet;
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

  // public String getSummary() {
  // return summary;
  // }
  //
  // public void setSummary(String summary) {
  // this.summary = summary;
  // }
  //
  // public BigDecimal getScore() {
  // return score;
  // }
  //
  // public void setScore(BigDecimal score) {
  // this.score = score;
  // }
  //
  // public AccessVectorEnum getAccess_vector() {
  // return access_vector;
  // }
  //
  // public void setAccess_vector(AccessVectorEnum access_vector) {
  // this.access_vector = access_vector;
  // }
  //
  // public HighToLowEnum getAccess_complexity() {
  // return access_complexity;
  // }
  //
  // public void setAccess_complexity(HighToLowEnum access_complexity) {
  // this.access_complexity = access_complexity;
  // }
  //
  // public InstanceCountEnum getAuthentication() {
  // return authentication;
  // }
  //
  // public void setAuthentication(InstanceCountEnum authentication) {
  // this.authentication = authentication;
  // }
  //
  // public PartialToCompleteEnum getConfidentiality_impact() {
  // return confidentiality_impact;
  // }
  //
  // public void setConfidentiality_impact(
  // PartialToCompleteEnum confidentiality_impact) {
  // this.confidentiality_impact = confidentiality_impact;
  // }
  //
  // public PartialToCompleteEnum getIntegrity_impact() {
  // return integrity_impact;
  // }
  //
  // public void setIntegrity_impact(PartialToCompleteEnum integrity_impact) {
  // this.integrity_impact = integrity_impact;
  // }
  //
  // public PartialToCompleteEnum getAvailability_impact() {
  // return availability_impact;
  // }
  //
  // public void setAvailability_impact(PartialToCompleteEnum
  // availability_impact) {
  // this.availability_impact = availability_impact;
  // }
  //
  // public Date getLast_modified_date() {
  // return last_modified_date;
  // }
  //
  // public void setLast_modified_date(Date last_modified_date) {
  // this.last_modified_date = last_modified_date;
  // }
}
