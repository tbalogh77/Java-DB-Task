package com.tom.JavaDBTask;

import java.util.List;
import java.util.Vector;

public class CSVContent {

	protected List<String> m_lstHeader = new Vector<String>();

	public List<String> getHeader() {
		return m_lstHeader;
	}

	protected List<List<String>> m_lstLines = new Vector<List<String>>();

	public List<List<String>> getLines() {
		return m_lstLines;
	}
	public void createHeader(String[] straHeaders) {
		if (null == straHeaders)
			return;
		m_lstHeader.clear();
		for (String str : straHeaders) {
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
				System.out.println("Invalid CSV header: " + m_lstHeader.get(i)
						+ " != " + lstHeader.get(i));
				return false;
			}
		}
		return true;
	}

	public boolean addValidateHeader(List<String> lstHeader) {
		if (0 == m_lstHeader.size()) {
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
		if (m_lstHeader.size() < lstLine.size())
			return;
		m_lstLines.add(lstLine);
	}

	public void addObjects(List<Object> lstObj) {
		List<String> lstLine = new Vector<String>();
		for (Object obj : lstObj)
			lstLine.add(obj.toString());
		addLine(lstLine);
	}

	public void printContent(String strMsg) {
		if (null != strMsg)
			System.out.println(strMsg);

		System.out.println(getHeader().toString());
		for (List<String> item : getLines())
			System.out.println(item.toString());
		System.out.println("----------------------------------");
	}
}
