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
    private static final Logger logger = Logger.getLogger(BrowserActions.class.getName());

    // Static block to configure the logger
    static {
        try {
            FileHandler fileHandler = new FileHandler("browser_actions.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL); // Log all levels (INFO, SEVERE, WARNING, etc.)
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    // Constructor to initialize WebDriver and WebDriverWait
    public BrowserActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.extentTest = extentTest;
        logAndReport("BrowserActions initialized.");
    }
    
    // Helper method to log and report messages
    private void logAndReport(String message) {
        logAndReport(message);
        extentTest.log(Status.INFO, message);  // Log to Extent Report
    }
    
    private void logAndReportError(String message, Exception e) {
        logAndReportError(message + ": " + e.getMessage(), e);
        extentTest.log(Status.FAIL, message + ": " + e.getMessage());
    }

 // Click an element and log the action
    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            logAndReport("Clicked element: " + element.toString());
        } catch (Exception e) {
            logAndReportError("Failed to click element", e);
        }
    }

    // Enter text into a field and log the action
    public void enterText(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).clear();
            element.sendKeys(text);
            logAndReport("Entered text: '" + text + "' into element: " + element.toString());
        } catch (Exception e) {
            logAndReportError("Failed to enter text", e);
        }
    }

    // Get text from an element and log the action
    public String getText(WebElement element) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOf(element)).getText();
            logAndReport("Extracted text: '" + text + "' from element: " + element.toString());
            return text;
        } catch (Exception e) {
            logAndReportError("Failed to get text from element", e);
            return null;
        }
    }

    // Take a screenshot and log the action
    public void takeScreenshot(String filePath) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), Paths.get(filePath));
            logAndReport("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            logAndReportError("Failed to take screenshot", e);
        }
    }

    // Accept an alert and log the action
    public void acceptAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).accept();
            logAndReport("Alert accepted.");
        } catch (Exception e) {
            logAndReportError("Failed to accept alert", e);
        }
    }

    public void dismissAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).dismiss();
            logAndReport("Alert dismissed.");
        } catch (Exception e) {
            logAndReportError("Failed to dismiss alert: " + e.getMessage(), e);
        }
    }

    public String getPageTitle() {
        try {
            String title = driver.getTitle();
            logAndReport("Page title is: " + title);
            return title;
        } catch (Exception e) {
            logAndReportError("Failed to get page title: " + e.getMessage(), e);
            return null;
        }
    }

    public void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logAndReport("Scrolled to element: " + element.toString());
        } catch (Exception e) {
            logAndReportError("Failed to scroll to element: " + e.getMessage(), e);
        }
    }

    public void switchToIframe(WebElement frame) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
            logAndReport("Switched to iframe: " + frame.toString());
        } catch (Exception e) {
            logAndReportError("Failed to switch to iframe: " + e.getMessage(), e);
        }
    }

    public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
            logAndReport("Switched back to default content.");
        } catch (Exception e) {
            logAndReportError("Failed to switch to default content: " + e.getMessage(), e);
        }
    }

    public void closeBrowser() {
        try {
            driver.quit();
            logAndReport("Browser closed.");
        } catch (Exception e) {
            logAndReportError("Failed to close browser: " + e.getMessage(), e);
        }
    }

    public void refreshPage() {
        try {
            driver.navigate().refresh();
            logAndReport("Page refreshed.");
        } catch (Exception e) {
            logAndReportError("Failed to refresh page: " + e.getMessage(), e);
        }
    }

    public void navigateBack() {
        try {
            driver.navigate().back();
            logAndReport("Navigated back to the previous page.");
        } catch (Exception e) {
            logAndReportError("Failed to navigate back: " + e.getMessage(), e);
        }
    }

    public void navigateForward() {
        try {
            driver.navigate().forward();
            logAndReport("Navigated forward to the next page.");
        } catch (Exception e) {
            logAndReportError("Failed to navigate forward: " + e.getMessage(), e);
        }
    }
}