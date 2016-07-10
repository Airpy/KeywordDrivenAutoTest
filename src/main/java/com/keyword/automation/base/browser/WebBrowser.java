package com.keyword.automation.base.browser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
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
		WebElement webElement = null;
		By ByLocator = verifyLocator(locator, locatorValue);
		try {
			webElement = this.driver.findElement(ByLocator);
		} catch (Exception e) {
		}
		checkIsElementExists(webElement, locatorValue);
		this.driver.switchTo().frame(webElement);
	}

	public void switchToFrame(WebElement webElement) {
		this.driver.switchTo().frame(webElement);
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
	
	public void addCookie(Cookie cookie) {
		this.driver.manage().addCookie(cookie);
	}

	public void deleteCookieByName(String cookieName) {
		this.driver.manage().deleteCookieNamed(cookieName);
	}

	public void deleteCookie(Cookie cookie) {
		this.driver.manage().deleteCookie(cookie);
	}

	public void deleteAllCookies() {
		this.driver.manage().deleteAllCookies();
	}
	
	public Set<Cookie> getAllCookies() {
		return this.driver.manage().getCookies();
	}

	public WebElement findElement(String locator, String locatorValue) {
		WebElement webElement = null;
		By ByLocator = verifyLocator(locator, locatorValue);
		try {
			webElement = this.driver.findElement(ByLocator);
		} catch (Exception e) {
		}
		checkIsElementExists(webElement, locatorValue);
		return webElement;
	}

	public WebElement findElement(WebElement parent, String locator, String locatorValue) {
		WebElement webElement = null;
		By ByLocator = verifyLocator(locator, locatorValue);
		try {
			webElement = parent.findElement(ByLocator);
		} catch (Exception e) {
		}
		checkIsElementExists(webElement, locatorValue);
		return webElement;
	}

	public List<WebElement> findElements(String locator, String locatorValue) {
		List<WebElement> webElements = new ArrayList<WebElement>();
		By ByLocator = verifyLocator(locator, locatorValue);
		try {
			webElements = this.driver.findElements(ByLocator);
		} catch (Exception e) {
		}
		checkIsElementExists(webElements, locatorValue);
		return webElements;
	}

	public List<WebElement> findElements(WebElement parent, String locator, String locatorValue) {
		List<WebElement> webElements = new ArrayList<WebElement>();
		By ByLocator = verifyLocator(locator, locatorValue);
		try {
			webElements = parent.findElements(ByLocator);
		} catch (Exception e) {
		}
		checkIsElementExists(webElements, locatorValue);
		return webElements;
	}

	private By verifyLocator(String locator, String locatorValue) {
		if (locator.equalsIgnoreCase("id")) {
			return By.id(locatorValue);
		}
		if (locator.equalsIgnoreCase("name")) {
			return By.name(locatorValue);
		}
		if (locator.equalsIgnoreCase("linkText")) {
			return By.linkText(locatorValue);
		}
		if (locator.equalsIgnoreCase("partialLinkText")) {
			return By.partialLinkText(locatorValue);
		}
		if (locator.equalsIgnoreCase("tagName")) {
			return By.tagName(locatorValue);
		}
		if (locator.equalsIgnoreCase("xpath")) {
			return By.xpath(locatorValue);
		}
		if (locator.equalsIgnoreCase("className")) {
			return By.className(locatorValue);
		}
		if (locator.equalsIgnoreCase("cssSelector")) {
			return By.cssSelector(locatorValue);
		}
		// 如果传入元素定位类型错误，则关闭浏览器并退出执行
		this.driver.quit();
		throw new RuntimeException("当前使用过的元素定位符[" + locator + "]不正确,请检查");
	}

	private void checkIsElementExists(WebElement webElement, String locator) {
		if (null == webElement) {
			this.driver.quit();
			throw new NoSuchElementException(
					"no such element by [" + locator + "],please change locatorType[xpath,or the others]");
		}
	}

	private void checkIsElementExists(List<WebElement> webElements, String locator) {
		if (webElements.isEmpty()) {
			this.driver.quit();
			throw new NoSuchElementException(
					"no such element by [" + locator + "],please change locatorType[xpath,or the others]");
		}
	}
}
