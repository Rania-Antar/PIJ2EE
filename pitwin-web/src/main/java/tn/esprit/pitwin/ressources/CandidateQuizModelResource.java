package tn.esprit.pitwin.ressources;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.pitwin.entities.Candidate;
import tn.esprit.pitwin.entities.CandidateQuiz;
import tn.esprit.pitwin.entities.Question;
import tn.esprit.pitwin.entities.Quiz;
import tn.esprit.pitwin.interfaces.ICandidateQuizModelService;
import tn.esprit.pitwin.interfaces.ICandidateService;
import tn.esprit.pitwin.interfaces.IQuestionService;
import tn.esprit.pitwin.ressource.util.HeaderUtil;
import tn.esprit.pitwin.services.QuizModelService;
import tn.esprit.pitwin.utilities.PDFGenerator;
import tn.esprit.pitwin.utilities.TicketGenerator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("candidate-quiz")
public class CandidateQuizModelResource {


    @EJB
    private ICandidateQuizModelService candidateQuizModelService;
    @EJB
    private IQuestionService questionService;
    @EJB
    private ICandidateService candidateService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCandidateQuizModel(CandidateQuiz candidateQuiz) throws URISyntaxException {

        candidateQuizModelService.create(candidateQuiz);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/rest/candidate-quiz/" + candidateQuiz.getId())),
                "candidateQuizModel", candidateQuiz.getPassingDate().toString())
                .entity(candidateQuiz).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCandidateQuizModel(CandidateQuiz candidateQuiz) throws URISyntaxException {

        candidateQuizModelService.edit(candidateQuiz);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidateQuiz", candidateQuiz.getId().toString())
                .entity(candidateQuiz).build();
    }

    @GET
    @Produces("application/json")
    public List<CandidateQuiz> getAllCandidateQuizModels() {

        List<CandidateQuiz> candidateQuizModels = candidateQuizModelService.findAll();
        return candidateQuizModels;
    }


    @Path("/{id}")
    @GET
    @Produces("application/json")
    public Response getCandidateQuizModel(@PathParam("id") Long id) {

        CandidateQuiz candidateQuizModel = candidateQuizModelService.find(id);
        return Optional.ofNullable(candidateQuizModel)
                .map(result -> Response.status(Response.Status.OK).entity(candidateQuizModel).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }


    @Path("/{id}")
    @DELETE
    public Response removeCandidateQuizModel(@PathParam("id") Long id) {

        candidateQuizModelService.remove(candidateQuizModelService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidateQuizModel", id.toString()).build();
    }

    @Path("/{id}/score")
    @GET
    @Produces("application/json")
    public double calculateScore(@PathParam("id") Long id) {
        CandidateQuiz candidateQuizModel = candidateQuizModelService.find(id);
        return candidateQuizModelService.calculateScore(candidateQuizModel);
        
        
    }
    
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("scorepdf/{id}")
	public Response scorePDF(@PathParam("id") long id) {
        PDFGenerator generator = new PDFGenerator();
  		System.out.println("your score is : " + generator);

  		CandidateQuiz toGenerate = candidateQuizModelService.find(id);
  		double score = calculateScore(id);
  		System.out.println("Score Quiz To Generate: " + score);
  		generator.GenerateTicket(score);
  		
  		return Response.status(Status.OK).build();

	}

    @Path("/{idCandidateQuiz}/score/{idQuestion}")
    @GET
    @Produces("application/json")
    public double getSingleQuestionNote(@PathParam("idCandidateQuiz") Long idCandidateQuizModel,
                                        @PathParam("idQuestion") Long idQuestion) {
        CandidateQuiz candidateQuizModel = candidateQuizModelService.find(idCandidateQuizModel);
        Question question = questionService.find(idQuestion);
        return candidateQuizModelService.calculateSingleQuestionNote(candidateQuizModel, question);
    }

    @Path("/historique")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<CandidateQuiz, Double> getHistorique() {
        return candidateQuizModelService.getHistorique();
    }

    
    @Path("min/{candidateId}/{minScore}")
    @GET
    @Produces("application/json")
    public List<CandidateQuiz> getByCandidate(@PathParam("candidateId") long candidateId,
    		                                   @PathParam("minScore")double minScore){

        Candidate candidate=candidateService.find(candidateId);
        if (candidate!=null)
            return candidateQuizModelService.getByCandidate(candidate,minScore);

        return null;
    }
    
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("quizTest/{id}")
	public Response test(@PathParam("id") long id) {
        TicketGenerator ticketgenerator = new TicketGenerator();
  		System.out.println("quiz: " + ticketgenerator);

  		CandidateQuiz tickettoGenerate = candidateQuizModelService.find(id);
  		System.out.println("Ticket To Generate: " + tickettoGenerate);
  		ticketgenerator.GenerateTicket(tickettoGenerate);
  		
  		return Response.status(Status.OK).build();

	}
    
    @PUT
	@Path("state")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setQuizState(@QueryParam("id") long id) {
    	candidateQuizModelService.setState(id);
		CandidateQuiz quiz = candidateQuizModelService.find(id);
		return Response.status(Status.OK).entity(quiz.getState()).build();
	}
  
}
