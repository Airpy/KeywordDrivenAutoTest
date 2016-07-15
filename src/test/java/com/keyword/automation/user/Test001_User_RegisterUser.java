package com.keyword.automation.user;

import java.io.File;

import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.FileUtils;

public class Test001_User_RegisterUser {
	public static void main(String[] args) throws InterruptedException {
		// BrowserKeyword.browserOpen("chrome", "http://www.smzdm.com/");
		// Set<Cookie> cookies = BrowserKeyword.getAllCookies();
		// CookieUtils.writeCookiesToFile(cookies);
		// Thread.sleep(3000);
		// BrowserKeyword.browserQuit();
		// System.setProperty("webdriver.chrome.driver",
		// System.getProperty("user.dir") + Constants.CHROME_PATH_MAC);
		// WebDriver driver = new ChromeDriver();
		// driver.get("http://www.baidu.com");
		// driver.findElement(By.id("s_is_index_css"));
		if (FileUtils.createNewFile("E:\\aaa\\bbb\\a.txt")) {
			System.out.println(System.getProperty("user.dir"));
			System.out.println(111);
		} else {
			System.out.println(222);
			File file = new File(System.getProperty("user.dir") + Constants.CHROME_PATH_WIN);
			System.out.println(System.getProperty("user.dir") + Constants.CHROME_PATH_WIN);
			System.out.println(file.exists());
		}
	}
}
