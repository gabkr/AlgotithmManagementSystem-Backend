package cs509.thalassa.demo.http;

import java.util.UUID;

public class CreateUserRequest {
	public String id;
	public String name;
	public String email;
	public String username;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public CreateUserRequest() {
	}
	
	public CreateUserRequest (String id, String name, String email, String username) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
	}
	
	/**
	public CreateClassificationRequest (String n, double val, boolean system) {
		this.parent = n;
		this.child = val;
		this.system = system;
	}
	**/
	
	public String toString() {
		return "CreateAlgorithm(" + name + "," + id + " "+ ","+ "id)";
	}
}
