package cs509.thalassa.demo.http;

public class DeleteAlgorithmRequest {
	public String algorithmId;
	
	public String getAlgorithmId() { return algorithmId; }
	public void setAlgorithmId(String c) { this.algorithmId = c; }
	
	public DeleteAlgorithmRequest() {
	}
	
	public DeleteAlgorithmRequest (String algorithmId) {
		this.algorithmId = algorithmId;
	}
	
	public String toString() {
		return "DeleteAlgorithm(" + algorithmId + ")";
	}
}
