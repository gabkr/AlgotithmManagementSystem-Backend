package cs509.thalassa.demo.http;

import java.util.UUID;

public class CreateImplementationRequest {
	public String implementationFile;
	public String algorithmId;
	public String idImplementation;
	public boolean system;
	public String value;
	
	public String getImplementationFile( ) { return implementationFile; }
	public void setImplementationFile(String implementationFile) { this.implementationFile = implementationFile; }
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String getIdImplementation() { return idImplementation; }
	public void setIdImplementation(String d) { this.idImplementation = d; }
	
	public String getAlgorithmId() { return algorithmId; }
	public void setAlgorithmId(String c) { this.algorithmId = c; }
	
	public String getValue() { return value; }
	public void setValue(String d) { this.value = d; }
	
	public CreateImplementationRequest() {
		this.idImplementation = UUID.randomUUID().toString();
	}
	
	public CreateImplementationRequest (String n, String algorithmId, String value) {
		this.implementationFile = n;
		this.idImplementation = UUID.randomUUID().toString();
		this.algorithmId = algorithmId;
		this.system = true;
		this.value = value;
	}
	
	
	public String toString() {
		return "CreateImplementation(" + implementationFile + "," + idImplementation + " "+ ","+ algorithmId+ value+")";
	}
}
