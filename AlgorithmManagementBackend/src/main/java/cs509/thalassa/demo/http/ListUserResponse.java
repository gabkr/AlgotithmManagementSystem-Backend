package cs509.thalassa.demo.http;

import java.util.ArrayList;
import java.util.List;

import cs509.thalassa.demo.model.Classification;
import cs509.thalassa.demo.model.User;

public class ListUserResponse {
	public final List<User> list;
	public final int statusCode;
	public final String error;
	
	public ListUserResponse (List<User> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
		
	public ListUserResponse (int code, String errorMessage) {
		this.list = new ArrayList<User>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	/**
	public String toString() {
		if (list == null) { return "Users not Found"; }
		return "ListUserResponse(" + list.size() + ")";
	}
	**/
}
