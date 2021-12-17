package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.DeleteClassificationRequest;
import cs509.thalassa.demo.http.DeleteClassificationResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DeleteClasificationHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	DeleteClassificationHandler handler = new DeleteClassificationHandler();
    	DeleteClassificationRequest req = new Gson().fromJson(incoming, DeleteClassificationRequest.class);
       
        DeleteClassificationResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	DeleteClassificationHandler handler = new DeleteClassificationHandler();
    	DeleteClassificationRequest req = new Gson().fromJson(incoming, DeleteClassificationRequest.class);

    	DeleteClassificationResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	DeleteClassificationRequest ccr = new DeleteClassificationRequest( "2e7ad24a-7025-496b-8755-04b155937598","User7","IserId7");
        
    	ccr.getClassificationId();
    	
    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
 
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
}
