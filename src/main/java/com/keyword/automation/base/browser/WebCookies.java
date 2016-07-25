package com.keyword.automation.base.browser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;

import org.openqa.selenium.Cookie;

import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.FileUtils;

/**
 * Cookie关键字实现类
 * 
 * @author airpy
 *
 */
public class WebCookies {
	// 拼装cookie文件绝对路径
	public static final String cookiePath = System.getProperty("user.dir") + Constants.COOKIE_FILE_PATH;

	/**
	 * 把cookie追加写入cookie文件
	 * 
	 * @param isDeleteFirst
	 *            是否先删除cookie文件
	 */
	public static void writeCookieToFile(Cookie cookie, boolean isDeleteFirst) {
		String strCookie = cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";"
				+ cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure() + ";" + cookie.isHttpOnly();
		if (isDeleteFirst) {
			FileUtils.deleteFile(cookiePath);
			FileUtils.createFile(cookiePath);
		}
		FileUtils.writeFile(cookiePath, strCookie);
	}

	/**
	 * 把cookies写入cookie文件
	 * 
	 * @param cookies
	 *            cookie集合
	 */
	public static void writeCookiesToFile(Set<Cookie> cookies) {
		FileUtils.deleteFile(cookiePath);
		FileUtils.createFile(cookiePath);
		FileUtils.writeFile(cookiePath, cookies);
	}

	/**
	 * 从文件中读取cookie加载到WebDriver
	 */
	@SuppressWarnings({ "null", "finally" })
	public static Set<Cookie> getAllCookiesFromFile() {
		Cookie cookie = null;
		Set<Cookie> cookies = null;
		try {
			FileReader fileReader = new FileReader(cookiePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while (null != (line = bufferedReader.readLine())) {
				StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
				while (stringTokenizer.hasMoreTokens()) {
					String name = stringTokenizer.nextToken();
					String value = stringTokenizer.nextToken();
					String domain = stringTokenizer.nextToken();
					String path = stringTokenizer.nextToken();
					Date expiry = null;
					String date;
					if (!(date = stringTokenizer.nextToken()).equals("null")) {
						expiry = new Date(date);
					}
					Boolean isSecure = new Boolean(stringTokenizer.nextToken()).booleanValue();
					Boolean isHttpOnly = new Boolean(stringTokenizer.nextToken()).booleanValue();
					cookie = new Cookie(name, value, domain, path, expiry, isSecure, isHttpOnly);
					cookies.add(cookie);
				}
				bufferedReader.close();
				fileReader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return cookies;
		}
	}
}
