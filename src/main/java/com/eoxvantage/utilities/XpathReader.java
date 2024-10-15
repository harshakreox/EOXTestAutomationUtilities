package com.eoxvantage.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class XpathReader {

	static String filePath;

	public XpathReader(String filePath) {
		this.filePath = filePath;
	}

	static Properties xpaths = new Properties();

	static {
		try {
			
			FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + filePath));
			xpaths.load(fis);

		} catch (IOException ioe) {
			System.out.println("Error loading the xpath.properties file");
			ioe.printStackTrace();
		}
	}

	public static String getXpath(String elementName) {
		return xpaths.getProperty(elementName);
	}
}
