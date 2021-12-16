package cs509.thalassa.demo.model;

public class ProblemInstance {
	public final String id;
	public final String algorithmId;
	public final String input;
	public final String name;
	
	public ProblemInstance(String id, String algorithmId, String input, String name) {
		this.id = id;
		this.algorithmId = algorithmId;
		this.input = input;
		this.name = name;
	}
	
	public boolean equals (Object o) {
		if (o == null) {
			return false;
		}
		
		if (o instanceof ProblemInstance) {
			ProblemInstance other = (ProblemInstance) o;
			return id.equals(other.id);
		}
		
		return false;
	}
	
	public String toString() {
		return "[" + id + " " + algorithmId + " " + name + " " + input + "]";
	}
}
