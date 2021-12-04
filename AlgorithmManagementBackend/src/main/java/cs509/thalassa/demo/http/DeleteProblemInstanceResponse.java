package cs509.thalassa.demo.http;

public class DeleteProblemInstanceResponse {
	public final String response;
	public final int httpCode;
	
	public DeleteProblemInstanceResponse(String response, int httpCode) {
		this.response = response;
		this.httpCode = httpCode;
	}
	
	public DeleteProblemInstanceResponse(String response) {
		this.response = response;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
