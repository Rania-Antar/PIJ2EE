/*package tn.esprit.pitwin.ressources;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import tn.esprit.pitwin.utilities.CognitiveServiceTextAnalytics;


@Path("sentiments")
@RequestScoped
public class SentimentResource {


	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getScore(@QueryParam("answer")String answer){
		String t = CognitiveServiceTextAnalytics.GetSentimentAnalytics(answer);
		System.out.println("**********************");
		System.out.println("********* "+ t +"*************");
		System.out.println("**********************");
		return t;

	}

}
*/