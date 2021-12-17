package cs509.thalassa.demo;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.AlgorithmsDAO;
import cs509.thalassa.demo.db.UsersDAO;
import cs509.thalassa.demo.http.CreateAlgorithmResponse;
import cs509.thalassa.demo.http.CreateUserRequest;
import cs509.thalassa.demo.http.CreateUserResponse;
import cs509.thalassa.demo.model.Algorithm;
import cs509.thalassa.demo.model.User;

public class CreateUserHandler implements RequestHandler<CreateUserRequest, CreateUserResponse> {
	
	LambdaLogger logger;
	@Override
	public CreateUserResponse handleRequest(CreateUserRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		logger.log("ID" + req.id);
		logger.log("Name" + req.name);
		logger.log("Parent" + req.email);

		CreateUserResponse response;
		try {

			if (createUser(req.id, req.name, req.email, req.username)) {
				response = new CreateUserResponse(req.id);
			} else {
				response = new CreateUserResponse(req.id, 422);
			}
		} catch (Exception e) {
			response = new CreateUserResponse("Unable to create user: " + req.name + "(" + e.getMessage() + ")", 400);
		}
		return response;
	}
	
	boolean createUser(String id, String name, String email, String username) throws Exception { 
		if (logger != null) { logger.log("in createAlgorithm"); }
		UsersDAO dao = new UsersDAO(logger);
		
		// check if present
		User exist = dao.getUser(id);
		if (exist == null) {
			User user = new User(id, name, email, username);
			return dao.addUser(user);
		} else {
			return false;
		}
	}
}
