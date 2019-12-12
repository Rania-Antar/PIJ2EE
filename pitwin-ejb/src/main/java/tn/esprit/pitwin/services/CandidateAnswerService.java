package tn.esprit.pitwin.services;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pitwin.entities.Answer;
import tn.esprit.pitwin.entities.CandidateAnswer;
import tn.esprit.pitwin.entities.CandidateQuiz;
import tn.esprit.pitwin.interfaces.ICandidateAnswerService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.docraptor.ApiClient;
import com.docraptor.ApiException;
import com.docraptor.Doc;
import com.docraptor.DocApi;

@Stateless
public class CandidateAnswerService extends AbstractService<CandidateAnswer> implements ICandidateAnswerService, Serializable {

    @PersistenceContext(unitName = "pitwin-ejb")
    private EntityManager em;

    public CandidateAnswerService() {
        super(CandidateAnswer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    @Override
	public void PDF(long id) {

    	
    	//CandidateAnswer CA = search(id);
    	CandidateQuiz CQ = searchQ(id);
    	//Set<Answer> li = CA.getAnswers();
    	Set<Answer> li = CQ.getCandidateAnswer().getAnswers();
    	List<Answer> lii =  new ArrayList<>(li);
	    DocApi docraptor = new DocApi();
	    ApiClient client = docraptor.getApiClient();
	    client.setUsername("YOUR_API_KEY_HERE");
	    Doc doc = new Doc();
	    doc.setTest(true);  
	    for(Answer a : lii ) {
	    doc.setDocumentContent(a.getAnswer());   
	    }
	    doc.setDocumentType(Doc.DocumentTypeEnum.PDF);                      
	    doc.setName("docraptor-java.pdf");                               
	    doc.setJavascript(true);                                          
	    try {
			docraptor.createDoc(doc);
		      byte[] create_response = docraptor.createDoc(doc);
		      FileOutputStream file = new FileOutputStream("C:\\Users\\DELL\\Desktop\\CandidateQuiz.pdf");
		      file.write(create_response);
		      file.close();
		      System.err.println("Wrote PDF to /tmp/docraptor-java.pdf");
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 

	}
    
    public CandidateAnswer search(long id) {
		return em.find(CandidateAnswer.class, id);
	}
    
    public CandidateQuiz searchQ(long id) {
		return em.find(CandidateQuiz.class, id);
	}
 

}
