package cs509.thalassa.demo.http;

public class UserHistoryRequest {
	public String userId;
	
	public String getuserId() { return userId; }
	
	public UserHistoryRequest() {
	}
	
	public UserHistoryRequest (String userId) {
		this.userId = userId;
	}
	
	public String toString() {
		return "ListUserHistoryId(" + userId + ")";
	}
}
