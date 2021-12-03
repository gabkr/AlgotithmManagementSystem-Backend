package cs509.thalassa.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import cs509.thalassa.demo.model.Algorithm;
import cs509.thalassa.demo.model.ProblemInstance;

public class ProblemInstanceDAO {
	java.sql.Connection conn;
	LambdaLogger logger;
	final String tblName = "ProblemInstance";
	
	public ProblemInstanceDAO(LambdaLogger logger) {
		this.logger = logger;
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	public List<ProblemInstance> getProblemInstances(String algorithmId) throws Exception {
		try {
			List<ProblemInstance> problemInstances = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE AlgorithmId=?;");
            ps.setString(1,  algorithmId);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
            	ProblemInstance problemInstance = generateProblemInstance(resultSet);
            	problemInstances.add(problemInstance);
            }
            resultSet.close();
            ps.close();
            
            return problemInstances;
		} catch (Exception e) {
			e.printStackTrace();
            throw new Exception("Failed in getting problemInstances: " + e.getMessage());
		}
	}
	
	public boolean addProblemInstance(ProblemInstance problemInstance) throws Exception {
		try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName + " (AlgorithmID, ProblemInstanceID, input) values(?,?,?);");
            ps.setString(1,  problemInstance.algorithmId);
            ps.setString(2,  problemInstance.id);
            ps.setString(3,  problemInstance.input);
            ps.execute();
            return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert problem instance" + e.getMessage());
		}
	}

	private ProblemInstance generateProblemInstance(ResultSet resultSet) throws Exception {
		String id = resultSet.getString("ProblemInstanceId");
		String algorithmId = resultSet.getString("AlgorithmId");
		String input = resultSet.getString("input");
		return new ProblemInstance(id, algorithmId, input);
	}
}
