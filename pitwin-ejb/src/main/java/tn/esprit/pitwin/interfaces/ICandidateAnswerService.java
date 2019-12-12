package tn.esprit.pitwin.interfaces;



import javax.ejb.Remote;

import tn.esprit.pitwin.entities.CandidateAnswer;

import java.util.List;

@Remote
public interface ICandidateAnswerService {
	
    void create(CandidateAnswer candidateAnswer);

    CandidateAnswer edit(CandidateAnswer candidateAnswer);

    void remove(CandidateAnswer candidateAnswer);

    CandidateAnswer find(Object id);

    List<CandidateAnswer> findAll();
    
    void PDF(long id);
    
    //List<CandidateAnswer> getMyAnswers(int id) ;
}
