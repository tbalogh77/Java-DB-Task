/**
 * 
 */
package com.tom.JavaDBTask;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MySQLConnectorTest {
	@Test
	public void test() {
		MySQLConnector mySQLConnector = new MySQLConnector();
		assertNotNull(mySQLConnector.getConnection());
	}

}
