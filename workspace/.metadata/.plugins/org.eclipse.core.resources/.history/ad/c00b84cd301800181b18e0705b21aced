package com.tom.JavaDBTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnector {

	public static boolean connect() {
		Properties props = new PropsFile().getProperties();

		if (null == props) {
			System.out.println("Could not load properties file");
			return false;
		}

		try {
			String strUser = props.getProperty(PropsFile.strUser);
			String strPass = props.getProperty(PropsFile.strPassword);
			String strUrl = props.getProperty(PropsFile.strUrl);
			System.out.println("Connecting to database " + strUrl + " as user \"" + strUser + "\"");
			Connection connection = DriverManager.getConnection(strUrl, strUser, strPass);
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return true;
	}

}
