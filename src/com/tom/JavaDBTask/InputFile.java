package com.tom.JavaDBTask;

import java.util.List;
import java.util.Vector;

///TODO: kill'em all unused
public class InputFile extends CSVFile {

	// /Sequence is important here!!!
	public static final String straInputHeaders[] = { "LineNumber",
			"OrderItemId", "OrderId", "BuyerName", "BuyerEmail", "Address",
			"PostCode", "SalePrice", "ShippingPrice", "SKU", "Status",
			"OrderDate" };

	public InputFile(String strFileName) {
		super(strFileName, straInputHeaders);
	}
}
