package tn.esprit.pitwin.ressources;

import tn.esprit.pitwin.interfaces.IQuizModelService;
import tn.esprit.pitwin.entities.Quiz;
import tn.esprit.pitwin.ressource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("quiz")
public class QuizModelResource {


    @EJB
    private IQuizModelService quizModelService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createQuizModel(Quiz quiz) throws URISyntaxException {

        quizModelService.create(quiz);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/rest/quiz/" + quiz.getId())),
                "quiz", quiz.getName())
                .entity(quiz).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateQuizModel(Quiz quiz) throws URISyntaxException {

        quizModelService.edit(quiz);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "quiz", quiz.getName())
                .entity(quiz).build();
    }

    @GET
    @Produces("application/json")
    public List<Quiz> getAllQuizModels() {

        List<Quiz> quizs = quizModelService.findAll();
        return quizs;
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuizModel(@PathParam("id") Long id) {

        Quiz quiz = quizModelService.find(id);
        return Optional.ofNullable(quiz)
                .map(result -> Response.status(Response.Status.OK).entity(quiz).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeQuizModel(@PathParam("id") Long id) {

        quizModelService.remove(quizModelService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "quiz", id.toString()).build();
    }

    @Path("/random")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRandomQuizModel(Quiz quiz, Long categoryId) throws URISyntaxException {
        quizModelService.addRandomQuizModel(quiz, categoryId);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/rest/quiz/" + quiz.getId())),
                "quiz", quiz.getName())
                .entity(quiz).build();
    }
 
    @Path("/recherche")
    @GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response SearchForQuizs(@QueryParam(value="search")String search,
									@QueryParam(value="name")String name,
									@QueryParam(value="duration")int duration,
									@QueryParam(value="questionsNumber")int questionsNumber
									
			){
		
		List<Quiz> liste=null;
		if(search!=null && name==null && duration==0 && questionsNumber==0 )
		liste = quizModelService.SearchForQuizs(search);   
		else if (search==null && name!=null && duration==0 && questionsNumber==0)
			liste = quizModelService.findQuizByName(name);             
		else if (search==null && name==null && duration!=0 && questionsNumber==0)
			liste = quizModelService.findQuizByDuration(duration);                       
		else if (search==null && name==null && duration==0 && questionsNumber!=0)
			liste = quizModelService.findQuizByQuestionsNumber(questionsNumber);                   
		else if (search==null && name==null && duration==0 && questionsNumber==0)
				 liste= quizModelService.findAll();

		return Response.status(Status.OK).entity(liste).build();
	}
    

}
