package cs509.thalassa.demo;

import java.util.UUID;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class CreateClassificationRequest {
	public String nameClassification;
	public String id;
	public String parentClassification;
	public String userName;
	public String userID;
	public String  time;
	public boolean system;
	
	public String getNameClassification( ) { return nameClassification; }
	public void setNameClassification(String nameClassification) { this.nameClassification = nameClassification; }
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String getId() { return id; }
	public void setId(String d) { this.id = d; }
	
	public String getParentClassification() { return parentClassification; }
	public void setParentClassification(String c) { this.parentClassification = c; }
	
	public CreateClassificationRequest() {
		this.id = UUID.randomUUID().toString();	
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public CreateClassificationRequest (String n, String parentId, String userName, String userID) {
		this.nameClassification = n;
		this.id = UUID.randomUUID().toString();
		this.parentClassification = parentId;
		this.userName= userName;
		this.userID = userID;
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public CreateClassificationRequest (String n, String userName, String userID) {
		this.nameClassification = n;
		this.id = UUID.randomUUID().toString();
		this.parentClassification = null;
		this.userName= userName;
		this.userID = userID;
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public String toString() {
		return "CreateClassification(" + nameClassification + "," + id + " "+ ","+ "parentClassification)";
	}
}
