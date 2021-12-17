package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.CreateUserRequest;
import cs509.thalassa.demo.http.CreateUserResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateUserRequestTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	CreateUserHandler handler = new CreateUserHandler();
    	CreateUserRequest req = new Gson().fromJson(incoming, CreateUserRequest.class);
       
        CreateUserResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	CreateUserHandler handler = new CreateUserHandler();
    	CreateUserRequest req = new Gson().fromJson(incoming, CreateUserRequest.class);

    	CreateUserResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	CreateUserRequest ccr = new CreateUserRequest("13","Harry", "harry@harry", "hry");
    	ccr.getEmail();
    	ccr.getId();
    	ccr.getName();
    	ccr.getUsername();
    	ccr.setEmail("harry@harry");
    	ccr.setId("13");
    	ccr.setName("Harry");
    	ccr.setUsername("hry");
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
