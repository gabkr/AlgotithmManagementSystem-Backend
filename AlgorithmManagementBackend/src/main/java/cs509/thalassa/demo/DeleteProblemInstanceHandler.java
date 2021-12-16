package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.ProblemInstanceDAO;
import cs509.thalassa.demo.db.UserHistoryDAO;
import cs509.thalassa.demo.http.DeleteProblemInstanceRequest;
import cs509.thalassa.demo.http.DeleteProblemInstanceResponse;
import cs509.thalassa.demo.model.UserHistory;

public class DeleteProblemInstanceHandler implements RequestHandler<DeleteProblemInstanceRequest, DeleteProblemInstanceResponse>{
	public LambdaLogger logger;
	
	boolean deleteProblemInstance(String problemInstanceId) throws Exception {
		if (logger != null) {
			logger.log("Delete problem instance");
		}
		
		ProblemInstanceDAO dao = new ProblemInstanceDAO(logger);
		
		return dao.deleteProblemInstance(problemInstanceId);
	}
	
	void createUserHistory(String name, String userName, String userID, String time) throws Exception { 
		if (logger != null) { logger.log("in deleteProblemInstance"); }
		
		UserHistoryDAO dao1 = new UserHistoryDAO(logger);
		// check if present
		UserHistory userHistory = new UserHistory(userID, userName, name, time);
		dao1.addUserHistory(userHistory,"Delete");}
	
	@Override
	public DeleteProblemInstanceResponse handleRequest(DeleteProblemInstanceRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		DeleteProblemInstanceResponse response;
		
		try {
			if (deleteProblemInstance(req.problemInstanceId)) {
				response = new DeleteProblemInstanceResponse("Problem Instance deleted successfully");
				createUserHistory(req.name, req.userName, req.userID, req.time);
			} else {
				response = new DeleteProblemInstanceResponse("Unable to delete problem instance", 422);
			}
		} catch (Exception e) {
			response = new DeleteProblemInstanceResponse("Unable to delete problem instance" + e.getMessage(), 400);
		}

		return response;
	}
}
