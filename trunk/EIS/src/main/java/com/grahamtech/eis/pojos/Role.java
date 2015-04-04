package com.grahamtech.eis.pojos;

import java.util.HashSet;
import java.util.Set;



//import javax.persistence.CascadeType;
//import javax.persistence.ElementCollection;
//import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "roles")
public class Role implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private long role_id;
  @Column(name = "role_name")
  @Enumerated(EnumType.STRING)
  private RolesEnum role_name;
  @Column(name = "role_description")
  private String role_description;
  @Column(name = "role_status")
  private String role_status;

  // This end is not the owner. It's the inverse of the UserProfile.roleSet
  // association
  @ManyToMany(mappedBy = "roleSet")
  @JsonBackReference
  private Set<UserProfile> userProfileSet = new HashSet<UserProfile>();

  public Role() {
    // default constructor
  }

  public Role(RolesEnum role_name, StatusEnum status) {
    this.role_name = role_name;
    this.role_status = status.getEnumString();
  }

  public long getRole_id() {
    return role_id;
  }

  public void setRole_id(long role_id) {
    this.role_id = role_id;
  }

  public String getRole_description() {
    return role_description;
  }

  public void setRole_description(String role_description) {
    this.role_description = role_description;
  }

  public String getRole_status() {
    return role_status;
  }

  public void setRole_status(StatusEnum role_status) {
    this.role_status = role_status.name();
  }

  public RolesEnum getRole_name() {
    return role_name;
  }

  public void setRole_name(RolesEnum role_name) {
    this.role_name = role_name;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public void setRole_status(String role_status) {
    this.role_status = role_status;
  }

  public Set<UserProfile> getUserProfileSet() {
    return userProfileSet;
  }

  public void setUserProfileSet(Set<UserProfile> userProfileSet) {
    for (UserProfile obj : userProfileSet) {
      this.getUserProfileSet().add(obj);
      obj.getRoleSet().add(this);
    }
  }

}
