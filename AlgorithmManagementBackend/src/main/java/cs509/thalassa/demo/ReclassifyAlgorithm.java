package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.AlgorithmsDAO;
import cs509.thalassa.demo.http.AlgorithmReclassifyRequest;
import cs509.thalassa.demo.http.AlgorithmReclassifyResponse;

public class ReclassifyAlgorithm implements RequestHandler<AlgorithmReclassifyRequest, AlgorithmReclassifyResponse> {
	public LambdaLogger logger;
	@Override
	public AlgorithmReclassifyResponse handleRequest(AlgorithmReclassifyRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Initiating DeleteAlgorithm Handler");
		logger.log("Deleting algo: " + req.getAlgorithmId());
		AlgorithmReclassifyResponse response = null;
		try {
			if(reclassifyAlgorithm(req)) {
				logger.log("Reclassified algorithm successfully");
			}
			response = new AlgorithmReclassifyResponse(req.getAlgorithmId());
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
	
}
