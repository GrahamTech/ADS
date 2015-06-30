package com.grahamtech.ads.pojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h1>Agile Delivery Service</h1> Flattened Adverse Drug Event result for
 * prototype storing purposes.
 * <p>
 * This Controller exposes the following REST URL
 * 
 * @author Rodney Morris
 * @version 1.0
 * @since 2015-06-24
 */
@Entity
@Table(name = "adverseDrugEventResults")
public class AdverseDrugEventResultFlattened implements java.io.Serializable {
  // private static final Logger logger =
  // LoggerFactory.getLogger(Project.class);

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    @JsonProperty
    private long event_id;

    @Column(name = "safetyreportid")
    @JsonProperty
    private String safetyreportid;
    @Column(name = "senderorganization")
    @JsonProperty
    private String senderorganization;
    @Column(name = "serious")
    @JsonProperty
    private long serious;
    @Column(name = "companynumb")
    @JsonProperty
    private String companynumb;
    @Column(name = "patient_reactions")
    // @JsonIgnore
    @JsonProperty(value = "patient_reactions")
    private String patient_reactions;

    // @JsonProperty(value = "patient_reactions")
    // private String[] patient_reactions_array;

    public AdverseDrugEventResultFlattened() {
	// default constructor
    }

    public AdverseDrugEventResultFlattened(long event_id, String safetyreportid,
	    String sender,
	    long serious, String companynumb, String patient_reactions) {
	this(safetyreportid, sender, serious, companynumb, patient_reactions);
	this.event_id = event_id;
    }

    public AdverseDrugEventResultFlattened(String safetyreportid,
	    String sender, long serious, String companynumb,
	    String patient_reactions) {
	this(sender, serious, companynumb, patient_reactions);
	this.safetyreportid = safetyreportid;
    }

    private AdverseDrugEventResultFlattened(String sender, long serious,
	    String companynumb,
	    String patient_reactions) {
	this.setCompanynumb(companynumb);
	this.setEvent_id(event_id);
	this.setPatient_reactions(patient_reactions);
	this.setSenderorganization(sender);
	this.setSerious(serious);
    }

    public AdverseDrugEventResultFlattened(Results result) {
	this.setSafetyreportid(result.getSafetyreportid());
	this.setSenderorganization(result.getSender().getSenderorganization());
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

    public String getSafetyreportid() {
	return safetyreportid;
    }

    public void setSafetyreportid(String safetyreportid) {
	this.safetyreportid = safetyreportid;
    }

    public String getSenderorganization() {
	return senderorganization;
    }

    public void setSenderorganization(String senderorganization) {
	this.senderorganization = senderorganization;
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

    public String flattenFromDB(String keyValuePairs) {
	List<String> valuesList = new ArrayList<String>();
	if(keyValuePairs != null){
		String[] reactionsArrayDot = keyValuePairs.split("\\,");
		List<String> keyValueList = Arrays.asList(reactionsArrayDot);
		for (String keyValue : keyValueList) {
		    // String keyValueTrimmed = keyValue.trim();
		    String keyValueTrimmed = keyValue.replaceAll("^\\s+", "")
			    .replaceAll("\\s+$", "");
		    if (keyValueTrimmed != null && !keyValueTrimmed.equals(" ")
			    && !keyValueTrimmed.equals("")) {
			String[] reactionsArraySplit = keyValue.split("\\:"); // reactionmeddrapt:
			List<String> reactionsArraySplitList = Arrays
				.asList(reactionsArraySplit);
			for (String reaction : reactionsArraySplitList) {
			    String vlaueTrimmed = reaction.replaceAll("^\\s+", "")
				    .replaceAll("\\s+$", "");
			    if (!vlaueTrimmed.equals("reactionmeddrapt")) {
				valuesList.add(vlaueTrimmed);
			    }
			}// end for
		    }// end if
		}// end for
	}//end if not null
	return createCommaSeparatedList(valuesList);
    }

    private String createCommaSeparatedList(List<String> theList) {
	int count = 0;
	StringBuilder sb = new StringBuilder();
	for (String reaction : theList) {
	    reaction.trim();
	    if (count != 0) {
		sb.append(", ");
	    }
	    sb.append(reaction);
	    count++;
	}
	return sb.toString();
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((companynumb == null) ? 0 : companynumb.hashCode());
	result = prime * result + (int) (event_id ^ (event_id >>> 32));
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof AdverseDrugEventResultFlattened))
	    return false;
	AdverseDrugEventResultFlattened other = (AdverseDrugEventResultFlattened) obj;
	if (companynumb == null) {
	    if (other.companynumb != null)
		return false;
	} else if (!companynumb.equals(other.companynumb))
	    return false;
	if (event_id != other.event_id)
	    return false;
	return true;
    }

}
