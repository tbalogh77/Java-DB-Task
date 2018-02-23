package com.tom.JavaDBTask;

import java.util.List;
import java.util.Vector;

public class ResponseFile extends CSVFile {
	public static final String strFileNameDefault = "data/responseFile.csv";
	private static final String straResponseHeaders[] = {
		"LineNumber",
		"Status",
		"Message"
	};
	public ResponseFile() {
		super(strFileNameDefault, straResponseHeaders);
	}
	public void addContent(int nLineNumber, boolean bStatus, String strMessageOrNULL) {
		List<Object> lstContent = new Vector<Object>();
		lstContent.add(new Integer(nLineNumber));
		lstContent.add(bStatus ? "OK" : "ERROR");
		lstContent.add(strMessageOrNULL != null ? strMessageOrNULL : "");
		m_Content.addObjects(lstContent);
	}

	public static void createFile(int nLineNumber, boolean bStatus, String strMessageOrNULL) {
		ResponseFile responseFile = new ResponseFile();
		responseFile.addContent(nLineNumber, bStatus, strMessageOrNULL);
		responseFile.store();
	}
}
