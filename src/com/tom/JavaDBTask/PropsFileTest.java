/**
 * 
 */
package com.tom.JavaDBTask;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author t
 *
 */
public class PropsFileTest {

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
	 * Test method for {@link com.tom.JavaDBTask.PropsFile#getProperties()}.
	 */
	@Test
	public void testGetProperties() {
		Properties props = new PropsFile().getProperties();
		assertNotNull(props);
	}

	/**
	 * Test method for {@link com.tom.JavaDBTask.PropsFile#store()}.
	 */
	@Test
	public void testStore() {
		PropsFile propsFile = new PropsFile();
		assertTrue("PropsFile.store()", propsFile.store());
	}

	/**
	 * Test method for {@link com.tom.JavaDBTask.PropsFile#load()}.
	 */
	@Test
	public void testLoad() {
		PropsFile propsFile = new PropsFile();
		assertTrue("ATTENTION: load contains explicite store", propsFile.load());
	}

}
