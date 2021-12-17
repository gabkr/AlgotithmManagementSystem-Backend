package cs509.thalassa.demo.http;

public class DeleteUserRequest {
	String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	DeleteUserRequest() {}
	
	DeleteUserRequest(String username) {
		this.username = username;
	}
	
	public String toString() {
		return "Algorithm reclassified (" + username  + ")";
	}
}
