package cs509.thalassa.demo.http;

import java.util.UUID;

public class CreateImplementationRequest {
	public String implementationName;
	public String algorithmId;
	public String idImplementation;
	public boolean system;
	public String value;

	public boolean isUpload;
	public String implementationFileFormat;
	public String implementationMimeType;

	
	public boolean getIsUpload() {
		return this.isUpload;
	}
	
	public void setIsUpload(boolean isUpload) {
		this.isUpload = isUpload;
	}
	
	public String getImplementationFileFormat() {
		return this.implementationFileFormat;
	}
	
	public void setImplementationFileFormat(String implementationFileFormat) {
		this.implementationFileFormat = implementationFileFormat;
	}
	
	public String getImplementationMimeType() {
		return this.implementationMimeType;
	}
	
	public void setImplementationMimeType(String implementationMimeType) {
		this.implementationMimeType = implementationMimeType;
	}
	
	
	
	
	public String getImplementationName( ) { return implementationName; }
	public void setImplementationName(String implementationName) { this.implementationName = implementationName; }
	
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
		this.isUpload = false;
	}
	
	public CreateImplementationRequest (String n, String algorithmId, String value) {
		this.implementationName = n;
		this.idImplementation = UUID.randomUUID().toString();
		this.algorithmId = algorithmId;
		this.system = true;
		this.value = value;
	}
	
	
	public String toString() {
		return "CreateImplementation(" + implementationName + "," + idImplementation + " "+ ","+ algorithmId+ value+")";
	}
}
