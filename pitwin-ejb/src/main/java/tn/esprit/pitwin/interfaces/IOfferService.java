package tn.esprit.pitwin.interfaces;

import javax.ejb.Remote;

import tn.esprit.pitwin.entities.Offre;

@Remote
public interface IOfferService {
	
	Offre find(Object id);

}
