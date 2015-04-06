package com.grahamtech.eis.pojos;

//import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.grahamtech.eis.utilities.enums.StatusEnum;

@Entity
@Table(name = "system_products")
public class SystemProduct extends RiskMetrics implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private long product_id;
  private String product_name;
  @Enumerated(EnumType.STRING)
  private StatusEnum product_state;
  private String lessons_learned;

  // private String product_version;
  // private String product_type;
  // private String product_description;
  //
  // @Column(name = "last_modified_date", columnDefinition = "DATETIME")
  // @Temporal(TemporalType.TIMESTAMP)
  // @JsonSerialize(using = DateSerializer.class)
  // private Date last_modified_date;
  // private BigDecimal severity;
  // private BigDecimal cvss_base_score;
  // private BigDecimal cvss_score;
  // private BigDecimal cvss_exploit_sub_score;
  // private BigDecimal cvss_impact_sub_score;
  // private BigDecimal cvss_version;
  // @Column(name = "cvss_publish_date", columnDefinition = "DATETIME")
  // @Temporal(TemporalType.TIMESTAMP)
  // @JsonSerialize(using = DateSerializer.class)
  // private Date cvss_publish_date;
  // private BigDecimal latitude;
  // private BigDecimal longitude;

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
        + ", pub date= "
        + ((this.getPublished_datetime() == null) ? "N/A" : this
            .getPublished_datetime().toString())
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
        + ", generated_on_datetime= "
        + ((this.getGenerated_on_datetime() == null) ? "N/A" : this
            .getGenerated_on_datetime().toString()) + ", summary= "
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

  // public String getProduct_version() {
  // return product_version;
  // }
  //
  // public void setProduct_version(String product_version) {
  // this.product_version = product_version;
  // }
  //
  // public String getProduct_type() {
  // return product_type;
  // }
  //
  // public void setProduct_type(String product_type) {
  // this.product_type = product_type;
  // }
  //
  // public String getProduct_description() {
  // return product_description;
  // }
  //
  // public void setProduct_description(String product_description) {
  // this.product_description = product_description;
  // }

  public String getLessons_learned() {
    return lessons_learned;
  }

  public void setLessons_learned(String lessons_learned) {
    this.lessons_learned = lessons_learned;
  }

  // public Date getLast_modified_date() {
  // return last_modified_date;
  // }
  //
  // public void setLast_modified_date(Date last_modified_date) {
  // this.last_modified_date = last_modified_date;
  // }
  //
  // public BigDecimal getSeverity() {
  // return severity;
  // }
  //
  // public void setSeverity(BigDecimal severity) {
  // this.severity = severity;
  // }
  //
  // public BigDecimal getCvss_base_score() {
  // return cvss_base_score;
  // }
  //
  // public void setCvss_base_score(BigDecimal cvss_base_score) {
  // this.cvss_base_score = cvss_base_score;
  // }
  //
  // public BigDecimal getCvss_score() {
  // return cvss_score;
  // }
  //
  // public void setCvss_score(BigDecimal cvss_score) {
  // this.cvss_score = cvss_score;
  // }
  //
  // public BigDecimal getCvss_exploit_sub_score() {
  // return cvss_exploit_sub_score;
  // }
  //
  // public void setCvss_exploit_sub_score(BigDecimal cvss_exploit_sub_score) {
  // this.cvss_exploit_sub_score = cvss_exploit_sub_score;
  // }
  //
  // public BigDecimal getCvss_impact_sub_score() {
  // return cvss_impact_sub_score;
  // }
  //
  // public void setCvss_impact_sub_score(BigDecimal cvss_impact_sub_score) {
  // this.cvss_impact_sub_score = cvss_impact_sub_score;
  // }
  //
  // public BigDecimal getCvss_version() {
  // return cvss_version;
  // }
  //
  // public void setCvss_version(BigDecimal cvss_version) {
  // this.cvss_version = cvss_version;
  // }
  //
  // public void setCvss_publish_date(Date cvss_publish_date) {
  // this.cvss_publish_date = cvss_publish_date;
  // }
  //
  // public BigDecimal getLatitude() {
  // return latitude;
  // }
  //
  // public void setLatitude(BigDecimal latitude) {
  // this.latitude = latitude;
  // }
  //
  // public BigDecimal getLongitude() {
  // return longitude;
  // }
  //
  // public void setLongitude(BigDecimal longitude) {
  // this.longitude = longitude;
  // }

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

}
