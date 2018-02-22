package com.tom.JavaDBTask;

import java.util.Vector;

public class ResponseFile extends CSVFile {
	public ResponseFile(String strFileName) {
		super(strFileName);
		// TODO Auto-generated constructor stub
	}
	protected void createHeader() {
		m_lstHeader = new Vector<String>();
		m_lstHeader.add("LineNumber");
		m_lstHeader.add("Status");
		m_lstHeader.add("Message");
	}
}
