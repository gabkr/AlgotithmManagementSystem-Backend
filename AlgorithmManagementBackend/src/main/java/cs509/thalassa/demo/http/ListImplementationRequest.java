package cs509.thalassa.demo.http;

public class ListImplementationRequest {
	public String parentAlgorithm;
	
	public String getParentAlgorithm() { return parentAlgorithm; }
	public void setParentAlgorithm(String c) { this.parentAlgorithm = c; }
	
	public ListImplementationRequest() {
	}
	
	public ListImplementationRequest (String parentId) {
		this.parentAlgorithm = parentId;
	}
	
	public String toString() {
		return "ListImplementation(" + parentAlgorithm + ")";
	}
}
