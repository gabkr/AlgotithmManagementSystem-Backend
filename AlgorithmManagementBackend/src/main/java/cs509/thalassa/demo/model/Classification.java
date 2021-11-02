package cs509.thalassa.demo.model;

public class Classification {
	public final String nameClassification;
	public final String id;
	public final boolean system;      // when TRUE this is actually stored in S3 bucket
	public final String parentClassification;
	
	public Classification (String nameClassification, String id, String parentClassification) {
		this.nameClassification = nameClassification;
		this.id = id;
		this.parentClassification = parentClassification;
		this.system = false;
	}

	public Classification (String nameClassification, String id) {
		this.nameClassification = nameClassification;
		this.id = id;
		this.parentClassification = null;
		this.system = false;
	}
	
	/**
	public Classification (String parent, double child, boolean system) {
		this.parent = parent;
		this.child = child;
		this.system = system;
	}
	
	public boolean getSystem() { return system; }
	**/
	
	/**
	 * Equality of Constants determined by name alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Classification) {
			Classification other = (Classification) o;
			return nameClassification.equals(other.nameClassification);
		}
		
		return false;  // not a Classification
	}

	public String toString() {
		String sysString = "";
		if (system) { sysString = " (system)"; }
		return "[" + nameClassification+ " " + id+ " " + parentClassification + sysString + "]";
	}
}
