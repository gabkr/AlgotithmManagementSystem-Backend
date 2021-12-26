package cs509.thalassa.demo.model;

public class User {
	public final String name;
	public final String id;
	public final String username;
	public final String email;
	
	public User (String id, String name, String username, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
	}

	/**
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
**/
	/**
	 * Equality of Constants determined by name alone.
	 */
	/**
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Algorithm) {
			Algorithm other = (Algorithm) o;
			return name.equals(other.name);
		}
		
		return false;  // not an algorithm
	}
**/
	/**
	public String toString() {
		return "[" + id + " " + name + " " + username + " " + email + "]";
	}
	**/
}
