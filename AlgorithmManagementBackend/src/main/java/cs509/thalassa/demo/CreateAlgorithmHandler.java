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
	
	// To access S3 storage
	//private AmazonS3 s3 = null;
		
	// Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	//public static final String REAL_BUCKET = "constants/";
	
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean createAlgorithm(String nameAlgorithm, String idAlgorithm, String parentId) throws Exception { 
		if (logger != null) { logger.log("in createAlgorithm"); }
		AlgorithmsDAO dao = new AlgorithmsDAO(logger);
		
		// check if present
		Algorithm exist = dao.getAlgorithm(nameAlgorithm);
		Algorithm algorithm = new Algorithm (nameAlgorithm, idAlgorithm, parentId);
		if (exist == null) {
			return dao.addAlgorithm(algorithm);
		} else {
			return false;
		}
	}
	
	/** Create S3 bucket
	 * 
	 * @throws Exception 
	 */
	/**
	boolean createSystemConstant(String name, double value) throws Exception {
		if (logger != null) { logger.log("in createSystemConstant"); }
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}

		String bucket = REAL_BUCKET;
		
		byte[] contents = ("" + value).getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		
		// makes the object publicly visible
		PutObjectResult res = s3.putObject(new PutObjectRequest("calctest", bucket + name, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		// if we ever get here, then whole thing was stored
		return true;
	}
	**/
	@Override 
	public CreateAlgorithmResponse handleRequest(CreateAlgorithmRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		CreateAlgorithmResponse response;
		try {
			/**
			if (req.system) {
				if (createSystemConstant(req.name, req.value)) {
					response = new CreateClassificationResponse(req.name);
				} else {
					response = new CreateClassificationResponse(req.name, 422);
				}
			} else {
			**/
			if (createAlgorithm(req.nameAlgorithm, req.idAlgorithm, req.parentId)) {
				response = new CreateAlgorithmResponse(req.nameAlgorithm);
			} else {
				response = new CreateAlgorithmResponse(req.nameAlgorithm, 422);
			}
		} catch (Exception e) {
			response = new CreateAlgorithmResponse("Unable to create algorithm: " + req.nameAlgorithm + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
}
