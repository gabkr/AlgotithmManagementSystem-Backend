package cs509.thalassa.demo.http;

public class AlgorithmReclassifyRequest {
	public String algorithmId;
	public String classificationId;
	
	public String getAlgorithmId() { return algorithmId; }
	public String getClassificationId() { return classificationId; }
	
	public void setAlgorithmId(String c) { this.algorithmId = c; }
	public void setClassificationId(String c) { this.classificationId = c; }
	
	public AlgorithmReclassifyRequest() {
	}
	
	public AlgorithmReclassifyRequest (String algoId, String parentClassId) {
		this.algorithmId = algoId;
		this.classificationId = parentClassId;
	}
	
	public String toString() {
		return "Algorithm reclassified (" + algorithmId + " " + classificationId + ")";
	}
}
