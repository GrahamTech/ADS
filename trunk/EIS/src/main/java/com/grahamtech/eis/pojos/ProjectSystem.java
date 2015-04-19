package com.grahamtech.eis.pojos;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
import com.grahamtech.eis.utilities.enums.RiskMetricsCalcEnum;

@Entity
@Table(name = "project_systems")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
public class ProjectSystem implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "system_id")
  private long system_id;
  private String system_name;
  private BigDecimal latitude;
  private BigDecimal longitude;
  @Column(name = "score")
  private BigDecimal score;

  // START RISK METRICS
  // @Column(name = "summary")
  // private String summary;
  // @Enumerated(EnumType.STRING)
  // private VeryHighToVeryLowEnum system_weight;
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

  public Map<RiskMetricsCalcEnum, Double> getWeightedScore() {
    Map<RiskMetricsCalcEnum, Double> calculationMap =
        new TreeMap<RiskMetricsCalcEnum, Double>();

    int riskCount = 0;
    Double system_categories_weighted_totals = 0.0;
    Double category_weight_totals_nvd = 0.0;
    Double category_weight_totals_vul = 0.0;
    Double category_weight_totals_prod = 0.0;
    Double category_score_totals = 0.0;
    Double category_weighted_score_totals = 0.0;

    for (NVDEntryMessage nvdEntryMessage : this.getSystemNVDEntryMessageSet()) {
      Map<RiskMetricsCalcEnum, Double> map =
          nvdEntryMessage.getWeightedScores();
      
      if (category_weight_totals_nvd == 0.0) {
        // will be the same so only do it once.
        category_weight_totals_nvd =
            map.get(RiskMetricsCalcEnum.criteria_weight_total);
      }

      category_score_totals =
          category_score_totals
              + map.get(RiskMetricsCalcEnum.category_score_totals);

      category_weighted_score_totals =
          category_weighted_score_totals
              + map.get(RiskMetricsCalcEnum.category_weighted_score_totals);

      riskCount++;
    }// end for each

    for (SystemProduct systemProduct : this.getSystemProductSet()) {
      Map<RiskMetricsCalcEnum, Double> map = systemProduct.getWeightedScores();

      if (category_weight_totals_prod == 0) {
        // will be the same so only do it once.
        category_weight_totals_prod =
            map.get(RiskMetricsCalcEnum.criteria_weight_total);
      }

      category_score_totals =
          category_score_totals
              + map.get(RiskMetricsCalcEnum.category_score_totals);

      category_weighted_score_totals =
          category_weighted_score_totals
              + map.get(RiskMetricsCalcEnum.category_weighted_score_totals);

      riskCount++;
    }// end for each

    for (SystemVulnerability systemVulnerability : this
        .getSystemVulnerabilitySet()) {
      Map<RiskMetricsCalcEnum, Double> map =
          systemVulnerability.getWeightedScores();

      if (category_weight_totals_vul == 0) {
        // will be the same so only do it once.
        category_weight_totals_vul =
            map.get(RiskMetricsCalcEnum.criteria_weight_total);
      }

      category_score_totals =
          category_score_totals
              + map.get(RiskMetricsCalcEnum.category_score_totals);

      category_weighted_score_totals =
          category_weighted_score_totals
              + map.get(RiskMetricsCalcEnum.category_weighted_score_totals);

      riskCount++;
    }// end for each

    system_categories_weighted_totals =
        category_weight_totals_nvd + category_weight_totals_prod
            + category_weight_totals_vul;
    calculationMap.put(RiskMetricsCalcEnum.system_categories_weighted_totals, system_categories_weighted_totals);
    
    calculationMap.put(RiskMetricsCalcEnum.category_score_totals, category_score_totals);
    
    calculationMap.put(RiskMetricsCalcEnum.category_weighted_score_totals, category_weighted_score_totals);
    
    Double system_weighted_score =
        category_weighted_score_totals * system_categories_weighted_totals;
    calculationMap.put(RiskMetricsCalcEnum.system_weighted_score, system_weighted_score);
    
    Double system_score = system_weighted_score / riskCount;
    calculationMap.put(RiskMetricsCalcEnum.system_score, system_score);

    this.setScore(new BigDecimal(system_score));

    return calculationMap;
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

  public BigDecimal getScore() {
    return score;
  }

  public void setScore(BigDecimal score) {
    this.score = score;
  }

}
