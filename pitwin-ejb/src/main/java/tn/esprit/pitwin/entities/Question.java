package tn.esprit.pitwin.entities;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import tn.esprit.pitwin.utilities.QuestionType;
import java.io.Serializable;
import java.util.List;

@Entity
@XmlRootElement
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @Enumerated
    private QuestionType type;

    private Double score;

    @ManyToOne(targetEntity = CandidateAnswer.class, fetch = FetchType.EAGER)
    private CandidateAnswer candidateAnswer;

    @OneToMany(targetEntity = Answer.class, mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answers;

    @ManyToOne(targetEntity = Quiz.class, fetch = FetchType.EAGER)
    private Quiz quiz;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
    private Category category;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //@XmlTransient 
    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getType() {
        return this.type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Double getScore() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
    @XmlTransient 
    public CandidateAnswer getCandidateAnswer() {
        return this.candidateAnswer;
    }

    public void setCandidateAnswer(CandidateAnswer candidateAnswer) {
        this.candidateAnswer = candidateAnswer;
    }
    // @XmlTransient 
    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    @XmlTransient 
    public Quiz getQuiz() {
        return this.quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    @XmlTransient
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Question() {}
	
	public Question(Long id, String question,Double score, QuestionType type,  CandidateAnswer candidateAnswer, Quiz quiz, Category category) {
		super();
		this.id = id;
		this.question = question;
		this.score = score;
		this.type = type;
		this.candidateAnswer = candidateAnswer;
		this.quiz = quiz;
		this.category = category;
	}
	public Question(Long id, String question,Double score, QuestionType type) {
		super();
		this.id = id;
		this.question = question;
		this.score = score;
		this.type = type;
	}
   
}
