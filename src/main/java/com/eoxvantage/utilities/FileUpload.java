package com.eoxvantage.utilities;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FileUpload {

	WebDriver driver;

	public FileUpload(WebDriver driver, String testFile) {
		this.driver = driver;
	}

	public static void copyFilepath(String filePath) throws AWTException {

		System.out.println("Processing file path: " + filePath);
		StringSelection filePathSelection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);
		RobotActions.copyPasteAction();
	}

	public void UploadFile(Optional<WebElement> element, WebElement browse, String filePath)
			throws InterruptedException, AWTException {
		FileUpload.copyFilepath(filePath);
		Thread.sleep(5000);
		confirmFileUpload(Optional.empty(), browse);
	}

	/**
	 * The Function is used to confirm the file Upload, post its upload, it takes
	 * the file name as second argument, searches it in the place its uploaded.
	 * 
	 * @param driver
	 * @param element
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void confirmFileUpload(Optional<List<WebElement>> element, WebElement browse)
			throws AWTException, InterruptedException {

		if (element.isPresent()) { // Check if the element is provided
			List<WebElement> fileElement = element.get();
			
			for(WebElement ele : fileElement){
			
			// Get the file name from the element
			String fileName = ((WebElement) fileElement).getAttribute("innerHTML");
			System.out.println("The uploaded file name is: " + fileName);
			// Handle cases where the file name is null or empty
			if (fileName == null || fileName.isEmpty()) {
				browse.click(); // Retry file upload if necessary
				Thread.sleep(2000);
				FileUpload.copyFilepath(null); // Provide file path dynamically as needed
			} else {
				System.out.println("File uploaded successfully!");
			}
		}
		} else {
			System.out.println("No file confirmation element provided. Skipping verification.");
		}
		
	}

}
