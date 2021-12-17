package cs509.thalassa.demo.http;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeleteAlgorithmRequest {
	public String algorithmId;
	public String userName;
	public String userID;
	public String  time;
	
	public String getAlgorithmId() { return algorithmId; }
	public void setAlgorithmId(String c) { this.algorithmId = c; }
	
	public DeleteAlgorithmRequest() {
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public DeleteAlgorithmRequest (String algorithmId, String userName, String userID) {
		this.algorithmId = algorithmId;
		this.userName= userName;
		this.userID = userID;
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	/**
	public String toString() {
		return "DeleteAlgorithm(" + algorithmId + ")";
	}
	**/
}
