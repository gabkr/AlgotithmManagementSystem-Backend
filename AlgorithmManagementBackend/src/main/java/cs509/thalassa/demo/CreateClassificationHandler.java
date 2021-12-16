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

import cs509.thalassa.demo.db.ClassificationsDAO;
import cs509.thalassa.demo.db.UserHistoryDAO;
import cs509.thalassa.demo.http.CreateClassificationRequest;
import cs509.thalassa.demo.http.CreateClassificationResponse;
import cs509.thalassa.demo.model.Classification;
import cs509.thalassa.demo.model.UserHistory;

/**
 * Create a new constant and store in S3 bucket.
 */
public class CreateClassificationHandler implements RequestHandler<CreateClassificationRequest,CreateClassificationResponse> {

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
	boolean createClassification(String nameClassification, String id, String parentClassification) throws Exception { 
		if (logger != null) { logger.log("in createClassification"); }
		ClassificationsDAO dao = new ClassificationsDAO(logger);
		
		// check if present
		Classification exist = dao.getClassification(nameClassification);
		Classification classification = new Classification(nameClassification, id, parentClassification);
		if (exist == null) {
			return dao.addClassification(classification);
		} else {
			return false;
		}
	}
	
	void createUserHistory(String nameClassification, String userName, String userID, String time) throws Exception { 
		if (logger != null) { logger.log("in createClassification"); }
		
		UserHistoryDAO dao1 = new UserHistoryDAO(logger);
		// check if present
		UserHistory userHistory = new UserHistory(userID, userName, nameClassification, time);
		dao1.addUserHistory(userHistory);}
	
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
	public CreateClassificationResponse handleRequest(CreateClassificationRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		CreateClassificationResponse response;
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
			if (createClassification(req.nameClassification, req.id, req.parentClassification)) {
				logger.log("createclassification..");
				response = new CreateClassificationResponse(req.id);
				createUserHistory(req.nameClassification, req.userName, req.userID, req.time);
			} else {
				response = new CreateClassificationResponse(req.nameClassification + " already exists", 422);
			}
		} catch (Exception e) {
			response = new CreateClassificationResponse("Unable to create classification: " + req.nameClassification + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;
	}
}
