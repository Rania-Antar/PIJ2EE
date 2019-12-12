package tn.esprit.pitwin.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pitwin.entities.User;

@Remote
public interface UserRemote {
	void createUser(User user);
	List<User> findAllUsers();
	List<User> authenticate(String login, String password);
	boolean loginExists(String login);
	 User findUserById(String Cin);
	void deleteUser(User u);
	 boolean updateUser(User u);  
	 boolean EmailExists(String mail);

	//User authenticateJEE(String login, String password);
	//  void updateStatu(String login, String password);
	// void updateInc(String login, String password);
	// void UpdateUser(User e);

}
