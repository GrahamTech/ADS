package com.grahamtech.eis.pojos;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.grahamtech.eis.utilities.ConstantsUtil;
import com.grahamtech.eis.utilities.StringUtil;
import com.grahamtech.eis.utilities.enums.AccessVectorEnum;
import com.grahamtech.eis.utilities.enums.HighToLowEnum;
import com.grahamtech.eis.utilities.enums.InstanceCountEnum;
import com.grahamtech.eis.utilities.enums.PartialToCompleteEnum;
import com.grahamtech.eis.utilities.enums.StatusEnum;

@Entity
@Table(name = "system_products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
public class SystemProduct extends RiskMetrics implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private long product_id;
  @Column(name = "product_name")
  private String product_name;
  @Column(name = "product_state")
  @Enumerated(EnumType.STRING)
  private StatusEnum product_state;
  @Column(name = "lessons_learned")
  private String lessons_learned;

  // START RISK METRICS
  @Column(name = "summary")
  private String summary;
  @Column(name = "source")
  private String source;
  @Column(name = "score")
  private BigDecimal score;
  @Column(name = "access_vector")
  @Enumerated(EnumType.STRING)
  private AccessVectorEnum access_vector; // access-vector
  @Column(name = "access_complexity")
  @Enumerated(EnumType.STRING)
  private HighToLowEnum access_complexity; // access-complexity
  @Column(name = "authentication")
  @Enumerated(EnumType.STRING)
  private InstanceCountEnum authentication; // authentication
  @Column(name = "confidentiality_impact")
  @Enumerated(EnumType.STRING)
  private PartialToCompleteEnum confidentiality_impact; // confidentiality-impact
  @Column(name = "integrity_impact")
  @Enumerated(EnumType.STRING)
  private PartialToCompleteEnum integrity_impact; // integrity-impact
  @Column(name = "availability_impact")
  @Enumerated(EnumType.STRING)
  private PartialToCompleteEnum availability_impact; // availability-impact
  @Column(name = "generated_on_date", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date generated_on_date; // generated-on-datetime
  @Column(name = "published_date", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date published_date; // published-datetime
  @Column(name = "last_modified_date", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date last_modified_date; // last-modified-datetime
  // END RISK METRICS

  @ManyToOne
  @JoinColumn(name = "last_modified_by_fk_products")
  @JsonBackReference
  private UserProfile last_modified_by_fk_products;

  @ManyToOne
  @JoinColumn(name = "project_system_fk_products", insertable = false, updatable = false)
  @JsonBackReference
  private ProjectSystem projectSystemAttribute;

  @ManyToOne
  @JoinColumn(name = "flagged_by_fk_products")
  @JsonBackReference
  private FlaggedAsset flaggedAsset;

  public SystemProduct() {
    // default constructor
  }

  @Override
  public String toString() {
    return "Feed [product_id= "
        + product_id
        + ", product_name= "
        + this.getProduct_name()
        + ", product_state= "
        + this.getProduct_state().getEnumString()
        + ", score= "
        + this.getScore()
        + ", access_vector="
        + this.getAccess_vector()
        + ", access_complexity= "
        + this.getAccess_complexity()
        + ", authentication= "
        + this.getAuthentication()
        + ", confidentiality_impact= "
        + this.getConfidentiality_impact()
        + ", integrity_impact= "
        + this.getIntegrity_impact()
        + ", availability_impact= "
        + this.getAvailability_impact()
        + ", source= "
        + this.getSource()
        + ", generated_on_date= "
        + ((this.getGenerated_on_date() == null) ? "N/A" : this
            .getGenerated_on_date_String())
        + ", pub_date= "
        + ((this.getPublished_date() == null) ? "N/A" : this
            .getPublished_date_String())
        + ", last_mod_date= "
        + ((this.getLast_modified_date() == null) ? "N/A" : this
            .getLast_modified_date_String()) + ", summary= "
        + this.getSummary() + ", lesson_learned= " + this.getLessons_learned()
        + "]";
  }

  public SystemProduct(String product_name) {
    this.product_name = product_name;
  }

  public long getProduct_id() {
    return product_id;
  }

  public void setProduct_id(long product_id) {
    this.product_id = product_id;
  }

  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }

  public StatusEnum getProduct_state() {
    return product_state;
  }

  public void setProduct_state(StatusEnum product_state) {
    this.product_state = product_state;
  }

  public String getLessons_learned() {
    return lessons_learned;
  }

  public void setLessons_learned(String lessons_learned) {
    this.lessons_learned = lessons_learned;
  }

  public UserProfile getLast_modified_by_fk_products() {
    return last_modified_by_fk_products;
  }

  public void setLast_modified_by_fk_products(
      UserProfile last_modified_by_fk_products) {
    this.last_modified_by_fk_products = last_modified_by_fk_products;
  }

  public ProjectSystem getProjectSystemAttribute() {
    return projectSystemAttribute;
  }

  public void setProjectSystemAttribute(ProjectSystem projectSystemAttribute) {
    this.projectSystemAttribute = projectSystemAttribute;
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

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
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

  public Date getGenerated_on_date() {
    return generated_on_date;
  }

  public void setGenerated_on_date(Date generated_on_date) {
    this.generated_on_date = generated_on_date;
  }

  public Date getPublished_date() {
    return published_date;
  }

  public void setPublished_date(Date published_date) {
    this.published_date = published_date;
  }

  public Date getLast_modified_date() {
    return last_modified_date;
  }

  public void setLast_modified_date(Date last_modified_date) {
    this.last_modified_date = last_modified_date;
  }

  public String getGenerated_on_date_String() {
    return StringUtil.dateToString(this.getGenerated_on_date(),
        ConstantsUtil.DATE_FORMAT);
  }

  public String getPublished_date_String() {
    return StringUtil.dateToString(this.getPublished_date(),
        ConstantsUtil.DATE_FORMAT);
  }

  public String getLast_modified_date_String() {
    return StringUtil.dateToString(this.getLast_modified_date(),
        ConstantsUtil.DATE_FORMAT);
  }
}
