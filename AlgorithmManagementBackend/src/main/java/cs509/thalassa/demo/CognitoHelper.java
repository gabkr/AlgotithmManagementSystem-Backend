package cs509.thalassa.demo;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserResult;
import com.amazonaws.services.cognitoidp.model.ListUsersRequest;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.amazonaws.services.cognitoidp.model.UserType;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class CognitoHelper {
	public AWSCognitoIdentityProvider cognitoClient;
	String REGION="us-east-2";
	String userPoolId = "us-east-2_8Cd5yjV0Q";
	String AWS_ACCESS_KEY = "AKIAUGHXJMCZLGWVQCMS";
	String AWS_SECRET_KEY = "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";
	LambdaLogger logger;
	CognitoHelper(LambdaLogger logger) {
		this.logger = logger;
	}
	
	public AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
	      AWSCredentials awsCreds = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
	      AWSCredentialsProvider creds = new AWSStaticCredentialsProvider(awsCreds);
	      
	       return AWSCognitoIdentityProviderClientBuilder.standard()
	                      .withCredentials(creds)
	                      .withRegion(REGION)
	                      .build();
	 	   }
	
	public AdminDeleteUserResult deleteUser(String username) {
		logger.log("Iside delete use cognito");
		AdminDeleteUserRequest request = new AdminDeleteUserRequest();
		request.withUsername(username)
				.withUserPoolId(userPoolId);

		AWSCognitoIdentityProvider client = this.getAmazonCognitoIdentityClient();
		return client.adminDeleteUser(request);
	}
	
	public ArrayList<String> listUsers(int limit) {
		logger.log("Inside list user list");
		AWSCognitoIdentityProvider client = this.getAmazonCognitoIdentityClient();
		if(client == null) {
			logger.log("cleint is null");
		}
		else {
			logger.log("client:" + client.toString() + "----------");
		}
		 try {
	            ArrayList<String> userList = new ArrayList<>();
	            // List all users
	            ListUsersRequest listUsersRequest = new ListUsersRequest();
	    		listUsersRequest.withUserPoolId(userPoolId);
	    		listUsersRequest.setLimit(limit);
	    		logger.log("ListUserRequest -------: " + listUsersRequest.getUserPoolId() + "-----------");
	    		ListUsersResult response = client.listUsers(listUsersRequest);
	    		logger.log("REsponse:-------- " + response.getUsers().size());
	            for(UserType user : response.getUsers()) {
	                userList.add(user.getUsername());
	            }
	            return userList;
	        } catch (Exception e){
	        	logger.log("List User failed" + e);
	        }
	        return null;
	    }	
	}
