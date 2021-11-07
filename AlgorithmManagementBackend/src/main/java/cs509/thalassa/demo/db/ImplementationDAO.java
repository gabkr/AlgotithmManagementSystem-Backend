package cs509.thalassa.demo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import cs509.thalassa.demo.model.Implementation;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 */
public class ImplementationDAO { 
	
	private AmazonS3 s3 = null;
	java.sql.Connection conn;
	LambdaLogger logger;
	final String tblName = "Implementation";   // Exact capitalization

    public ImplementationDAO(LambdaLogger logger) {
    	this.logger = logger;
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Implementation getImplementation(String id) throws Exception {
        
        try {
            Implementation implementation = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE algorithmId=?;");
//            PreparedStatement ps = conn.prepareStatement("Select c1.*, COUNT(distinct c2.id) as childClassificationsCount, COUNT(distinct A.idAlgorithm) as algorithmsCount\n" + 
//    				"from " + tblName + " c1\n" + 
//    				"         LEFT JOIN " + tblName + " c2 on c1.id = c2.parentClassification\n" + 
//    				"         LEFT JOIN " + tblAlgorithmName + " A on c1.id = A.parentId\n" + 
//    				"where c1.id=? group by c1.id;");
            ps.setString(1,  id);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                implementation = generateImplementation(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return implementation;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting implementation: " + e.getMessage());
        }
    }

    
    public List<Implementation> getRelatedImplementations(String parentAlgorithm) throws Exception {
        
    	List<Implementation> allImplementations = new ArrayList<>();
    	
        try {
        	PreparedStatement ps;
                ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE algorithmId=?;");
                ps.setString(1,  parentAlgorithm);        		

        	ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
            	Implementation c = generateImplementationGet(resultSet);
                allImplementations.add(c);
            }
            resultSet.close();
            ps.close();
            
            return allImplementations;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting implementation: " + e.getMessage());
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

    public boolean addImplementation(Implementation implementation) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE idImplementation = ?;");
            ps.setString(1, implementation.idImplementation);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Implementation c = generateImplementation(resultSet);
                resultSet.close();
                return false;
            }
            
            ps = conn.prepareStatement("INSERT INTO " + tblName + " (idImplementation,algorithmId,implementationFile,s3Url) values(?,?,?,?);");
            ps.setString(1,  implementation.idImplementation);
            ps.setString(2,  implementation.algorithmId);
            ps.setString(3,  implementation.implementationFile);
            s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
        	String s3loc = s3.getUrl("cs509-thalassa-algorithm-management-system", "implementations/"+implementation.implementationFile+".txt").toString();
        	System.out.print(s3loc);
            ps.setString(4, s3loc);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert implementation: " + e.getMessage());
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
	
    private Implementation generateImplementation(ResultSet resultSet) throws Exception {
        String implementationFile  = resultSet.getString("implementationFile");
        String idImplementation = resultSet.getString("idImplementation");
        String algorithmId = resultSet.getString("algorithmId");
        String value = resultSet.getString("value");

        return new Implementation (implementationFile, idImplementation, algorithmId, value);
    }
    
    private Implementation generateImplementationGet(ResultSet resultSet) throws Exception {
        String implementationFile  = resultSet.getString("implementationFile");
        String idImplementation = resultSet.getString("idImplementation");
        String algorithmId = resultSet.getString("algorithmId");
        String s3Url = resultSet.getString("s3Url");

        return new Implementation (implementationFile, idImplementation, algorithmId, "Refer s3 Url", s3Url);
    }
}
