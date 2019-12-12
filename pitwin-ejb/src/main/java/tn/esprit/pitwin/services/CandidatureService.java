package tn.esprit.pitwin.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pitwin.entities.Candidature;
import tn.esprit.pitwin.interfaces.ICandidatureService;

@Stateless
public class CandidatureService extends AbstractService<Candidature> implements ICandidatureService {
	
	@PersistenceContext(unitName = "pitwin-ejb")
    private EntityManager em;

    public CandidatureService() {
        super(Candidature.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
	@Override
	public void Accept(long id) {
		Query query  = em.createQuery("UPDATE Candidature o SET State ='ACCEPTED' where  o.id=:CandidatureId")
				.setParameter("CandidatureId", id);
		query.executeUpdate();
		
	}


	@Override
	public void Reject(long id) {
		Query query  = em.createQuery("UPDATE Candidature o SET State ='REFUSED' where  o.id=:CandidatureId")
				.setParameter("CandidatureId", id);
		query.executeUpdate();
	}

}
