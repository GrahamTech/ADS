package com.grahamtech.ads.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private Reaction[] reaction;

    public Patient() {

    }

    @Override
    public String toString() {
	return "Patient [reactions= " + this.getReactionArray() + "]";
    }

    @JsonIgnore
    @JsonProperty(value="reactionArray")
    public String getReactionArray() {
	StringBuffer strBuffer = new StringBuffer();
	int count = 0;
	for (Reaction token : this.getReaction()) {
	    if (count > 0) {
		strBuffer.append(", ");
	    }
	    strBuffer.append(token.toString());
	}
	return strBuffer.toString();
    }

    public Reaction[] getReaction() {
	if (this.reaction == null) {
	    reaction = new Reaction[] {};
	}
	return reaction;
    }

    public void setReaction(Reaction[] reaction) {
	this.reaction = reaction;
    }
}
