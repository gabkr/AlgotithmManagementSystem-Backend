package cs509.thalassa.demo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import cs509.thalassa.demo.model.Algorithm;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 */
public class AlgorithmsDAO { 

	java.sql.Connection conn;
	LambdaLogger logger;
	final String tblName = "Algorithm";   // Exact capitalization

    public AlgorithmsDAO(LambdaLogger logger) {
    	this.logger = logger;
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Algorithm getAlgorithm(String nameAlgorithm) throws Exception {
        
        try {
            Algorithm algorithm = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE AlgorithmName=?;");
            ps.setString(1,  nameAlgorithm);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                algorithm = generateAlgorithm(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return algorithm;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting algorithm: " + e.getMessage());
        }
    }

    
    public List<Algorithm> getRelatedAlgorithms(String parentId) throws Exception {
        
    	List<Algorithm> allAlgorithms = new ArrayList<>();
    	
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ClassificationID=?;");
            ps.setString(1,  parentId);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
            	Algorithm c = generateAlgorithm(resultSet);
                allAlgorithms.add(c);
            }
            resultSet.close();
            ps.close();
            
            return allAlgorithms;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting algorithm: " + e.getMessage());
        }
    }
    
    /**
    public boolean updateClassification(Classification classification) throws Exception {
        try {
        	String query = "UPDATE " + tblName + " SET value=? WHERE name=?;";
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, classification.child);
            ps.setString(2, classification.parent);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to update report: " + e.getMessage());
        }
    }
    **/
    /**
    public boolean deleteClassification(Classification classification) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE name = ?;");
            ps.setString(1, classification.parent);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert classification: " + e.getMessage());
        }
    }
	**/

    public boolean addAlgorithm(Algorithm algorithm) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE AlgorithmName = ?;");
            ps.setString(1, algorithm.nameAlgorithm);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Algorithm c = generateAlgorithm(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (AlgorithmName,AlgorithmID,ClassificationID) values(?,?,?);");
            ps.setString(1,  algorithm.nameAlgorithm);
            ps.setString(2,  algorithm.idAlgorithm);
            ps.setString(3,  algorithm.parentId);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert algorithm: " + e.getMessage());
        }
    }

    /**
    public List<Classification> getAllClassifications() throws Exception {
        
        List<Classification> allClassifications = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM " + tblName + ";";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Classification c = generateClassification(resultSet);
                allClassifications.add(c);
            }
            resultSet.close();
            statement.close();
            return allClassifications;

        } catch (Exception e) {
            throw new Exception("Failed in getting classifications: " + e.getMessage());
        }
    }
	**/
	
    private Algorithm generateAlgorithm(ResultSet resultSet) throws Exception {
        String nameAlgorithm  = resultSet.getString("AlgorithmName");
        String idAlgorithm = resultSet.getString("AlgorithmID");
        String parentId = resultSet.getString("ClassificationID");
        return new Algorithm (nameAlgorithm, idAlgorithm, parentId);
    }
}
