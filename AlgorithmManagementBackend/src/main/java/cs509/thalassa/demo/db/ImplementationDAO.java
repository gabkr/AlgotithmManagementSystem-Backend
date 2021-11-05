package cs509.thalassa.demo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import cs509.thalassa.demo.model.Implementation;

/**
 * Note that CAPITALIZATION matters regarding the table name. If you create with 
 * a capital "Constants" then it must be "Constants" in the SQL queries.
 */
public class ImplementationDAO { 

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
    
    public Implementation getImplementation(String idImplementation) throws Exception {
        
        try {
            Implementation  implementation = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE idImplementation=?;");
            ps.setString(1,  idImplementation);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                implementation = generateImplementation(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return implementation;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting algorithm: " + e.getMessage());
        }
    }

    /**
    public List<Algorithm> getRelatedAlgorithms(String parentId) throws Exception {
        
    	List<Algorithm> allAlgorithms = new ArrayList<>();
    	
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE parentId=?;");
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

    public boolean addImplementation(Implementation implementation) throws Exception {
    	Path dir = Paths.get(implementation.implementationFile);
    	System.out.print(dir);
    	try {
        	Stream<Path> list = Files.list(dir);
        	//System.out.print(list);
        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE idImplementation = ?;");
            ps.setString(1, implementation.idImplementation);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Implementation c = generateImplementation(resultSet);
                resultSet.close();
                return false;
            }
            
            List<Path> pathList = list.collect(Collectors.toList());
            for (Path path : pathList) {
			File file = path.toFile();
			String fileName = file.getName();
			long fileLength = file.length();
			long fileLengthInKb=fileLength/1024;
			String lngth=String.valueOf(fileLengthInKb);
            
            ps = conn.prepareStatement("INSERT INTO " + tblName + " (idImplementation,algorithmId,implementationName,implementationContent,"
            		+ "implementation_size_kb) values(?,?,?,?,?);");
            ps.setString(1,  implementation.idImplementation);
            ps.setString(2,  implementation.algorithmId);
            ps.setString(3,  fileName.substring(fileName.lastIndexOf(".")+1));
            ps.setCharacterStream(4, new FileReader(file), fileLength);
            ps.setString(5,  lngth);
            ps.execute();}
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
	
    private Implementation generateImplementation(ResultSet resultSet) throws Exception {
        String implementationFile  = resultSet.getString("implementationFile");
        String idImplementation = resultSet.getString("idImplementation");
        String algorithmId = resultSet.getString("algorithmId");
        return new Implementation (implementationFile, idImplementation, algorithmId);
    }
}
