package cs509.thalassa.demo.model;

public class Implementation {
	public final String implementationFile;
	public final String idImplementation;
	public final boolean system;      // when TRUE this is actually stored in S3 bucket
	public final String algorithmId;
	public final String value;
	
	public Implementation (String implementationFile, String idImplementation, String algorithmId, String value) {
		this.implementationFile = implementationFile;
		this.idImplementation = idImplementation;
		this.algorithmId = algorithmId;
		this.system = true;
		this.value = value;
	}
	
	public boolean getSystem() { return system; }
	
	/**
	 * Equality of Constants determined by name alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Implementation) {
			Implementation other = (Implementation) o;
			return implementationFile.equals(other.implementationFile);
		}
		
		return false;  // not a Classification
	}

	public String toString() {
		String sysString = "";
		if (system) { sysString = " (system)"; }
		return "[" + implementationFile+ " " + idImplementation+ " " + algorithmId + value + sysString + "]";
	}
}