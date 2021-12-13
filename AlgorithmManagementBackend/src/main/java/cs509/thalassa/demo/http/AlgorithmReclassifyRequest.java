package cs509.thalassa.demo.http;

public class AlgorithmReclassifyRequest {
	public String algorithmId;
	public String parentClassification;
	
	public String getAlgorithmId() { return algorithmId; }
	public String getParentClassification() { return parentClassification; }
	
	public void setAlgorithmId(String c) { this.algorithmId = c; }
	public void setParentClassification(String c) { this.parentClassification = c; }
	
	public AlgorithmReclassifyRequest() {
	}
	
	public AlgorithmReclassifyRequest (String algoId, String parentClassId) {
		this.algorithmId = algoId;
		this.parentClassification = parentClassId;
	}
	
	public String toString() {
		return "Algorithm reclassified (" + algorithmId + " " + parentClassification + ")";
	}
}
