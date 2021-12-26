package cs509.thalassa.demo.http;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AlgorithmReclassifyRequest {
	public String algorithmId;
	public String classificationId;
	public String userName;
	public String userID;
	public String  time;
	
	public String getAlgorithmId() { return algorithmId; }
	public String getClassificationId() { return classificationId; }
	
	public void setAlgorithmId(String c) { this.algorithmId = c; }
	public void setClassificationId(String c) { this.classificationId = c; }
	
	public AlgorithmReclassifyRequest() {
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public AlgorithmReclassifyRequest (String algoId, String parentClassId, String userName, String userID) {
		this.algorithmId = algoId;
		this.classificationId = parentClassId;
		this.userName= userName;
		this.userID = userID;
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	/**
	public String toString() {
		return "Algorithm reclassified (" + algorithmId + " " + classificationId + ")";
	}
	**/
}
