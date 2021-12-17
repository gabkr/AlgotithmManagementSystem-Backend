package cs509.thalassa.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import cs509.thalassa.demo.db.AlgorithmsDAO;
import cs509.thalassa.demo.http.AllAlgorithmsResponse; 
import cs509.thalassa.demo.http.ListAlgorithmRequest;
import cs509.thalassa.demo.model.Algorithm;

/**
 * Eliminated need to work with JSON
 */
public class ListAllAlgorithmsHandler implements RequestHandler<ListAlgorithmRequest, AllAlgorithmsResponse> {

	public LambdaLogger logger;

	// Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	//public static final String REAL_BUCKET = "constants";

	//public static final String TOP_LEVEL_BUCKET = "calctest";
	
	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Algorithm> getAlgorithms(String parentId) throws Exception {
		logger.log("in getImplementations");
		AlgorithmsDAO dao = new AlgorithmsDAO(logger);
		
		return dao.getRelatedAlgorithms(parentId);
	}
	
	@Override
	public AllAlgorithmsResponse handleRequest(ListAlgorithmRequest req, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all algorithms");
		logger.log("classificationId: " + req.classificationId);
		
		AllAlgorithmsResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<Algorithm> list = getAlgorithms(req.classificationId);
			/**
			for (Constant c : systemConstants()) {
				if (!list.contains(c)) {
					list.add(c);
				}
			}
			**/
			response = new AllAlgorithmsResponse(list, 200);
		} catch (Exception e) {
			response = new AllAlgorithmsResponse(403, e.getMessage());
		}
		
		return response;
	}
}
