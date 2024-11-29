package com.eoxvantage.utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInitialiser {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private DriverInitialiser() {
	}

	public static WebDriver getDriver() {

		if (driver.get() == null) {

			// Added Maven dependency for the below line,
			// now no need to to update the drivers manually, this will take care
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized"); // Open browser in maximized mode
			options.addArguments("--disable-notifications"); // Disable browser notifications
			options.addArguments("--incognito"); // Open browser in incognito mode
	//		options.addArguments("--headless"); // Run in headless mode (if needed) but cannot run since file uploads do not work in headless 

			// You can also add experimental options or preferences if needed
			options.addArguments("--remote-allow-origins=*");
			driver.set(new ChromeDriver(options)); // Initialize WebDriver with ChromeOptions
		}
		return driver.get();
	}

	public void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
