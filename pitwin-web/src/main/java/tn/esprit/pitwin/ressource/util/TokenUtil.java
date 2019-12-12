package tn.esprit.pitwin.ressource.util;

import io.jsonwebtoken.*;
import tn.esprit.pitwin.entities.*;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.UriInfo;

	public class TokenUtil {
		
		@Context
		private static UriInfo uriInfo;

	    public static String getToken(User user, Key key) {
	    	
	    	Map<String, Object> claims = new HashMap<String, Object>();

	        claims.put("id", user.getId().toString());

	         if (user instanceof Candidate) {
	            claims.put("role", Roles.CANDIDATE.toString());
	        }

	        claims.put("firstname", user.getFirstname());
	        claims.put("lastname", user.getLastname());
	        claims.put("exp", new Date(new Date().getTime() + 3600));

	      return Jwts.builder()
	                .setSubject(user.getUsername())
	                .setClaims(claims)
	                .signWith(SignatureAlgorithm.HS256, key)
	                .compact();
	        
	       /* return Jwts.builder()
	        		.setSubject(user.getUsername())
	        		.setIssuer(uriInfo.getAbsolutePath().toString())
					.setIssuedAt(new Date())
					.setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
					.signWith(SignatureAlgorithm.HS512, key)
					.compact();*/
	        
	    }

	    public static Jws<Claims> getClaims(Cookie cookie, Key key) {
	        if (cookie == null) {
	            throw new NotAuthorizedException("Not token found");
	        }

	        try {
	            return Jwts.parser().setSigningKey(key).parseClaimsJws(cookie.getValue());
	        } catch (JwtException je) {
	            throw new NotAuthorizedException("Token error");
	        }
	    }
	    
	    private static Date toDate(LocalDateTime localDateTime) {
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		}
	    
	    public static String issueToken(User user) {
	    	
			String keyString = "simplekey";
			Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
			System.out.println("the key is : " + key.hashCode());
			System.out.println("uriInfo.getAbsolutePath().toString() : " + uriInfo.getAbsolutePath().toString());
			System.out.println("Expiration date: " + toDate(LocalDateTime.now().plusMinutes(15L)));
			String jwtToken = Jwts.builder().setSubject(user.getUsername()).setIssuer(uriInfo.getAbsolutePath().toString())
					.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
					.signWith(SignatureAlgorithm.HS512, key).compact();
			System.out.println("the returned token is : " + jwtToken);
			return jwtToken;
		}


	}

