package com.grahamtech.eis.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "nvd_entry_vulnerable_software")
public class NVDEntryVulnerableSoftware implements java.io.Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vulnerable_software_id")
  private long vulnerable_software_id;

  private String vulnerable_software_name;

  @ManyToOne(optional = false)
  @JoinColumn(name = "entry_message_id_fk")
  // @JoinColumn(name = "vulnerable_software_id")
  @JsonBackReference
  private NVDEntryMessage nvdEntryMessageAttribute;

  public NVDEntryVulnerableSoftware() {
  }

  public NVDEntryVulnerableSoftware(String vulnerable_software_name) {
    this.vulnerable_software_name = vulnerable_software_name;
  }

  @Override
  public String toString() {
    return "Vuln Soft [vulnerable_software_name= " + vulnerable_software_name
        + "]";
  }

  public long getVulnerable_software_id() {
    return vulnerable_software_id;
  }

  public void setVulnerable_software_id(long vulnerable_software_id) {
    this.vulnerable_software_id = vulnerable_software_id;
  }

  public String getVulnerable_software_name() {
    return vulnerable_software_name;
  }

  public void setVulnerable_software_name(String vulnerable_software_name) {
    this.vulnerable_software_name = vulnerable_software_name;
  }

  public NVDEntryMessage getNvdEntryMessageAttribute() {
    return nvdEntryMessageAttribute;
  }

  public void setNvdEntryMessageAttribute(
      NVDEntryMessage nvdEntryMessageAttribute) {
    this.nvdEntryMessageAttribute = nvdEntryMessageAttribute;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
