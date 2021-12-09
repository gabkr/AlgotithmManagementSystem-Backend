package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.ProblemInstanceDAO;
import cs509.thalassa.demo.http.CreateProblemInstanceRequest;
import cs509.thalassa.demo.http.CreateProblemInstanceResponse;
import cs509.thalassa.demo.model.ProblemInstance;

public class CreateProblemInstanceHandler implements RequestHandler<CreateProblemInstanceRequest, CreateProblemInstanceResponse> {
	LambdaLogger logger;
	
	boolean createProblemInstance(String problemInstanceId, String algorithmId, String input) throws Exception {
		if (logger != null) {
			logger.log("Create Problem Instance Handler");
		}
		
		ProblemInstanceDAO dao = new ProblemInstanceDAO(logger);
		ProblemInstance problemInstance = new ProblemInstance(problemInstanceId, algorithmId, input);
		return dao.addProblemInstance(problemInstance);
	}
	
	@Override
	public CreateProblemInstanceResponse handleRequest(CreateProblemInstanceRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		logger.log("ID" + req.problemInstanceId);
		logger.log("AlgorithmId" + req.algorithmId);
		logger.log("input" + req.input);

		CreateProblemInstanceResponse response;
		
		try	{
			if (createProblemInstance(req.problemInstanceId, req.algorithmId, req.input)) {
				response = new CreateProblemInstanceResponse(req.problemInstanceId, "Created successfully");
			} else {
				response = new CreateProblemInstanceResponse("Unable to create algorithm", 422);
			}
		} catch (Exception e) {
			response = new CreateProblemInstanceResponse("Unable to create algorithm", 400);		
		}
		
		return response;
	}
}
