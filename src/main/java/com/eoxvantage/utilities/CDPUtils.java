package com.eoxvantage.utilities;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.browser.Browser;
import org.openqa.selenium.devtools.v110.browser.Browser.SetDownloadBehaviorBehavior;
import org.openqa.selenium.devtools.v110.network.Network;
import org.openqa.selenium.edge.EdgeDriver;

public abstract class CDPUtils {

	private WebDriver driver;
	private DevTools devTools;

	public CDPUtils(WebDriver driver) {
		if (driver instanceof ChromeDriver || driver instanceof EdgeDriver) {
			this.driver = driver;
			ChromeDriver chromeDriver = (ChromeDriver) driver;

			devTools = chromeDriver.getDevTools();
			devTools.createSession();
		} else {
			throw new IllegalArgumentException("CDP Utils only Suports ChromeDriver or Edge Driver");
		}

	}

	// Enable Multiple File Downloads from a Browser 
	public abstract void allowMultipleFileDownload();
	
	
	// Enable Network Interception
	public abstract void enableNetworkInterception();
		
	
	public void setFileDownloadBehaviour() {
		devTools.send(Browser.setDownloadBehavior(SetDownloadBehaviorBehavior.ALLOW, Optional.empty(), Optional.empty(), Optional.empty()));
	}

	// Enable Logging is any Request Fails with Request URL and Status code
	public void getFailedRequests() {
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devTools.addListener(Network.responseReceived(), request -> {

			if (request.getResponse().getStatus().toString().startsWith("4")
					|| request.getResponse().getStatus().toString().startsWith("5")) {
				System.out.println("This API Call Failed " + request.getResponse().getUrl() + " With response code: "
						+ request.getResponse().getStatus());
			}
		});
	}
	
	

}
