package cs509.thalassa.demo.model;

public class Algorithm {
	public final String name;
	public final String id;
	public final String classificationId;
	
	public Algorithm (String id, String name, String classificationId) {
		this.id = id;
		this.name = name;
		this.classificationId = classificationId;
	}

	/**
	 * Equality of Constants determined by name alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Algorithm) {
			Algorithm other = (Algorithm) o;
			return name.equals(other.name);
		}
		
		return false;  // not an algorithm
	}

	public String toString() {
		return "[" + id + " " + name + " " + classificationId + "]";
	}
}
