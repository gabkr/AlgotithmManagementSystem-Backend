package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.AlgorithmsDAO;
import cs509.thalassa.demo.http.AllAlgorithmsResponse;
import cs509.thalassa.demo.http.DeleteAlgorithmRequest;
import cs509.thalassa.demo.http.DeleteAlgorithmsResponse;

public class DeleteAlgorithmHandler implements RequestHandler<DeleteAlgorithmRequest, DeleteAlgorithmsResponse> {
	public LambdaLogger logger;
	@Override
	public DeleteAlgorithmsResponse handleRequest(DeleteAlgorithmRequest req, Context context) {
		// TODO Auto-generated method stub
		logger = context.getLogger();
		logger.log("Initiating DeleteAlgorithm Handler");
		logger.log("Deleting algo: " + req.getAlgorithmId());
		DeleteAlgorithmsResponse response = null;
		try {
			Boolean isDeleted = deleteAlgorithm(req);
			response = new DeleteAlgorithmsResponse(req.getAlgorithmId());
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
	
}
