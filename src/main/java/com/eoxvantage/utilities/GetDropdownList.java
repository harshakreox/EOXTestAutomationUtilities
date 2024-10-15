package com.eoxvantage.utilities;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class GetDropdownList {

	private WebDriver driver;

	public GetDropdownList(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	DriverMethods dm = new DriverMethods(this.driver);

	@Deprecated
	public void getDList(By element1, By element2) {

		List<WebElement> Dlist = driver.findElements(element1);
		System.out.println("The Size of the list is : " + Dlist.size());

		try {
			for (int i = 0; i <= Dlist.size() - 1; i++) {
				String val = Dlist.get(i).getAttribute("innerHTML").replace("<span>", " ").replace("</span>", " ");
				if (val == null) {
					driver.findElement(element2).click();
					Thread.sleep(2000);
					val = Dlist.get(i).getText();
				}
				System.out.println(val);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("\n");
	}

	/**
	 * This function uses Stream api where you have to pass the list of WebElements
	 * which will be converted to Stream and then actions will be performed.
	 * 
	 * Use this method if you are not able to get the text directly and only if the
	 * attribute is present in the html.
	 * 
	 * @param driver
	 * @param ele
	 * @param eleName
	 */
	public void getDropdownListInStream(By ele, String eleName) {
		List<WebElement> dList = driver.findElements(ele);
		WebElement strEle = dList.stream().filter(s -> s.getAttribute("data-value").equalsIgnoreCase(eleName))
				.distinct().findFirst().orElse(null);
		if (strEle != null) {
			strEle.click();
		} else {
			System.out.println("No element found to click!");
		}
	}

	/**
	 * This function uses Stream api where you have to pass the list of WebElements
	 * which will be converted to Stream and then actions will be performed.
	 * 
	 * Use this method if you are not able to get the text directly and only if the
	 * attribute is present in the html and if you are using @FindBy
	 * 
	 * @param driver
	 * @param eleList
	 * @param eleNameToBeFound
	 */
	public void getDropdownListInStream(List<WebElement> eleList, String eleNameToBeFound) {

		WebElement strEle = eleList.stream()
				.filter(s -> s.getAttribute("data-value").equalsIgnoreCase(eleNameToBeFound)).distinct().findFirst()
				.orElse(null);
		if (strEle != null) {
			strEle.click();
		} else {
			System.out.println("No element found to click!");
		}
	}
	
	public void getDropdownListInStream(String eleNameToBeFound, List<WebElement> eleList) {
		WebElement strEle = eleList.stream()
				.filter(s -> s.getText().equalsIgnoreCase(eleNameToBeFound)).distinct().findFirst().orElse(null);
		if(strEle != null) {
			strEle.click();
		} else {
			System.out.println("No element found to click!");
		}
	}

	/**
	 * This function uses Stream api where you have to pass the list of WebElements
	 * which will be converted to Stream and then actions will be performed.
	 * 
	 * Use this function when you have a multi-select drop down and want to select
	 * all the options.
	 * 
	 * @param driver
	 * @param ele
	 */
	public void getDropdownListAndSelectAll(List<WebElement> ele) {
		ele.stream().forEach(s -> s.click());
	}

	/**
	 * his function uses Stream api where you have to pass the list of WebElements
	 * which will be converted to Stream and then actions will be performed.
	 * 
	 * Use this function to get the list and then perform a click action on one of the option
	 * 
	 * @param parentEle
	 * @param ele
	 * @param buttonName
	 */
	public void getListAndClickOne(WebElement parentEle, By ele, String buttonName) {
		List<WebElement> dList = parentEle.findElements(ele);
		dList.stream().forEach(s -> System.out.println(s.getText()));
		WebElement resEle = dList.stream()
				.filter(s -> s.getText().stripLeading().stripTrailing().equalsIgnoreCase(buttonName)).distinct()
				.findFirst().orElse(null);
		System.out.println(resEle);
		if (resEle != null) {
			resEle.click();
		} else {
			System.out.println("No Element Found to click !");
		}
	}

	/**
	 * This function uses Stream api where you have to pass the list of WebElements
	 * which will be converted to Stream and then actions will be performed.
	 * 
	 * @param driver
	 * @param eleList
	 * @param recordName
	 * @return singleRecordMatch
	 */
	public WebElement getListViewRecord(By eleList, String recordName) {
		List<WebElement> dList = driver.findElements(eleList);
		return dList.stream().filter(s -> s.getText().equals(recordName)).distinct().findFirst().orElse(null);
	}

	/**
	 * This function uses Stream api where you have to pass the list of WebElements
	 * which will be converted to Stream and then actions will be performed on the
	 * first found element.
	 * 
	 * Use this method if you have access the text present in the html.
	 * 
	 * @param driver
	 * @param eleName
	 * @param ele
	 */
	public void getDropdownListInStream(String eleName, By ele) {
		List<WebElement> dList = driver.findElements(ele);
		WebElement strEle = dList.stream().filter(s -> s.getText().equalsIgnoreCase(eleName)).distinct().findFirst()
				.orElse(null);
		if (strEle != null) {
			strEle.click();
		} else {
			System.out.println("No element found to click!");
		}
	}

	/**
	 * This function is exclusively designed for Hub Drive Online, for the Home Page
	 * which has tile Get the active tile list, pass it as a parameter, pass the
	 * tile name that you need to get.
	 *
	 * @param driver
	 * @param tileName
	 * @param buttonName
	 * @param tileButtonsList
	 * @param tileList
	 */
	public void getTile(WebDriver driver, String tileName, String buttonName, By tileButtonsListText,By tileButtonsList, By tileList,
			WebElement visibleElement) {
		dm.waitUntilElementIsVisible(visibleElement);
		List<WebElement> dList = driver.findElements(tileList);
		dList.stream().forEach(s->System.out.println(s.getText()));
		WebElement tile = dList.stream().filter(s -> s.getText().equalsIgnoreCase(tileName)).distinct().findFirst()
				.orElse(null);
		if (tile != null) {
			getListAndClickOne(tile, tileButtonsList, buttonName);
		} else {
			throw new NoSuchElementException();
		}
	}

}