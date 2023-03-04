package com.medexpert.util;

import com.medexpert.entity.Test;

public class TestFactory implements ITestFactory {

	private static TestFactory instance;
	
	
	private TestFactory() {}
	
	
	public static TestFactory getInstance() {
		synchronized (TestFactory.class) {
			if (instance == null) {
				instance = new TestFactory();
			}
		}
		return instance;
	}
	
	
	@Override
	public Test createTest(String[] values) {
		if (values != null || values.length != 0) {
			
			String name = values[TestsFileDefinition.NAME.getColIndex()];
			double price = Double.parseDouble(values[TestsFileDefinition.PRICE.getColIndex()]);
			String unit = values[TestsFileDefinition.UNIT.getColIndex()];
			double minValue = Double.parseDouble(values[TestsFileDefinition.MIN_VALUE.getColIndex()]);
			double maxValue = Double.parseDouble(values[TestsFileDefinition.MAX_VALUE.getColIndex()]);

			return new Test(name, price, unit, minValue, maxValue);
		}
		else {
			return null;
		}
	}
}
