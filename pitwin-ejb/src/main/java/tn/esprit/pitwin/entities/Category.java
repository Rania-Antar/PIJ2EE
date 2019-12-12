package tn.esprit.pitwin.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.List;

@Entity
@XmlRootElement
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @OneToMany(targetEntity = Question.class, mappedBy = "category", fetch = FetchType.EAGER )
    private List<Question> questions;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlTransient 
    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
