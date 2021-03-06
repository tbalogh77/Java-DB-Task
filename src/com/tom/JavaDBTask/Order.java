package com.tom.JavaDBTask;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.lang.Integer;
import java.lang.Float;

import javax.mail.internet.*;

public class Order {

	private final static DateFormat m_dfFormat = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.ENGLISH);

	public enum OrderStatus {
		IN_STOCK(0, "IN_STOCK"), OUT_OF_STOCK(1, "OUT_OF_STOCK");

		public int m_nValue;
		public String m_strName;

		private OrderStatus(int nValue, String strName) {
			m_nValue = nValue;
			m_strName = strName;
		}
		
		public String toString() { 
		    return m_strName;
		} 
	}

	public int 			m_nLineNumber 		= 0;
	public int 			m_nOrderItemId 		= 0;
	public int 			m_nOrderId 			= 0;
	public String 		m_strBuyerName 		= "";
	public String 		m_strBuyerEmail 	= "";
	public Date 		m_datOrderDate 		= new Date();
	public float 		m_fOrderTotalValue	= 0.0f;
	public String 		m_strAddress 		= "";
	public int 			m_nPostCode 		= 0;

	public float 		m_fSalePrice 			= 0.0f;
	public float 		m_fShippingPrice 		= 0.0f;
	public String 		m_strSKU 				= "";
	public OrderStatus 	m_eStatus 				= OrderStatus.OUT_OF_STOCK;

	public Order() {
	}

	private static int parseInt(String str) {
		int nRetVal = -1;
		try {
			nRetVal = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
		}
		return nRetVal;
	}

	private static float parseFloat(String str) {
		float fRetVal = -1.0f;
		try {
			fRetVal = Float.parseFloat(str);
		} catch (NumberFormatException nfe) {
		}
		return fRetVal;
	}

	public static boolean validateEmailAddress(String strEmail) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(strEmail);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	public static String Error(int nLineNumber, String strMessage) {
		String strError = "Error Line "
				+ (0 == nLineNumber ? "Header" : nLineNumber) + ": "
				+ strMessage;
		ResponseFile.createFile(nLineNumber, false, strError);
		return strError;
	}

	public static String processCSVFile(String strCSVFileName) {
		final int nHeaders = InputFile.straInputHeaders.length;
		InputFile inputFile = new InputFile(strCSVFileName);

		if (!inputFile.load())
			return Error(0, "Could not load inputFile" + strCSVFileName);

		CSVContent csvContent = inputFile.getContent();
		List<String> lstHeader = csvContent.getHeader();
		if (null == lstHeader || nHeaders != lstHeader.size())
			return Error(0, "Invalid Header");

		List<List<String>> lstLines = csvContent.getLines();
		// /CSV file has header but does not have any more lines
		int nLines = lstLines.size();
		if (0 == nLines)
			return Error(0, "No Content");

		for (int nIndex = 0; nIndex < nLines; nIndex++) {
			List<String> lstLine = csvContent.getLines().get(nIndex);
			final int nActLineNumber = nIndex + 1;
			// /Last Item "OrderDate" can be missing
			if (lstLine.size() < nHeaders - 1)
				return Error(nIndex, "Short Content");

			Order order = new Order();
			String strError = order.fromCSV(nActLineNumber, lstHeader, lstLine);
			if (null == strError) {
				// /Have a valid Order object
				if (null != (strError = order.dbInsertItem()))
					return Error(nActLineNumber, strError);
			} else
				return Error(nActLineNumber, strError);
		}

		ResponseFile.createFile(0, true, null);
		return "OK";
	}

	public String fromCSV(int nLineNumber, List<String> lstHeader,
			List<String> lstLine) {
		final String strErrorPosInt = " must be an Integer > 0";
		int nLineSize = lstLine.size();
		for (int nIndex = 0; nIndex < nLineSize; nIndex++) {

			String strItem = lstLine.get(nIndex);
			if (nIndex < nLineSize - 1 && 0 == strItem.length())
				return "No field can be empty apart from OrderDate";

			switch (nIndex) {
			case 0:
				if ((m_nLineNumber = parseInt(strItem)) < 0)
					return "LineNumber" + strErrorPosInt;
				if (m_nLineNumber != nLineNumber)
					return "Line number does not match";
				break;

			case 1:
				// /Todo: check if OrderItemId is already in db (error if true)
				if ((m_nOrderItemId = parseInt(strItem)) < 0)
					return "OrderItemId" + strErrorPosInt;
				break;
			case 2:
				// /Todo: check if OrderId is already in db (error if true)
				if ((m_nOrderId = parseInt(strItem)) < 0)
					return "OrderId" + strErrorPosInt;
				break;
			case 3:
				m_strBuyerName = strItem;
				break;
			case 4:
				m_strBuyerEmail = strItem;
				if (!validateEmailAddress(m_strBuyerEmail))
					return "Invalid email address";
				break;
			case 5:
				m_strAddress = strItem;
				break;
			case 6:
				if ((m_nPostCode = parseInt(strItem)) < 0)
					return "PostCode" + strErrorPosInt;
				break;
			case 7:
				if ((m_fSalePrice = parseFloat(strItem)) < 1.0f)
					return "Sale price must be >= 1.0f";
				break;
			case 8:
				if ((m_fShippingPrice = parseFloat(strItem)) < 0.0f)
					return "Shipping price must be >= 0.0f";
				break;
			case 9:
				m_strSKU = strItem;
				break;
			case 10:
				boolean bValid = false;
				for (OrderStatus orderStatus : OrderStatus.values()) {
					if (strItem.equalsIgnoreCase(orderStatus.toString())) {
						m_eStatus = orderStatus;
						bValid = true;
						break;
					}
				}
				if (!bValid)
					return "Status must be IN_STOCK or OUT_OF_STOCK";
				break;
			case 11:
				if (0 == strItem.length())
					break;

				try {
					m_datOrderDate = m_dfFormat.parse(strItem);
				} catch (ParseException pe) {
					return "Expected Date format is yyyy-MM-dd";
				}
				break;

			default:
				break;
			}
		}
		return null;
	}

	private String dbExecCommand(String strSQLCmd) {
		MySQLConnector mySQLConnector = new MySQLConnector();
		if (mySQLConnector.execUpdateSQLCommand(strSQLCmd) < 0)
			return "DB error: " + mySQLConnector.getLastErrorString();
		return null;
	}

	private String dbInsertOrder() {
		m_fOrderTotalValue = m_fSalePrice + m_fShippingPrice;
		final String strSQLCmd = "INSERT INTO Orders.order "
				+ "( OrderId,BuyerName,BuyerEmail,OrderDate,OrderTotalValue,Address,Postcode) "
				+ "VALUES (" + m_nOrderId + ", \"" + m_strBuyerName + "\", \""
				+ m_strBuyerEmail + "\", \"" + m_dfFormat.format(m_datOrderDate)
				+ "\", " + m_fOrderTotalValue + ", \"" + m_strAddress + "\", "
				+ m_nPostCode + "  );";

		if (!dbHaveTableWithId("Orders.order", "OrderId", m_nOrderId)) return dbExecCommand(strSQLCmd); 
		return null;
	}

	private String dbInsertOrderItem() {
		float fTotalPrice = m_fSalePrice + m_fShippingPrice;
		final String strSQLCmd = "INSERT INTO Orders.order_item ( OrderItemId,OrderId,SalePrice,ShippingPrice,TotalItemPrice,SKU,Status ) "
				+ "VALUES (" + m_nOrderItemId + "," + m_nOrderId + "," + m_fSalePrice + "," + m_fShippingPrice
				+ "," + fTotalPrice	+ ",\"SKU\",\""	+ m_eStatus.m_strName + "\") ;";
		if (dbHaveTableWithId("Orders.order_item", "OrderItemId", m_nOrderItemId))
			return "OrderItemId already exists in DataBase";
		else
			return dbExecCommand(strSQLCmd);
	}

	private boolean dbHaveTableWithId(String strTable, String strIdName, int nId) {
		final String strSQLCmd = "SELECT * FROM " + strTable + " WHERE "
				+ strIdName + " = " + nId + ";";
		MySQLConnector mySQLConnector = new MySQLConnector();
		return 0 < mySQLConnector.countRawsOfSelect(strSQLCmd);
	}
	private String dbUpdateOrderTotalValue( int nOrderId) {
		final String strSQLCmd = "UPDATE Orders.order SET OrderTotalValue = (SELECT  SUM(TotalItemPrice) FROM Orders.order_item WHERE OrderId = "+nOrderId+") WHERE OrderId = "+nOrderId+";";
		return dbExecCommand(strSQLCmd);
	}
	private String dbInsertItem() {
		String strError = null;
		if (null != (strError = dbInsertOrder()) || null != (strError = dbInsertOrderItem())) return strError;
		return dbUpdateOrderTotalValue(m_nOrderId);
	}
}
