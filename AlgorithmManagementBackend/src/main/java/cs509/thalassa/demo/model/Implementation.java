package cs509.thalassa.demo.model;

public class Implementation {
	public final String implementationName;
	public final String idImplementation;
	public final boolean system;      // when TRUE this is actually stored in S3 bucket
	public final String algorithmId;
	public final String value;
	public final String s3Url;
	
	public Implementation (String implementationName, String idImplementation, String algorithmId, String value) {
		this.implementationName = implementationName;
		this.idImplementation = idImplementation;
		this.algorithmId = algorithmId;
		this.system = true;
		this.value = value;
		this.s3Url = null;
	}
	
	public Implementation (String implementationName, String idImplementation, String algorithmId, String value, String s3Url) {
		this.implementationName = implementationName;
		this.idImplementation = idImplementation;
		this.algorithmId = algorithmId;
		this.system = true;
		this.value = "";
		this.s3Url = s3Url;
	}
	
	public boolean getSystem() { return system; }
	
	/**
	 * Equality of Constants determined by name alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Implementation) {
			Implementation other = (Implementation) o;
			return implementationName.equals(other.implementationName);
		}
		
		return false;  // not a Classification
	}

	public String toString() {
		String sysString = "";
		if (system) { sysString = " (system)"; }
		return "[" + implementationName+ " " + idImplementation+ " " + algorithmId + value + sysString + "]";
	}
}