package com.grahamtech.eis.pojos;

//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.persistence.Access;
//import javax.persistence.AccessType;
//import javax.persistence.Column;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.DateSerializer;
//import com.grahamtech.eis.utilities.enums.AccessVectorEnum;
//import com.grahamtech.eis.utilities.enums.HighToLowEnum;
//import com.grahamtech.eis.utilities.enums.InstanceCountEnum;
//import com.grahamtech.eis.utilities.enums.PartialToCompleteEnum;

//@MappedSuperclass
//@Access(AccessType.FIELD)
public abstract class RiskMetrics implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  // @Column(name = "summary")
  // private String summary;
  // @Column(name = "source")
  // private String source;
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

  // public String getSummary() {
  // return summary;
  // }
  //
  // public void setSummary(String summary) {
  // this.summary = summary;
  // }

  // @Column(name = "generated_on_date", columnDefinition = "DATETIME")
  // @Temporal(TemporalType.TIMESTAMP)
  // @JsonSerialize(using = DateSerializer.class)
  // private Date generated_on_date; // generated-on-datetime
  // @Column(name = "published_date", columnDefinition = "DATETIME")
  // @Temporal(TemporalType.TIMESTAMP)
  // @JsonSerialize(using = DateSerializer.class)
  // private Date published_date; // published-datetime
  // // @Column(name = "last_modified_date", columnDefinition =
  // // "Timestamp default CURRENT_TIMESTAMP")
  // @Column(name = "last_modified_date", columnDefinition = "DATETIME")
  // @Temporal(TemporalType.TIMESTAMP)
  // @JsonSerialize(using = DateSerializer.class)
  // private Date last_modified_date; // last-modified-datetime


}
