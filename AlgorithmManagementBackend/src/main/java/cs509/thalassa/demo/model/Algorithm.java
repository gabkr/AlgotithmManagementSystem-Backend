package cs509.thalassa.demo.model;

public class Algorithm {
	public final String nameAlgorithm;
	public final String idAlgorithm;
	public final boolean system;      // when TRUE this is actually stored in S3 bucket
	public final String parentId;
	
	public Algorithm (String nameAlgorithm, String idAlgorithm, String parentId) {
		this.nameAlgorithm = nameAlgorithm;
		this.idAlgorithm = idAlgorithm;
		this.parentId = parentId;
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
		
		if (o instanceof Algorithm) {
			Algorithm other = (Algorithm) o;
			return nameAlgorithm.equals(other.nameAlgorithm);
		}
		
		return false;  // not a Classification
	}

	public String toString() {
		String sysString = "";
		if (system) { sysString = " (system)"; }
		return "[" + nameAlgorithm+ " " + idAlgorithm+ " " + parentId + sysString + "]";
	}
}
