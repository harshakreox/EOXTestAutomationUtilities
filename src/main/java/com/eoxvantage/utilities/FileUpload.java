package com.eoxvantage.utilities;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FileUpload {
	
	static String testFile;
	WebDriver driver;
	public FileUpload(WebDriver driver, String testFile) {
		this.driver = driver;
		this.testFile = testFile;
	}
	

	// This will get the current directory and then the this path will be appended
	// to the file path where the files are stored.
	static String path = System.getProperty("user.dir");

	public static void copyFilepath(String filePath) throws AWTException {

		System.out.println("Processing file path: " + filePath);
		StringSelection filePathSelection  = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);
		RobotActions.copyPasteAction();
	}

	public void UploadFile(Optional<WebElement> element, WebElement browse) throws InterruptedException, AWTException {
		FileUpload.copyFilepath(null);
		Thread.sleep(5000);
		confirmFileUpload(Optional.empty(), browse);
	}

	/**
	 * @param driver
	 * @param element
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void confirmFileUpload(Optional<WebElement> element, WebElement browse) throws AWTException, InterruptedException {

		if (element.isPresent()) { // Check if the element is provided
	        WebElement fileElement = element.get();

	        // Get the file name from the element
	        String fileName = fileElement.getAttribute("innerHTML");
	        System.out.println("The uploaded file name is: " + fileName);

	        // Handle cases where the file name is null or empty
	        if (fileName == null || fileName.isEmpty()) {
	        	browse.click(); // Retry file upload if necessary
	            Thread.sleep(2000);
	            FileUpload.copyFilepath(null); // Provide file path dynamically as needed
	        } else {
	            System.out.println("File uploaded successfully!");
	        }
	    } else {
	        System.out.println("No file confirmation element provided. Skipping verification.");
	    }
	}

}
