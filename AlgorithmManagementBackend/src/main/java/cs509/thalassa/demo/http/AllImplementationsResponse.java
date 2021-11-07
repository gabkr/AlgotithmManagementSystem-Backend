package cs509.thalassa.demo.http;

import java.util.ArrayList;
import java.util.List;

import cs509.thalassa.demo.model.Implementation;

public class AllImplementationsResponse {
	public final List<Implementation> list;
	public final int statusCode;
	public final String error;
	
	public AllImplementationsResponse (List<Implementation> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllImplementationsResponse (int code, String errorMessage) {
		this.list = new ArrayList<Implementation>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyImplementations"; }
		return "AllImplementations(" + list.size() + ")";
	}
}
