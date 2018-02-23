package com.tom.JavaDBTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnector {

	public Connection getConnection() {
		Connection connection = null;
		Properties props = new PropsFile().getProperties();
		if (null == props) {
			System.out.println("Could not load properties file");
			return connection;
		}
		try {
			String strUser = props.getProperty(PropsFile.strUser);
			String strPass = props.getProperty(PropsFile.strPassword);
			String strUrl = props.getProperty(PropsFile.strUrl);
			System.out.println("Connecting to database " + strUrl + " as user \"" + strUser + "\"");
			connection = DriverManager.getConnection(strUrl, strUser, strPass);
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return connection;
	}
	/*public void selectAllFromOrder() {
		final String strSelect = "SELECT * FROM Orders.order ;";
		Connection connection = getConnection();
		if ( null != connection) {
			try {
				Statement statement = connection.createStatement();
				System.out.println(strSelect);
				if(statement.execute(strSelect)) {
					ResultSet resultSet = statement.getResultSet();
					System.out.println(resultSet.toString());
					
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					int columnsNumber = resultSetMetaData.getColumnCount();
					while (resultSet.next()) {
					    for (int i = 1; i <= columnsNumber; i++) {
					        if (i > 1) System.out.print(",  ");
					        String columnValue = resultSet.getString(i);
					        System.out.print(columnValue + " " + resultSetMetaData.getColumnName(i));
					    }
					    System.out.println("");
					}
				}
				statement.close();
				connection.close();
				System.out.println("Database connection closed");
			} catch (SQLException sqlException) {
				
			}
		}
	}*/
	public int executeSQLCommand(String strCommand) {
		Connection connection = getConnection();
		int nLastInsertId = 0;
		if ( null != connection) {
			try {
				Statement statement = connection.createStatement();
				int nResult = statement.executeUpdate(strCommand);
				System.out.println(strCommand + " ->" + nResult);
				
				
				statement = connection.createStatement();
				statement.execute("SELECT LAST_INSERT_ID() ;");
				ResultSet resultSet = statement.getResultSet();
				System.out.println(resultSet.toString());
				
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int columnsNumber = resultSetMetaData.getColumnCount();
				
				resultSet.next();
				nLastInsertId = Integer.parseInt(resultSet.getString(1));
				//statement.close();
				connection.close();
				System.out.println("Database connection closed");
			} catch (SQLException sqlException) {
				System.out.println(sqlException.getMessage());
			}
		}
		return nLastInsertId;
	}
}
