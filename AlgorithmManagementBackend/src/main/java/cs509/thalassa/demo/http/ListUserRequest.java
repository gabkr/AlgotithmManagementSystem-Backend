package cs509.thalassa.demo.http;

public class ListUserRequest {
	public int limit;
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public ListUserRequest() {
	}
	
	public ListUserRequest (int limit) {
		this.limit = limit;
	}
	
	public String toString() {
		return "Limit for list Users" + limit + ")";
	}
}
