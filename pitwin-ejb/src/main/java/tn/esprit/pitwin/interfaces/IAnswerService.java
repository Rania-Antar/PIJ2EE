package tn.esprit.pitwin.interfaces;


import javax.ejb.Remote;

import tn.esprit.pitwin.entities.Answer;

import java.util.List;

@Remote
public interface IAnswerService {
	
    void create(Answer answer);

    Answer edit(Answer answer);

    void remove(Answer answer);

    Answer find(Object id);

    List<Answer> findAll();
    
    List<Answer> findQuestionAnswers(Long questionId);

    List<Answer> findCorrectAnswers(Long questionId);

    List<Answer> findWrongAnswers(Long questionId);
    
    void setCorrect( int AnswerId);
    
    void setFalse( int AnswerId);

}
