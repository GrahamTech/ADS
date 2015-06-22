package com.grahamtech.eis.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description: Flattened Adverse Drug Event result for prototype purposes.
 * 
 * @author morrisrod
 * 
 */
@Entity
@Table(name = "adverseDrugEventResults")
public class AdversDrugEventResultFlattened implements java.io.Serializable {
  // private static final Logger logger =
  // LoggerFactory.getLogger(Project.class);

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long event_id;
    @Column(name = "sender")
    private String sender;
    @Column(name = "serious")
    private long serious;
    @Column(name = "companynumb")
    private String companynumb;
    @Column(name = "patient_reactions")
    private String patient_reactions;

    public AdversDrugEventResultFlattened() {
	// default constructor
    }

    public AdversDrugEventResultFlattened(long event_id, String sender,
	    long serious, String companynumb, String patient_reactions) {
	this.setCompanynumb(companynumb);
	this.setEvent_id(event_id);
	this.setPatient_reactions(patient_reactions);
	this.setSender(sender);
	this.setSerious(serious);
    }

    public AdversDrugEventResultFlattened(Results result) {
	this.setSender(result.getSender().getSenderorganization());
	this.setCompanynumb(result.getCompanynumb());
	this.setSerious(new Long(result.getSerious()).longValue());
	this.setPatient_reactions(result.getPatient().getReactionArray());
    }

    public long getEvent_id() {
	return event_id;
    }

    public void setEvent_id(long event_id) {
	this.event_id = event_id;
    }

    public String getSender() {
	return sender;
    }

    public void setSender(String sender) {
	this.sender = sender;
    }

    public long getSerious() {
	return serious;
    }

    public void setSerious(long serious) {
	this.serious = serious;
    }

    public String getCompanynumb() {
	return companynumb;
    }

    public void setCompanynumb(String companynumb) {
	this.companynumb = companynumb;
    }

    public String getPatient_reactions() {
	return patient_reactions;
    }

    public void setPatient_reactions(String patient_reactions) {
	this.patient_reactions = patient_reactions;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

}
