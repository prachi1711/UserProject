package com.project.user.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO
{
	private Connection connection;
	
	public ConnectionDAO(){
		
	}
	
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
		return connection;
	}
	
	public void closeConnection() throws SQLException{
		connection.close();
	}
	
}