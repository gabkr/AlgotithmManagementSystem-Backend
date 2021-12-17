package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.CreateImplementationRequest;
import cs509.thalassa.demo.http.CreateImplementationResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateImplementationHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	CreateImplementationHandler handler = new CreateImplementationHandler();
    	CreateImplementationRequest req = new Gson().fromJson(incoming, CreateImplementationRequest.class);
       
        CreateImplementationResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	CreateImplementationHandler handler = new CreateImplementationHandler();
    	CreateImplementationRequest req = new Gson().fromJson(incoming, CreateImplementationRequest.class);

    	CreateImplementationResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	CreateImplementationRequest ccr = new CreateImplementationRequest("ImplementationNew","57c64e86-0eff-43b9-a29b-bc38ea142991","This is a sample implementation",
    			"User7","UserID7");
    	
    	ccr.getAlgorithmId();
    	ccr.getIdImplementation();
    	ccr.getImplementationFileFormat();
    	ccr.getImplementationMimeType();
    	ccr.getImplementationName();
    	ccr.getIsUpload();
    	ccr.getSystem();
    	ccr.getValue();
    	ccr.setAlgorithmId("57c64e86-0eff-43b9-a29b-bc38ea142991");
    	ccr.setSystem(true);
    	ccr.setValue("This is a code");
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    @Test
    public void test2() {
    	int rndNum = (int)(200*(Math.random()));
    	String rand = "Algo" + rndNum;
    	String var = "throwAway" + rndNum;
    	
    	CreateImplementationRequest ccr = new CreateImplementationRequest(rand,"f36c4990-a8ab-45cb-80e2-f3cc7e66f358","This is a sample implementation",
    			"User7","UserID7");
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
