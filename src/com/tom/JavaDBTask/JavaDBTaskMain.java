package com.tom.JavaDBTask;

public class JavaDBTaskMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*InputFile inputFile = new InputFile("data/inputFile.csv");
		inputFile.store();
		inputFile.load();*/

		/*InputFile inputFile = new InputFile("data/inputFileOK.csv");
		inputFile.load();*/
		
		/*Order order = new Order();
		String strError = order.fromCSV(inputFile.getContent());
		System.out.println( "inputFile " +  (( null == strError ? "valid" : "invalid: ")) + ((null != strError) ? strError : "" ));*/
		
		
		/*ResponseFile responseFile = new ResponseFile("data/responseFile.csv");
		responseFile.addContent(132, true, null);
		responseFile.store();
		responseFile.load();*/
		
		PropsFile propsFile = new PropsFile();
		//propsFile.store();
		MySQLConnector.connect();

		System.out.println(Order.processCSVFile("data/inputFile.csv"));
		FTPConnector ftpConnector = new FTPConnector("speedtest.tele2.net", "anonymous", "anonymous");
		ftpConnector.upload(ResponseFile.strFileNameDefault, "upload/responseFile.csv");
	}
}
