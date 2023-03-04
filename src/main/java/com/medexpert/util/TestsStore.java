package com.medexpert.util;

import java.util.ArrayList;
import java.util.List;

import com.medexpert.entity.Test;

public class TestsStore {
	
	private static TestsStore instance;
	private List<Test> testsList;
	
	private TestsStore() {
		this.testsList = new ArrayList<>();
	}
	
	
	public static TestsStore getInstance () {
		synchronized (TestsStore.class) {
			if (instance == null) {
				instance = new TestsStore();
			}
		}
		return instance;
	}

	public void addTestToList(Test test) {
		this.testsList.add(test);
	}
	
	public List<Test> getTestsList() {
		return this.testsList;
	}
}
