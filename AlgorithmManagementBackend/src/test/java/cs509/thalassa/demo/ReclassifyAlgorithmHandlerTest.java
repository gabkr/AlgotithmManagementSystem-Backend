package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.AlgorithmReclassifyRequest;
import cs509.thalassa.demo.http.AlgorithmReclassifyResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ReclassifyAlgorithmHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	ReclassifyAlgorithm handler = new ReclassifyAlgorithm();
    	AlgorithmReclassifyRequest req = new Gson().fromJson(incoming, AlgorithmReclassifyRequest.class);
       
        AlgorithmReclassifyResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	ReclassifyAlgorithm handler = new ReclassifyAlgorithm();
    	AlgorithmReclassifyRequest req = new Gson().fromJson(incoming, AlgorithmReclassifyRequest.class);

    	AlgorithmReclassifyResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	AlgorithmReclassifyRequest ccr = new AlgorithmReclassifyRequest("6354b889-95e3-4815-af49-e49ea042cf48", "0d8f81d0-faf7-4312-bcb8-c066764aa88d", "User5", "UserID5");
        
    	ccr.getAlgorithmId();
    	ccr.getClassificationId();
    	ccr.setAlgorithmId("6354b889-95e3-4815-af49-e49ea042cf48");
    	ccr.setClassificationId("0d8f81d0-faf7-4312-bcb8-c066764aa88d");
    	
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
