package cs509.thalassa.demo.http;

import java.util.UUID;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class CreateAlgorithmRequest {
	public String nameAlgorithm;
	public String idAlgorithm;
	public String parentId;
	public String userName;
	public String userID;
	public String  time;
	public boolean system;
	
	public String getNameAlgorithm( ) { return nameAlgorithm; }
	public void setNameAlgorithm(String nameAlgorithm) { this.nameAlgorithm = nameAlgorithm; }
	
	public boolean getSystem() { return system; }
	public void setSystem(boolean system) { this.system = system; }
	
	public String getIdAlgorithm() { return idAlgorithm; }
	public void setIdAlgorithm(String d) { this.idAlgorithm = d; }
	
	public String getParentId() { return parentId; }
	public void setParentId(String c) { this.parentId = c; }
	
	public CreateAlgorithmRequest() {
		this.idAlgorithm = UUID.randomUUID().toString();
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public CreateAlgorithmRequest (String n, String parentId, String userName, String userID) {
		this.nameAlgorithm = n;
		this.idAlgorithm = UUID.randomUUID().toString();
		this.parentId = parentId;
		this.userName= userName;
		this.userID = userID;
		this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
	public String toString() {
		return "CreateAlgorithm(" + nameAlgorithm + "," + idAlgorithm + " "+ ","+ "parentId)";
	}
}
