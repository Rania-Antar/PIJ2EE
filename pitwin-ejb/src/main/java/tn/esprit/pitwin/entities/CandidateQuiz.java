package tn.esprit.pitwin.entities;


import tn.esprit.pitwin.entities.Candidate;
import tn.esprit.pitwin.utilities.CustomerDateAndTimeDeserialize;
import tn.esprit.pitwin.utilities.State;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement
public class CandidateQuiz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    @JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
    private Date passingDate;

    @OneToOne(targetEntity = CandidateAnswer.class, mappedBy = "candidateQuiz", fetch = FetchType.EAGER)
    private CandidateAnswer candidateAnswer;

    @OneToOne(targetEntity = Quiz.class, fetch = FetchType.EAGER)
    private Quiz quizModel;
    
    @ManyToOne(targetEntity = Candidate.class, fetch = FetchType.EAGER)
    private Candidate candidate;
    
    @Enumerated(EnumType.STRING)
	private State state;

    public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPassingDate() {
        return this.passingDate;
    }

    public void setPassingDate(Date passingDate) {
        this.passingDate = passingDate;
    }

    @JsonIgnore
    @XmlTransient
    public CandidateAnswer getCandidateAnswer() {
        return this.candidateAnswer;
    }

    public void setCandidateAnswer(CandidateAnswer candidateAnswer) {
        this.candidateAnswer = candidateAnswer;
    }

    public Quiz getQuizModel() {
        return this.quizModel;
    }

    public void setQuizModel(Quiz quizModel) {
        this.quizModel = quizModel;
    }

    public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidateQuiz)) return false;

        CandidateQuiz that = (CandidateQuiz) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

	public CandidateQuiz(Long id, Date passingDate) {
		super();
		this.id = id;
		this.passingDate = passingDate;
	}
    
	public CandidateQuiz() {
		
	}
}
