package tn.esprit.pitwin.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import tn.esprit.pitwin.utilities.CustomerDateAndTimeDeserialize;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


@Entity
@XmlRootElement
public class Interview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
    private Date date;
    
    private Time time;

	@ManyToOne(targetEntity = Candidature.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Candidature candidature;

	@XmlTransient
	public Candidature getCandidature() {
		return candidature;
	}

	public void setCandidature(Candidature candidature) {
		this.candidature = candidature;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @XmlTransient
    public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

   

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Interview interview = (Interview) o;

        return id.equals(interview.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
