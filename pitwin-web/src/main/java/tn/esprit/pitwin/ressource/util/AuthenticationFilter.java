package tn.esprit.pitwin.ressource.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import tn.esprit.pitwin.interfaces.ITokenService;

import javax.ejb.EJB;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
@Secured
public class AuthenticationFilter implements ContainerRequestFilter {

    @EJB
    private ITokenService tokenService;
    
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        System.out.println("here " + containerRequestContext.getCookies());

        if (containerRequestContext.getCookies().containsKey("access_token")) {
            String token = containerRequestContext.getCookies().get("access_token").getValue();

            try {
                Jws<Claims> jws = Jwts.parser().setSigningKey(tokenService.getKey()).parseClaimsJws(token);

                if (!requiredRoles(jws)) {
                    throw new NotAuthorizedException("You do not have enough permission");
                }
            } catch (JwtException je) {
                throw new NotAuthorizedException("Token error");
            }
        } else {
            throw new NotAuthorizedException("Token not found");
        }
    }

    private boolean requiredRoles(Jws<Claims> jws) {
        Secured secured = resourceInfo.getResourceMethod().getAnnotation(Secured.class);
        if (secured == null) {
            secured = resourceInfo.getResourceClass().getAnnotation(Secured.class);
        }

        for (Roles role : secured.value()) {
            if (role == Roles.ANONYMOUS && jws.getBody().get("role") != null) {
                return true;
            }

            if (role.toString().equals(jws.getBody().get("role"))) {
                return true;
            }
        }

        return false;
    }
}

