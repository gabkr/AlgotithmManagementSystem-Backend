package cs509.thalassa.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import cs509.thalassa.demo.model.Algorithm;
import cs509.thalassa.demo.model.Classification;
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
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE AlgorithmID=?;");
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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName + " (AlgorithmID, ProblemInstanceID, input, name) values(?,?,?,?);");
            ps.setString(1,  problemInstance.algorithmId);
            ps.setString(2,  problemInstance.id);
            ps.setString(3,  problemInstance.input);
            ps.setString(4,  problemInstance.name);
            ps.execute();
            return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert problem instance" + e.getMessage());
		}
	}
	
	public boolean deleteProblemInstance(String problemInstanceId) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE ProblemInstanceID=?;");
			ps.setString(1, problemInstanceId);
			ps.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to delete problemInstance: " + e.getMessage()); 
		}
	}

    public ProblemInstance getProblemInstanceByID(String id) throws Exception {
        
        try {
            ProblemInstance problemInstance = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProblemInstanceID=?;");
            ps.setString(1,  id);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                problemInstance = generateProblemInstance(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return problemInstance;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting problemInstance: " + e.getMessage());
        }
    }
	
	private ProblemInstance generateProblemInstance(ResultSet resultSet) throws Exception {
		String id = resultSet.getString("ProblemInstanceID");
		String algorithmId = resultSet.getString("AlgorithmID");
		String input = resultSet.getString("input");
		String name = resultSet.getString("name");
		return new ProblemInstance(id, algorithmId, input, name);
	}
	

}
