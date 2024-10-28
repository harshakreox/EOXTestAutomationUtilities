package com.eoxvantage.utilities;

import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestNGListeners implements ITestListener {

    private static ExtentReports extent;
    private static ExtentSparkReporter extentSparkReporter;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    CurrentDateandTime date = new CurrentDateandTime();
    private WebDriver driver = DriverInitialiser.driver1;

    @Override
    public void onStart(ITestContext context) {
        extent = new ExtentReports();

        String reportPath = System.getProperty("user.dir") + "/Reports/" + date.date() +
                "/Test Automation Report - " + context.getCurrentXmlTest().getName() + ".html";

        extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setDocumentTitle("Automation Test Reports");
        extentSparkReporter.config().setReportName("Test Automation Results");
        extentSparkReporter.config().enableOfflineMode(true);
        extentSparkReporter.config().setTimelineEnabled(true);

        extent.attachReporter(extentSparkReporter);

        System.out.println("Test Execution Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();  // Write the report to the filesystem
        try {
            CreateZipfile.callZip();  // Archive the report
        } catch (IOException e) {
            e.printStackTrace();
        }
        SendEmailReport.sendEmail("Automation Test Report", "Please find the attached report.");
        System.out.println("Test Execution Finished: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        test.set(extentTest);

        System.out.println("Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
        System.out.println("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.get().log(Status.FAIL, result.getThrowable());  // Log exception stacktrace

        // Capture a screenshot on failure
        if (driver != null) {
            String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
            test.get().addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure");
        }

        System.out.println("Test Failed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        System.out.println("Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);  // Treat timeout as a failure
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Failed but within Success Percentage: " + result.getMethod().getMethodName());
    }

    // Helper method to take a screenshot
    private String takeScreenshot(String methodName) {
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + methodName + ".png";
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            java.nio.file.Files.write(java.nio.file.Paths.get(screenshotPath), screenshot);
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
        return screenshotPath;
    }
}
