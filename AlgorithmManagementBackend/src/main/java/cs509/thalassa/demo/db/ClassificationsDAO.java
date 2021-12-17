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
    		logger.log("Inside DAO delete");
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
     * @throws Exception 
	**/
    public Boolean reclassifyClassifications(String oldClassificationID, String newClassificationID) throws Exception {
    	try {
    		PreparedStatement ps = conn.prepareStatement("UPDATE " + tblName + " SET ParentID = ? " + "WHERE ParentID = ?;");
    		ps.setString(1, newClassificationID);
    		ps.setString(2, oldClassificationID);
    		ps.executeUpdate();
    		ps.close();
    		logger.log("Reclassified Classifications");
    	} catch (Exception e) {
    		e.printStackTrace();
            throw new Exception("Failed in reclassifying classification: " + e.getMessage());
    	}
    	return true;
    }
	public Boolean mergeClassifications(String name, String newID, String classID1, String classID2) throws Exception {
		try {
        	List<Classification> classList = new ArrayList<>();
			PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ClassificationID in (?,?);");
            ps1.setString(1,  classID1);
            ps1.setString(2, classID2);
            ResultSet resultSet = ps1.executeQuery();
            while(resultSet.next()) {
            	classList.add(generateClassification(resultSet));
            }
            if(classList.size() == 2) {
            	Classification classification1 = classList.get(0);
            	Classification classification2 = classList.get(1);
            	String parentClassificationID = null;
            	if(classification1.parentClassification != null && classification2.parentClassification != null) {
            		if(classification1.parentClassification.equalsIgnoreCase(classification2.parentClassification)) {
            			parentClassificationID = classification1.parentClassification;
            			logger.log("Merging two classifications with parent " + parentClassificationID + " new ID = " + newID);
                		PreparedStatement ps2 = conn.prepareStatement("INSERT INTO "+ tblName +" (ClassificationID, ClassificationName, ParentID) VALUES (?, ?, ?)");
            			ps2.setString(1, newID);
            			ps2.setString(2, name);
            			ps2.setString(3, parentClassificationID);
            			ps2.executeUpdate();
            			
            			logger.log("Reclassifying algorithms for existing classifications");
            			AlgorithmsDAO algoDAO = new AlgorithmsDAO(logger);
            			algoDAO.reclassifyAlgorithmForMerge(classification1.id, newID);
            			algoDAO.reclassifyAlgorithmForMerge(classification2.id, newID);
            			logger.log("Reclassifying Classification");
            			reclassifyClassifications(classification1.id, newID);
            			reclassifyClassifications(classification2.id, newID);
            			logger.log("Delete exisiting classifications");
                		PreparedStatement ps = conn.prepareStatement("DELETE FROM" + " " + tblName + " " + "WHERE classificationID in (?, ?);");
                		ps.setNString(1, classID1);
                		ps.setString(2, classID2);
                		int rs = ps.executeUpdate();
                		logger.log("Deleted " + rs + " rows");
            			return true;
            		}
            		else {
	            		logger.log("parent classification exists for both ..... terminating merge");
	            		return false;
            		}
            	}
            	else if(classification1.parentClassification == null || classification2.parentClassification == null) {
            		
            		
            		if(classification1.parentClassification != null && classification2.parentClassification == null) {
            			parentClassificationID = classification1.parentClassification;
            		}
            		else if(classification1.parentClassification == null && classification2.parentClassification != null) {
            			parentClassificationID = classification2.parentClassification;
            		}
            		logger.log("Merging two classifications with parent " + parentClassificationID + " new ID = " + newID);
            		PreparedStatement ps2 = conn.prepareStatement("INSERT INTO "+ tblName +" (ClassificationID, ClassificationName, ParentID) VALUES (?, ?, ?)");
        			ps2.setString(1, newID);
        			ps2.setString(2, name);
        			ps2.setString(3, parentClassificationID);
        			ps2.executeUpdate();
        			
        			logger.log("Reclassifying algorithms for existing classifications");
        			AlgorithmsDAO algoDAO = new AlgorithmsDAO(logger);
        			algoDAO.reclassifyAlgorithmForMerge(classification1.id, newID);
        			algoDAO.reclassifyAlgorithmForMerge(classification2.id, newID);
        			logger.log("Reclassifying Classification");
        			reclassifyClassifications(classification1.id, newID);
        			reclassifyClassifications(classification2.id, newID);
        			logger.log("Delete exisiting classifications");
            		PreparedStatement ps = conn.prepareStatement("DELETE FROM" + " " + tblName + " " + "WHERE classificationID in (?, ?);");
            		ps.setNString(1, classID1);
            		ps.setString(2, classID2);
            		int rs = ps.executeUpdate();
            		logger.log("Deleted " + rs + " rows");
        			return true;
            	}
            }
            else {
            	logger.log("Classification List size not adequate");
            	return false;
            }
		}
		catch (Exception e) {
			e.printStackTrace();
            throw new Exception("Failed to merge classification: " + e.getMessage());
		}
		return false;
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
