package cs509.thalassa.demo.http;

public class DeleteProblemInstanceRequest {
	public String problemInstanceId;
	
	public DeleteProblemInstanceRequest() {
		
	}
	
	public DeleteProblemInstanceRequest(String problemInstanceId) {
		this.problemInstanceId = problemInstanceId;
	}
	
	public String getProblemInstanceId() {
		return this.problemInstanceId;
	}
	
	public void setProblemInstanceId(String problemInstanceId) {
		this.problemInstanceId = problemInstanceId;
	}
}
