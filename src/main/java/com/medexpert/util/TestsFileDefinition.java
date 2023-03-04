package com.medexpert.util;

public enum TestsFileDefinition {

	NAME(0, "name"),
	PRICE(1, "price"),
	UNIT(2, "unit"),
	MIN_VALUE(3, "min_value"),
	MAX_VALUE(4, "max_value");
	
	private int colIndex;
	private String colName;
	
	private TestsFileDefinition(int colIndex, String colName) {
		this.colIndex = colIndex;
		this.colName = colName;
	}

	public int getColIndex() {
		return colIndex;
	}
	
	public String getColName() {
		return colName;
	}
}
