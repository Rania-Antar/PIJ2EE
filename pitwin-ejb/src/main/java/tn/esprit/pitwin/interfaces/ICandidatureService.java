package tn.esprit.pitwin.interfaces;


import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pitwin.entities.Candidature;

@Remote
public interface ICandidatureService {
	
	void create(Candidature candidature);

	Candidature edit(Candidature candidature);

    void remove(Candidature candidature);

    Candidature find(Object id);

    List<Candidature> findAll();
    
	void Accept(long id);

	void Reject(long id);

}
