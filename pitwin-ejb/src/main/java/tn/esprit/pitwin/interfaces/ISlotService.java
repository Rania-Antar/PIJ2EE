/*package tn.esprit.pitwin.interfaces;

import java.util.List;
import java.util.Optional;

import javax.ejb.Remote;

import tn.esprit.pitwin.entities.Slot;
import tn.esprit.pitwin.entities.User;

@Remote
public interface ISlotService {
	
	List<User> getResponsables();
	
	Optional<Slot> findSlotById(int id);
	
	Optional<List<Slot>> findSlotsByDate(String date);
	
	boolean isSlotReserved(int id);
	
	Optional<Slot> updateSlotAvailability(int id, boolean reserved);
	
	

}
*/