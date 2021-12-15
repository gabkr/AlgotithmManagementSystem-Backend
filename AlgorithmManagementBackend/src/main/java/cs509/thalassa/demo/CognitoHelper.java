package cs509.thalassa.demo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserResult;
import com.amazonaws.services.cognitoidp.model.ListUsersRequest;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;

public class CognitoHelper {
	public AWSCognitoIdentityProvider cognitoClient;
	String REGION="us-east-2";
	String userPoolId = "us-east-2_8Cd5yjV0Q";
	
	CognitoHelper() {}
	
	public AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
	      ClasspathPropertiesFileCredentialsProvider propertiesFileCredentialsProvider = 
	           new ClasspathPropertiesFileCredentialsProvider();
	      AWSCredentials cognitoCreds = propertiesFileCredentialsProvider.getCredentials();
	      AWSCredentialsProvider creds = new AWSStaticCredentialsProvider(cognitoCreds);
	      
	       return AWSCognitoIdentityProviderClientBuilder.standard()
	                      .withCredentials(creds)
	                      .withRegion(REGION)
	                      .build();
	 
	   }
	
	public AdminDeleteUserResult deleteUser(String username) {
		AdminDeleteUserRequest request = new AdminDeleteUserRequest();
		request.withUsername(username)
				.withUserPoolId(userPoolId);

		AWSCognitoIdentityProvider client = getAmazonCognitoIdentityClient();
		return client.adminDeleteUser(request);
	}
	
	public ListUsersResult listUsers(String userPoolId, Integer limit) {
		ListUsersRequest listUsersRequest = new ListUsersRequest();
		listUsersRequest.withUserPoolId(userPoolId);
		listUsersRequest.setLimit(limit);
		AWSCognitoIdentityProvider client = getAmazonCognitoIdentityClient();
		return client.listUsers(listUsersRequest);
	}
}
