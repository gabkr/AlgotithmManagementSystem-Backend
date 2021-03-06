package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.ProblemInstanceDAO;
import cs509.thalassa.demo.db.UserHistoryDAO;
import cs509.thalassa.demo.http.CreateProblemInstanceRequest;
import cs509.thalassa.demo.http.CreateProblemInstanceResponse;
import cs509.thalassa.demo.model.ProblemInstance;
import cs509.thalassa.demo.model.UserHistory;

public class CreateProblemInstanceHandler implements RequestHandler<CreateProblemInstanceRequest, CreateProblemInstanceResponse> {
	LambdaLogger logger;
	
	boolean createProblemInstance(String problemInstanceId, String algorithmId, String input, String name) throws Exception {
		if (logger != null) {
			logger.log("Create Problem Instance Handler");
		}
		
		ProblemInstanceDAO dao = new ProblemInstanceDAO(logger);
		ProblemInstance problemInstance = new ProblemInstance(problemInstanceId, algorithmId, input, name);
		return dao.addProblemInstance(problemInstance);
	}
	
	void createUserHistory(String name, String userName, String userID, String time) throws Exception { 
		if (logger != null) { logger.log("in createProblemInstance"); }
		
		UserHistoryDAO dao1 = new UserHistoryDAO(logger);
		// check if present
		UserHistory userHistory = new UserHistory(userID, userName, name, time);
		dao1.addUserHistory(userHistory, "Create");}
	
	@Override
	public CreateProblemInstanceResponse handleRequest(CreateProblemInstanceRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		logger.log("ID" + req.problemInstanceId);
		logger.log("AlgorithmId" + req.algorithmId);
		logger.log("name" + req.name);
		logger.log("input" + req.input);

		CreateProblemInstanceResponse response;
		
		try	{
			if (createProblemInstance(req.problemInstanceId, req.algorithmId, req.input, req.name)) {
				response = new CreateProblemInstanceResponse(req.problemInstanceId, "Created successfully");
				createUserHistory(req.name, req.userName, req.userID, req.time);
			} else {
				response = new CreateProblemInstanceResponse("Unable to create algorithm", 422);
			}
		} catch (Exception e) {
			response = new CreateProblemInstanceResponse("Unable to create algorithm", 400);		
		}
		
		return response;
	}
}
