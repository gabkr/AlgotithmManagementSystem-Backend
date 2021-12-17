package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.ClassificationMergeRequest;
import cs509.thalassa.demo.http.ClassificationMergeResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ClassificationMergeHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	ClassificationMergeHandler handler = new ClassificationMergeHandler();
    	ClassificationMergeRequest req = new Gson().fromJson(incoming, ClassificationMergeRequest.class);
       
        ClassificationMergeResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	ClassificationMergeHandler handler = new ClassificationMergeHandler();
    	ClassificationMergeRequest req = new Gson().fromJson(incoming, ClassificationMergeRequest.class);

    	ClassificationMergeResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	ClassificationMergeRequest ccr = new ClassificationMergeRequest("a240feea-af7e-4906-8882-013543c4657e", "a834299b-c467-4202-b713-29285bd176b7", "Merged5","User5", "UserID5");
        
    	ccr.getClassificationId1();
    	ccr.getClassificationId2();
    	ccr.getName();
    	ccr.setClassificationId1("a240feea-af7e-4906-8882-013543c4657e");
    	ccr.setClassificationId2("a834299b-c467-4202-b713-29285bd176b7");
    	ccr.setName("Merged5");
    	
    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    /**
    @Test
    public void testGarbageInput() {
    	String SAMPLE_INPUT_STRING = "{\"sdsd\": \"e3\", \"hgfgdfgdfg\": \"this is not a number\"}";
        
        try {
        	testFailInput(SAMPLE_INPUT_STRING, 400);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    **/
    /**
    // overwrites into it
    @Test
    public void testCreateSystemConstant() {
    	// create constant
    	int rndNum = (int)(990*(Math.random()));
        CreateConstantRequest csr = new CreateConstantRequest("to-delete-again" + rndNum, 9.29837, true);
        
        CreateConstantResponse resp = new CreateConstantHandler().handleRequest(csr, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
    **/
}
