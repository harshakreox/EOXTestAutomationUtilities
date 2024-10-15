package com.eoxvantage.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GetTileButton {

	@FindBy(className = ".pd-activeButton p")
	List<WebElement> ActiveTilesList;

	By buttonList = By.cssSelector(".badge.bbg-success :first-child");

	/**
	 * This function is for getting the buttons that are displayed inside a Tile as displayed in the HDO Project
	 * @param driver
	 * @param TileName
	 * @param buttonName
	 * @return
	 */
	public WebElement getTileButton(WebDriver driver, String TileName, String buttonName) {

		WebElement reqTile = ActiveTilesList.stream().filter(l->l.getText().contains(TileName)).findFirst()
				.orElse(null);
		
		List<WebElement> tilebuttonList = reqTile.findElements(buttonList);
		WebElement button = tilebuttonList.stream().filter(btnl->btnl.getText().contains(buttonName)).findFirst().orElse(null);

		return button;
	}

}
