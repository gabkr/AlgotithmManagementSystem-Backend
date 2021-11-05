package cs509.thalassa.demo.http;

public class ListClassificationRequest {
	public String parentClassification;
	
	public String getParentClassification() { return parentClassification; }
	public void setParentClassification(String c) { this.parentClassification = c; }
	
	public ListClassificationRequest() {
	}
	
	public ListClassificationRequest (String parentId) {
		this.parentClassification = parentId;
	}
	
	public String toString() {
		return "ListClassification(" + parentClassification + ")";
	}
}
