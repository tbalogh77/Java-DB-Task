package com.tom.JavaDBTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnector {

	private String m_strLastErrorString = null;

	public String getLastErrorString() {
		return m_strLastErrorString;
	}

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
			System.out.println("Connecting to database " + strUrl
					+ " as user \"" + strUser + "\"");
			connection = DriverManager.getConnection(strUrl, strUser, strPass);
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return connection;
	}
	public int execUpdateSQLCommand(String strCommand) {
		int nResult = -1;
		m_strLastErrorString = null;
		Connection connection = getConnection();
		if ( null != connection) {
			try {
				Statement statement = connection.createStatement();
				System.out.println(strCommand);
				nResult = statement.executeUpdate(strCommand);
				///System.out.println(" ->" + nResult);
				statement.close();
				connection.close();
				System.out.println("Database connection closed");
			} catch (SQLException sqlException) {
				m_strLastErrorString = sqlException.getMessage();
				System.out.println(sqlException.getMessage());
			} finally {
				System.out.println("Result: " + " ->" + nResult);
			}
		}
		return nResult;
	}
	public int countRawsOfSelect(String strCommand) {
		int nRows = 0;
		m_strLastErrorString = null;
		Connection connection = getConnection();
		if ( null != connection) {
			try {
				Statement statement = connection.createStatement();
				System.out.println(strCommand);
				ResultSet resultSet = statement.executeQuery(strCommand);
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				resultSet.last();
				nRows = resultSet.getRow();
				statement.close();
				connection.close();
				System.out.println("Database connection closed");
			} catch (SQLException sqlException) {
				m_strLastErrorString = sqlException.getMessage();
				System.out.println(sqlException.getMessage());
			}
		}
		return nRows;
	}
}
