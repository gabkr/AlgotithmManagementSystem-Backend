package cs509.thalassa.demo;

import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.AlgorithmsDAO;
import cs509.thalassa.demo.db.ClassificationsDAO;
import cs509.thalassa.demo.db.UserHistoryDAO;
import cs509.thalassa.demo.http.ClassificationMergeRequest;
import cs509.thalassa.demo.http.ClassificationMergeResponse;
import cs509.thalassa.demo.http.CreateClassificationResponse;
import cs509.thalassa.demo.model.Algorithm;
import cs509.thalassa.demo.model.Classification;
import cs509.thalassa.demo.model.UserHistory;
	
public class ClassificationMergeHandler implements RequestHandler<ClassificationMergeRequest, ClassificationMergeResponse>{
	
	LambdaLogger logger;
	@Override
	public ClassificationMergeResponse handleRequest(ClassificationMergeRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Inside merger classification");
		ClassificationMergeResponse response;
		
		ClassificationsDAO dao2 = new ClassificationsDAO(logger);
		Classification ps;
		Classification ps1;
		
		try {
			ps = dao2.getClassification(req.getClassificationId1());
			ps1 = dao2.getClassification(req.getClassificationId2());
			Boolean status = mergeClassification(req.getName(), req.getClassificationId1(), req.getClassificationId2());
			if(status) {
				logger.log("Merge successful-----");
			}
			else {
				logger.log("Merge failed");
			}
			response = new ClassificationMergeResponse(req.getName(),200);
			createUserHistory(ps.nameClassification, req.userName, req.userID, req.time);
		} catch (Exception e){
			response = new ClassificationMergeResponse("Unable to merge classification: " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}
		return response;
	}
	
	boolean mergeClassification(String classificationName, String classificationID1, String classificationID2) throws Exception {
		if (logger != null) { logger.log("in createClassification"); }
		ClassificationsDAO dao = new ClassificationsDAO(logger);
		String newClassificationID = UUID.randomUUID().toString();
		Boolean status = dao.mergeClassifications(classificationName, newClassificationID, classificationID1, classificationID2);
		return status;
	}
	
	void createUserHistory(String nameAlgorithm, String userName, String userID, String time) throws Exception { 
		if (logger != null) { logger.log("in reclassify algorithm"); }
		
		UserHistoryDAO dao1 = new UserHistoryDAO(logger);
		// check if present
		UserHistory userHistory = new UserHistory(userID, userName, nameAlgorithm, time);
		dao1.addUserHistory(userHistory, "Merge");}
}
