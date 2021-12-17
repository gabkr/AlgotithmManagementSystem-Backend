package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.CreateAlgorithmRequest;
import cs509.thalassa.demo.http.CreateAlgorithmResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateAlgorithmHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	CreateAlgorithmHandler handler = new CreateAlgorithmHandler();
    	CreateAlgorithmRequest req = new Gson().fromJson(incoming, CreateAlgorithmRequest.class);
       
        CreateAlgorithmResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	CreateAlgorithmHandler handler = new CreateAlgorithmHandler();
    	CreateAlgorithmRequest req = new Gson().fromJson(incoming, CreateAlgorithmRequest.class);

    	CreateAlgorithmResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	CreateAlgorithmRequest ccr = new CreateAlgorithmRequest("Custom6", "0cf3717c-245d-48f9-ad12-2646c1adb4be", "User5", "UserID5");
        
    	ccr.getIdAlgorithm();
    	ccr.getNameAlgorithm();
    	ccr.getParentId();
    	ccr.getSystem();
    	ccr.setParentId("0cf3717c-245d-48f9-ad12-2646c1adb4be");
    	ccr.setSystem(false);
    	
    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    @Test
    public void test2() {
    	int rndNum = (int)(500*(Math.random()));
    	String rand = "Algo" + rndNum;
    	String var = "throwAway" + rndNum;
    	
    	CreateAlgorithmRequest ccr = new CreateAlgorithmRequest("rand", "91c4ce24-d71f-47ae-8d0a-090c90b980a1", "User5", "UserID5");
        
    	ccr.setNameAlgorithm(rand);
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
