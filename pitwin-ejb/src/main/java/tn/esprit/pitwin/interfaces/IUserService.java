package tn.esprit.pitwin.interfaces;

import javax.ejb.Remote;

import tn.esprit.pitwin.entities.User;

@Remote
public interface IUserService {
	
	 User login(String username, String password);
	 boolean authenticate(String username, String password);
	 void updateToken(String username,String token);
	 void confirmCode(String code, long idUser);
	 void logout();
	 void addUser(User user);
	 boolean UsernameMailUnique(String username,String email);

}
