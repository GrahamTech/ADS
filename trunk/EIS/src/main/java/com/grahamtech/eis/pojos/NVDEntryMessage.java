package com.grahamtech.eis.pojos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

/*
 * Represents one RSS entry message
 */
@Entity
@Table(name = "nvd_entry_message")
public class NVDEntryMessage implements java.io.Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "entry_message_id")
  private long entry_message_id;
  @Column(name = "cve_id", unique = true)
  private String cve_id = ""; // cve-id
  @Column(name = "published_datetime", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date published_datetime; // published-datetime
  @Column(name = "last_modified_datetime", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date last_modified_datetime; // last-modified-datetime
  // CVE Base Metrics
  private BigDecimal score;
  private String access_vector = ""; // access-vector
  private String access_complexity = ""; // access-complexity
  private String authentication = ""; // authentication
  private String confidentiality_impact = ""; // confidentiality-impact
  private String integrity_impact = ""; // integrity-impact
  private String availability_impact = ""; // availability-impact
  private String source = "";
  @Column(name = "generated_on_datetime", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonSerialize(using = DateSerializer.class)
  private Date generated_on_datetime; // generated-on-datetime
  private String summary = "";

  @OneToMany(mappedBy = "nvdEntryMessageAttribute", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonManagedReference
  private Set<NVDEntryVulnerableSoftware> vulnerability_software_list =
      new HashSet<NVDEntryVulnerableSoftware>(); // product

  @ManyToOne(optional = false)
  @JoinColumn(name = "system_id_fk")
  @JsonBackReference
  private ProjectSystem nvdEntrySystemAttribute;

  public NVDEntryMessage() {
  }

  @Override
  public String toString() {
    return "Feed [cve_id= " + cve_id + ", pub date= "
        + ((published_datetime == null) ? "N/A" : published_datetime.toString())
        + ", score= " + score
        + ", access_vector=" + access_vector + ", access_complexity= "
        + access_complexity + ", authentication= " + authentication
        + ", confidentiality_impact= " + confidentiality_impact
        + ", integrity_impact= " + integrity_impact + ", availability_impact= "
        + availability_impact + ", source= " + source
        + ", generated_on_datetime= "
        + ((generated_on_datetime == null) ? "N/A" : generated_on_datetime
            .toString()) + ", summary= "
        + summary + ", product list= " + getSoftwareListToString() + "]";
  }

  public String getSoftwareListToString() {
    StringBuffer strBuffer = new StringBuffer();
    int count = 0;
    for (NVDEntryVulnerableSoftware software : vulnerability_software_list) {
      if (count > 0) {
        strBuffer.append(", ");
      }
      strBuffer.append(software.getVulnerable_software_name());
      count++;
    }
    return strBuffer.toString();
  }
  
  public long getEntry_message_id() {
    return entry_message_id;
  }

  public void setEntry_message_id(long entry_message_id) {
    this.entry_message_id = entry_message_id;
  }

  public String getCve_id() {
    return cve_id;
  }

  public void setCve_id(String cve_id) {
    this.cve_id = cve_id;
  }

  public Set<NVDEntryVulnerableSoftware> getVulnerability_software_list() {
    return vulnerability_software_list;
  }

  // used in Hibernate foreign key insertion
  public void setVulnerability_software_list(
      Set<NVDEntryVulnerableSoftware> vulnerability_software_list) {
    for (NVDEntryVulnerableSoftware vs : vulnerability_software_list) {
      this.getVulnerability_software_list().add(vs);
      vs.setNvdEntryMessageAttribute(this);
    }
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
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

  public BigDecimal getScore() {
    return score;
  }

  public void setScore(BigDecimal score) {
    this.score = score;
  }

  public String getAccess_vector() {
    return access_vector;
  }

  public void setAccess_vector(String access_vector) {
    this.access_vector = access_vector;
  }

  public String getAccess_complexity() {
    return access_complexity;
  }

  public void setAccess_complexity(String access_complexity) {
    this.access_complexity = access_complexity;
  }

  public String getAuthentication() {
    return authentication;
  }

  public void setAuthentication(String authentication) {
    this.authentication = authentication;
  }

  public String getConfidentiality_impact() {
    return confidentiality_impact;
  }

  public void setConfidentiality_impact(String confidentiality_impact) {
    this.confidentiality_impact = confidentiality_impact;
  }

  public String getIntegrity_impact() {
    return integrity_impact;
  }

  public void setIntegrity_impact(String integrity_impact) {
    this.integrity_impact = integrity_impact;
  }

  public String getAvailability_impact() {
    return availability_impact;
  }

  public void setAvailability_impact(String availability_impact) {
    this.availability_impact = availability_impact;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Date getGenerated_on_datetime() {
    return generated_on_datetime;
  }

  public void setGenerated_on_datetime(Date generated_on_datetime) {
    this.generated_on_datetime = generated_on_datetime;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public ProjectSystem getNvdEntrySystemAttribute() {
    return nvdEntrySystemAttribute;
  }

  public void setNvdEntrySystemAttribute(ProjectSystem nvdEntrySystemAttribute) {
    this.nvdEntrySystemAttribute = nvdEntrySystemAttribute;
  }

}

/*
 * <entry id="CVE-2015-0922"> <vuln:vulnerable-configuration
 * id="http://www.nist.gov/"> <cpe-lang:logical-test operator="OR"
 * negate="false"> <cpe-lang:fact-ref
 * name="cpe:/a:mcafee:epolicy_orchestrator:4.6.8"/> <cpe-lang:fact-ref
 * name="cpe:/a:mcafee:epolicy_orchestrator:5.0.0"/> <cpe-lang:fact-ref
 * name="cpe:/a:mcafee:epolicy_orchestrator:5.0.1"/> <cpe-lang:fact-ref
 * name="cpe:/a:mcafee:epolicy_orchestrator:5.1.0"/> <cpe-lang:fact-ref
 * name="cpe:/a:mcafee:epolicy_orchestrator:5.1.1"/> </cpe-lang:logical-test>
 * </vuln:vulnerable-configuration> <vuln:vulnerable-software-list>
 * <vuln:product>cpe:/a:mcafee:epolicy_orchestrator:4.6.8</vuln:product>
 * <vuln:product>cpe:/a:mcafee:epolicy_orchestrator:5.1.1</vuln:product>
 * <vuln:product>cpe:/a:mcafee:epolicy_orchestrator:5.1.0</vuln:product>
 * <vuln:product>cpe:/a:mcafee:epolicy_orchestrator:5.0.0</vuln:product>
 * <vuln:product>cpe:/a:mcafee:epolicy_orchestrator:5.0.1</vuln:product>
 * </vuln:vulnerable-software-list> <vuln:cve-id>CVE-2015-0922</vuln:cve-id>
 * <vuln
 * :published-datetime>2015-01-09T13:59:11.540-05:00</vuln:published-datetime>
 * <vuln
 * :last-modified-datetime>2015-02-11T14:14:22.023-05:00</vuln:last-modified
 * -datetime> <vuln:cvss> <cvss:base_metrics> <cvss:score>5.0</cvss:score>
 * <cvss:access-vector>NETWORK</cvss:access-vector>
 * <cvss:access-complexity>LOW</cvss:access-complexity>
 * <cvss:authentication>NONE</cvss:authentication>
 * <cvss:confidentiality-impact>PARTIAL</cvss:confidentiality-impact>
 * <cvss:integrity-impact>NONE</cvss:integrity-impact>
 * <cvss:availability-impact>NONE</cvss:availability-impact>
 * <cvss:source>http://nvd.nist.gov</cvss:source>
 * <cvss:generated-on-datetime>2015
 * -02-10T15:46:33.633-05:00</cvss:generated-on-datetime> </cvss:base_metrics>
 * </vuln:cvss> <vuln:cwe id="CWE-200"/> <vuln:references xml:lang="en"
 * reference_type="VENDOR_ADVISORY"> <vuln:source>CONFIRM</vuln:source>
 * <vuln:reference
 * href="https://kc.mcafee.com/corporate/index?page=content&amp;id=SB10095"
 * xml:lang
 * ="en">https://kc.mcafee.com/corporate/index?page=content&amp;id=SB10095
 * </vuln:reference> </vuln:references> <vuln:references xml:lang="en"
 * reference_type="UNKNOWN"> <vuln:source>XF</vuln:source> <vuln:reference
 * href="http://xforce.iss.net/xforce/xfdb/99949"
 * xml:lang="en">macafee-cve20150922-info-disc(99949)</vuln:reference>
 * </vuln:references> <vuln:references xml:lang="en" reference_type="UNKNOWN">
 * <vuln:source>BID</vuln:source> <vuln:reference
 * href="http://www.securityfocus.com/bid/72298"
 * xml:lang="en">72298</vuln:reference> </vuln:references> <vuln:references
 * xml:lang="en" reference_type="UNKNOWN"> <vuln:source>FULLDISC</vuln:source>
 * <vuln:reference href="http://seclists.org/fulldisclosure/2015/Jan/8"
 * xml:lang="en">20150106 McAfee ePolicy Orchestrator Authenticated XXE and
 * Credential Exposure</vuln:reference> </vuln:references> <vuln:references
 * xml:lang="en" reference_type="UNKNOWN"> <vuln:source>FULLDISC</vuln:source>
 * <vuln:reference href="http://seclists.org/fulldisclosure/2015/Jan/37"
 * xml:lang="en">20150112 Re: McAfee ePolicy Orchestrator Authenticated XXE and
 * Credential Exposure</vuln:reference> </vuln:references> <vuln:references
 * xml:lang="en" reference_type="UNKNOWN"> <vuln:source>MISC</vuln:source>
 * <vuln:reference href=
 * "http://packetstormsecurity.com/files/129827/McAfee-ePolicy-Orchestrator-Authenticated-XXE-Credential-Exposure.html"
 * xml:lang="en">http://packetstormsecurity.com/files/129827/McAfee-ePolicy-
 * Orchestrator-Authenticated-XXE-Credential-Exposure.html</vuln:reference>
 * </vuln:references> <vuln:summary>McAfee ePolicy Orchestrator (ePO) before
 * 4.6.9 and 5.x before 5.1.2 uses the same secret key across different
 * customers' installations, which allows attackers to obtain the administrator
 * password by leveraging knowledge of the encrypted password.</vuln:summary>
 * </entry>
 */