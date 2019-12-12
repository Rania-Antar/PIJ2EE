/*package tn.esprit.pitwin.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Appointment implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private int slotId;
    private String candidateId;
    
    public Appointment() {}

    public Appointment(int id, int slotId, String candidateId) {
	this.id = id;
	this.slotId = slotId;
	this.candidateId = candidateId;
    }

    @JsonCreator
    public static Appointment of(@JsonProperty("id") int id,
	    @JsonProperty("slotId") int slotId,
	    @JsonProperty("candidateId") String candidateId) {
	return new Appointment(id, slotId, candidateId);
    }

    public int getId() {
	return id;
    }

    public int getSlotId() {
	return slotId;
    }

    public String getCandidateId() {
	return candidateId;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;

	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Appointment)) {
	    return false;
	}

	Appointment other = (Appointment) obj;

	return id == other.id;
    }

    @Override
    public String toString() {
	return "Appointment [id=" + id + ", slotId=" + slotId + ", candidateId="
		+ candidateId + "]";
    }
}
*/
