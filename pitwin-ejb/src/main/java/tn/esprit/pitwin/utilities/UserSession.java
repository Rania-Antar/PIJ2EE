package tn.esprit.pitwin.utilities;

import java.util.Date;

import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pitwin.entities.User;

public final class UserSession {

	@PersistenceContext(unitName = "pitwin-ejb")
	EntityManager em;
	
	private static UserSession instance;

	private long id;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private boolean recieveMailNotifs;

	private Gender gender;

	private Date birthday;

	public boolean enable;

	private String confirm;

	private String address;

	private String username;

	private String token;

	private Role role;
	

	public UserSession(String username, String password, long id) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
	}

	public UserSession(long id, String email, String firstName, String lastName, String password,
			boolean recieveMailNotifs, Gender gender, Date date, boolean enable, String confirm, String address,
			  String username, String token) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.recieveMailNotifs = recieveMailNotifs;
		this.gender = gender;
		this.birthday = date;
		this.enable = enable;
		this.confirm = confirm;
		this.address = address;
		this.username = username;
		this.token = token;
	}

	public UserSession() {
	}

	public static UserSession getInstance(User user) {
		if (instance == null) {
			instance = new UserSession(user.getId(), user.getEmail(), user.getFirstname(), user.getLastname(), user.getPassword(), 
					user.isRecieveMailNotifs(), user.getGender(), user.getBirthday(), user.isEnable(), user.getConfirm(), user.getAddress(),
					 user.getUsername(), user.getToken());
		}
		return instance;
	}
	

	public static UserSession getInstance() {
		if (instance == null) {
			instance = new UserSession();
		}
		return instance;
	}

	
	public void cleanUserSession() {

		instance = null;

	}


	public static void setInstance(UserSession instance) {
		UserSession.instance = instance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRecieveMailNotifs() {
		return recieveMailNotifs;
	}

	public void setRecieveMailNotifs(boolean recieveMailNotifs) {
		this.recieveMailNotifs = recieveMailNotifs;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

}
