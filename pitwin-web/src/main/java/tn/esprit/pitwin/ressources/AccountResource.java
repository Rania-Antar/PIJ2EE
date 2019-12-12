package tn.esprit.pitwin.ressources;

import tn.esprit.pitwin.entities.User;
import tn.esprit.pitwin.interfaces.ITokenService;
import tn.esprit.pitwin.interfaces.IUserService;
import tn.esprit.pitwin.ressource.util.TokenUtil;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Optional;


@Path("account")
public class AccountResource {
	
    @EJB
    private ITokenService tokenService;

    @EJB
    private IUserService userService;

    @Path("login/{username}/{password}")
    @GET
    public Response login(@PathParam("username") String username, @PathParam("password") String password) {
        
    	User user = userService.login(username, password);

        System.out.println("User found, adding coockie");


        return Optional.ofNullable(user)
                .map(u ->Response.ok(u).cookie(new NewCookie("access_token",
                                        TokenUtil.getToken(user, tokenService.getKey()),
                                        "/",
                                        "localhost",
                                        "",
                                        36000,
                                        false)).build())
                .orElseThrow(NotFoundException::new);
    }

    @Path("logout")
    @GET
    public Response logout() {
        return Response.ok().cookie(new NewCookie("access_token",
                "none",
                "/pitwin-web/rest/account/",
                "localhost",
                "",
                3600,
                true)).build();
    }
}
