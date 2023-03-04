package com.medexpert.util;

import com.medexpert.entity.Test;

public interface ITestFactory {
	public Test createTest(String[] values);
}
