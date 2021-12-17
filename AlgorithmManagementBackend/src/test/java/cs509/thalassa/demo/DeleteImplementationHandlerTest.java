package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.DeleteImplementationRequest;
import cs509.thalassa.demo.http.DeleteImplementationResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DeleteImplementationHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	DeleteImplementationHandler handler = new DeleteImplementationHandler();
    	DeleteImplementationRequest req = new Gson().fromJson(incoming, DeleteImplementationRequest.class);
       
        DeleteImplementationResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	DeleteImplementationHandler handler = new DeleteImplementationHandler();
    	DeleteImplementationRequest req = new Gson().fromJson(incoming, DeleteImplementationRequest.class);

    	DeleteImplementationResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	DeleteImplementationRequest ccr = new DeleteImplementationRequest( "0abb1e18-d8de-4b59-9ca4-ee3665f568ea");
        
    	ccr.getImplementationId();
    	ccr.setImplementationId("0abb1e18-d8de-4b59-9ca4-ee3665f568ea");
    	
    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
 
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
}
