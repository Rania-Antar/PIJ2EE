
package tn.esprit.pitwin.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@XmlRootElement
public class CandidateAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = CandidateQuiz.class, fetch = FetchType.EAGER)
    private CandidateQuiz candidateQuiz;

	@OneToMany(targetEntity = Question.class, mappedBy = "candidateAnswer", fetch = FetchType.LAZY)
    private Set<Question> questions;

    @OneToMany(targetEntity = Answer.class, mappedBy = "candidateAnswer", fetch = FetchType.EAGER)
    private Set<Answer> answers;

    public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public CandidateQuiz getCandidateQuiz() {
		return candidateQuiz;
	}

	public void setCandidateQuiz(CandidateQuiz candidateQuiz) {
		this.candidateQuiz = candidateQuiz;
	}
    
    public Set<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    } 

	@Override
	public String toString() {
		return "CandidateAnswer [id=" + id + ", candidateQuiz=" + candidateQuiz + ", questions=" + questions
				+ ", answers=" + answers + "]";
	}
    
    

}
