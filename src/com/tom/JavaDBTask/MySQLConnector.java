package com.tom.JavaDBTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnector {

	public static void connect() {
		
		String url = "jdbc:mysql://localhost:3306/Orders";
		String username = "Abigel";
		String password = "password";

		System.out.println("Connecting database...");

	    //private Properties properties;

		
		
		try  {
			Connection connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		
		
	}

	
}
