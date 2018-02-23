package com.tom.JavaDBTask;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderTest {

	@Test
	public void test() {
		Order order = new Order();
		assertTrue("Trying out whole process", Order.processCSVFile("data/inputFile.csv").equalsIgnoreCase("OK") );
		
		ResponseFile responseFile = new ResponseFile();
		responseFile.load();
		assertTrue("Validateing responseFile", responseFile.getContent().getLines().get(0).get(1).equalsIgnoreCase("OK"));
	}

}
