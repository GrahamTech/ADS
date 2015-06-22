package com.grahamtech.ads.pojos;

//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "event_id")
    // private long event_id;
    // @Column(name = "safetyreportid")
    @JsonProperty
    private String safetyreportid;

    // @Column(name = "senderorganization")
    @JsonProperty
    private Sender sender;
    // @Column(name = "serious")
    @JsonProperty
    private String serious;
    // @Column(name = "companynumb")
    @JsonProperty
    private String companynumb;
    @JsonProperty
    private Patient patient;

    public Results() {
	// default constructor
    }

    @Override
    public String toString() {
	return "Results [safetyreportid= " + this.getSafetyreportid()
		+ ", sender=" + this.getSender() + ", serious= "
		+ this.getSerious() + ", companynumb= "
 + this.getCompanynumb() + "patient= "
 + this.getPatient().toString() + "]";
    }

    // public long getEvent_id() {
    // return event_id;
    // }
    //
    // public void setEvent_id(long event_id) {
    // this.event_id = event_id;
    // }

    public String getSafetyreportid() {
	return safetyreportid;
    }

    public void setSafetyreportid(String safetyreportid) {
	this.safetyreportid = safetyreportid;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public Sender getSender() {
	return sender;
    }

    public void setSender(Sender sender) {
	this.sender = sender;
    }

    public String getSerious() {
	return serious;
    }

    public void setSerious(String serious) {
	this.serious = serious;
    }

    public String getCompanynumb() {
	return companynumb;
    }

    public void setCompanynumb(String companynumb) {
	this.companynumb = companynumb;
    }

    public Patient getPatient() {
	return patient;
    }

    public void setPatient(Patient patient) {
	this.patient = patient;
    }


}
