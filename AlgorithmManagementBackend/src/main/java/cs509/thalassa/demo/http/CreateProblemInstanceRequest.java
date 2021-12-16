package cs509.thalassa.demo.http;

import java.util.UUID;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class CreateProblemInstanceRequest {
	public String problemInstanceId;
	public String algorithmId;
	public String input;
	public String name;
	public String userName;
	public String userID;
	public String  time;
	
	public CreateProblemInstanceRequest() {
		this.problemInstanceId = UUID.randomUUID().toString();
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public CreateProblemInstanceRequest(String algorithmId, String input, String name, String userName, String userID) {
		this.algorithmId = algorithmId;
		this.input = input;
		this.problemInstanceId = UUID.randomUUID().toString();
		this.name = name;
		this.userName= userName;
		this.userID = userID;
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public String getAlgorithmId() {
		return this.algorithmId;
	}
	
	public String getProblemInstanceId() {
		return this.problemInstanceId;
	}
	
	public String getInput() {
		return this.input;
	}

	public void setAlgorithmId(String algorithmId) {
		this.algorithmId = algorithmId;
	}
	
	public void setProblemInstanceId(String problemInstanceId) {
		this.problemInstanceId = problemInstanceId;
	}
	
	public void setInput(String input) {
		this.input = input;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
