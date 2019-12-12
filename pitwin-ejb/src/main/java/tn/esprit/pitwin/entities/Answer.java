package tn.esprit.pitwin.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.List;


@Entity
@XmlRootElement
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    private Boolean correct;

    @ManyToOne(targetEntity = Question.class, fetch = FetchType.EAGER)
    private Question question;

    @ManyToOne(targetEntity = CandidateAnswer.class, fetch = FetchType.EAGER)
    private CandidateAnswer candidateAnswer;

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
     
    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean isCorrect() {
        return this.correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
    @XmlTransient
    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    @XmlTransient
    public CandidateAnswer getCandidateAnswer() {
        return this.candidateAnswer;
    }

    public void setCandidateAnswer(CandidateAnswer candidateAnswer) {
        this.candidateAnswer = candidateAnswer;
    }

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answer=" + answer + ", correct=" + correct + ", question=" + question
				+ ", candidateAnswer=" + candidateAnswer + "]";
	}
}
