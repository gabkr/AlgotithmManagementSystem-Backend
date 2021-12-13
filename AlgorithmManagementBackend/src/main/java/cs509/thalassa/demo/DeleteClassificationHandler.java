package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.ClassificationsDAO;
import cs509.thalassa.demo.http.DeleteAlgorithmRequest;
import cs509.thalassa.demo.http.DeleteAlgorithmsResponse;
import cs509.thalassa.demo.http.DeleteClassificationRequest;
import cs509.thalassa.demo.http.DeleteClassificationResponse;

public class DeleteClassificationHandler implements RequestHandler<DeleteClassificationRequest, DeleteClassificationResponse>{
	public LambdaLogger logger;
	@Override
	public DeleteClassificationResponse handleRequest(DeleteClassificationRequest req, Context context) {
		// TODO Auto-generated method stub
		logger = context.getLogger();
		logger.log("Initiating Delete Classification Handler");
		DeleteClassificationResponse response;
		try {
			Boolean isDeleted = deleteClassification(req);
			response = new DeleteClassificationResponse(req.getClassificationId());
		}
		catch (Exception e) {
			response = new DeleteClassificationResponse(e.getMessage(), 403);
	}
		return null;
	}
	
	public Boolean deleteClassification(DeleteClassificationRequest req) throws Exception{
		ClassificationsDAO dao = new ClassificationsDAO(logger);
		return dao.deleteClassification(req.classificationId);
	}
	
}
