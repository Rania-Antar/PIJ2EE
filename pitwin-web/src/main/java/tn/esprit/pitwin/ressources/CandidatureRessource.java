package tn.esprit.pitwin.ressources;

import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.pitwin.entities.Candidate;
import tn.esprit.pitwin.entities.Candidature;
import tn.esprit.pitwin.interfaces.ICandidateService;
import tn.esprit.pitwin.interfaces.ICandidatureService;

@Path("candidature")
public class CandidatureRessource {
	
	@EJB
    private ICandidatureService candidatureService;
	
	@EJB
    private ICandidateService candidateService;
	
	@GET
    @Produces("application/json")
    public List<Candidature> getAll() {

        List<Candidature> answers =  candidatureService.findAll();
        return answers;
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public Response getOne(@PathParam("id") Long id) {

    	Candidature candidature = candidatureService.find(id);
        return Optional.ofNullable(candidature)
                .map(result -> Response.status(Response.Status.OK).entity(candidature).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
	
    @PUT
	@Path("/accept")
	@Produces(MediaType.APPLICATION_JSON)
	public Response accept(@QueryParam("CandidatureId")long CandidatureId) {
	
		candidatureService.Accept(CandidatureId);
		Candidature candidature = candidatureService.find(CandidatureId);
        return Optional.ofNullable(candidature)
                .map(result -> Response.status(Response.Status.OK).entity(candidature).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
		
	}
    
    @PUT
	@Path("/reject")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reject(@QueryParam("CandidatureId")long CandidatureId) {

    	candidatureService.Reject(CandidatureId);
    	Candidature candidature = candidatureService.find(CandidatureId);
        return Optional.ofNullable(candidature)
                .map(result -> Response.status(Response.Status.OK).entity(candidature).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
		
	}
    @Path("/candidate")
    @GET
    @Produces("application/json")
    public List<Candidate> getAllCandidate() {

        List<Candidate> answers =  candidateService.findCandidate();
        return answers;
    }

}
