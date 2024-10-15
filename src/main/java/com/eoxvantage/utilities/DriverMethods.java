package com.eoxvantage.utilities;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class DriverMethods {

	static String parentWindow;
	static String childWindow;
	static Iterator<String> it;
	private WebDriver driver;

	public DriverMethods(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void click(By byElement, int secondsToWaitFor) {
		this.driver.findElement(byElement).click();
	}

	public void waitUntilElementIsClickable(By element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		// wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitUntilElementIsVisible(WebElement dropdownSearch) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(dropdownSearch));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitUntilElementIsVisible(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitUntilElementStalenessInGone(WebElement ele) {
		try
		{
			WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.stalenessOf(ele));
		}catch (Exception e) {
			Throwable cause = e.getCause();
			System.out.println(cause);
			cause.printStackTrace();
		}
		
	}

	public void waitUntilElementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitUntilPageIsRefreshed() {
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		});
	}

	public void waitUntilElementIsRefreshed(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(ele)));
	}

	public void waitUntilElementHasValue(WebElement ele, String elementValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElement(ele, elementValue));
	}

	@AfterClass
	public void browserClose(WebDriver driver) {
		driver.close();
	}

	public static void getActiveWindows(WebDriver driver) {

		Set<String> tabs = driver.getWindowHandles();
		it = tabs.iterator();
		parentWindow = it.next();
		childWindow = it.next();
	}

	public static void switchToParentTab(WebDriver driver) {
		driver.switchTo().window(parentWindow);
	}

	public static void switchToNextTab(WebDriver driver) {
		if (it.hasNext()) {
			driver.switchTo().window(childWindow);
		} else {
			System.out.println("No Additional Windows detected!");
		}
	}

	// This function is for closing the tab which is open in addition to the main
	// window/tab.
	public void currentWindowClose(WebDriver driver) {
		driver.close();
	}
}
