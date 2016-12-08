package com.project.user.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.user.property.Text;


public class TextDAO
{
	public static Text insert(Text text) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ConnectionDAO conn= new ConnectionDAO();
		int id;
		String query = " insert into user_text (userText)"
                + " values (?)";
		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);		
		preparedStmt.setString (1, text.getUserPost());
		// execute the preparedstatement
		preparedStmt.execute();
		ResultSet rs = preparedStmt.getGeneratedKeys();
	    rs.next();
	    id = rs.getInt(1);
	    text.setUserTxtId(id);
		conn.closeConnection();
		return text;
	}		
}