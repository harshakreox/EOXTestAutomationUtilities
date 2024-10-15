package com.eoxvantage.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderReader {


	/**
	 * This is Exclusively for EOX Header App names, when one wants to perform some actions on them.
	 * 
	 * @param driver
	 * @param listOfHeaderElements
	 * @param element2
	 * @param mainMenuLogo
	 * @param element4
	 * @param appToFind
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public void headerReader(WebDriver driver, By listOfHeaderElements, By element2, By mainMenuLogo, By element4, String appToFind)
			throws InterruptedException, Exception {

		List<WebElement> list = driver.findElements(listOfHeaderElements);
		System.out.println("Number of apps open are " + list.size());

		// Considering the flag to be true, i.e., in this case that some of the apps are
		// open the application when logged in.
		boolean flag = true;

		if (list.size() == 0) {
			flag = true;
		} else {
			flag = false;
		}

		System.out.println(flag);

		if (flag == true) {
			// Here Element 3 represents the Logo which will be present in the Top Left or
			// its the Main menu button
			driver.findElement(mainMenuLogo).click();
			Thread.sleep(2000);

			// Here Element 4 represents the App which we are trying to open, the same app
			// here will found through the main menu list
			driver.findElement(element4).click();
		} else if (flag == false) {
			System.out.println("The List of Header elements are :");

			for (int i = 0; i < list.size(); i++) {
				String name = list.get(i).getText().toString();
				System.out.println(name);

				String AppToFind = appToFind;
				if (name.equals(AppToFind)) {

					String newXpath = changeXpath(i, element2);

					By elementToBeFound = By.xpath(newXpath);

					WebElement ele = driver.findElement(elementToBeFound);

					String value = ele.getAttribute("data-focused");

					System.out.println(value);
					if (value.equals("false")) {
						ele.click();
					} else {
						break;
					}
				}
			}
		}
	}

	/**
	 * The below method is used to change the xpath at runtime by adding the i value
	 * from for loop plus one to it so that in the html tree structure the value
	 * will be taken as 1, from the beginning of the list.
	 * 
	 * The value of the element 2 is being extracted here and the use of Substring
	 * at index 10 is because there is a text which is attached to that has a total
	 * of 10 characters to it, so we need xpath from 11th character.
	 * 
	 * @param i
	 * @param element2
	 * @return modifiedXpath
	 */
	public static String changeXpath(int i, By element2) {

		int index = i + 1;
		System.out.println(index);

		String modifiedXpath = element2.toString().substring(10) + "[" + index + "]";
		System.out.println(modifiedXpath);
		return modifiedXpath;
	}

}
