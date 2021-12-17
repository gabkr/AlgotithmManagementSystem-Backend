package cs509.thalassa.demo.http;

import java.util.ArrayList;
import java.util.List;

import cs509.thalassa.demo.model.UserHistory;

public class UserHistoryResponse {
	public final List<UserHistory> list;
	public final int statusCode;
	public final String error;
	
	public UserHistoryResponse (List<UserHistory> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public UserHistoryResponse (int code, String errorMessage) {
		this.list = new ArrayList<UserHistory>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyUserHistory"; }
		return "UserHistory(" + list.size() + ")";
	}
}
