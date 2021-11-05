package cs509.thalassa.demo.http;

public class CreateAlgorithmRequest {
	public String nameAlgorithm;
	public String idAlgorithm;
	public String parentId;
	public boolean system;
	
	public String getNameAlgorithm( ) { return nameAlgorithm; }
	public void setNameAlgorithm(String nameAlgorithm) { this.nameAlgorithm = nameAlgorithm; }
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String getIdAlgorithm() { return idAlgorithm; }
	public void setIdAlgorithm(String d) { this.idAlgorithm = d; }
	
	public String getParentId() { return parentId; }
	public void setParentId(String c) { this.parentId = c; }
	
	public CreateAlgorithmRequest() {
	}
	
	public CreateAlgorithmRequest (String n, String id, String parentId) {
		this.nameAlgorithm = n;
		this.idAlgorithm = id;
		this.parentId = parentId;
	}
	
	/**
	public CreateClassificationRequest (String n, double val, boolean system) {
		this.parent = n;
		this.child = val;
		this.system = system;
	}
	**/
	
	public String toString() {
		return "CreateAlgorithm(" + nameAlgorithm + "," + idAlgorithm + " "+ ","+ "parentId)";
	}
}
