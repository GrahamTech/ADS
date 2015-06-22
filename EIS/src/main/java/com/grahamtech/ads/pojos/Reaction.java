package com.grahamtech.ads.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reaction implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private String reactionmeddrapt;

    public Reaction() {

    }

    @Override
    public String toString() {
	return "Reaction [reaction= " + this.getReactionmeddrapt() + "]";
    }

    public String getReactionmeddrapt() {
	return reactionmeddrapt;
    }

    public void setReactionmeddrapt(String reactionmeddrapt) {
	this.reactionmeddrapt = reactionmeddrapt;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

}
