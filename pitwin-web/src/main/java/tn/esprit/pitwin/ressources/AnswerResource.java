package tn.esprit.pitwin.ressources;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.pitwin.entities.Answer;
import tn.esprit.pitwin.interfaces.IAnswerService;
import tn.esprit.pitwin.ressource.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("answer")
public class AnswerResource {

    @EJB
    private IAnswerService answerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAnswer(Answer answer) throws URISyntaxException {

        answerService.create(answer);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/rest/answer/" + answer.getId())),
                "answer", answer.toString())
                .entity(answer).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAnswer(Answer answer) throws URISyntaxException {

        answerService.edit(answer);
        return Response.ok(answer).build();
    }
    @GET
    @Produces("application/json")
    public List<Answer> getAllAnswers() {

        List<Answer> answers = answerService.findAll();
        return answers;
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public Response getAnswer(@PathParam("id") Long id) {

        Answer answer = answerService.find(id);
        return Optional.ofNullable(answer)
                .map(result -> Response.status(Response.Status.OK).entity(answer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeAnswer(@PathParam("id") Long id) {

        answerService.remove(answerService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "answer", id.toString()).build();
    }
    @Path("/question/{id}")
    @GET
    @Produces("application/json")
    public List<Answer> getQuestionAnswers(@PathParam("id") Long id) {

        List<Answer> answers = answerService.findQuestionAnswers(id);
        return answers;
    }

}
