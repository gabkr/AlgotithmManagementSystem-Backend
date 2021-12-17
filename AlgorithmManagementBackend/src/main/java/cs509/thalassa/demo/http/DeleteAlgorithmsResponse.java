package cs509.thalassa.demo.http;

public class DeleteAlgorithmsResponse {
	public final String response;
	public final int httpCode;
	
	public DeleteAlgorithmsResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public DeleteAlgorithmsResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	/**
	public String toString() {
		return "Response(" + response + ")";
	}
	**/
}
