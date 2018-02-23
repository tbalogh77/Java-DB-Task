package com.tom.JavaDBTask;

public class JavaDBTaskMain {

	public static void main(String[] args) {
		System.out.println(Order.processCSVFile("data/inputFile.csv"));
		FTPConnector ftpConnector = new FTPConnector("speedtest.tele2.net", "anonymous", "anonymous");
		ftpConnector.upload(ResponseFile.strFileNameDefault, "upload/responseFile.csv");
	}
}
