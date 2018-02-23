package com.tom.JavaDBTask;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.Writer;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
public class CSVFile {
	private static final int EOL = '\n';
	private static final int TAB = '\r';
	private static final int QUOT = '\"';
	private static final int COMMA = ',';
	
	protected final String m_strFileName;
	protected CSVContent m_Content = new CSVContent();
	public CSVContent getContent() { return m_Content;}

	public CSVFile(String strFileName, String [] straHeaders) {
		m_strFileName = strFileName;
	 	m_Content.createHeader(straHeaders);
	}
	private void writeLine(Writer writer, List<String> values) throws IOException {
		///TODO: improve this primitive, intuitive, beautiful writer, parser
		boolean firstVal = true;
		for (String str : values) {
			if (!firstVal) {
				writer.write(COMMA);
			}
			writer.write(QUOT);
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if (ch == QUOT) {
					writer.write(QUOT);
				}
				writer.write(ch);
			}
			writer.write(QUOT);
			firstVal = false;
		}
		writer.write(EOL);
	}
	private List<String> parseLine(Reader reader) throws IOException {
		///TODO: improve this primitive, intuitive, beautiful writer, parser
		int ch = reader.read();
		while (ch == TAB) {
			ch = reader.read();
		}
		if (ch < 0) {
			return null;
		}
		Vector<String> store = new Vector<String>();
		StringBuffer curVal = new StringBuffer();
		boolean inquotes = false;
		boolean started = false;
		while (ch >= 0) {
			if (inquotes) {
				started = true;
				if (ch == QUOT) {
					inquotes = false;
				} else {
					curVal.append((char) ch);
				}
			} else {
				if (ch == QUOT) {
					inquotes = true;
					if (started) {
						curVal.append(QUOT);
					}
				} else if (ch == COMMA) {
					store.add(curVal.toString());
					curVal = new StringBuffer();
					started = false;
				} else if (ch == TAB) {
				} else if (ch == EOL) {
					break;
				} else {
					curVal.append((char) ch);
				}
			}
			ch = reader.read();
		}
		store.add(curVal.toString());
		return store;
	}
	public boolean store() {
		///TODO: some more error check needed: Existence of path in m_strFileName 4xmpl
		OutputStream stream = null;

		try {
			stream = new FileOutputStream(m_strFileName);
			Writer writer = new OutputStreamWriter(stream, "UTF-8");

			/*List<List<String>> lstSCVContent = m_Content.getContent();
			if ( null == lstSCVContent ) {
				System.out.println("Will not store empty CSV file " + m_strFileName);
				return false;
			}*/
			writeLine(writer, m_Content.getHeader());
			for (List<String> item : m_Content.getLines()) {
				writeLine(writer, item);
			}
			writer.flush();
			writer.close();
		} catch (IOException io) {
			io.printStackTrace();
			return false;
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}

		}
		System.out.println("CSV file " + m_strFileName + " stored");
		return true;
	}

	public boolean load() {
		InputStream stream = null;
		m_Content.clearData();
		System.out.println("Loading CSV file " + m_strFileName);

		try {
			stream = new FileInputStream(m_strFileName);
			Reader reader = new InputStreamReader(stream, "UTF-8");
			List<String> lstLine = null;

			int nLines = 0;
			while ((lstLine = parseLine(reader)) != null) {
				if (0 == nLines++) {
					if (m_Content.addValidateHeader(lstLine)) {
						System.out.println("CSV file " + m_strFileName
								+ " validated successfully");
					} else {
						return false;
					}
				} else {
					System.out.println(lstLine);					
					m_Content.addLine(lstLine);
				}
			}
			m_Content.printContent(nLines + " csv lines parsed");
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
}