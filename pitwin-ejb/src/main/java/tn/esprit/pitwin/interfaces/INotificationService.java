package tn.esprit.pitwin.interfaces;

import java.util.List;
import javax.ejb.Remote;

import tn.esprit.pitwin.entities.Notif;
import tn.esprit.pitwin.utilities.NotifType;


@Remote
public interface INotificationService {
	
	void CreateNotification(int idReciever, String body, 
			NotifType type, int trigger, int target);
	boolean deleteNotif(int id);
	boolean updateNotif(int idNotif,String notif_message);

	Notif get(int id);	
	List<Notif> listNotifications();
	List<Notif> userNotifications(int id);
	List<Notif> typeNotifications(int id, NotifType type);
	
	boolean setSeen(int id);
	int findDuplicate(Notif notif);


}
