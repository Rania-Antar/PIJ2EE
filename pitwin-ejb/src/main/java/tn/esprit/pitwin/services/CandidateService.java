package tn.esprit.pitwin.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pitwin.entities.Answer;
import tn.esprit.pitwin.entities.Candidate;
import tn.esprit.pitwin.interfaces.ICandidateService;

@Stateless
public class CandidateService extends AbstractService<Candidate> implements ICandidateService, Serializable{
	
	@PersistenceContext(unitName = "pitwin-ejb")
    private EntityManager em;

    public CandidateService() {
        super(Candidate.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<Candidate> findCandidate() {
        String query = "SELECT distinct a FROM Candidate a JOIN a.candidatures  c  WHERE c.state = 'ACCEPTED' ";
        Query q = em.createQuery(query);
        List<Candidate> answers = q.getResultList();
        return answers;
    }
    
    



}
