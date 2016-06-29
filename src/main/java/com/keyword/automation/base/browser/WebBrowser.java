package com.keyword.automation.base.browser;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.keyword.automation.base.utils.LogUtils;

/**
 * 浏览器操作实现类
 * 
 * @author airpy
 *
 */
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

	public String getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}

	public String getCurrentTitle() {
		return this.driver.getTitle();
	}

	public String getPageSource() {
		return this.driver.getPageSource();
	}

	public void browserClose() {
		this.driver.close();
	}

	public void browserQuit() {
		this.driver.quit();
	}

	public void switchToWindow() {
		String currentWindow = this.driver.getWindowHandle();
		Set<String> windowHandles = this.driver.getWindowHandles();
		Iterator<String> it = windowHandles.iterator();
		while (it.hasNext()) {
			if (currentWindow == it.next())
				continue;
			this.driver.switchTo().window(it.next());
		}
	}

	public void switchToWindow(String windowTitle) {
		long start = System.currentTimeMillis();
		long maxWaitTime = 15000L;
		do {
			try {
				Set<String> windowHandles = this.driver.getWindowHandles();
				LogUtils.debug("all window handles: " + windowHandles);
				for (String windowHandle : windowHandles) {
					this.driver.switchTo().window(windowHandle);
					String currentTitle = this.driver.getTitle();
					LogUtils.info(
							"Try to check window <title: " + currentTitle + ", expect window: " + windowHandle + ">.");
					if (((windowTitle.equals("")) && (currentTitle.equals("")))
							|| ((!windowTitle.equals("")) && (currentTitle.contains(windowTitle)))) {
						LogUtils.debug("swith to window: " + currentTitle + "success.");
						return;
					}
				}
			} catch (Exception e) {
				LogUtils.debug("Ignored Exception");
			}
		} while (System.currentTimeMillis() - start < maxWaitTime);
		throw new NoSuchWindowException("Window <Title:" + windowTitle + "> is not exist in browser.");
	}

	public void switchToFrame(Object obj) {
		if (obj instanceof String) {
			this.driver.switchTo().frame(obj.toString());
		} else if (obj instanceof Integer) {
			this.driver.switchTo().frame(Integer.parseInt(obj.toString()));
		}
	}

	public void switchToFrame(String locator, String locatorValue) {

	}

	public void browserBack() {
		this.driver.navigate().back();
	}

	public void browserForward() {
		this.driver.navigate().forward();
	}

	public void browserRefresh() {
		this.driver.navigate().refresh();
	}

	public void browserMax() {
		this.driver.manage().window().maximize();
	}

	public void addCookie(String cookieName, String CookieValue) {
		Cookie cookie = new Cookie(cookieName, CookieValue);
		this.driver.manage().addCookie(cookie);
	}

	public void deleteCookieByName(String cookieName) {
		this.driver.manage().deleteCookieNamed(cookieName);
	}

	public void deleteAllCookies() {
		this.driver.manage().deleteAllCookies();
	}

	public WebElement findElement(String locator, String locatorValue) {
		return null;
	}

	public List<WebElement> findElements(String locator, String locatorValue) {
		return null;
	}
}
