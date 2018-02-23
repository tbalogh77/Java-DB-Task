package com.tom.JavaDBTask;

import static org.junit.Assert.*;

import org.junit.Test;

public class CSVFileTest {

	@Test
	public void test() {
		CSVFile csvFile = new CSVFile("data/inputFile.csv", null);
		assertTrue(csvFile.load());
		assertNotNull("Test if CSVFile have real content", csvFile.getContent());
		assertTrue("inputFile should begin with \"LineNumber\"", csvFile.getContent().getHeader().get(0).equalsIgnoreCase("LineNumber"));
	}

}
