/*package tn.esprit.pitwin.entities;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import tn.esprit.pitwin.utilities.SlotDateTimeDeserializer;
import tn.esprit.pitwin.utilities.SlotDateTimeSerializer;

@Entity
public class Slot implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private long responsableId;

    public Slot() {}
    
    private Slot(Slot other) {
	this(other.id, other.startDateTime, other.endDateTime, other.responsableId);
    }

    private Slot(int id, LocalDateTime startDateTime,
	    LocalDateTime endDateTime, long responsableId) {
	this.id = id;
	this.startDateTime = startDateTime;
	this.endDateTime = endDateTime;
	this.responsableId = responsableId;
    }

    public static Slot from(Slot s) {
	return of(s.id, s.startDateTime, s.endDateTime, s.responsableId);
    }

    @JsonCreator
    public static Slot of(
	    @JsonProperty("id") int id,
	    @JsonProperty("startDateTime") @JsonSerialize(using = SlotDateTimeSerializer.class) @JsonDeserialize(using = SlotDateTimeDeserializer.class) LocalDateTime startDateTime,
	    @JsonProperty("endDateTime") @JsonSerialize(using = SlotDateTimeSerializer.class) @JsonDeserialize(using = SlotDateTimeDeserializer.class) LocalDateTime endDateTime,
	    @JsonProperty("responsableId") long responsableId) {
	return new Slot(id, startDateTime, endDateTime, responsableId);
    }

    public int getId() {
	return id;
    }

    public LocalDateTime getStartDateTime() {
	return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
	return endDateTime;
    }

    public long getResponsableId() {
	return responsableId;
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
	if (!(obj instanceof Slot)) {
	    return false;
	}

	Slot other = (Slot) obj;

	return id == other.id;
    }

    @Override
    public String toString() {
	return "Slot [startDateTime="
		+ ISO_LOCAL_DATE_TIME.format(startDateTime) + ", endDateTime="
		+ ISO_LOCAL_DATE_TIME.format(endDateTime) + ", responsableId="
		+ responsableId + "]";
    }
}
*/
