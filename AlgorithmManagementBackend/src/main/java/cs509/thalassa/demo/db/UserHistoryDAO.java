package cs509.thalassa.demo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import cs509.thalassa.demo.model.UserHistory;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 */
public class UserHistoryDAO { 

	java.sql.Connection conn;
	LambdaLogger logger;
	final String tblName = "UserHistory";   // Exact capitalization

    public UserHistoryDAO(LambdaLogger logger) {
    	this.logger = logger;
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public UserHistory getUserHistory(String Activity) throws Exception {
        
        try {
            UserHistory userHistory = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE Activity=?;");
            ps.setString(1,  Activity);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                userHistory = generateUserHistory(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return userHistory;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting user history: " + e.getMessage());
        }
    }

 /**   
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
    **/
    
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

    public boolean addUserHistory(UserHistory userHistory, String operation) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE Activity = ?;");
            ps.setString(1, userHistory.Activity);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                UserHistory c = generateUserHistory(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (Username,UserID,Activity,ActivityTime) values(?,?,?,?);");
            ps.setString(1,  userHistory.Username);
            ps.setString(2,  userHistory.UserID);
            ps.setString(3,  operation+" "+userHistory.Activity);
            ps.setString(4,  userHistory.ActivityTime);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert userHistory: " + e.getMessage());
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
	
    private UserHistory generateUserHistory(ResultSet resultSet) throws Exception {
        String UserID  = resultSet.getString("UserID");
        String Username = resultSet.getString("Username");
        String Activity = resultSet.getString("Activity");
        String ActivityTime = resultSet.getString("ActivityTime");
        return new UserHistory(UserID, Username, Activity, ActivityTime);
    }
}
