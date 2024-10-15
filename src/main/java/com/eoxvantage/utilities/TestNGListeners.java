package com.eoxvantage.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestNGListeners implements ITestListener {


	private static ExtentReports extent = new ExtentReports();
	private static ExtentSparkReporter extentSparkReporter;
	private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	CurrentDateandTime date = new CurrentDateandTime();
	private WebDriver driver = DriverInitialiser.driver1;
		
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extent.flush();
		try {
			CreateZipfile.callZip();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendEmailReport.sendEmail("Email Subject", "Email Body");
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
		extent = new ExtentReports();

		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")
				+ "/Reports/" + date.date() + "/" + "Test Automation reports for " + context.getCurrentXmlTest()
						.getClasses().stream().findFirst().get().getName().replaceFirst("testNGTests.", " ")
				+ date.date());
		
		extentSparkReporter.config().setTheme(Theme.STANDARD);
		extentSparkReporter.config().setDocumentTitle("Automation Test Reports");
		extentSparkReporter.config().setReportName("Test Autmation results report");
		extentSparkReporter.config().enableOfflineMode(true);
		extentSparkReporter.config().setTimelineEnabled(true);
		extent.attachReporter(extentSparkReporter);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		test.set(extentTest);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
	}

	
}