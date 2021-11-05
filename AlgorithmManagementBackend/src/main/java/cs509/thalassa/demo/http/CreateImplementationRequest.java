package cs509.thalassa.demo.http;

public class CreateImplementationRequest {
	public String implementationFile;
	public String algorithmId;
	public String idImplementation;
	public boolean system;
	
	public String getImplementationFile( ) { return implementationFile; }
	public void setImplementationFile(String implementationFile) { this.implementationFile = implementationFile; }
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String getIdImplementation() { return idImplementation; }
	public void setIdImplementation(String d) { this.idImplementation = d; }
	
	public String getAlgorithmId() { return algorithmId; }
	public void setAlgorithmId(String c) { this.algorithmId = c; }
	
	public CreateImplementationRequest() {
	}
	
	public CreateImplementationRequest (String n, String idImplementation, String algorithmId) {
		this.implementationFile = n;
		this.idImplementation = idImplementation;
		this.algorithmId = algorithmId;
	}
	
	/**
	public CreateClassificationRequest (String n, double val, boolean system) {
		this.parent = n;
		this.child = val;
		this.system = system;
	}
	**/
	
	public String toString() {
		return "CreateImplementation(" + implementationFile + "," + idImplementation + " "+ ","+ algorithmId+")";
	}
}
