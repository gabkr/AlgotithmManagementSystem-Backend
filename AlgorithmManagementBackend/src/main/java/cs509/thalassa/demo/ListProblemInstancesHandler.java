package cs509.thalassa.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.*;

import cs509.thalassa.demo.db.ProblemInstanceDAO;
import cs509.thalassa.demo.http.ListProblemInstancesRequest;
import cs509.thalassa.demo.http.ListProblemInstancesResponse;
import cs509.thalassa.demo.model.ProblemInstance;

public class ListProblemInstancesHandler implements RequestHandler<ListProblemInstancesRequest, ListProblemInstancesResponse> {
	public LambdaLogger logger;
	
	List<ProblemInstance> getProblemInstances(String algorithmId) throws Exception {
		logger.log("Get Implementations");
		ProblemInstanceDAO dao = new ProblemInstanceDAO(logger);
		
		return dao.getProblemInstances(algorithmId);
	}
	
	@Override
	public ListProblemInstancesResponse handleRequest(ListProblemInstancesRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Lambda to get list of problem instances of an algorithm");
		logger.log("algorithmId: " + req.algorithmId);
		
		ListProblemInstancesResponse res;
		try {
			List<ProblemInstance> list = getProblemInstances(req.algorithmId);
			
			res = new ListProblemInstancesResponse(list, 200);
		} catch (Exception e) {
			res = new ListProblemInstancesResponse(403, e.getMessage());
		}
		return res;
	}
}
