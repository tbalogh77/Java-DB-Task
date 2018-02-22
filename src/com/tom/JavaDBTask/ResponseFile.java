package com.tom.JavaDBTask;

import java.util.List;
import java.util.Vector;

public class ResponseFile extends CSVFile {
	private static final String straResponseHeaders[] = {
		"LineNumber",
		"Status",
		"Message"
	};
	public ResponseFile(String strFileName) {
		super(strFileName, straResponseHeaders);
	}
	public void addContent(int nLineNumber, boolean bStatus, String strMessageOrNULL) {
		List<Object> lstContent = new Vector<Object>();
		lstContent.add(new Integer(nLineNumber));
		lstContent.add(bStatus ? "OK" : "ERROR");
		lstContent.add(strMessageOrNULL != null ? strMessageOrNULL : "");
		m_Content.addObjects(lstContent);
	}
}
