package cs509.thalassa.demo.http;

public class ListAlgorithmRequest {
	public String parentAlgorithm;
	
	public String getParentAlgorithm() { return parentAlgorithm; }
	public void setParentAlgorithm(String c) { this.parentAlgorithm = c; }
	
	public ListAlgorithmRequest() {
	}
	
	public ListAlgorithmRequest (String parentId) {
		this.parentAlgorithm = parentId;
	}
	
	public String toString() {
		return "ListClassification(" + parentClassification + ")";
	}
}
