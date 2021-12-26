package cs509.thalassa.demo.http;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeleteProblemInstanceRequest {
	public String problemInstanceId;
	public String userName;
	public String userID;
	public String  time;
	
	public DeleteProblemInstanceRequest() {
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public DeleteProblemInstanceRequest(String problemInstanceId, String userName, String userID) {
		this.problemInstanceId = problemInstanceId;
		this.userName= userName;
		this.userID = userID;
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public String getProblemInstanceId() {
		return this.problemInstanceId;
	}
	
	/**
	public void setProblemInstanceId(String problemInstanceId) {
		this.problemInstanceId = problemInstanceId;
	}
	**/
}
