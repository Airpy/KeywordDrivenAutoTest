package com.keyword.automation.base.browser;

import com.keyword.automation.dirver.Chrome;
import com.keyword.automation.dirver.Firefox;
import com.keyword.automation.dirver.InternetExplorer;

/**
 * 创建不同类型的driver
 * 
 * @author airpy
 *
 */
public class Browsers {
	private static WebBrowser activeBrowser;

	public static WebBrowser getActiveBrowser() {
		return activeBrowser;
	}

	public static void setActiveBrowser(WebBrowser activeBrowser) {
		Browsers.activeBrowser = activeBrowser;
	}

	/**
	 * 创建本地浏览器driver
	 * 
	 * @param browserType
	 *            浏览器类型
	 * @return WebBrowser对象
	 */
	public static WebBrowser createDriver(BrowserType browserType) {
		WebBrowser webBrowser;
		switch (browserType) {
		case Chrome:
			webBrowser = new Chrome();
			break;
		case Firefox:
			webBrowser = new Firefox();
			break;
		case IE:
			webBrowser = new InternetExplorer();
		default:
			webBrowser = new Chrome();
			break;
		}
		setActiveBrowser(webBrowser);
		return webBrowser;
	}

	/**
	 * 创建远程浏览器driver
	 * 
	 * @param browserType
	 *            browserType 浏览器类型
	 * @param remoteUrl
	 *            远程地址
	 * @return WebBrowser对象
	 */
	public static WebBrowser createDriver(BrowserType browserType, String remoteUrl) {
		WebBrowser webBrowser;
		switch (browserType) {
		case Chrome:
			webBrowser = new Chrome(remoteUrl);
			break;
		case Firefox:
			webBrowser = new Firefox(remoteUrl);
			break;
		case IE:
			webBrowser = new InternetExplorer(remoteUrl);
		default:
			webBrowser = new Chrome(remoteUrl);
			break;
		}
		setActiveBrowser(webBrowser);
		return webBrowser;
	}
}
