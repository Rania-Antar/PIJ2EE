package tn.esprit.pitwin.ressources;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.pitwin.entities.CandidateAnswer;
import tn.esprit.pitwin.interfaces.ICandidateAnswerService;
import tn.esprit.pitwin.ressource.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("candidate-answer")
public class CandidateAnswerResource  {

    @EJB
    private ICandidateAnswerService candidateAnswerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCandidateAnswer(CandidateAnswer candidateAnswer) throws URISyntaxException {

        candidateAnswerService.create(candidateAnswer);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/rest/candidate-answer/" + candidateAnswer.getId())),
                "candidateAnswer", candidateAnswer.toString())
                .entity(candidateAnswer).build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCandidateAnswer(CandidateAnswer candidateAnswer) throws URISyntaxException {

        candidateAnswerService.edit(candidateAnswer);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidateAnswer", candidateAnswer.getId().toString())
                .entity(candidateAnswer).build();
    }


    @GET
    @Produces("application/xml")
    public List<CandidateAnswer> getAllCandidateAnswers() {
        System.out.println("CANDID:" + candidateAnswerService);
        return candidateAnswerService.findAll();
    }


    @Path("/{id}")
    @GET
    @Produces("application/xml")
    public Response getCandidateAnswer(@PathParam("id") Long id) {

        CandidateAnswer candidateAnswer = candidateAnswerService.find(id);
        return Optional.ofNullable(candidateAnswer)
                .map(result -> Response.status(Response.Status.OK).entity(candidateAnswer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeCandidateAnswer(@PathParam("id") Long id) {

        candidateAnswerService.remove(candidateAnswerService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidateAnswer", id.toString()).build();
    }
    
    @GET
	@Path("/pdf")
	@Produces(MediaType.APPLICATION_JSON)
	public String PDF(@QueryParam(value="id")long id) {
    	candidateAnswerService.PDF(id);
		return "CandidateAnswer exported :) ";
	}

}
