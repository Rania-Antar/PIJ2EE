package tn.esprit.pitwin.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import tn.esprit.pitwin.entities.Candidate;
import tn.esprit.pitwin.entities.CandidateQuiz;
import tn.esprit.pitwin.entities.Interview;
import tn.esprit.pitwin.entities.Quiz;
import tn.esprit.pitwin.interfaces.IInterviewService;
import tn.esprit.pitwin.utilities.State;

@Stateless
public class InterviewService extends AbstractService<Interview> implements IInterviewService {
	
	public static CandidateService c = new CandidateService();

    @PersistenceContext(unitName = "pitwin-ejb")
    private EntityManager em;

    public InterviewService() {
        super(Interview.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
	public boolean setDate(long interview_id, String date) {
		if (isWeekend(date) || validDay(date)) {
			Interview in = em.find(Interview.class, interview_id);
			in.setDate(Date.valueOf(date));
			return true;
		}
		return false;
	}

	@Override
	public boolean isWeekend(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		Calendar cal = new GregorianCalendar(year, month, day);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return (Calendar.SUNDAY == dayOfWeek || Calendar.SATURDAY == dayOfWeek);
	}

	@Override
	public boolean validDay(String date) {
		System.out.println(Date.valueOf(LocalDate.now()));
		if (Date.valueOf(date).after(Date.valueOf(LocalDate.now()))) {
			System.out.println("Sorry but the date is not valid");
			return true;
		}
		return false;
	}

	@Override
	public boolean validTime(String time) {
		int hours = Integer.parseInt(time.substring(0, 2));
		if (hours > 9 && hours < 18)
			return true;
		return false;
	}

	@Override
	public boolean setTime(long interview_id,String time) {
		if(validTime(time)) {
			Interview interview =em.find(Interview.class, interview_id);
			interview.setTime(Time.valueOf(time));
			return true;
		}
		return false;
	}
	
	@Override
	public boolean setInterview(long quiz_id) {
		CandidateQuiz q = em.find(CandidateQuiz.class, quiz_id);
			if (q.getState() == State.Validated) {
				Interview in = new Interview();
				String da = generateDate();
				while (!(isThisDateValid(da, "yyyy-mm-dd")) || !(isWeekend(da)) || !(validDay(da))) {
					da = generateDate();
				}
				in.setDate(Date.valueOf(da));
				em.persist(in);
				return true;
			}
		return false;
	}
	

	public String generateDate() {
		int year = ThreadLocalRandom.current().nextInt(Year.now().getValue(), Year.now().getValue() + 2);
		int month = ThreadLocalRandom.current().nextInt(1, 13);
		int day = ThreadLocalRandom.current().nextInt(1, 32);
		if (month < 10 && day < 10) {
			String da = String.valueOf(year) + "-0" + String.valueOf(month) + "-0" + String.valueOf(day);
			return da;
		}
		if (month < 10) {
			String da = String.valueOf(year) + "-0" + String.valueOf(month) + "-" + String.valueOf(day);
			return da;
		}
		if (day < 10) {
			String da = String.valueOf(year) + "-0" + String.valueOf(month) + "-0" + String.valueOf(day);
			return da;
		}
		String da = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
		return da;
	}


	public boolean isThisDateValid(String dateToValidate, String dateFromat) {
		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			java.util.Date date = sdf.parse(dateToValidate);
			System.out.println(date);

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}
}
