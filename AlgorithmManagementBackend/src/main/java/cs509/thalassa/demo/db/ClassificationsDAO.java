package cs509.thalassa.demo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import cs509.thalassa.demo.model.Classification;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 */
public class ClassificationsDAO { 

	java.sql.Connection conn;
	LambdaLogger logger;
	final String tblName = "Classification";   // Exact capitalization
	final String tblAlgorithmName = "Algorithm";   // Exact capitalization

    public ClassificationsDAO(LambdaLogger logger) {
    	this.logger = logger;
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Classification getClassification(String id) throws Exception {
        
        try {
            Classification classification = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE classificationID=?;");
//            PreparedStatement ps = conn.prepareStatement("Select c1.*, COUNT(distinct c2.id) as childClassificationsCount, COUNT(distinct A.idAlgorithm) as algorithmsCount\n" + 
//    				"from " + tblName + " c1\n" + 
//    				"         LEFT JOIN " + tblName + " c2 on c1.id = c2.parentClassification\n" + 
//    				"         LEFT JOIN " + tblAlgorithmName + " A on c1.id = A.parentId\n" + 
//    				"where c1.id=? group by c1.id;");
            ps.setString(1,  id);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                classification = generateClassification(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return classification;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting classification: " + e.getMessage());
        }
    }

    
    public List<Classification> getRelatedClassifications(String parentClassification) throws Exception {
        
    	List<Classification> allClassifications = new ArrayList<>();
    	
        try {
        	PreparedStatement ps;
        	if (parentClassification != null) {
//        		ps = conn.prepareStatement("Select c1.*, COUNT(distinct c2.id) as childClassificationsCount, COUNT(distinct A.idAlgorithm) as algorithmsCount\n" + 
//        				"from " + tblName + " c1\n" + 
//        				"         LEFT JOIN " + tblName + " c2 on c1.id = c2.parentClassification\n" + 
//        				"         LEFT JOIN " + tblAlgorithmName + " A on c1.id = A.parentId\n" + 
//        				"where c1.parentClassification=? group by c1.id;");
                ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ParentID=?;");
                ps.setString(1,  parentClassification);        		
        	} else {
                ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ParentID is NULL;");
//        		ps = conn.prepareStatement("Select c1.*, COUNT(distinct c2.id) as childClassificationsCount, COUNT(distinct A.idAlgorithm) as algorithmsCount\n" + 
//        				"from " + tblName + " c1\n" + 
//        				"         LEFT JOIN " + tblName + " c2 on c1.id = c2.parentClassification\n" + 
//        				"         LEFT JOIN " + tblAlgorithmName + " A on c1.id = A.parentId\n" + 
//        				"where c1.parentClassification is NULL group by c1.id;");
        	}

        	ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
            	Classification c = generateClassification(resultSet);
                allClassifications.add(c);
            }
            resultSet.close();
            ps.close();
            
            return allClassifications;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting classification: " + e.getMessage());
        }
    }
    
    public boolean deleteClassification(String classificationId) throws Exception {
    	try {
    		PreparedStatement ps = conn.prepareStatement("DELETE FROM" + " " + tblName + " " + "WHERE classificationID = ?;");
    		ps.setNString(1, classificationId);
    		int rs = ps.executeUpdate();
    		if(rs==0) {
    			logger.log("Deleted:" + classificationId);
    		}
    	}
    	catch (Exception e) {
    		throw new Exception("Failed to delete classification: " + e.getMessage());
    	}
    	return true;
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

    public boolean addClassification(Classification classification) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ClassificationName = ?;");
            ps.setString(1, classification.nameClassification);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Classification c = generateClassification(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (ClassificationName,ClassificationID,ParentID) values(?,?,?);");
            ps.setString(1,  classification.nameClassification);
            ps.setString(2,  classification.id);
            ps.setString(3,  classification.parentClassification);
            ps.execute();
            return true;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed to insert classification: " + e.getMessage());
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
	public Boolean mergeClassifications(String name, String newID, String classID1, String classID2) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE "+ tblName +"SET ClassificationID = ?, ClassificationName = ? WHERE ClassificationID in (?,?)");
			ps.setString(1, newID);
			ps.setString(2, name);
			ps.setString(3, classID1);
			ps.setString(4, classID2);
			ps.executeUpdate();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
            throw new Exception("Failed to merge classification: " + e.getMessage());
		}
	}
    private Classification generateClassification(ResultSet resultSet) throws Exception {
        String nameClassification  = resultSet.getString("ClassificationName");
        String id = resultSet.getString("ClassificationID");
        String parentClassification = resultSet.getString("ParentID");
//        int childClassificationsCount = resultSet.getInt("childClassificationsCount");
//        int algorithmsCount = resultSet.getInt("algorithmsCount");

        return new Classification(nameClassification, id, parentClassification);
    }
}
