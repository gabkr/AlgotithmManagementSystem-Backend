package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.CreateProblemInstanceRequest;
import cs509.thalassa.demo.http.CreateProblemInstanceResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateProblemInstanceHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	CreateProblemInstanceHandler handler = new CreateProblemInstanceHandler();
    	CreateProblemInstanceRequest req = new Gson().fromJson(incoming, CreateProblemInstanceRequest.class);
       
        CreateProblemInstanceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	CreateProblemInstanceHandler handler = new CreateProblemInstanceHandler();
    	CreateProblemInstanceRequest req = new Gson().fromJson(incoming, CreateProblemInstanceRequest.class);

    	CreateProblemInstanceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(100*(Math.random()));
    	String rand = "Prob" + rndNum;
    	String var = "throwAway" + rndNum;
    	
    	CreateProblemInstanceRequest ccr = new CreateProblemInstanceRequest("6151f3ef-4311-4d0f-856a-dead4e6b4045","1,2,3,4", rand,"User6", "UserID6");
        
    	ccr.getAlgorithmId();
    	ccr.getInput();
    	ccr.getName();
    	ccr.getProblemInstanceId();
    	ccr.setAlgorithmId("6151f3ef-4311-4d0f-856a-dead4e6b4045");
    	ccr.setName(rand);
    	ccr.setInput("1,2,3,4");
    	
    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
 
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
}
