package com.eoxvantage.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader {

	private static Properties properties;
	private String root = System.getProperty("user.dir");
	private String filePath;

	public TestDataReader(String fileName) {
		setFilePath(root.concat(fileName));
		properties = new Properties();

		try {
			FileInputStream fis = new FileInputStream(filePath);
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getTestData(String elementName) {
		return properties.getProperty(elementName);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
		if(filePath!=null) {
			System.out.println(this.filePath);
		}else {
			throw new NullPointerException();
		}
		
	}

}
