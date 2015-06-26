package com.grahamtech.ads.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sender implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private String senderorganization;

    public Sender() {
    }

    public Sender(String senderorganization) {
	this.senderorganization = senderorganization;
    }
    
    @Override
    public String toString() {
	return "Sender [senderorganization=" + this.getSenderorganization()
		+ "]";
    }

    public String getSenderorganization() {
	return senderorganization;
    }

    public void setSenderorganization(String senderorganization) {
	this.senderorganization = senderorganization;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

}
