/*package tn.esprit.pitwin.services;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pitwin.entities.Appointment;
import tn.esprit.pitwin.interfaces.IAppointmentService;

@Stateless
public class AppointmentService extends AbstractService<Appointment> implements IAppointmentService {
	
	@PersistenceContext(unitName = "pitwin-ejb")
    private EntityManager em;

    public AppointmentService() {
    	super(Appointment.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    private ConcurrentMap<Integer, Appointment> appointmentMap = new ConcurrentHashMap<>();

	@Override
	public Optional<Appointment> newAppointment(int slotId, String candidateId) {
		Appointment appt = appointmentMap.getOrDefault(slotId,
				Appointment.of(slotId, slotId, candidateId));

			return appt.getCandidateId().equals(candidateId) ? Optional
				.of(appointmentMap.computeIfAbsent(slotId, key -> appt))
				: Optional.empty();
	}

	@Override
	public Optional<Appointment> cancelAppointment(int id, String candidateId) {
		return findAppointmentById(id, candidateId).isPresent() ? Optional
				.of(appointmentMap.remove(id)) : Optional.empty();
	}

	@Override
	public Optional<Appointment> findAppointmentById(int id, String candidateId) {
		Appointment appt = appointmentMap.get(id);

		return appt != null && appt.getCandidateId().equals(candidateId) ? Optional
			.of(appt) : Optional.empty();
	}

}
*/