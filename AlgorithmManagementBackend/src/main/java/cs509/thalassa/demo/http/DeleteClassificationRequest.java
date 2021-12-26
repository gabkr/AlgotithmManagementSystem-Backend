package cs509.thalassa.demo.http;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeleteClassificationRequest {
	public String classificationId;
	public String userName;
	public String userID;
	public String  time;
	
	public String getClassificationId() { return classificationId; }
	public void setClassificationId(String c) { this.classificationId = c; }
	
	public DeleteClassificationRequest() {
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public DeleteClassificationRequest (String parentId, String userName, String userID) {
		this.classificationId = parentId;
		this.userName= userName;
		this.userID = userID;
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	//public String toString() {
		//return "Deleted classification(" + classificationId + ")";
	//}
}
