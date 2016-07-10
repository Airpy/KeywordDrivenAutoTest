package com.keyword.automation.user;

import java.util.Set;

import org.openqa.selenium.Cookie;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.utils.CookieUtils;

public class Test001_User_RegisterUser {
	public static void main(String[] args) throws InterruptedException {
		BrowserKeyword.browserOpen("chrome", "http://www.smzdm.com/");
		Set<Cookie> cookies = BrowserKeyword.getAllCookies();
		CookieUtils.writeCookiesToFile(cookies);
		Thread.sleep(3000);
		BrowserKeyword.browserQuit();
		// System.setProperty("webdriver.chrome.driver",
		// System.getProperty("user.dir") + Constants.CHROME_PATH_MAC);
		// WebDriver driver = new ChromeDriver();
		// driver.get("http://www.baidu.com");
		// driver.findElement(By.id("s_is_index_css"));
	}
}
