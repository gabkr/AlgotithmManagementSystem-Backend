package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.CreateClassificationRequest;
import cs509.thalassa.demo.http.CreateClassificationResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateClassificationHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	CreateClassificationHandler handler = new CreateClassificationHandler();
    	CreateClassificationRequest req = new Gson().fromJson(incoming, CreateClassificationRequest.class);
       
        CreateClassificationResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	CreateClassificationHandler handler = new CreateClassificationHandler();
    	CreateClassificationRequest req = new Gson().fromJson(incoming, CreateClassificationRequest.class);

    	CreateClassificationResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	CreateClassificationRequest ccr = new CreateClassificationRequest("ClassificationABCD","10daa583-9a21-41db-9830-9ea47e76b025", "User6", "UserID6");
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
 
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    @Test
    public void test2() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	CreateClassificationRequest ccr = new CreateClassificationRequest("ClassificationABCDE", "User6", "UserID6");
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
        
        ccr.getParentClassification();
        ccr.setParentClassification("91540fbb-7c31-4c61-8e4d-9a2a0fb79ac8");
        ccr.setNameClassification("ClassificationABCDEF");
        ccr.getNameClassification();
        ccr.getSystem();
        ccr.setSystem(false);
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }

    
    @Test
    public void test3() {
    	int rndNum = (int)(990*(Math.random()));
    	String rand = "Class" + rndNum;
    	String var = "throwAway" + rndNum;
    	
    	CreateClassificationRequest ccr = new CreateClassificationRequest(rand, "User9", "UserID9");
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
        
        ccr.getParentClassification();
        ccr.setParentClassification("91540fbb-7c31-4c61-8e4d-9a2a0fb79ac8");
        ccr.setNameClassification("ClassificationABCDEF");
        ccr.getNameClassification();
        ccr.getSystem();
        ccr.setSystem(false);
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
