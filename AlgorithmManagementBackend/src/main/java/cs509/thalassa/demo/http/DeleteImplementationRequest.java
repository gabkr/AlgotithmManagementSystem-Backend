package cs509.thalassa.demo.http;

public class DeleteImplementationRequest {
	public String implementationId;
		
	public String getImplementationId() {
		return implementationId;
	}

	public void setImplementationId(String implementationId) {
		this.implementationId = implementationId;
	}

	public DeleteImplementationRequest() {
	}
	
	public DeleteImplementationRequest (String implementationId) {
		this.implementationId = implementationId;
	}
	
	public String toString() {
		return "DeleteImplementation(" + implementationId + ")";
	}
}
