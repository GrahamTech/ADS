package com.grahamtech.eis.pojos;

//import java.util.Set;

//import javax.persistence.CascadeType;
//import javax.persistence.ElementCollection;
//import javax.persistence.Embeddable;
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

@Entity
@Table(name = "flagged_assets")
public class FlaggedAsset implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "flagged_id")
  private long flagged_id;
  private String flagged_reason;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum flagged_risk_state;
  @Column(name = "flagged_date", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date flagged_date;
  @Enumerated(EnumType.STRING)
  private HighToLowEnum unflagged_risk_state;
  @Column(name = "unflagged_date", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date unflagged_date;

  @ManyToOne
  @JoinColumn(name = "flagged_by_fk_assets")
  @JsonBackReference
  private UserProfile flaggedByUserProfile;

  // TODO figure out why this is being reported as No row exists
  // @ManyToOne
  // @JoinColumn(name = "unflagged_by_fk_assets")
  // private UserProfile unflaggedByUserProfile;

  public FlaggedAsset() {
    // default constructor
  }

  public long getFlagged_id() {
    return flagged_id;
  }

  public void setFlagged_id(long flagged_id) {
    this.flagged_id = flagged_id;
  }

  public String getFlagged_reason() {
    return flagged_reason;
  }

  public void setFlagged_reason(String flagged_reason) {
    this.flagged_reason = flagged_reason;
  }

  public HighToLowEnum getFlagged_risk_state() {
    return flagged_risk_state;
  }

  public void setFlagged_risk_state(HighToLowEnum flagged_risk_state) {
    this.flagged_risk_state = flagged_risk_state;
  }

  public Date getFlagged_date() {
    return flagged_date;
  }

  public void setFlagged_date(Date flagged_date) {
    this.flagged_date = flagged_date;
  }

  public HighToLowEnum getUnflagged_risk_state() {
    return unflagged_risk_state;
  }

  public void setUnflagged_risk_state(HighToLowEnum unflagged_risk_state) {
    this.unflagged_risk_state = unflagged_risk_state;
  }

  public Date getUnflagged_date() {
    return unflagged_date;
  }

  public void setUnflagged_date(Date unflagged_date) {
    this.unflagged_date = unflagged_date;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public UserProfile getFlaggedByUserProfile() {
    return flaggedByUserProfile;
  }

  public void setFlaggedByUserProfile(UserProfile flaggedByUserProfile) {
    this.flaggedByUserProfile = flaggedByUserProfile;
  }

  // public UserProfile getUnflaggedByUserProfile() {
  // return unflaggedByUserProfile;
  // }
  //
  // public void setUnflaggedByUserProfile(UserProfile unflaggedByUserProfile) {
  // this.unflaggedByUserProfile = unflaggedByUserProfile;
  // }

}
