/**
 * 
 */
package com.tom.JavaDBTask;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author t
 *
 */
public class FTPConnectorTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.tom.JavaDBTask.FTPConnector#upload(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpload() {
		
		
		FTPConnector ftpConnector = new FTPConnector("speedtest.tele2.net", "anonymous", "anonymous");
		boolean bSuccess = ftpConnector.upload("data/inputFile.csv"/*ResponseFile.strFileNameDefault*/, "upload/inputFile.csv");
		
		assertTrue("FTPConnector.upload", bSuccess);

	}

}
