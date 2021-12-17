package cs509.thalassa.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs509.thalassa.demo.db.ImplementationDAO;
import cs509.thalassa.demo.http.DeleteImplementationRequest;
import cs509.thalassa.demo.http.DeleteImplementationResponse;

public class DeleteImplementationHandler implements RequestHandler<DeleteImplementationRequest, DeleteImplementationResponse>{
	LambdaLogger logger;
	@Override
	public DeleteImplementationResponse handleRequest(DeleteImplementationRequest req, Context context) {
		// TODO Auto-generated method stub
				logger = context.getLogger();
				logger.log("Initiating DeleteImplementation Handler");
				logger.log("Deleting implementation: " + req.getImplementationId());
				DeleteImplementationResponse response = null;
				try {
					Boolean isDeleted = deleteImplementation(req);
					response = new DeleteImplementationResponse(req.getImplementationId());
				}
				catch (Exception e) {
					response = new DeleteImplementationResponse(e.getMessage(), 403);
			}
				return response;
	}
	
	public Boolean deleteImplementation(DeleteImplementationRequest req) throws Exception{
		ImplementationDAO dao = new ImplementationDAO(logger);
		return dao.deleteImplementation(req.getImplementationId());
	}

}
