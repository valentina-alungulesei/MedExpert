package com.medexpert.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.medexpert.entity.Test;

public class TestsDataReader {
	
	private static TestsDataReader instance;
	
	
	private TestsDataReader() {}
	
	
	public static TestsDataReader getInstance() {
		synchronized (TestsDataReader.class) {
			if (instance == null) {
				instance = new TestsDataReader();
			}
			return instance;
		}
	}
	
	public void readFromFile(String path) {
		File file = new File(path);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line = null;
			String lineValues[];
			int count = 1;
			
			while ((line = reader.readLine()) != null) {
				
				lineValues = line.split(",");
				trimEmptySpaces(lineValues);
			
				if (count == 1) {
					validateFileStructure(lineValues);
				}
				else {
					Test test = TestFactory.getInstance().createTest(lineValues);
					TestsStore.getInstance().addTestToList(test);	
				}
				count++;
			}
			reader.close();
			
		} catch (IOException e) {
			System.err.println("There was an error with reading tests data.");
			e.printStackTrace();
		} catch (InvalidTestsFileException e) {
			e.printStackTrace();		}
	}

	private void validateFileStructure(String[] lineValues) throws InvalidTestsFileException {
		for (int colIndex = 0; colIndex < TestsFileDefinition.values().length; colIndex++) {
			if (!lineValues[colIndex].matches(TestsFileDefinition.values()[colIndex].getColName())) {
				throw new InvalidTestsFileException(
						"Invalid tests file structure for (Column " 
						+ (colIndex + 1)
						+ ").");
			}
		}
	}

	private void trimEmptySpaces(String[] lineValues) {
		for (int index = 0; index < lineValues.length; index++) {
			lineValues[index] = lineValues[index].trim();
		}
	}
}
