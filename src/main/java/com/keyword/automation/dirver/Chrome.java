package com.keyword.automation.dirver;

import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.keyword.automation.base.browser.WebBrowser;
import com.keyword.automation.base.common.Constants;

/**
 * Chrome Webdriver生成器
 * 
 * @author airpy
 * 
 */
public class Chrome extends WebBrowser {
	/**
	 * 本地初始化Chrome浏览器driver
	 */
	public Chrome() {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + Constants.CHROME_PATH_WIN);
		} else {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + Constants.CHROME_PATH_MAC);
		}
		this.driver = new ChromeDriver();
	}

	/**
	 * 使用remoteWebDriver远程初始化Chrome浏览器driver(暂未调测)
	 * 
	 * @param remoteUrl
	 *            远程服务器的URL
	 */
	public Chrome(String remoteUrl) {
		try {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			this.driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);
		} catch (Throwable e) {
			throw new RuntimeException("Failed to start remote Chrome driver.", e);
		}
	}
}
