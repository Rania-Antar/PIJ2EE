package tn.esprit.pitwin.services;


import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tn.esprit.pitwin.entities.Candidate;


@Stateless
public class MailingService {
	
	
 
    public void send(Candidate candidate , String subject, String content) {
 
    	final String username = "antar.rania@gmail.com";
		final String password = "raniaantar1234";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}
		  });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("antar.rania@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(candidate.getEmail()));
			message.setSubject(subject);
			message.setText(content);
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
 
            System.out.println("Message successfully send to: " + candidate.getEmail());
        } 
 
    }
