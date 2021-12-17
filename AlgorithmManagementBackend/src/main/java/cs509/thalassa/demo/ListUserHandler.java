package cs509.thalassa.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.AlgorithmsDAO;
import cs509.thalassa.demo.db.UsersDAO;
import cs509.thalassa.demo.http.AllAlgorithmsResponse;
import cs509.thalassa.demo.http.ListAlgorithmRequest;
import cs509.thalassa.demo.http.ListUserRequest;
import cs509.thalassa.demo.http.ListUserResponse;
import cs509.thalassa.demo.model.Algorithm;
import cs509.thalassa.demo.model.User;

public class ListUserHandler implements RequestHandler<ListUserRequest, ListUserResponse>{
	LambdaLogger logger;
	
	List<User> listUsers(int limit) throws Exception {
		logger.log("in list Users");
		UsersDAO dao = new UsersDAO(logger);
		
		return dao.listUsers(limit);
	}
	
	@Override
	public ListUserResponse handleRequest(ListUserRequest req, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all algorithms");
		logger.log("classificationId: " + req.getLimit());
		
		ListUserResponse response;
		try {
			List<User> list = listUsers(req.getLimit());
			response = new ListUserResponse(list, 200);
		} catch (Exception e) {
			response = new ListUserResponse(403, e.getMessage());
		}
		
		return response;
	}
	
}
