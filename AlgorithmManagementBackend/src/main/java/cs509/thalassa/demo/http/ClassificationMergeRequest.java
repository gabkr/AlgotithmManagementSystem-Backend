package cs509.thalassa.demo.http;

public class ClassificationMergeRequest {
	public String parentClassificationID;
	public String childClassificationID;
	public String newClassificationName;
	
	public String getParentClassification() { return parentClassificationID; }
	public String getChildClassification() { return childClassificationID; }
	public String getNewClassificationName() {return newClassificationName; }
	
	public void setParentClassification(String c) { this.parentClassificationID = c; }
	public void setChildClassification(String c) { this.childClassificationID = c; }
	public void setNewclassificationName(String c) {this.newClassificationName = c; }
	
	public ClassificationMergeRequest() {
	}
	
	public ClassificationMergeRequest (String parentId, String childId, String newName) {
		this.parentClassificationID = parentId;
		this.childClassificationID = childId;
		this.newClassificationName = newName;
	}
	
	public String toString() {
		return "ListClassification(" + parentClassificationID + " " + childClassificationID + ")";
	}
}
