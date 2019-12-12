package tn.esprit.pitwin.interfaces;

import tn.esprit.pitwin.entities.Candidate;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ICandidateService {
	
    void create(Candidate candidate);

    Candidate edit(Candidate candidate);

    void remove(Candidate candidate);

    Candidate find(Object id);

    List<Candidate> findAll();
    
    List<Candidate> findCandidate();

}
