package tn.esprit.pitwin.entities;

import tn.esprit.pitwin.utilities.Gender;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@XmlRootElement
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_role", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "role",
        defaultImpl = Candidate.class)
@JsonSubTypes({ @JsonSubTypes.Type(value = Candidate.class, name = "Candidate") })
//@DiscriminatorOptions(force=true)
public class User implements Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String username;

    private String password;

    private String email;

    private String tel;
    
    @Enumerated
    private Gender gender;

    private String address;
    
    private boolean recieveMailNotifs;
    
    private boolean enable;
    
	private String confirm;
	
	private String token;
	
	private Date accountCreationDate;

	@Column(name = "active", columnDefinition = "tinyint(1) default 1")
    private Boolean active;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reciever", fetch = FetchType.EAGER)
	private Set<Notif> Notifications;

    public Set<Notif> getNotifications() {
		return Notifications;
	}

	public void setNotifications(Set<Notif> notifications) {
		Notifications = notifications;
	}

	public boolean isRecieveMailNotifs() {
		return recieveMailNotifs;
	}

	public void setRecieveMailNotifs(boolean recieveMailNotifs) {
		this.recieveMailNotifs = recieveMailNotifs;
	}

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getAccountCreationDate() {
		return accountCreationDate;
	}

	public void setAccountCreationDate(Date accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User users = (User) o;

        return id.equals(users.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User() {
		super();
	}
    
    
    

}
