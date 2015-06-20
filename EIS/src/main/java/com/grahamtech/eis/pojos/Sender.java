package com.grahamtech.eis.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sender implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private String senderorganization;

    public Sender() {

    }

    @Override
    public String toString() {
	return "Sender [senderorganization="
		+ this.getSenderorganization() + "]";
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
