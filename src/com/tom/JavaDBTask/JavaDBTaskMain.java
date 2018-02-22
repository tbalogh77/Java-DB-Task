package com.tom.JavaDBTask;

public class JavaDBTaskMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		InputFile inputFile = new InputFile("data/inputFile.csv");
		inputFile.store();
		inputFile.load();
		
		ResponseFile responseFile = new ResponseFile("data/responseFile.csv");
		responseFile.store();
		responseFile.load();
		
		PropsFile propsFile = new PropsFile();
		//propsFile.store();
		//System.out.println(propsFile.getProperties().getProperty("dbuser"));
		MySQLConnector.connect();
		
		FTPConnector ftpConnector = new FTPConnector("speedtest.tele2.net", "anonymous", "anonymous");
		ftpConnector.upload("data/inputFile.csv", "upload/inputFile.csv");
		//ftpConnector.upload();
	}

}
