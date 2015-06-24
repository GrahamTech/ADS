package com.grahamtech.ads.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private String last_updated;

    public Meta() {
	// default constructor
    }

    public Meta(String last_updated) {
	this.last_updated = last_updated;
    }
    public String toString() {
	return "Meta [last_updated= " + this.getLast_updated() + "]";
    }

    public String getLast_updated() {
	return last_updated;
    }

    public void setLast_updated(String last_updated) {
	this.last_updated = last_updated;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

}
