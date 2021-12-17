package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.DeleteAlgorithmRequest;
import cs509.thalassa.demo.http.DeleteAlgorithmsResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DeleteAlgorithmHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	DeleteAlgorithmHandler handler = new DeleteAlgorithmHandler();
    	DeleteAlgorithmRequest req = new Gson().fromJson(incoming, DeleteAlgorithmRequest.class);
       
        DeleteAlgorithmsResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	DeleteAlgorithmHandler handler = new DeleteAlgorithmHandler();
    	DeleteAlgorithmRequest req = new Gson().fromJson(incoming, DeleteAlgorithmRequest.class);

    	DeleteAlgorithmsResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	DeleteAlgorithmRequest ccr = new DeleteAlgorithmRequest( "59ecdd0a-bd42-4dbc-9ad1-e2ffb738b850","User7","IserId7");
        
    	ccr.getAlgorithmId();
    	
    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
 
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
}
