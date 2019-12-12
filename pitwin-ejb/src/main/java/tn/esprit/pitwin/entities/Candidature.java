package tn.esprit.pitwin.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import tn.esprit.pitwin.utilities.CandidatureState;

@Entity
public class Candidature implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(targetEntity = Candidate.class, fetch = FetchType.EAGER)
	private Candidate candidate;
	
	@ManyToOne(targetEntity = Offre.class, fetch = FetchType.EAGER)
	private Offre offre;
	
	@OneToMany(mappedBy="candidature",cascade = CascadeType.ALL)
	private List<Interview> interviews ;

	
	@Enumerated(EnumType.STRING)
	private CandidatureState state;

	public CandidatureState getState() {
		return state;
	}

	public void setState(CandidatureState state) {
		this.state = state;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	@XmlTransient
	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Candidature [id=" + id + ", candidate=" + candidate + ", offre=" + offre + ", interviews=" + interviews
				+ ", state=" + state + "]";
	}
	
	

}
