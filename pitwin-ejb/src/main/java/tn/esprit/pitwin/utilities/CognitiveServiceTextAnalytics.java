/*package tn.esprit.pitwin.utilities;


import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CognitiveServiceTextAnalytics {
	
	public static String GetSentimentAnalytics(String comment) {
		HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/sentiment");

            //builder.setParameter("numberOfLanguagesToDetect", "1");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", "a55cd2956a944c4aa289222be8d17e25");

			// Request body
			StringEntity reqEntity = new StringEntity("{'documents':[{'language':'en','id':'1','text':'"+comment+"'}]}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            
            if (entity != null) 
            {
                //System.out.println(EntityUtils.toString(entity));
                return EntityUtils.toString(entity);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + "hereeeeeee ");
            return null;

        }
        return null;
	}
}*/