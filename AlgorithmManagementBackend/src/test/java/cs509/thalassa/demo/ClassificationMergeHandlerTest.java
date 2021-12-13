package cs509.thalassa.demo;

import java.io.IOException;

import org.junit.Assert;

import com.google.gson.Gson;

import cs509.thalassa.demo.http.AllClassificationsResponse;
import cs509.thalassa.demo.http.ListClassificationRequest;

public class ClassificationMergeHandlerTest extends LambdaTest{
	void testSuccessInput(String incoming) throws IOException {
    	ListAllClassificationsHandler handler = new ListAllClassificationsHandler();
    	ListClassificationRequest req = new Gson().fromJson(incoming, ListClassificationRequest.class);
       
        AllClassificationsResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);
    }
}	
