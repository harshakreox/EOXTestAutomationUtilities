package com.eoxvantage.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BrowserActions {

	private WebDriver driver;
	private WebDriverWait wait;
	private ExtentTest extentTest;
	Alert alert = driver.switchTo().alert();
	private static final Logger logger = Logger.getLogger(BrowserActions.class.getName());

	// Static block to configure the logger
	static {
		try {
			if (logger.getHandlers().length == 0) { // Avoid duplicate handlers
				FileHandler fileHandler = new FileHandler("browser_actions.log", true);
				fileHandler.setFormatter(new SimpleFormatter());
				logger.addHandler(fileHandler);
				logger.setLevel(Level.ALL); // Log all levels (INFO, SEVERE, WARNING, etc.)
			}
		} catch (IOException e) {
			System.err.println("Failed to initialize logger: " + e.getMessage());
		}
	}

	// Constructor to initialize WebDriver and WebDriverWait
	public BrowserActions(WebDriver driver, ExtentTest extentTest) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Correct for Selenium 4
		this.extentTest = extentTest; // Assign the ExtentTest instance
		logAndReport("BrowserActions initialized.");
	}

	// Helper method to log and report messages
	private void logAndReport(String message) {
		logger.info(message); // Log to Java Logger
		if (extentTest != null) { // Ensure ExtentTest is not null
			extentTest.log(Status.INFO, message); // Log to Extent Report
		}
	}

	// Log errors to both Logger and ExtentTest
	private void logAndReportError(String message, Exception e) {
		String errorMsg = message + ": " + (e.getMessage() != null ? e.getMessage() : "No message");
		logger.log(Level.SEVERE, errorMsg, e); // Log full stack trace
		if (extentTest != null) {
			extentTest.log(Status.FAIL, errorMsg);
		}
	}

	// Click an element and log the action
	public void click(WebElement element) {
		try {
			validateElement(element);
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			logAndReport("Clicked element: " + element.getTagName());
		} catch (Exception e) {
			logAndReportError("Failed to click element", e);
		}
	}

	// Enter text into a field and log the action
	public void enterText(WebElement element, String text) {
		try {
			validateElement(element);
			wait.until(ExpectedConditions.visibilityOf(element)).clear();
			element.sendKeys(text);
			logAndReport("Entered text: '" + text + "' into element: " + element.getTagName());
		} catch (Exception e) {
			logAndReportError("Failed to enter text", e);
		}
	}

	// Get text from an element and log the action
	public String getText(WebElement element) {
		try {
			validateElement(element);
			String text = wait.until(ExpectedConditions.visibilityOf(element)).getText();
			logAndReport("Extracted text: '" + text + "' from element: " + element.getTagName());
			return text;
		} catch (Exception e) {
			logAndReportError("Failed to get text from element", e);
			return null;
		}
	}

	// Take a screenshot and log the action
	public void takeScreenshot(String filePath) {
		try {
			Files.createDirectories(Paths.get(filePath).getParent()); // Ensure directory exists
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(src.toPath(), Paths.get(filePath));
			logAndReport("Screenshot saved at: " + filePath);
		} catch (IOException e) {
			logAndReportError("Failed to take screenshot", e);
		}
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void acceptAlert() {
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			if (isAlertPresent()) {
				logAndReport("Alert text: " + alert.getText());
				alert.accept(); // Accept the alert
				logAndReport("Alert accepted.");
			} else {
				System.out.println("No alert present.");
			}
		} catch (NoAlertPresentException e) {
			System.out.println("No alert is present.");
		} catch (UnhandledAlertException e) {
			System.out.println("Unexpected alert handled. Message: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Failed to accept alert: " + e.getMessage());
			throw e;
		}
	}

	public void dismissAlert() {
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			if(isAlertPresent()) {
				logAndReport("Alert text: " + alert.getText());
				alert.dismiss();
				logAndReport("Alert dismissed.");
			}else {
				logAndReport("No Alert Present to dismiss");
			}
		} catch (NoAlertPresentException e) {
			System.out.println("No alert is present.");
		} catch (UnhandledAlertException e) {
			System.out.println("Unexpected alert handled. Message: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Failed to accept alert: " + e.getMessage());
			throw e;
		}
	}

	public String getPageTitle() {
		try {
			String title = driver.getTitle();
			logAndReport("Page title is: " + title);
			return title;
		} catch (Exception e) {
			logAndReportError("Failed to get page title", e);
			return null;
		}
	}

	public void scrollToElement(WebElement element) {
		try {
			validateElement(element);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			logAndReport("Scrolled to element: " + element.getTagName());
		} catch (Exception e) {
			logAndReportError("Failed to scroll to element", e);
		}
	}

	public void switchToIframe(WebElement frame) {
		try {
			validateElement(frame);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
			logAndReport("Switched to iframe: " + frame.getTagName());
		} catch (Exception e) {
			logAndReportError("Failed to switch to iframe", e);
		}
	}

	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
			logAndReport("Switched back to default content.");
		} catch (Exception e) {
			logAndReportError("Failed to switch to default content", e);
		}
	}

	public void closeBrowser() {
		try {
			driver.quit();
			logAndReport("Browser closed.");
		} catch (NoSuchSessionException e) {
			logAndReportError("Browser session already closed", e);
		} catch (Exception e) {
			logAndReportError("Failed to close browser", e);
		}
	}

	public void refreshPage() {
		try {
			driver.navigate().refresh();
			logAndReport("Page refreshed.");
		} catch (Exception e) {
			logAndReportError("Failed to refresh page", e);
		}
	}

	public void navigateBack() {
		try {
			driver.navigate().back();
			logAndReport("Navigated back to the previous page.");
		} catch (Exception e) {
			logAndReportError("Failed to navigate back", e);
		}
	}

	public void navigateForward() {
		try {
			driver.navigate().forward();
			logAndReport("Navigated forward to the next page.");
		} catch (Exception e) {
			logAndReportError("Failed to navigate forward", e);
		}
	}

	// Validate WebElement is not null
	private void validateElement(WebElement element) {
		if (element == null) {
			throw new IllegalArgumentException("WebElement is null");
		}
	}
}
