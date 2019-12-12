
package tn.esprit.pitwin.ressources;

import tn.esprit.pitwin.entities.User;
import tn.esprit.pitwin.interfaces.*;

import javax.ejb.EJB;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("user")
//@Secured(Roles.CHIEF_HUMAN_RESOURCES_OFFICER)
public class UsersResource {

    @EJB
    private IUserService userService;
    
    @Context
	UriInfo uriInfo;
    
    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)

    public Response addUser(@QueryParam("username")String username,
    		                @QueryParam("email")String email,
    		                @QueryParam("password")String password) {
		
		User user = new User(username, password, email);
		
		if(!userService.UsernameMailUnique(user.getUsername(), user.getEmail())) {
		userService.addUser(user);


	 	return Response.status(Status.CREATED).entity("ADDED").build();
	 	}
		return Response.status(Status.NOT_ACCEPTABLE).entity("username or email exist").build();
		 

	 	}


    
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("authenticate")
	public Response authenticateUser(@QueryParam("email") String email, @QueryParam("password") String password) {

			Boolean test = userService.authenticate(email, password);
				if (test)
				{
					String token = issueToken(email);
					System.out.print("------------------------ "+ token);
					userService.updateToken(email,token);
					System.out.println("****************** " + token);
					 return Response.status(Status.CREATED).entity("CONNECTED").build();

				}
				else {
					return Response.status(Status.NOT_FOUND).entity("NOT FOUND").build();

				}

	}
    
    @PUT
	@Path("confirm")
	@Produces(MediaType.APPLICATION_JSON)

	public Response Confirm(@QueryParam("code") String code, @QueryParam("idUser") long idUser

	) {
		userService.confirmCode(code, idUser);

		return Response.status(Status.ACCEPTED).entity("ACCEPTED").build();
	}

    @PUT
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)

	public Response Logout(

	) {
		userService.logout();
		return Response.status(Status.ACCEPTED).entity("ACCEPTED").build();
	}
    
	private String issueToken(String email) {

		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		System.out.println("the key is : " + key.hashCode());

		System.out.println("uriInfo.getAbsolutePath().toString() : " + uriInfo.getAbsolutePath().toString());
		//System.out.println("Expiration date: " + toDate(LocalDateTime.now().plusMinutes(15L)));

		String jwtToken = Jwts.builder().setSubject(email).setIssuer(uriInfo.getAbsolutePath().toString())
				//.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
				.signWith(SignatureAlgorithm.HS512, key).compact();

		System.out.println("the returned token is : " + jwtToken);
		return jwtToken;
	
	}
	
		private Date toDate(LocalDateTime localDateTime) {
			
			return  Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		}

		


}
