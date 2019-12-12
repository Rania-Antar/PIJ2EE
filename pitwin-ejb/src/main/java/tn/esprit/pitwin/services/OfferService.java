package tn.esprit.pitwin.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.pitwin.entities.Offre;
import tn.esprit.pitwin.interfaces.IOfferService;

@Stateless
public class OfferService extends AbstractService<Offre> implements IOfferService {
	
	@PersistenceContext(unitName="pitwin-ejb")
	EntityManager em ;
	
	public OfferService() {
        super(Offre.class);
    }
	
	@Override
    protected EntityManager getEntityManager() {
        return em;
    }
	
	


}
