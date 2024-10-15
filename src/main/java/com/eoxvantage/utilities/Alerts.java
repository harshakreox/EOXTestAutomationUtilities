package com.eoxvantage.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class Alerts {
	
	WebDriver driver;
	
	public Alerts(WebDriver driver) {
		this.driver = driver;
	}

	DriverMethods driverMethods = new DriverMethods(driver);

	public void alertAccept(WebDriver driver) throws InterruptedException {

		try {
			// driverMethods.wait();
			Alert alertA = driver.switchTo().alert();
			System.out.println(driver.switchTo().alert().getText());
			alertA.accept();
		} catch (Exception e) {
			System.out.println(e.getCause());
			e.printStackTrace();
		}

	}

	public void rejectAlert(WebDriver driver) throws InterruptedException {

		driverMethods.wait();
		Alert alertD = driver.switchTo().alert();
		System.out.println(driver.switchTo().alert().getText());
		alertD.dismiss();
	}
}
