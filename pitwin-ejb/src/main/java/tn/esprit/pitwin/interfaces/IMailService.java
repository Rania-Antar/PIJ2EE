package tn.esprit.pitwin.interfaces;

import javax.ejb.Remote;

@Remote
public interface IMailService {
	
	 void sendEmail(String toEmail,String subject, String body);
     boolean isValidEmailAddress(String email);

}
