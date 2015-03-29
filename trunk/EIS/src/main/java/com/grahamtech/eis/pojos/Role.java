package com.grahamtech.eis.pojos;

//import java.util.Set;

//import javax.persistence.CascadeType;
//import javax.persistence.ElementCollection;
//import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private long role_id;
  @Column(name = "role_name")
  private String role_name;
  @Column(name = "role_description")
  private String role_description;
  @Column(name = "role_status")
  private String role_status;

  // @ManyToMany(mappedBy="roles")
  // @JsonBackReference
  // private Set<UserProfile> users = new HashSet<UserProfile>();

  public Role() {
    // default constructor
  }

  public Role(RolesEnum role_name, StatusEnum status) {
    this.role_name = role_name.getEnumString();
    this.role_status = status.getEnumString();
  }

  public long getRole_id() {
    return role_id;
  }

  public void setRole_id(long role_id) {
    this.role_id = role_id;
  }

  public String getRole_name() {
    return this.role_name;
  }

  public void setRole_name(RolesEnum role_name) {
    this.role_name = role_name.name();
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

  // public Set<UserProfile> getUserProfileSet() {
  // return userProfileSet;
  // }
  //
  // public void setUserProfileSet(Set<UserProfile> userProfileSet) {
  // this.userProfileSet = userProfileSet;
  // }

}
