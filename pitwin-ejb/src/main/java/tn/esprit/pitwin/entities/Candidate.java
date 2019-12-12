package tn.esprit.pitwin.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Candidate extends User implements Serializable {
	
	
	@OneToMany(mappedBy="candidate")
	private List<Candidature> candidatures ;

	@XmlTransient
	public List<Candidature> getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}
	

}
