package com.eoxvantage.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationFileReader {
	
	static String filePath;
	
	public ApplicationFileReader(String filePath) {
		this.filePath = filePath;
	}

	static Properties creds = new Properties();

	static {
		try {
			FileInputStream fis = new FileInputStream(
					new File(System.getProperty("user.dir").concat(filePath)));
			creds.load(fis);

		}

		catch (IOException ioe) {
			System.out.println("Error loading the Application.properties file");
			ioe.printStackTrace();
		}
	}

	public static String getCreds(String elementName) {
		return creds.getProperty(elementName);
	}

}
