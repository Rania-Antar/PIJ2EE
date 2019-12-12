package tn.esprit.pitwin.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@XmlRootElement
public class Quiz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Integer duration;
    private Integer questionsNumber;
    private Integer answersNumber;
    private Boolean penalty;


    @OneToMany(targetEntity = Question.class, mappedBy = "quiz", fetch = FetchType.EAGER)
    private Set<Question> questions;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getQuestionsNumber() {
        return this.questionsNumber;
    }

    public void setQuestionsNumber(Integer questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    public Integer getAnswersNumber() {
        return this.answersNumber;
    }

    public void setAnswersNumber(Integer answersNumber) {
        this.answersNumber = answersNumber;
    }

    public Boolean isPenalty() {
        return this.penalty;
    }

    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }
    @XmlTransient 
    public Set<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

	public Quiz(int id, String name, Integer duration, Integer questionsNumber, Integer answersNumber,
			Boolean penalty) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.questionsNumber = questionsNumber;
		this.answersNumber = answersNumber;
		this.penalty = penalty;
	}

	public Quiz() {}
    
	
}
