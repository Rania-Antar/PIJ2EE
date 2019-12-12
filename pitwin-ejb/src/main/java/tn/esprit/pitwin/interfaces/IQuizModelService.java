package tn.esprit.pitwin.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pitwin.entities.Quiz;

@Remote
public interface IQuizModelService {
	
    void create(Quiz quiz);

    Quiz edit(Quiz quiz);

    void remove(Quiz quiz);

    Quiz find(Object id);

    List<Quiz> findAll();

    void addRandomQuizModel(Quiz quiz, Long id);
    
    List<Quiz> SearchForQuizs(String search);
    
    List<Quiz> findQuizByName(String name);
    
    List<Quiz> findQuizByDuration(int duration);
    
    List<Quiz> findQuizByQuestionsNumber(int questionsNumber);
}
