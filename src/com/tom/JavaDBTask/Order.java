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

	private final static DateFormat m_dfFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	public enum OrderStatus {
		IN_STOCK(0, "IN_STOCK"), OUT_OF_STOCK(1, "OUT_OF_STOCK");

		public int m_nValue;
		public String m_strName;

		private OrderStatus(int nValue, String strName) {
			m_nValue = nValue;
			m_strName = strName;
		}
	}

	public int nLineNumber = 0;
	public int nOrderItemId = 0;
	public int nOrderId = 0;
	public String strBuyerName = "";
	public String strBuyerEmail = "";
	public Date datOrderDate = new Date();
	public float fOrderTotalValue = 0.0f;
	public String strAddress = "";
	public int nPostCode = 0;

	public float fSalePrice = 0.0f;
	public float fShippingPrice = 0.0f;
	public String strSKU = "";
	public OrderStatus eStatus = OrderStatus.OUT_OF_STOCK;

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
			// /Last Item "OrderDate" can be missing
			if (lstLine.size() < nHeaders - 1)
				return Error(nIndex, "Short Content");

			Order order = new Order();
			String strError = order.fromCSV(nIndex + 1, lstHeader, lstLine);
			if (null == strError) {
				// /Have a valid Order object
				order.dbInsertOrder();
				order.dbInsertOrderItem();
			} else
				return Error(nIndex + 1, strError);
		}

		ResponseFile.createFile(0, true, null);
		return "CSVFile " + strCSVFileName + " processed without error";
	}
	public String fromCSV(int nLineNum, List<String> lstHeader,
			List<String> lstLine) {
		final String strErrorPosInt = " must be an Integer > 0";
		int nLineSize = lstLine.size();
		for (int nIndex = 0; nIndex < nLineSize; nIndex++) {

			String strItem = lstLine.get(nIndex);
			if (nIndex < nLineSize - 1 && 0 == strItem.length())
				return "No field can be empty apart from OrderDate";

			switch (nIndex) {
			case 0:
				if ((nLineNumber = parseInt(strItem)) < 0)
					return "LineNumber" + strErrorPosInt;
				if (nLineNumber != nLineNum)
					return "Line number does not match";
				break;

			case 1:
				// /Todo: check if OrderItemId is already in db (error if true)
				if ((nOrderItemId = parseInt(strItem)) < 0)
					return "OrderItemId" + strErrorPosInt;
				break;
			case 2:
				// /Todo: check if OrderId is already in db (error if true)
				if ((nOrderId = parseInt(strItem)) < 0)
					return "OrderId" + strErrorPosInt;
				break;
			case 3:
				strBuyerName = strItem;
				break;
			case 4:
				strBuyerEmail = strItem;
				if (!validateEmailAddress(strBuyerEmail))
					return "Invalid email address";
				break;
			case 5:
				strAddress = strItem;
				break;
			case 6:
				if ((nPostCode = parseInt(strItem)) < 0)
					return "PostCode" + strErrorPosInt;
				break;
			case 7:
				if ((fSalePrice = parseFloat(strItem)) < 1.0f)
					return "Sale price must be >= 1.0f";
				break;
			case 8:
				if ((fShippingPrice = parseFloat(strItem)) < 0.0f)
					return "Shipping price must be >= 0.0f";
				break;
			case 9:
				strSKU = strItem;
				break;
			case 10:
				boolean bValid = false;
				for (OrderStatus orderStatus : OrderStatus.values()) {
					if (strItem.equalsIgnoreCase(orderStatus.m_strName)) {
						eStatus = orderStatus;
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
					datOrderDate = m_dfFormat.parse(strItem);
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
	private void dbInsertOrder() {
		fOrderTotalValue = fSalePrice+fShippingPrice;

		final String strSQLCommand = "INSERT INTO Orders.order "
				+ "( BuyerName,BuyerEmail,OrderDate,OrderTotalValue,Address,Postcode) "
				+ "VALUES (\"" + strBuyerName + "\", \"" + strBuyerEmail
				+ "\", \"" + m_dfFormat.format(datOrderDate) + "\", " + fOrderTotalValue + ", \"" + strAddress
				+ "\", " + nPostCode + "  );";
		
		System.out.println(strSQLCommand);
		MySQLConnector mySQLConnector = new MySQLConnector();
		nOrderId = mySQLConnector.executeSQLCommand(strSQLCommand);
	}
	private void dbInsertOrderItem() {
		float fTotalPrice = fSalePrice+fShippingPrice;
		final String strSQLCommand = "INSERT INTO Orders.order_item ( OrderId,SalePrice,ShippingPrice,TotalItemPrice,SKU,Status ) " +
				"VALUES ("+  nOrderId +","+ fSalePrice +","+fShippingPrice+","+fTotalPrice+",\"SKU\",\""+eStatus.m_strName+"\") ;";

		
		System.out.println(strSQLCommand);
		MySQLConnector mySQLConnector = new MySQLConnector();
		nOrderItemId = mySQLConnector.executeSQLCommand(strSQLCommand);
	}
}