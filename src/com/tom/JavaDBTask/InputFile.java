package com.tom.JavaDBTask;

import java.util.List;
import java.util.Vector;

public class InputFile extends CSVFile {
	private static final List<String> m_lstHeader;
	static {
		m_lstHeader = new Vector<String>();
		m_lstHeader.add("LineNumber");
		m_lstHeader.add("OrderItemId");
		m_lstHeader.add("OrderId");
		m_lstHeader.add("BuyerName");
		m_lstHeader.add("BuyerEmail");
		m_lstHeader.add("Address");
		m_lstHeader.add("PostCode");
		m_lstHeader.add("SalePrice");
		m_lstHeader.add("ShippingPrice");
		m_lstHeader.add("SKU");
		m_lstHeader.add("Status");
		m_lstHeader.add("OrderDate");
	}

	public InputFile(String strFileName) {
		super(strFileName);
	}

	/*
	 * public List<String> getValues() { Vector<String> values = new
	 * Vector<String>(); values.add("Tom"); values.add(Integer.toString(42));
	 * values.add(Float.toString(3.14f)); //
	 * values.add(astrologicalSign.toString()); return values; }
	 */
	/*private void construct(List<String> values) {

		// String _name = values.get(0);
		// int _age = Integer.parseInt(values.get(1));
		// float _shoeSize = Float.parseFloat(values.get(2));
		// CustomType _aSign = new CustomType(values.get(3));

		int _LineNumber = Integer.parseInt(values.get(0));

		// return new MyClass(_name, _age, _shoeSize, _aSign);
	}*/
	private boolean validateHeader(List<String> header) {
		if (m_lstHeader.size() != header.size())
			return false;
		for (int i = 0; i < m_lstHeader.size(); i++) {
			if (!m_lstHeader.get(i).equalsIgnoreCase(header.get(i))) {
				System.out.println("Invalid CSV file " + m_strFileName);
				return false;
			}
		}
		System.out.println("CSV file " + m_strFileName
				+ " validated successfully");
		return true;
	}

	public boolean load() {
		m_lstHeader.clear();
		if (!super.load())
			return false;

		if (!validateHeader(m_lstLines.get(0))) return false;
	    /*values = parseLine(reader);*/

		
		return true;
	}

	public boolean store() {
		m_lstLines.add(m_lstHeader);
		return super.store();
	}
}
