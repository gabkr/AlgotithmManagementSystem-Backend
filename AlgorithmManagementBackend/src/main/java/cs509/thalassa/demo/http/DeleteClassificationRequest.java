package cs509.thalassa.demo.http;

public class DeleteClassificationRequest {
	public String classificationId;
	
	public String getClassificationId() { return classificationId; }
	public void setClassificationId(String c) { this.classificationId = c; }
	
	public DeleteClassificationRequest() {
	}
	
	public DeleteClassificationRequest (String parentId) {
		this.classificationId = parentId;
	}
	
	public String toString() {
		return "Deleted classification(" + classificationId + ")";
	}
}
