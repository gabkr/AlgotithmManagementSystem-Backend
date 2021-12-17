package cs509.thalassa.demo.http;

public class DeleteImplementationResponse {
	public final String response;
	public final int httpCode;
	
	public DeleteImplementationResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public DeleteImplementationResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
