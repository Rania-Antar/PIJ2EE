package tn.esprit.pitwin.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pitwin.entities.Answer;
import tn.esprit.pitwin.entities.Question;
import tn.esprit.pitwin.entities.Quiz;
import tn.esprit.pitwin.interfaces.IAnswerService;
import tn.esprit.pitwin.interfaces.IQuestionService;
import tn.esprit.pitwin.interfaces.IQuizModelService;
import tn.esprit.pitwin.utilities.QuestionType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Stateless
public class QuizModelService extends AbstractService<Quiz> implements IQuizModelService {

    @PersistenceContext(unitName = "pitwin-ejb")
    private EntityManager em;
    @EJB
    private IQuestionService questionService;
    @EJB
    private IAnswerService answerService;

    public QuizModelService() {
        super(Quiz.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void addRandomQuizModel(Quiz quizModel, Long id) {
        Random random = new Random();
        List<Question> questions;
        if(id != null){
            questions = questionService.findRandomQuestionsByCategory(quizModel.getQuestionsNumber(), id);
        }
        else{
            questions = questionService.findRandomQuestions(quizModel.getQuestionsNumber());
        }
        List<Answer> keepedAnswer = new ArrayList();
        questions.stream().forEach(question -> {
            int randomInt = random.nextInt(quizModel.getAnswersNumber()) + 1;
            if(question.getType().equals(QuestionType.CHECKBOX)){
                keepedAnswer.addAll(answerService.findCorrectAnswers(question.getId()).subList(0, randomInt));
                keepedAnswer.addAll(answerService.findWrongAnswers(question.getId()).subList(0, quizModel.getAnswersNumber() - randomInt));
            }
            else{
                keepedAnswer.add(answerService.findCorrectAnswers(question.getId()).get(0));
                keepedAnswer.addAll(answerService.findWrongAnswers(question.getId()).subList(0, quizModel.getAnswersNumber() - 1));
            }
            
        });
        quizModel.setQuestions(new HashSet( questions));
        create(quizModel);
    }
    
    
	@Override
	public List<Quiz> SearchForQuizs(String search) {
		List<Quiz> events = (List<Quiz>) em
				.createQuery("SELECT new Quiz(e.id,e.name,e.duration,e.questionsNumber,e.answersNumber,e.penalty) "
						+ "FROM Quiz e  "
						+ "WHERE e.name LIKE :search ")
				.setParameter("search", '%' + search + '%').getResultList();
				//.setParameter("search1", Integer.parseInt(search))
				//.setParameter("search2", Integer.parseInt(search)).getResultList();

		return events;

	}
	
	@Override
	public List<Quiz> findQuizByName(String name) {
		List<Quiz> quizs = (List<Quiz>) em.createQuery("SELECT new Quiz(q.id,q.name,q.duration,q.questionsNumber,q.answersNumber,q.penalty)  FROM Quiz q  WHERE q.name LIKE :name")
				.setParameter("name", name).getResultList();

		return quizs;
	}

	@Override
	public List<Quiz> findQuizByDuration(int duration) {
		List<Quiz> quizs = (List<Quiz>) em
				.createQuery("SELECT new Quiz(q.id,q.name,q.duration,q.questionsNumber,q.answersNumber,q.penalty)  FROM Quiz q  WHERE q.duration= :duration")
				.setParameter("duration", duration).getResultList();

		return quizs;
	}

	@Override
	public List<Quiz> findQuizByQuestionsNumber(int questionsNumber) {
		List<Quiz> quizs = (List<Quiz>) em
				.createQuery("SELECT new Quiz(q.id,q.name,q.duration,q.questionsNumber,q.answersNumber,q.penalty)  FROM Quiz q  WHERE q.questionsNumber= :questionsNumber")
				.setParameter("questionsNumber", questionsNumber).getResultList();

		return quizs;
	}
	
	
}
