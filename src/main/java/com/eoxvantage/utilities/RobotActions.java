package com.eoxvantage.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RobotActions {

//	static By SignaturePad = By.xpath(XpathReader.getXpath("SignaturePad"));

	// This method will copy a string file path and put it in the file upload
	// dialogue box and click on Key board ENTER
	public static void copyPasteAction() throws AWTException {

		Robot robot = new Robot();
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(90);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	// This is to perform a signature on a canvas
	public static void drawsignature(WebDriver driver, By signaturePad) {

		Actions builder = new Actions(getDriver(driver));

		// Here we are sending the xpath of the canvas to get the signature done
		WebElement canvasElement = driver.findElement(signaturePad);

		org.openqa.selenium.interactions.Action signature = builder.contextClick(canvasElement).clickAndHold()
				.moveToElement(canvasElement, 20, -50).moveByOffset(50, 50).moveByOffset(80, -50).moveByOffset(100, 50)
				.release(canvasElement).build();
		signature.perform();
	}

	private static WebDriver getDriver(WebDriver driver) {
		// TODO Auto-generated method stub
		return driver;
	}

	// This is to perform the mouse actions that is mouse right click and release
	public int rightClick(Point point) throws AWTException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_F10);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_F10);
		return 0;
	}

	// This is to send the data to the CK Editor's text area field
	public void sendDataToCKEditor(WebDriver driver, String testData) {

		// Locating the body of the description box
		WebElement editorbody = driver.findElement(By.tagName("body"));

		// Instantiating the Action class for performing the action on the description
		// box
		Actions builder = new Actions(driver);

		builder.click(editorbody).sendKeys(testData).build().perform();

	}
}
