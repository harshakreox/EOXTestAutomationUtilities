package com.eoxvantage.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInitialiser {

	public static WebDriver driver1;

	public static WebDriver driver(String url) {

		// Added Maven dependency for the below line,
		// now no need to to update the drivers manually, this will take care

		WebDriverManager.chromedriver().setup();

		ChromeOptions option = new ChromeOptions();

		System.out.println(option.getBrowserVersion());

		option.addArguments("--disable-notifications");
		// option.addArguments("headless"); //------ Not able to Run in this mode
		// because file upload will trigger an external Window
		option.addArguments("--disable-popup-blocking");
		option.addArguments("--remote-allow-origins=*");
		option.addArguments("version");
		option.addArguments("--disable-infobars");
//		option.addArguments("force-device-scale-factor=0.75");
		option.addArguments("high-dpi-support=0.75");

		WebDriver driver1 = new ChromeDriver(option);

		driver1.manage().window().maximize();
		driver1.manage().deleteAllCookies();
		driver1.get(url);
		driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver1.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));

		return driver1;
	}

	@AfterSuite
	public void tearDown() {
		driver1.close();
	}
}
