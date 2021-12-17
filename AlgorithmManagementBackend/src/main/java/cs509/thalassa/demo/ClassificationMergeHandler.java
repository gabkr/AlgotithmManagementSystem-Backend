package cs509.thalassa.demo;

import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.ClassificationsDAO;
import cs509.thalassa.demo.http.ClassificationMergeRequest;
import cs509.thalassa.demo.http.ClassificationMergeResponse;
import cs509.thalassa.demo.http.CreateClassificationResponse;
import cs509.thalassa.demo.model.Classification;
	
public class ClassificationMergeHandler implements RequestHandler<ClassificationMergeRequest, ClassificationMergeResponse>{
	
	LambdaLogger logger;
	@Override
	public ClassificationMergeResponse handleRequest(ClassificationMergeRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Inside merger classification");
		ClassificationMergeResponse response;
		try {
			Boolean status = mergeClassification(req.getName(), req.getClassificationId1(), req.getClassificationId2());
			if(status) {
				logger.log("Merge successful-----");
			}
			else {
				logger.log("Merge failed");
			}
			response = new ClassificationMergeResponse(req.getName(),200);
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
}
