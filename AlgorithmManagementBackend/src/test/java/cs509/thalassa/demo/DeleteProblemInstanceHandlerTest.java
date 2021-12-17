package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.DeleteProblemInstanceRequest;
import cs509.thalassa.demo.http.DeleteProblemInstanceResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DeleteProblemInstanceHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	DeleteProblemInstanceHandler handler = new DeleteProblemInstanceHandler();
    	DeleteProblemInstanceRequest req = new Gson().fromJson(incoming, DeleteProblemInstanceRequest.class);
       
        DeleteProblemInstanceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	DeleteProblemInstanceHandler handler = new DeleteProblemInstanceHandler();
    	DeleteProblemInstanceRequest req = new Gson().fromJson(incoming, DeleteProblemInstanceRequest.class);

    	DeleteProblemInstanceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	DeleteProblemInstanceRequest ccr = new DeleteProblemInstanceRequest( "1b322e36-bc74-4bc5-a5a7-8f698a43365a","UserABC", "UserID100");
        
    	ccr.getProblemInstanceId();
    	
    	
    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
 
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
}
