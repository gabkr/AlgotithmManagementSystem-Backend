package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.http.DeleteUserRequest;
import cs509.thalassa.demo.http.DeleteUserResponse;

public class DeleteUserHandler implements RequestHandler<DeleteUserRequest, DeleteUserResponse> {
	public LambdaLogger logger;
	@Override
	public DeleteUserResponse handleRequest(DeleteUserRequest req, Context context) {
		// TODO Auto-generated method stub
		logger.log("Inside Delete users");
		CognitoHelper helper = new CognitoHelper(logger);
		logger.log("helper objct val:" + helper.toString());
		logger.log("deleting user: " + req.getUsername());
		DeleteUserResponse response;
		try {
			helper.deleteUser(req.getUsername());
			response = new DeleteUserResponse(req.getUsername());
		}
		catch(Exception e) {
			response = new DeleteUserResponse(e.getMessage(), 403);
		}
		return response;
	}

}
