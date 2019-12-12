package tn.esprit.pitwin.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pitwin.entities.Interview;

@Remote
public interface IInterviewService {
	
    void create(Interview interview);

    Interview edit(Interview interview);

    void remove(Interview interview);

    Interview find(Object id);

    List<Interview> findAll();
    
    public boolean setDate(long interview_id, String date);
    
    boolean isWeekend(String date);
    
    boolean validDay(String date);
    
    boolean validTime(String time);
    
    boolean setTime(long interview_id,String time);
    
    boolean setInterview(long quiz_id);
}
