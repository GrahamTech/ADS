package com.grahamtech.ads.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h1>Agile Delivery Service</h1> Used to indicate the status of a request and
 * a detailed message.
 * 
 * @author Rodney Morris
 * @version 1.0
 * @since 2015-06-24
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusMessage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private String status;
    @JsonIgnore
    @JsonProperty(value="statusDetails")
    private String statusDetails;

    public StatusMessage() {

    }

    public StatusMessage(String status, String statusDetails) {
	this.status = status;
	this.statusDetails = statusDetails;
    }

    @Override
    public String toString() {
	return "status: " + this.getStatus() + ", statusDetails: "
		+ this.getStatusDetails();
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getStatusDetails() {
	return statusDetails;
    }

    public void setStatusDetails(String statusDetails) {
	this.statusDetails = statusDetails;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

}
