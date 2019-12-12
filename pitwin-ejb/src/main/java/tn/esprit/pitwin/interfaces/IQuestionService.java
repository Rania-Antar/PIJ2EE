package tn.esprit.pitwin.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pitwin.entities.Question;

@Remote
public interface IQuestionService {
	
    void create(Question question);

    Question edit(Question question);

    void remove(Question question);

    Question find(Object id);

    List<Question> findAll();

    List<Question> findRandomQuestions(int numberOfQuestions);

    List<Question> findRandomQuestionsByCategory(int numberOfQuestions, Long categoryId);
    
    void assignResponseToQuestion(int question_id, int answer_id);
}
