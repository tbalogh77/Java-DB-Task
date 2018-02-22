package com.tom.JavaDBTask;

import java.util.List;
import java.util.Vector;

public class CSVContent {
	
	///Todo: Lift'em up, shoot'em up
	public static final String straInputHeaders[] = {
		"LineNumber",
		"OrderItemId",
		"OrderId",
		"BuyerName",
		"BuyerEmail",
		"Address",
		"PostCode",
		"SalePrice",
		"ShippingPrice",
		"SKU",
		"Status",
		"OrderDate"
	};

	protected List<String> m_lstHeader = new Vector<String>();
	protected List<List<String>> m_lstLines = new Vector<List<String>>();

	public List<List<String>> getContent() {
		if ( null == m_lstHeader || 0 == m_lstHeader.size()) return null; 
		List<List<String>> lstContent = new Vector<List<String>>();
		lstContent.add(m_lstHeader);
		for ( List<String> lstLine : m_lstLines ) lstContent.add(lstLine); 
		return lstContent;
	}

	public void createHeader(String [] straHeaders) {
		if ( null == straHeaders  ) return;
		m_lstHeader.clear();
		for ( String str : straHeaders ) {
			addToHeader(str);
		}
	}


	public void clearHeader() {
		m_lstHeader.clear();
	}
	public void clearData() {
		m_lstLines.clear();
	}
	public void clear() {
		clearHeader();
		clearData();
	}
	public boolean validateHeader(List<String> lstHeader) {
		if (m_lstHeader.size() != lstHeader.size())
			return false;
		for (int i = 0; i < m_lstHeader.size(); i++) {
			if (!m_lstHeader.get(i).equalsIgnoreCase(lstHeader.get(i))) {
				System.out.println("Invalid CSV header: " + m_lstHeader.get(i) + " != " + lstHeader.get(i));
				return false;
			}
		}
		return true;
	}
	public boolean addValidateHeader(List<String> lstHeader) {
		if ( 0 == m_lstHeader.size() ) {
			m_lstHeader = lstHeader;
			return true;
		} else {
			return validateHeader(lstHeader);
		}
	}
	public void addToHeader(String str) {
		m_lstHeader.add(str);
	}
	public void addLine(List<String> lstLine) {
		if ( m_lstHeader.size() < lstLine.size()) return;
		m_lstLines.add(lstLine);
	}
	public void addObjects(List<Object> lstObj) {
		List<String> lstLine = new Vector<String>();
		for ( Object obj : lstObj ) lstLine.add(obj.toString());
		addLine(lstLine);
	}

	
	public void printContent(String strMsg) {
		if (null != strMsg) System.out.println(strMsg);
		List<List<String>> lstContent = getContent();
		if (null != lstContent) for (List<String> item : lstContent) System.out.println(item.toString());
		System.out.println("----------------------------------");
	}
}
