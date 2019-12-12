package tn.esprit.pitwin.interfaces;

import javax.ejb.Remote;

import tn.esprit.pitwin.entities.Candidate;
import tn.esprit.pitwin.entities.CandidateQuiz;
import tn.esprit.pitwin.entities.Question;

import java.util.List;
import java.util.Map;

@Remote
public interface ICandidateQuizModelService {
	
    void create(CandidateQuiz candidateQuiz);

    CandidateQuiz edit(CandidateQuiz candidateQuiz);

    void remove(CandidateQuiz candidateQuiz);

    CandidateQuiz find(Object id);

    List<CandidateQuiz> findAll();

    double calculateScore(CandidateQuiz candidateQuiz);

    double calculateSingleQuestionNote(CandidateQuiz candidateQuiz, Question question);

    Map<CandidateQuiz, Double> getHistorique();
    
    List<CandidateQuiz> getByCandidate(Candidate candidate, double minScore);
    
    CandidateQuiz getByCandidateId(long id);
    
    boolean setState(long id);
    
    void Accept(int CandidateId, int CandidateQuizId) ;
    
    void Reject(int CandidateId, int CandidateQuizId);
    

}
