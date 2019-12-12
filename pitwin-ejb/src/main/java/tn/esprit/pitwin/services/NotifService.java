/*package tn.esprit.pitwin.services;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import tn.esprit.pitwin.entities.Notif;
import tn.esprit.pitwin.entities.User;
import tn.esprit.pitwin.interfaces.INotificationService;
import tn.esprit.pitwin.utilities.EmailUtil;
import tn.esprit.pitwin.utilities.NotifType;

import java.util.concurrent.TimeUnit;

@Stateless
@LocalBean
public class NotifService implements INotificationService {

	@PersistenceContext(unitName = "pitwin-ejb")
	EntityManager em;
	EmailUtil mail = new EmailUtil();

	@Override
	public void CreateNotification(int idReciever,String body,
			NotifType type,int trigger,int targetId) {
			

		Set<NotifType> POST_TYPE = EnumSet.of(NotifType.Quiz_fail,NotifType.Quiz_success);
	
			
		Notif newNotification = new Notif();
		User reciever= em.find(User.class,idReciever); 
		Timestamp date = new Timestamp(System.currentTimeMillis());
		
		newNotification.setMessage(body);
		newNotification.setReciever(reciever); 
		newNotification.setDate(date);
		
		
		/* check if notification exists */
/*		int duplicateId = this.findDuplicate(newNotification);
		if (duplicateId != -1)
		{
			this.updateNotif(duplicateId,body);
		} else
		{
		em.persist(newNotification);
		}
		
		/* mail notification */
/*		if (reciever.isRecieveMailNotifs()) {
			
			
			String format ="<h4>You have a new Notification on Professional Network</h4><br>"
					+ "<p> %s </p> </br>"
					+ "<p> on %s . </p> </br>"
					+ "<p> click <a href='http://localhost:10080/pitwin-web/rest/%s/show?id=%s'>here</a> to see it. </p> </br>"
					+ "<p> You can disable these notifications. </p> </br>"
					+ "<b>Automatic Message by Professional Network </b>";
		
			String content = String.format(format,newNotification.getMessage(),
				new SimpleDateFormat("MMdd").format(date),body,"post","1");
	

			String subject = "You have a new Notification!";
			mail.sendEmail(reciever.getEmail(), subject, content);	
		}
		
	}
	
	public String parseText(NotifType type,String body,int triggerId,int target) {
		String name = em.find(User.class,triggerId).getFirstname()+" "+em.find(User.class,triggerId).getLastname();
		String content="";
		 switch (type) {
         case Quiz_success:
                  content += name +" : "+ body;
                 break;
	    
         case Quiz_fail:
                  content += name +" you failed";
                 break;

         default:
			
        	 	  content = "You have a new notification.";
        	 	  break;
     }
		 return content;
	}

	public List<Notif> listNotifications() {
		List<Notif> result =  em
				.createQuery("select notif from Notif notif", Notif.class)
				.getResultList();
		try {
			System.out.println("Wait for 2 seconds before returning " + result);
			Thread.sleep(TimeUnit.SECONDS.toMillis(2));
		} catch (InterruptedException e) {
		}

		return result;
	}

	public Notif get(int id) {
		return em.find(Notif.class,id);
	}

	@Override
	public boolean deleteNotif(int id) {
		Notif notif = em.find(Notif.class, id);
		if (notif == null)
		{
			return false;
		}
		em.remove(notif);
		return true;
		
	}

	@Override
	public boolean updateNotif(int id,String notif_message) {
		
		Notif notif = em.find(Notif.class, id);
		if (notif == null)
		{
			return false;
		}
		Timestamp date = new Timestamp(System.currentTimeMillis());
		notif.setDate(date);
		notif.setMessage(notif_message);
		return true;
		
	}

	@Override
	public List<Notif> userNotifications(int idUser) {
		List<Notif> notifs = em.createQuery("select n from Notif n where n.reciever.id=:Id",Notif.class)
				.setParameter("Id", idUser).getResultList();
		return notifs;
	}

	@Override
	public List<Notif> typeNotifications(int id, NotifType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findDuplicate(Notif notif) {
		List<Notif> duplicate =  em.createQuery("select n from Notif n where n.reciever.id=:reciever AND "
				+ "n.targetId=:targetId AND n.target=:target",Notif.class)
				.setParameter("reciever", notif.getReciever().getId())
				.getResultList();
		if (duplicate.isEmpty())
		{
		return (-1);
		}
		return duplicate.get(0).getId();
}

	@Override
	public boolean setSeen(int id) {
		Notif notif = em.find(Notif.class,id);
		if (notif == null)
		{
			return false;
		}
		notif.setSeen(true);
		em.merge(notif);
		return true;
	}
	
}
*/