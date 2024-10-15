package com.eoxvantage.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;



public class FormStackEsign {
	
	WebDriver driver;
	public FormStackEsign(WebDriver driver) {
		this.driver=driver;
	}

	DriverMethods dm = new DriverMethods(driver);
	SoftAssert sa = new SoftAssert();

	// These Xpaths are generic for the E-sign part so this is directly put here,
	// if these change in future every time need to make it dynamic.

	static String startButton = "//button[.='Start']";
	static String applySignButton = "//button[.='Apply']";
	static String clickToSignButton = "//span[@class= 'is-signature-field is-fillable ng-binding required']";
	static String formstackESignNumber = "//a[@class='into-highlight-required']/var[@class='ng-binding']";
	static String submitSignedDocument = "//span[.='Submit Document']";
	static String eSignConfirmMessage = "Thank you! The document signing is complete. We'll email you the final copy shortly.";

	// call this function to execute both the below functions which does the esign
	public void startEsign() {

		dm.getActiveWindows(driver);
		dm.switchToNextTab(driver);
		getEsignNumber(driver);
		sign(driver);
		submitDocument(driver);
		dm.currentWindowClose(driver);
		dm.switchToParentTab(driver);
	}

	// This function iterates through the number of E-signatures to be done and does
	// the signature

	/**
	 * @param driver
	 */
	public static void sign(WebDriver driver) {

		int intialValue = 0;
		int value = getEsignNumber(driver);

		List<WebElement> list = driver.findElements(By.xpath(clickToSignButton));

		for (WebElement ele : list) {
			System.out.println(intialValue);
			if (intialValue == 0) {
				driver.findElement(By.xpath(startButton)).click();
				list.get(intialValue).click();
				driver.findElement(By.xpath(applySignButton)).click();
			} else if (intialValue > 0) {
				list.get(intialValue).click();
			} else
				break;
			intialValue++;
		}
	}

	// This function returns the number of times e-sign has to be done on a document
	public static int getEsignNumber(WebDriver driver) {
		String esignNumber = driver.findElement(By.xpath(formstackESignNumber)).getText();
		int numberOfSignatures = Integer.parseInt(esignNumber);
		return numberOfSignatures;
	}

	// After the E-sign is completed submitting the same and closing the current tab
	// or in this case the E-sign tab.

	/**
	 * @param driver
	 */
	public void submitDocument(WebDriver driver) {
		driver.findElement(By.xpath(submitSignedDocument)).click();
		String browserConfirmMessage = driver.findElement(By.cssSelector("h6.text-primary.ng-binding.ng-scope"))
				.getText();
		sa.assertEquals(browserConfirmMessage, eSignConfirmMessage);
	}
}
