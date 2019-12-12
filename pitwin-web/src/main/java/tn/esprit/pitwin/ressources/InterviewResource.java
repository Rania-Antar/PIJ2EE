package tn.esprit.pitwin.ressources;

import tn.esprit.pitwin.entities.Candidate;
import tn.esprit.pitwin.entities.Category;
import tn.esprit.pitwin.entities.Interview;
import tn.esprit.pitwin.entities.Offre;
import tn.esprit.pitwin.interfaces.*;
import tn.esprit.pitwin.ressource.util.*;
import tn.esprit.pitwin.services.MailingService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.URISyntaxException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Path("interview")
//@Secured(Roles.RECRUITMENT_MANAGER)
public class InterviewResource {

	static List<Interview> interviews = new ArrayList<Interview>();

    @EJB
    private IInterviewService interviewService;
    
    @EJB
    private ICandidateService candidateService;
    
    @EJB
    private IOfferService offerService;
    
    @EJB
    private MailingService mailingService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInterview(Interview interview) throws URISyntaxException {

        interviewService.create(interview);

        notifyNewInterview(interview);

        return Response.created(new URI("/rest/interview/" + interview.getId())).entity(interview).build();
    }

    @PUT
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateInterview(Interview interview) throws URISyntaxException {

        return Optional.ofNullable(interviewService.find(interview.getId()))
                .map(interv -> {
                    if (!interview.equals(interv)) {
                        throw new NotAllowedException("Different Ids passed");
                    }
                    interv.setDate(interview.getDate());
                    interv.setTime(interview.getTime());
                    interviewService.edit(interv);
                    notifyUpdatedInterview(interv);
                    return Response.ok(interv).build();
                })
                .orElseThrow(NotFoundException::new);
    }


    @Path("one/{id}")
    @Produces("application/json")
    @GET
    public Response getInterview(@PathParam("id") Long id) {

        return Optional.ofNullable(interviewService.find(id))
                .map(interview -> Response.status(Response.Status.OK).entity(interview).build())
                .orElseThrow(NotFoundException::new);
    }
    @Produces("application/json")
    @GET
    public List<Interview> getAll() {

        List<Interview> categories = interviewService.findAll();
        return categories;
    }

    @Path("/{id}")
    @DELETE
    public Response removeInterview(@PathParam("id") Long id) {

        interviewService.remove(interviewService.find(id));
        notifyCanceledInterview(interviewService.find(id));
        return Response.ok().build();
    }
    
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getInterviewById(@PathParam("id")Long id)
	{
		if(interviewService.find(id)!=null)
		{
			return Response.status(Status.OK).entity(interviewService.findAll()).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
    
    @PUT
	@Path("date")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setDate(@QueryParam("id")long interview_id,@QueryParam("date")String date) {
		System.out.println(date);
		if(interviewService.setDate(interview_id, date))
			return Response.status(Status.OK).entity("date has been set").build();
		return Response.status(Status.OK).entity("invalid date").build();
	}
    
	
	@PUT
	@Path("time")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setTime(@QueryParam("id")long interview_id,@QueryParam("time")String time) {
		if(interviewService.setTime(interview_id, time))
			return Response.status(Status.OK).entity("time has been set").build();
		return Response.status(Status.BAD_REQUEST).entity("invalid time").build();
	}  

    public void notifyNewInterview(Interview interview) {
       Candidate candidate = candidateService.find(interview.getCandidature().getCandidate().getId());
       
       mailingService.send(candidate, "You have a new interview",
               "Dear " + candidate.getFirstname() + " " + candidate.getLastname()
                       + ", You are selected to pass an interview please select the day and the time ");  
   }

   public void notifyUpdatedInterview(Interview interview) {

       Candidate candidate = candidateService.find(interview.getCandidature().getCandidate().getId());

       mailingService.send(candidate, "Your interview is updated",
               "Dear " + candidate.getFirstname() + " " + candidate.getLastname()
                       + ", Your have an interview "
                       + "is changed to: " + interview.getDate());
   }
   
   public void notifyCanceledInterview(Interview interview) {

       Candidate candidate = candidateService.find(interview.getCandidature().getCandidate().getId());

       mailingService.send(candidate, "Cancelling interview !",
               "Dear " + candidate.getFirstname() + " " + candidate.getLastname()
                       + ", I’d like to inform you that, unfortunately, we need to cancel our interview that we had arranged for " +interview.getDate()
                       + "Thank you, again, for taking the time to apply and please accept my sincerest apologies for any inconvenience.");
   }


}
