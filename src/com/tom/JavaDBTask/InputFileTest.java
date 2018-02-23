package com.tom.JavaDBTask;

import static org.junit.Assert.*;

import org.junit.Test;

public class InputFileTest {

	@Test
	public void test() {
		final String strFileName = "data/inputFile.csv";
		InputFile inputFile = new InputFile(strFileName);

		assertTrue("Loading input file" + strFileName, inputFile.load());
		assertTrue("inputFile 11. header should be \"OrderDate\"", inputFile.getContent().getHeader().get(11).equalsIgnoreCase("OrderDate"));
	}

}
