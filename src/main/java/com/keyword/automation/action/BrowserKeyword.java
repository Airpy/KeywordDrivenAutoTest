package com.keyword.automation.action;

import com.keyword.automation.base.browser.BrowserType;
import com.keyword.automation.base.browser.Browsers;
import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.LogUtils;

/**
 * 封装浏览器操作关键字
 * 
 * @author airpy
 *
 */
public class BrowserKeyword {
	/**
	 * 使用默认浏览器打开指定url
	 * 
	 * @param requestUrl
	 *            请求url地址
	 */
	public static void browserOpen(String requestUrl) {
		BrowserType bType = BrowserType.valueOf(Constants.DEFAULT_BROWSER);
		String reqUrl = verifyRequestUrl(requestUrl);
		Browsers.createDriver(bType).open(reqUrl);
	}

	/**
	 * 根据传入浏览器类型打开指定url
	 * 
	 * @param browserType
	 *            浏览器类型
	 * @param requestUrl
	 *            请求url地址
	 */
	public static void browserOpen(String browserType, String requestUrl) {
		BrowserType bType = verifyBrowserType(browserType);
		String reqUrl = verifyRequestUrl(requestUrl);
		Browsers.createDriver(bType).open(reqUrl);
	}

	/**
	 * 验证传入浏览器类型
	 * 
	 * @param browserType
	 *            浏览器类型
	 * @return
	 */
	private static BrowserType verifyBrowserType(String browserType) {
		BrowserType bType;
		if (browserType.equalsIgnoreCase("Chrome")) {
			bType = BrowserType.Chrome;
		} else if (browserType.equalsIgnoreCase("Firefox")) {
			bType = BrowserType.Firefox;
		} else if (browserType.equalsIgnoreCase("IE")) {
			bType = BrowserType.IE;
		} else if (browserType.equalsIgnoreCase("Opera")) {
			bType = BrowserType.Opera;
		} else if (browserType.equalsIgnoreCase("Safari")) {
			bType = BrowserType.Safari;
		} else {
			bType = BrowserType.valueOf(Constants.DEFAULT_BROWSER);
			LogUtils.warn("您输入的浏览器类型错误，已经初始化Chrome浏览器.");
		}
		return bType;
	}

	/**
	 * 格式化请求URL
	 * 
	 * @param requestUrl
	 *            请求的url
	 * @return
	 */
	private static String verifyRequestUrl(String requestUrl) {
		String reqUrl;
		if (requestUrl.toLowerCase().startsWith("http://") || requestUrl.toLowerCase().startsWith("https://")) {
			reqUrl = requestUrl;
		} else {
			reqUrl = "http://" + requestUrl;
		}
		return reqUrl;
	}
}
