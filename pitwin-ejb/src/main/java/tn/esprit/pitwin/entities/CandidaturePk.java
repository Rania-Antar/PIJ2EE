/*package tn.esprit.pitwin.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CandidaturePk implements Serializable {
	
	private int idCandidate;
	private int idOffer;
	
	public int getIdCandidate() {
		return idCandidate;
	}
	public void setIdCandidate(int idCandidate) {
		this.idCandidate = idCandidate;
	}
	public int getIdOffer() {
		return idOffer;
	}
	public void setIdOffer(int idOffer) {
		this.idOffer = idOffer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCandidate;
		result = prime * result + idOffer;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidaturePk other = (CandidaturePk) obj;
		if (idCandidate != other.idCandidate)
			return false;
		if (idOffer != other.idOffer)
			return false;
		return true;
	}
	
	

}
*/
