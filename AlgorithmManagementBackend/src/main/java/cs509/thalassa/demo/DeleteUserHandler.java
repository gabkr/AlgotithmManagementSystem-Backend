package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.http.DeleteUserRequest;
import cs509.thalassa.demo.http.DeleteUserResponse;

public class DeleteUserHandler implements RequestHandler<DeleteUserRequest, DeleteUserResponse> {

	CognitoHelper helper = new CognitoHelper();
	@Override
	public DeleteUserResponse handleRequest(DeleteUserRequest req, Context context) {
		// TODO Auto-generated method stub
		helper.deleteUser(req.getUsername());
		return null;
	}

}
