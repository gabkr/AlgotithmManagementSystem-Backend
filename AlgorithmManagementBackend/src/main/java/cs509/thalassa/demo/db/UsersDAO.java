package cs509.thalassa.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import cs509.thalassa.demo.model.Algorithm;
import cs509.thalassa.demo.model.Classification;
import cs509.thalassa.demo.model.User;

public class UsersDAO {
	java.sql.Connection conn;
	LambdaLogger logger;
	final String tblName = "User";
	
	public UsersDAO(LambdaLogger logger) {
    	this.logger = logger;
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
public List<User> listUsers(Integer limit) throws Exception {
        
    	List<User> userList = new ArrayList<>();
    	
        try {
        	PreparedStatement ps;
        	if (limit != null) {
                ps = conn.prepareStatement("SELECT * FROM " + tblName + " LIMIT ?;");
                ps.setInt(1,  limit);        		
        	} else {
                ps = conn.prepareStatement("SELECT * FROM " + tblName);
        	}

        	ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
            	User c = generateUser(resultSet);
                userList.add(c);
            }
            resultSet.close();
            ps.close();
            
            return userList;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting classification: " + e.getMessage());
        }
    }

public User getUser(String id) throws Exception {
    
    try {
        User user = null;
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE id=?;");
        ps.setString(1,  id);
        ResultSet resultSet = ps.executeQuery();
        
        while (resultSet.next()) {
            user = generateUser(resultSet);
        }
        resultSet.close();
        ps.close();
        
        return user;

    } catch (Exception e) {
    	e.printStackTrace();
        throw new Exception("Failed in getting user: " + e.getMessage());
    }
}

public boolean addUser(User user) throws Exception {
    try {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName + " (id, name, email, username) values(?,?,?,?);");
        ps.setString(1,  user.id);
        ps.setString(2,  user.name);
        ps.setString(3,  user.email);
        ps.setString(3,  user.username);
        ps.execute();
        return true;

    } catch (Exception e) {
        throw new Exception("Failed to insert algorithm: " + e.getMessage());
    }
}

private User generateUser(ResultSet resultSet) throws Exception {
    String name  = resultSet.getString("name");
    String id = resultSet.getString("id");
    String email = resultSet.getString("email");
    String username = resultSet.getString("username");

    return new User(id, name, username, email);
}
	
}
