package com.project.user.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.user.model.ConnectionDAO;
import com.project.user.property.Text;
import com.project.user.property.UserText;
import com.project.user.property.User;


public class UserTextDAO
{
	public static void insert(User user) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ConnectionDAO conn= new ConnectionDAO();
		int id = 0;
		String query = " select id from user"
                + " where userName = ?";
		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query);
		preparedStmt.setString(1, user.getUserName());
		// execute the preparedstatement		
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next()) {
			id = rs.getInt("id");
		}
		/* if the user does not exist, then create the user first */
		if (id == 0) {
			query = " insert into user (userName)"
	                + " values (?)";
			// create the mysql insert preparedstatement
			preparedStmt = conn.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, user.getUserName());
			// execute the preparedstatement
			preparedStmt.execute();
			rs = preparedStmt.getGeneratedKeys();
		    rs.next();
		    id = rs.getInt(1);
		}
					
	    query = " insert into user_text (user_id, userText)"
                + " values (?, ?)";
		// create the mysql insert preparedstatement
		preparedStmt = conn.getConnection().prepareStatement(query);
		preparedStmt.setInt(1, id);
		preparedStmt.setString(2, user.getUserPost().get(0));
		preparedStmt.execute();
		conn.closeConnection();
	}
	
	public static UserText select(String userName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ConnectionDAO conn= new ConnectionDAO();
		UserText userTxt = new UserText();
		ArrayList<Text> posts = new ArrayList<Text>();
		Text txt = null;
		int id = 0;
		String query = " select id from user"
                + " where userName = ?";
		// create the mysql insert preparedstatement
				
		PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query);
		preparedStmt.setString(1, userName);
		// execute the preparedstatement		
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next()) {
			id = rs.getInt("id");
		}
		
		if (id > 0) {
			query = " select * from user_text "
	                + " where user_id = ?";
			// create the mysql insert preparedstatement
			preparedStmt = conn.getConnection().prepareStatement(query);
			preparedStmt.setInt(1, id);
			// execute the preparedstatement
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				if(rs.getString("userText")!= null && !rs.getString("userText").equalsIgnoreCase("")) {
					txt = new Text();
					txt.setUserTxtId(rs.getInt("id"));
					txt.setUserPost(rs.getString("userText"));
					posts.add(txt);
				}									
			}		   
		}			
		userTxt.setUserPost(posts);
		userTxt.setUserName(userName);
		userTxt.setUserId(id);
		conn.closeConnection();
		return userTxt;
	} 
}