package cs509.thalassa.demo.http;

import java.util.ArrayList;
import java.util.List;

import cs509.thalassa.demo.model.Algorithm;

public class AllAlgorithmsResponse {
	public final List<Algorithm> list;
	public final int statusCode;
	public final String error;
	
	public AllAlgorithmsResponse (List<Algorithm> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllAlgorithmsResponse (int code, String errorMessage) {
		this.list = new ArrayList<Algorithm>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "Empty Algorithms"; }
		return "AllAlgorithms(" + list.size() + ")";
	}
}
