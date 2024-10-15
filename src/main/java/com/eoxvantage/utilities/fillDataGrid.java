package com.eoxvantage.utilities;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class fillDataGrid extends formComponents{

	WebDriver driver;

	public fillDataGrid(WebDriver driver) {
		this.driver = driver;
	}

	WebElement parentLocator;

	/**
	 * @param parentLocator
	 * @param eleList
	 * @param testDataList
	 * @return
	 */
	public HashMap<WebElement, String> getDataAndFillDataGrid(WebElement parentLocator, List<WebElement> eleList,
			List<String> testDataList) {
		this.parentLocator=parentLocator;
		HashMap<WebElement, String> eleAndDataList = new HashMap<WebElement, String>();
		if (eleList.size() == testDataList.size()) {
			for (int i = 0; i < eleList.size(); i++) {
				eleAndDataList.put(eleList.get(i), testDataList.get(i));
			}
		}	
		return eleAndDataList;
	}
	
	
	public void getDataAndFillDataGrid(List<WebElement> eleList,
			List<String> testDataList){
	}

	public void fillData() {
		
	}

}
