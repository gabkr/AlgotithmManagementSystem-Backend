package cs509.thalassa.demo.http;

public class CreateClassificationRequest {
	public String nameClassification;
	public String id;
	public String parentClassification;
	public boolean system;
	
	public String getNameClassification( ) { return nameClassification; }
	public void setNameClassification(String nameClassification) { this.nameClassification = nameClassification; }
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String getId() { return id; }
	public void setId(String d) { this.id = d; }
	
	public String getParentClassification() { return parentClassification; }
	public void setParentClassification(String c) { this.parentClassification = c; }
	
	public CreateClassificationRequest() {
	}
	
	public CreateClassificationRequest (String n, String id, String parentId) {
		this.nameClassification = n;
		this.id = id;
		this.parentClassification = parentId;
	}
	
	public CreateClassificationRequest (String n, String id) {
		this.nameClassification = n;
		this.id = id;
		this.parentClassification = null;
	}
	
	/**
	public CreateClassificationRequest (String n, double val, boolean system) {
		this.parent = n;
		this.child = val;
		this.system = system;
	}
	**/
	
	public String toString() {
		return "CreateClassification(" + nameClassification + "," + id + " "+ ","+ "parentClassification)";
	}
}
