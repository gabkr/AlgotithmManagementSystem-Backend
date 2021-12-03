package cs509.thalassa.demo.http;

import java.util.UUID;

public class CreateProblemInstanceRequest {
	public String problemInstanceId;
	public String algorithmId;
	public String input;
	
	public CreateProblemInstanceRequest() {
		this.problemInstanceId = UUID.randomUUID().toString();
	}
	
	public CreateProblemInstanceRequest(String algorithmId, String input) {
		this.algorithmId = algorithmId;
		this.input = input;
		this.problemInstanceId = UUID.randomUUID().toString();
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

}
