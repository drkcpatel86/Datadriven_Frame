package com.datadriven.frame.testcases;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestFF {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");

		 DesiredCapabilities capabilities = new DesiredCapabilities();

		 capabilities = DesiredCapabilities.firefox();
		 capabilities.setBrowserName("firefox");
		 capabilities.setVersion("35");
		 capabilities.setPlatform(Platform.WINDOWS);
		 capabilities.setCapability("marionette", false);

		 WebDriver driver = new FirefoxDriver(capabilities);

		 driver.get("https://maps.mapmyindia.com");
	}

}
