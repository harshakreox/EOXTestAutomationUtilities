package com.eoxvantage.utilities;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseActions {

	public void mouseWheelUp(WebDriver driver1) {

		JavascriptExecutor Scroll = (JavascriptExecutor) driver1;
		Scroll.executeScript("window.scrollBy(0,-300)", "");
		driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void mouseWheelDown(WebDriver driver1) {

		JavascriptExecutor Scroll = (JavascriptExecutor) driver1;
		Scroll.executeScript("window.scrollBy(0,300)", "");
		driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void doubleClick(WebElement ele) {

		Actions action = new Actions((WebDriver) ele);
		action.moveByOffset(80, 155).contextClick();
	}

	public void mouseHover(WebElement ele, WebDriver driver) {

		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
	}

	public void rightClick(WebElement ele, WebDriver driver) {
		Actions action = new Actions(driver);
		try {
		action.contextClick(ele).build().perform();
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
}
