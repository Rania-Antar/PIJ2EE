package tn.esprit.pitwin.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pitwin.entities.Answer;
import tn.esprit.pitwin.interfaces.IAnswerService;

import java.util.List;

@Stateless
public class AnswerService extends AbstractService<Answer> implements IAnswerService {

    @PersistenceContext(unitName = "pitwin-ejb")
    private EntityManager em;

    public AnswerService() {
        super(Answer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<Answer> findQuestionAnswers(Long questionId) {
        String query = "SELECT a FROM Answer a WHERE a.question.id = :id";
        Query q = em.createQuery(query);
        q.setParameter("id", questionId);
        List<Answer> answers = q.getResultList();
        return answers;
    }

    @Override
    public List<Answer> findCorrectAnswers(Long questionId) {
        String query = "SELECT a FROM Answer a WHERE a.question.id = :id AND a.correct = true ORDER BY RAND()";
        Query q = em.createQuery(query);
        q.setParameter("id", questionId);
        List<Answer> answers = q.getResultList();
        return answers;
    }

    @Override
    public List<Answer> findWrongAnswers(Long questionId) {
        String query = "SELECT a FROM Answer a WHERE a.question.id = :id AND a.correct = false ORDER BY RAND()";
        Query q = em.createQuery(query);
        q.setParameter("id", questionId);
        List<Answer> answers = q.getResultList();
        return answers;
    }
    
    @Override
	public void setCorrect( int AnswerId) {
		Query query  = em.createQuery("UPDATE Answer a SET correct =true where  a.id=:AnswerId ")
				.setParameter("AnswerId", AnswerId);
		query.executeUpdate();
		
	}


	@Override
	public void setFalse(int AnswerId) {
		Query query  = em.createQuery("UPDATE  Answer a SET correct =false where  a.id=:AnswerId")
				.setParameter("AnswerId", AnswerId);
		query.executeUpdate();
	}


}
