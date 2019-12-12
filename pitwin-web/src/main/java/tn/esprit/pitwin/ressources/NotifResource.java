/*package tn.esprit.pitwin.ressources;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.pitwin.entities.Notif;
import tn.esprit.pitwin.services.NotifService;
import tn.esprit.pitwin.utilities.NotifType;

@Stateless
@Path("notifs")
public class NotifResource {
	
	@EJB
    NotifService notificationService = new NotifService();

    @GET
    @Path("all")
    @Asynchronous
    @Produces(MediaType.APPLICATION_JSON)
    public void getNotifications(@Suspended final AsyncResponse ar) {
        ar.setTimeout(10, TimeUnit.SECONDS);
        List<Notif> result = notificationService.listNotifications();
        Response resp = Response.ok(result).build();
        ar.resume(resp);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("add")
    public void CreateNotification(@QueryParam("reciever")int idReciever
    		,@QueryParam("body")String body
    		,@QueryParam("by")int trigger,@QueryParam("target")int target) {
		NotifType type = NotifType.Quiz_success;
    	String message = notificationService.parseText(type,body,trigger,target);
    	notificationService.CreateNotification(idReciever, message, type,trigger,target);		
    }
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("show")
	public Response showNotification(@QueryParam("id")int id) {	
		Notif notif = notificationService.get(id);
		if (notif == null)
		return Response.status(Response.Status.NOT_FOUND).entity("Notification doesn't exist").build();
		else
		return Response.status(Status.OK).entity(notif).build();
	}

}
*/