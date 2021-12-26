package cs509.thalassa.demo.http;

import java.util.ArrayList;
import java.util.List;

import cs509.thalassa.demo.model.ProblemInstance;

public class ListProblemInstancesResponse {
	public final List<ProblemInstance> list;
	public final int statusCode;
	public final String error;
	
	public ListProblemInstancesResponse(List<ProblemInstance> list, int statusCode) {
		this.list = list;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public ListProblemInstancesResponse(int statusCode, String errorMessage) {
		this.list = new ArrayList<ProblemInstance>();
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	/**
	public String toString() {
		if (list == null) {
			return "Empty Problem Instances";
		}

		return "Problem Instances(" + list.size() + ")";
	}
	**/
}
