package cs509.thalassa.demo.http;

public class ListProblemInstancesRequest {
	public String algorithmId;
	
	public ListProblemInstancesRequest() {
	}

	public ListProblemInstancesRequest(String algorithmId) {
		this.algorithmId = algorithmId;
	}

	public String getAlgorithmId() {
		return algorithmId;
	}
	
	public void setAlgorithmId(String algorithmId) {
		this.algorithmId = algorithmId;
	}
	
	public String toString() {
		return "ListProblemInstances(" + this.algorithmId + ")";
	}
}
