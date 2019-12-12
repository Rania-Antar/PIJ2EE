package tn.esprit.pitwin.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Offre implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ; 
	private String name ; 
	private String desciption ; 
	private String Location ;
	private String diplome;	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date; 
	
	@OneToMany(targetEntity = Candidature.class, mappedBy = "offre", fetch = FetchType.EAGER)
	private List<Candidature> candidatures;
	
	@ManyToMany
	private List<User> users = new ArrayList<User>();
	
	 @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	 private User user ;
	 
	 @ManyToMany(targetEntity = Quiz.class)
	    private List<Quiz> quizModel;
	 
    @XmlTransient
	public List<Candidature> getCandidatures() {
			return candidatures;
	}
	public void setCandidatures(List<Candidature> candidatures) {
			this.candidatures = candidatures;
	} 
    public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	@XmlTransient
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@XmlTransient
	public List<Quiz> getQuizModel() {
		return quizModel;
	}
	public void setQuizModel(List<Quiz> quizModel) {
		this.quizModel = quizModel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Location == null) ? 0 : Location.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((desciption == null) ? 0 : desciption.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Offre other = (Offre) obj;
		if (Location == null) {
			if (other.Location != null)
				return false;
		} else if (!Location.equals(other.Location))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (desciption == null) {
			if (other.desciption != null)
				return false;
		} else if (!desciption.equals(other.desciption))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Offre [id=" + id + ", name=" + name + ", desciption=" + desciption + ", Location=" + Location + ", date="
				+ date +"]";
	}
	
	public Offre() {
		super();
	}
	public Offre(int id, String name, String desciption, String location, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.desciption = desciption;
		Location = location;
		this.date = date;
	} 


}
