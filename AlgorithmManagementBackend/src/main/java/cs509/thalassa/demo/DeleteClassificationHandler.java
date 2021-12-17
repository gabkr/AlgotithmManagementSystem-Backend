package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.ClassificationsDAO;
import cs509.thalassa.demo.db.ProblemInstanceDAO;
import cs509.thalassa.demo.db.UserHistoryDAO;
import cs509.thalassa.demo.http.DeleteAlgorithmRequest;
import cs509.thalassa.demo.http.DeleteAlgorithmsResponse;
import cs509.thalassa.demo.http.DeleteClassificationRequest;
import cs509.thalassa.demo.http.DeleteClassificationResponse;
import cs509.thalassa.demo.model.Classification;
import cs509.thalassa.demo.model.UserHistory;

public class DeleteClassificationHandler implements RequestHandler<DeleteClassificationRequest, DeleteClassificationResponse>{
	public LambdaLogger logger;
	@Override
	public DeleteClassificationResponse handleRequest(DeleteClassificationRequest req, Context context) {
		// TODO Auto-generated method stub
		logger = context.getLogger();
		logger.log("Initiating Delete Classification Handler");
		DeleteClassificationResponse response;
		
		ClassificationsDAO dao2 = new ClassificationsDAO(logger);
		Classification ps;
		
		try {
			ps = dao2.getClassification(req.classificationId);
			Boolean isDeleted = deleteClassification(req);
			logger.log("outside delete: " + isDeleted);
			response = new DeleteClassificationResponse(req.getClassificationId());
			createUserHistory(ps.nameClassification, req.userName, req.userID, req.time);
		}
		catch (Exception e) {
			response = new DeleteClassificationResponse(e.getMessage(), 403);
	}
		return response;
	}
	
	public Boolean deleteClassification(DeleteClassificationRequest req) throws Exception{
		ClassificationsDAO dao = new ClassificationsDAO(logger);
		logger.log("deleting classification calling DAO");
		return dao.deleteClassification(req.classificationId);
	}
	
	void createUserHistory(String name, String userName, String userID, String time) throws Exception { 
		if (logger != null) { logger.log("in deleteClassification"); }
		
		UserHistoryDAO dao1 = new UserHistoryDAO(logger);
		// check if present
		UserHistory userHistory = new UserHistory(userID, userName, name, time);
		dao1.addUserHistory(userHistory,"Delete");}
	
}
