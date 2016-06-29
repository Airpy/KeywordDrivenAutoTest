package com.keyword.automation.base.browser;

import org.openqa.selenium.WebDriver;

import com.keyword.automation.base.utils.LogUtils;

public class WebBrowser {
	protected WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void open(String requestUrl) {
		this.driver.get(requestUrl);
		try {
			this.driver.manage().window().maximize();
		} catch (Exception e) {
			LogUtils.error("browser does not support maximize");
		}
	}
}
