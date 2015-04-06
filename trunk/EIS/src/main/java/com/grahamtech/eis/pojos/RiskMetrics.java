package com.grahamtech.eis.pojos;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
//import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.grahamtech.eis.utilities.enums.AccessVectorEnum;
import com.grahamtech.eis.utilities.enums.HighToLowEnum;
import com.grahamtech.eis.utilities.enums.InstanceCountEnum;
import com.grahamtech.eis.utilities.enums.PartialToCompleteEnum;

//@Embeddable
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RiskMetrics implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private BigDecimal score;
  @Enumerated(EnumType.STRING)
  private AccessVectorEnum access_vector; // access-vector
  @Enumerated(EnumType.STRING)
  private HighToLowEnum access_complexity; // access-complexity
  @Enumerated(EnumType.STRING)
  private InstanceCountEnum authentication; // authentication
  @Enumerated(EnumType.STRING)
  private PartialToCompleteEnum confidentiality_impact; // confidentiality-impact
  @Enumerated(EnumType.STRING)
  private PartialToCompleteEnum integrity_impact; // integrity-impact
  @Enumerated(EnumType.STRING)
  private PartialToCompleteEnum availability_impact; // availability-impact

  private String source = "";
  private String summary = "";

  @Column(name = "generated_on_datetime", nullable = false, columnDefinition = "Timestamp default CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date generated_on_datetime; // generated-on-datetime
  @Column(name = "published_datetime", nullable = false, columnDefinition = "Timestamp default CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date published_datetime; // published-datetime
  @Column(name = "last_modified_datetime", nullable = false, columnDefinition = "Timestamp default CURRENT_TIMESTAMP")
  // @Generated(value = GenerationTime.ALWAYS)
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date last_modified_datetime; // last-modified-datetime

  private BigDecimal latitude;
  private BigDecimal longitude;

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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Date getGenerated_on_datetime() {
    return generated_on_datetime;
  }

  public void setGenerated_on_datetime(Date generated_on_datetime) {
    this.generated_on_datetime = generated_on_datetime;
  }

  public Date getPublished_datetime() {
    return published_datetime;
  }

  public void setPublished_datetime(Date published_datetime) {
    this.published_datetime = published_datetime;
  }

  public Date getLast_modified_datetime() {
    return last_modified_datetime;
  }

  public void setLast_modified_datetime(Date last_modified_datetime) {
    this.last_modified_datetime = last_modified_datetime;
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

}
