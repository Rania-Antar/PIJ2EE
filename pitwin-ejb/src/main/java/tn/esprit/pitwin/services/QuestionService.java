package tn.esprit.pitwin.services;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pitwin.entities.Answer;
import tn.esprit.pitwin.entities.Question;
import tn.esprit.pitwin.interfaces.IQuestionService;

import java.util.List;
import java.util.Set;

@Stateless

public class QuestionService extends AbstractService<Question> implements IQuestionService {

    @PersistenceContext(unitName = "pitwin-ejb")
    private EntityManager em;

    public QuestionService() {
        super(Question.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Question> findRandomQuestions(int numberOfQuestions) {
        String query = "SELECT q FROM Question q ORDER BY RAND()";
        Query q = em.createQuery(query);
        q.setMaxResults(numberOfQuestions);
        return q.getResultList();
    }

    @Override
    public List<Question> findRandomQuestionsByCategory(int numberOfQuestions, Long categoryId) {
        String query = "SELECT q FROM Question q WHERE q.category.id = :id ORDER BY RAND()";
        Query q = em.createQuery(query);
        q.setParameter("id", categoryId);
        q.setMaxResults(numberOfQuestions);
        return q.getResultList();
    }
    
    @Override
	public void assignResponseToQuestion(int question_id, int answer_id) {
		Question q=em.find(Question.class, question_id);
		Answer a=em.find(Answer.class, answer_id);
		Set<Answer> list=(Set<Answer>) q.getAnswers();
		list.add(a);
		q.setAnswers((List<Answer>) list);
		

	}
}
