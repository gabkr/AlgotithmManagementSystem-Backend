package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.AlgorithmsDAO;
import cs509.thalassa.demo.db.UserHistoryDAO;
import cs509.thalassa.demo.http.AlgorithmReclassifyRequest;
import cs509.thalassa.demo.http.AlgorithmReclassifyResponse;
import cs509.thalassa.demo.model.Algorithm;
import cs509.thalassa.demo.model.UserHistory;

public class ReclassifyAlgorithm implements RequestHandler<AlgorithmReclassifyRequest, AlgorithmReclassifyResponse> {
	public LambdaLogger logger;
	@Override
	public AlgorithmReclassifyResponse handleRequest(AlgorithmReclassifyRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Initiating DeleteAlgorithm Handler");
		logger.log("Deleting algo: " + req.getAlgorithmId());
		AlgorithmReclassifyResponse response = null;
		
		AlgorithmsDAO dao2 = new AlgorithmsDAO(logger);
		Algorithm ps;
		
		try {
			ps = dao2.getAlgorithmById(req.algorithmId);
			if(reclassifyAlgorithm(req)) {
				logger.log("Reclassified algorithm successfully");
			}
			response = new AlgorithmReclassifyResponse(req.getAlgorithmId());
			createUserHistory(ps.name, req.userName, req.userID, req.time);
		}
		catch (Exception e) {
			response = new AlgorithmReclassifyResponse(e.getMessage(), 403);
	}
		return response;
	}
	
	public Boolean reclassifyAlgorithm(AlgorithmReclassifyRequest req) throws Exception{
		AlgorithmsDAO dao = new AlgorithmsDAO(logger);
		return dao.reclassifyAlgorithm(req.getAlgorithmId(), req.getClassificationId());
	}
	
	void createUserHistory(String nameAlgorithm, String userName, String userID, String time) throws Exception { 
		if (logger != null) { logger.log("in reclassify algorithm"); }
		
		UserHistoryDAO dao1 = new UserHistoryDAO(logger);
		// check if present
		UserHistory userHistory = new UserHistory(userID, userName, nameAlgorithm, time);
		dao1.addUserHistory(userHistory, "Reclassify");}
}
