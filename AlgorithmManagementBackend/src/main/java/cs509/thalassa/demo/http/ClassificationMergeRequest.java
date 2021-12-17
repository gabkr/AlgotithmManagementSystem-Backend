package cs509.thalassa.demo.http;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClassificationMergeRequest {
	public String classificationId1;
	public String classificationId2;
	public String name;
	public String userName;
	public String userID;
	public String  time;
	
	public String getClassificationId1() {
		return classificationId1;
	}

	public void setClassificationId1(String classificationId1) {
		this.classificationId1 = classificationId1;
	}

	public String getClassificationId2() {
		return classificationId2;
	}

	public void setClassificationId2(String classificationId2) {
		this.classificationId2 = classificationId2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ClassificationMergeRequest() {
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public ClassificationMergeRequest (String classificationId1, String classificationId2, String name, String userName, String userID) {
		this.classificationId1 = classificationId1;
		this.classificationId2 = classificationId2;
		this.name = name;
		this.userName= userName;
		this.userID = userID;
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	/**
	public String toString() {
		return "ListClassification(" + classificationId1 + " " + classificationId2 + ")";
	}
	**/
}
