package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.ListImplementationRequest;
import cs509.thalassa.demo.http.AllImplementationsResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ListAllImplementationsHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	ListAllImplementationsHandler handler = new ListAllImplementationsHandler();
    	ListImplementationRequest req = new Gson().fromJson(incoming, ListImplementationRequest.class);
       
        AllImplementationsResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	ListAllImplementationsHandler handler = new ListAllImplementationsHandler();
    	ListImplementationRequest req = new Gson().fromJson(incoming, ListImplementationRequest.class);

    	AllImplementationsResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.statusCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	ListImplementationRequest ccr = new ListImplementationRequest("8c997607-99d3-4446-8928-456348d08556");
    	
    	ccr.getParentAlgorithm();
    	ccr.setParentAlgorithm("8c997607-99d3-4446-8928-456348d08556");
    	
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
