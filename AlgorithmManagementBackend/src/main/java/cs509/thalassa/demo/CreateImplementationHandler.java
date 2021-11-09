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

import cs509.thalassa.demo.db.ImplementationDAO;
import cs509.thalassa.demo.http.CreateImplementationRequest;
import cs509.thalassa.demo.http.CreateImplementationResponse;
import cs509.thalassa.demo.model.Implementation;
/**
 * Create a new constant and store in S3 bucket.
 */
public class CreateImplementationHandler implements RequestHandler<CreateImplementationRequest,CreateImplementationResponse> {

	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
		
	// Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	public static final String REAL_BUCKET = "implementations/";
	
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean createImplementation(String implementationFile, String idImplementation, String algorithmId, String value) throws Exception { 
		if (logger != null) { logger.log("in createImplementation"); }
		ImplementationDAO dao = new ImplementationDAO(logger);
		
		// check if present
		Implementation exist = dao.getImplementation(idImplementation);
		Implementation implementation = new Implementation (implementationFile, idImplementation, algorithmId, value);
		if (exist == null) {
			return dao.addImplementation(implementation);
		} else {
			return false;
		}
	}
	
	/** Create S3 bucket
	 * 
	 * @throws Exception 
	 */
	
	boolean createSystemImplementation(String name, String value) throws Exception {
		if (logger != null) { logger.log("in createSystemImplementation"); }
		
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
		PutObjectResult res = s3.putObject(new PutObjectRequest("cs509-thalassa-algorithm-management-system", bucket + name+".txt", bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		//System.out.print(res);
		
		// if we ever get here, then whole thing was stored
		return true;
		
	}

	@Override 
	public CreateImplementationResponse handleRequest(CreateImplementationRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		CreateImplementationResponse response;
		try {
				if (createSystemImplementation(req.idImplementation, req.value)) {
					response = new CreateImplementationResponse(req.idImplementation);
				} else {
					response = new CreateImplementationResponse(req.idImplementation, 422);
				}

				if (createImplementation(req.implementationFile, req.idImplementation, req.algorithmId, req.value)) {
					response = new CreateImplementationResponse(req.idImplementation);
				} else {
					response = new CreateImplementationResponse(req.idImplementation, 422);
				}
		} catch (Exception e) {
			response = new CreateImplementationResponse("Unable to create implementation: " + req.idImplementation + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
}
