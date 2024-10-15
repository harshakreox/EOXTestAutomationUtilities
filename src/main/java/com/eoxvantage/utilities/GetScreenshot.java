package com.eoxvantage.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GetScreenshot {

	static CurrentDateandTime date = new CurrentDateandTime();

	/**
	 * Takes Screenshot whenever called and stores the same inside a folder, Which
	 * will be under a main Folder REPORTS, current date and under that Screenshots.
	 * 
	 * @param driver
	 * @param methodName
	 * @return
	 */
	public static File getScreenshot(WebDriver driver, String methodName) {

		try {
			String fileName = methodName + " " + date.date() + " " + System.currentTimeMillis() + ".png";
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			System.out.println(fileName);
			String imgPath = System.getProperty("user.dir") + "./Reports/" + date.date() + "/Screenshots/" + fileName;
			File DestFile = new File(imgPath);
			// Copy file at destination
			FileUtils.copyFile(SrcFile, DestFile);
			return DestFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
