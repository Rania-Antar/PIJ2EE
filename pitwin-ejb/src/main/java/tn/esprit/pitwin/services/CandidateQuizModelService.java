package tn.esprit.pitwin.services;


import tn.esprit.pitwin.entities.Answer;
import tn.esprit.pitwin.entities.Candidate;
import tn.esprit.pitwin.entities.CandidateQuiz;
import tn.esprit.pitwin.entities.Question;
import tn.esprit.pitwin.interfaces.IAnswerService;
import tn.esprit.pitwin.interfaces.ICandidateQuizModelService;
import tn.esprit.pitwin.utilities.State;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class CandidateQuizModelService extends AbstractService<CandidateQuiz> implements ICandidateQuizModelService {

    @EJB
    IAnswerService answerService;
    @PersistenceContext(unitName = "pitwin-ejb")
    private EntityManager em;

    public CandidateQuizModelService() {
        super(CandidateQuiz.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public double calculateScore(CandidateQuiz candidateQuiz) {
    	
    	Set<Answer> candidateAnswers = candidateQuiz.getCandidateAnswer().getAnswers();
        Set<Question> quizModelQuestions = candidateQuiz.getQuizModel().getQuestions();
        double totalScore = quizModelQuestions.stream().distinct().mapToDouble(q -> q.getScore().doubleValue()).sum();
        final double[] totalNote = {0};
        Map<Question, List<Answer>> questionListMap = candidateAnswers.stream()
                .collect(Collectors.groupingBy(Answer::getQuestion));
        questionListMap.forEach((question, answerList) -> {
            double questionNote = calculateQuestionNote(question, answerList,
                                            candidateQuiz.getQuizModel().isPenalty(), totalScore);
            totalNote[0] += questionNote;
        });
        return totalNote[0] + 50;
    }

    @Override
    public double calculateSingleQuestionNote(CandidateQuiz candidateQuiz, Question question) {
    	
        Set<Answer> candidateAnswers = candidateQuiz.getCandidateAnswer().getAnswers();
        Set<Question> quizModelQuestions = candidateQuiz.getQuizModel().getQuestions();
        double totalScore = quizModelQuestions.stream().distinct().mapToDouble(q -> q.getScore().doubleValue()).sum();
        List<Answer> answerList = candidateAnswers.stream()
                        .filter(answer -> answer.getQuestion().equals(question)).collect(Collectors.toList());
        double questionNote = calculateQuestionNote(question, answerList,
                                            candidateQuiz.getQuizModel().isPenalty(), totalScore);
        return questionNote;
    }

    public double calculateQuestionNote(Question question, List<Answer> answers, boolean penalty, double totalScore){
        double numberOfGoodAnswers = answerService.findCorrectAnswers(question.getId()).size();
        final double[] questionNote = {0};
        answers.forEach(answer -> {
            if(answer.isCorrect()){
                questionNote[0] += 1 / numberOfGoodAnswers;
            }else{
                questionNote[0] -= 1 / numberOfGoodAnswers;
            }
        });
        if(!penalty && questionNote[0] < 0){
            questionNote[0] = 0;
        }
        questionNote[0] *= 100 * question.getScore() / totalScore;
        return questionNote[0];
    }
    
    
    @Override
    public Map<CandidateQuiz, Double> getHistorique() {
        Map<CandidateQuiz, Double> scoreQuizMap = new LinkedHashMap();
        List<CandidateQuiz> candidateQuizModelList = findAll();
        candidateQuizModelList.sort((c1, c2) -> Double.compare(calculateScore(c1), calculateScore(c2)));
        candidateQuizModelList.forEach(candidateQuizModel -> scoreQuizMap.put(candidateQuizModel, calculateScore(candidateQuizModel)));
        return scoreQuizMap;
    }

    @Override
    public List<CandidateQuiz> getByCandidate(Candidate candidate, double minScore) {

        List<CandidateQuiz> candidateQuizs=new ArrayList<>();
        Map<CandidateQuiz, Double> historique= getHistorique();
        for (Map.Entry<CandidateQuiz, Double> e: historique.entrySet()
             ) {
            if ((candidate.getId()==e.getKey().getCandidate().getId())&&(e.getValue()>=minScore))
            {
                candidateQuizs.add(e.getKey());
            }
        }
        return candidateQuizs;
    }
    

	@Override
	public CandidateQuiz getByCandidateId(long id) {
		try {
			Query query = em.
					createQuery("SELECT CandidateQuiz a WHERE a.candidate=:param")
					.setParameter("param", id);
			 return (CandidateQuiz) query.getResultList();

		} catch (Exception e) {
			System.err.println("Cant Find Answer");
			return new CandidateQuiz();
		}
	}
	
	@Override
	public boolean setState(long id) {
		CandidateQuiz CQ = em.find(CandidateQuiz.class, id);
		if (calculateScore(CQ) < 50) {
			CQ.setState(State.Failed);
			return false;
		} else {
			CQ.setState(State.Validated);
			return true;
		}

	}
	
	@Override
	public void Accept(int CandidateId, int CandidateQuizId) {
		Query query  = em.createQuery("UPDATE CandidateQuiz o SET State ='Validated' where  o.id=:CandidateQuizId AND o.candidate=:CandidateId")
				.setParameter("CandidateQuizId", CandidateQuizId)
				.setParameter("CandidateId", CandidateId);
		query.executeUpdate();
		
	}


	@Override
	public void Reject(int CandidateId, int CandidateQuizId) {
		Query query  = em.createQuery("UPDATE CandidateQuiz o SET State ='Failed' where  o.id=:CandidateQuizId AND o.candidate=:CandidateId")
				.setParameter("CandidateQuizId", CandidateQuizId)
				.setParameter("CandidateId", CandidateId);
		query.executeUpdate();
	}

}
