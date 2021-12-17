package cs509.thalassa.demo;

import java.io.ByteArrayInputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import cs509.thalassa.demo.db.AlgorithmsDAO;
import cs509.thalassa.demo.http.CreateAlgorithmRequest;
import cs509.thalassa.demo.http.CreateAlgorithmResponse;
import cs509.thalassa.demo.model.Algorithm;

/**
 * Create a new constant and store in S3 bucket.
 */
public class CreateAlgorithmHandler implements RequestHandler<CreateAlgorithmRequest,CreateAlgorithmResponse> {

	LambdaLogger logger;
	
	
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean createAlgorithm(String nameAlgorithm, String idAlgorithm, String parentId) throws Exception { 
		if (logger != null) { logger.log("in createAlgorithm"); }
		AlgorithmsDAO dao = new AlgorithmsDAO(logger);
		
		// check if present
		Algorithm exist = dao.getAlgorithm(nameAlgorithm);
		Algorithm algorithm = new Algorithm(idAlgorithm, nameAlgorithm, parentId);
		if (exist == null) {
			return dao.addAlgorithm(algorithm);
		} else {
			return false;
		}
	}
	
	@Override 
	public CreateAlgorithmResponse handleRequest(CreateAlgorithmRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());
		logger.log("ID" + req.idAlgorithm);
		logger.log("Name" + req.nameAlgorithm);
		logger.log("Parent" + req.parentId);

		CreateAlgorithmResponse response;
		try {

			if (createAlgorithm(req.nameAlgorithm, req.idAlgorithm, req.parentId)) {
				response = new CreateAlgorithmResponse(req.idAlgorithm);
			} else {
				response = new CreateAlgorithmResponse(req.nameAlgorithm, 422);
			}
		} catch (Exception e) {
			response = new CreateAlgorithmResponse("Unable to create algorithm: " + req.nameAlgorithm + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
}
