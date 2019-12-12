package tn.esprit.pitwin.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.AssertFalse;

@Entity
public class Notif implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
    private String message;
    @AssertFalse
    private boolean seen;
    private Timestamp date;
    
    @JoinColumn(name = "Reciever", referencedColumnName = "id")
	@ManyToOne(fetch=FetchType.EAGER)
    private User reciever;
    
	public Notif() {
    	super();
    } 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}    
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}

	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}
	
	 @Override
		public String toString() {
			return "Notification [id=" + id + ", message=" + message + "]";
		}

}

