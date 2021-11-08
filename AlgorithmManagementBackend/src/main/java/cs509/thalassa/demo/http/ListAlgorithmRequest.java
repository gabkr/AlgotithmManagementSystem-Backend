package cs509.thalassa.demo.http;

public class ListAlgorithmRequest {
	public String classificationId;
	
	public String getClassificationId() { return classificationId; }
	public void setClassificationId(String c) { this.classificationId = c; }
	
	public ListAlgorithmRequest() {
	}
	
	public ListAlgorithmRequest (String classificationId) {
		this.classificationId = classificationId;
	}
	
	public String toString() {
		return "ListClassification(" + classificationId + ")";
	}
}
