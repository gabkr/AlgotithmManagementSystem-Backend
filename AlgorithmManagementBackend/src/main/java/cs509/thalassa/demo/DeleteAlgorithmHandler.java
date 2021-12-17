package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.AlgorithmsDAO;
import cs509.thalassa.demo.db.ClassificationsDAO;
import cs509.thalassa.demo.db.UserHistoryDAO;
import cs509.thalassa.demo.http.AllAlgorithmsResponse;
import cs509.thalassa.demo.http.DeleteAlgorithmRequest;
import cs509.thalassa.demo.http.DeleteAlgorithmsResponse;
import cs509.thalassa.demo.model.Algorithm;
import cs509.thalassa.demo.model.Classification;
import cs509.thalassa.demo.model.UserHistory;

public class DeleteAlgorithmHandler implements RequestHandler<DeleteAlgorithmRequest, DeleteAlgorithmsResponse> {
	public LambdaLogger logger;
	@Override
	public DeleteAlgorithmsResponse handleRequest(DeleteAlgorithmRequest req, Context context) {
		// TODO Auto-generated method stub
		logger = context.getLogger();
		logger.log("Initiating DeleteAlgorithm Handler");
		logger.log("Deleting algo: " + req.getAlgorithmId());
		DeleteAlgorithmsResponse response = null;
		
		AlgorithmsDAO dao2 = new AlgorithmsDAO(logger);
		Algorithm ps;
		
		try {
			ps = dao2.getAlgorithmById(req.algorithmId);
			Boolean isDeleted = deleteAlgorithm(req);
			response = new DeleteAlgorithmsResponse(req.getAlgorithmId());
			createUserHistory(ps.name, req.userName, req.userID, req.time);
		}
		catch (Exception e) {
			response = new DeleteAlgorithmsResponse(e.getMessage(), 403);
	}
		return response;
	}
	
	public Boolean deleteAlgorithm(DeleteAlgorithmRequest req) throws Exception{
		AlgorithmsDAO dao = new AlgorithmsDAO(logger);
		return dao.deleteAlgorithm(req.getAlgorithmId());
	}
	
	void createUserHistory(String name, String userName, String userID, String time) throws Exception { 
		if (logger != null) { logger.log("in deleteAlgorithm"); }
		
		UserHistoryDAO dao1 = new UserHistoryDAO(logger);
		// check if present
		UserHistory userHistory = new UserHistory(userID, userName, name, time);
		dao1.addUserHistory(userHistory,"Delete");}
}
