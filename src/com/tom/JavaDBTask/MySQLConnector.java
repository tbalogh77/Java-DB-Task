package com.tom.JavaDBTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnector {

	public static boolean connect() {
		System.out.println("Connecting database...");

		Properties props = new PropsFile().getProperties();

		if (null == props) {
			System.out.println("Could not load properties file");
			return false;
		}

		try {
			Connection connection = DriverManager.getConnection(
					props.getProperty("dburl"), props.getProperty("dbuser"),
					props.getProperty("dbpassword")
			);
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		System.out.println("Connected to database " + props.getProperty("dburl") + " as user " + props.getProperty("dbuser"));
		return true;
	}

}
