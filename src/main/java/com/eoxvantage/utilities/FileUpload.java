package com.eoxvantage.utilities;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUpload {
	
	static String testFile;
	public FileUpload(String testFile) {
		this.testFile = testFile;
	}
	
	DriverInitialiser driver = new DriverInitialiser();

	// This will get the current directory and then the this path will be appended
	// to the file path where the files are stored.
	static String path = System.getProperty("user.dir");

	public static String copyFilepath(String file) throws AWTException {

		System.out.println(path + testFile);
		StringSelection ss2 = new StringSelection(path.concat(testFile));
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);
		RobotActions.copyPasteAction();
		return null;
	}

	public static void UploadFile(WebDriver driver) throws InterruptedException, AWTException {
		FileUpload.copyFilepath(null);
		Thread.sleep(5000);
	}

	/**
	 * @param driver
	 * @param element
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public static void confirmFileUpload(WebDriver driver, By element) throws AWTException, InterruptedException {

		String Filename = driver.findElement(element).getAttribute("innerHTML");
		System.out.println("The Uploaded file name is " + Filename);

		if (Filename == null) {
			driver.findElement(element).click();
			Thread.sleep(2000);
			FileUpload.copyFilepath(null);
		} else {
			System.out.println("File uploaded!");
		}
	}

}
