package cs509.thalassa.demo.http;

public class AlgorithmReclassifyResponse {
	public final String response;
	public final int httpCode;
	
	public AlgorithmReclassifyResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public AlgorithmReclassifyResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	/**
	public String toString() {
		return "Response(" + response + ")";
	}
	**/
}
