package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.ClassificationMergeRequest;
import cs509.thalassa.demo.http.ClassificationMergeResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ClassificationMergeHandlerTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	ClassificationMergeHandler handler = new ClassificationMergeHandler();
    	ClassificationMergeRequest req = new Gson().fromJson(incoming, ClassificationMergeRequest.class);
       
        ClassificationMergeResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	ClassificationMergeHandler handler = new ClassificationMergeHandler();
    	ClassificationMergeRequest req = new Gson().fromJson(incoming, ClassificationMergeRequest.class);

    	ClassificationMergeResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.httpCode);
    }

   
    // NOTE: this proliferates large number of constants! Be mindful
    @Test
    public void testShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	ClassificationMergeRequest ccr = new ClassificationMergeRequest("39c29836-cf91-46e4-b083-c651f7c02ecb", "681eba51-e415-4995-8445-4e6ea5c81bb5", "Merged8","User5", "UserID5");
        
    	ccr.getClassificationId1();
    	ccr.getClassificationId2();
    	ccr.getName();
    	ccr.setClassificationId1("3a6d3c33-c398-4c5e-a94b-e142d7bd70df");
    	ccr.setClassificationId2("3b286dbb-a519-4005-93e0-572985f77201");
    	ccr.setName("Merged8");
    	
    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
    @Test
    public void test2ShouldBeOk() {
    	int rndNum = (int)(990*(Math.random()));
    	String var = "throwAway" + rndNum;
    	
    	ClassificationMergeRequest ccr = new ClassificationMergeRequest("39c29836-cf91-46e4-b083-c651f7c02ecb", "681eba51-e415-4995-8445-4e6ea5c81bb5", "Merged9","User5", "UserID5");
        
    	ccr.getClassificationId1();
    	ccr.getClassificationId2();
    	ccr.getName();
    	ccr.setClassificationId1("91c4ce24-d71f-47ae-8d0a-090c90b980a1");
    	ccr.setClassificationId2("94bc160a-8764-4f62-a8fa-4946b8da0857");
    	ccr.setName("Merged9");
    	
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
