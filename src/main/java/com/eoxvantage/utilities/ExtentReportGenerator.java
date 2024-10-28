package com.eoxvantage.utilities;

import org.testng.annotations.AfterSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportGenerator {

    private static ExtentReports extent;

    // Singleton instance of ExtentReports
    public static ExtentReports getInstance(String reportPath) {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // Optional: Customize the report
            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add system information to the report
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }

    // Flush the report after all tests are executed
    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        if (extent != null) {
            extent.flush();  // Write the report to the file system
            System.out.println("Extent Report generated successfully.");
        }
    }


	@AfterSuite
	private SendEmailReport SendEmailReport() {

		return null;
	}

}