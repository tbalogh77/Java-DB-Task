package com.tom.JavaDBTask;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class CSVContentTest {

	@Test
	public void test() {
		String [] straHeaders1 = {"English", "Ékezetes Magyar", "$@ß$>#&"};
		String [] straHeaders2 = {"Angol", "Ékezetes Magyar", "$@ß$>#&"};

		CSVContent scvContent1 = new CSVContent();
		scvContent1.createHeader(straHeaders1);
		assertTrue("Content header validation with same header",scvContent1.validateHeader(scvContent1.getHeader()));

		CSVContent scvContent2 = new CSVContent();
		scvContent2.createHeader(straHeaders2);
		assertFalse("Content header validation with other header",scvContent1.validateHeader(scvContent2.getHeader()));
		

	}

}
