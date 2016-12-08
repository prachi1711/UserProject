package com.project.user.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.user.property.Text;


public class TextDAO
{
	public static void insert(Text text) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ConnectionDAO conn= new ConnectionDAO();
		String query = " insert into user_text (userText)"
                + " values (?)";
		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = conn.getConnection().prepareStatement(query);
		preparedStmt.setString (1, text.getUserPost());
		// execute the preparedstatement
		preparedStmt.execute();
		conn.closeConnection();
	}		
}