package cs509.thalassa.demo.http;

public class CreateProblemInstanceResponse {
	public final String id;
	public final String response;
	public final int httpCode;
	
	public CreateProblemInstanceResponse(String response, int httpCode) {
		this.id = null;
		this.response = response;
		this.httpCode = httpCode;
	}
	
	public CreateProblemInstanceResponse(String id, String response) {
		this.response = response;
		this.httpCode = 200;
		this.id = id;
	}
	
	/**
	public String toString() {
		return "Response(" + response + ")";
	}
	**/
}
