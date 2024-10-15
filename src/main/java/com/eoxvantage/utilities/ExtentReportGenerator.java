package com.eoxvantage.utilities;

import org.testng.annotations.AfterClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportGenerator {

	private static ExtentReports extent;

	public static ExtentReports getInstance(String reportPath) {
		if (extent == null) {
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			
			extent.setSystemInfo("OS", System.getProperty("os.name"));
	        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		}
		return extent;
	}

	@AfterClass
	private SendEmailReport SendEmailReport() {

		return null;
	}

}