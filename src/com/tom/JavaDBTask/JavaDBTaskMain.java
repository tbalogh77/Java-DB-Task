package com.tom.JavaDBTask;

public class JavaDBTaskMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		InputFile inputFile = new InputFile("data/a.csv");
		inputFile.store();
		inputFile.load();
		
		
		PropsFile propsFile = new PropsFile();
		//propsFile.store();
		//System.out.println(propsFile.getProperties().getProperty("dbuser"));
		MySQLConnector.connect();
	}

}
