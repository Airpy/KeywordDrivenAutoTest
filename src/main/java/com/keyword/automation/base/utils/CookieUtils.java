package com.keyword.automation.base.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;

import org.openqa.selenium.Cookie;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.common.Constants;

/**
 * Cookie工具类
 * 
 * @author airpy
 *
 */
public class CookieUtils {
	/**
	 * 把cookie写入cookie文件
	 * 
	 * @param cookies
	 *            cookie集合
	 */
	public static void writeCookiesToFile(Set<Cookie> cookies) {
		File cookieFile = new File(Constants.COOKIE_FILE_PATH);
		try {
			cookieFile.delete();
			cookieFile.createNewFile();
			FileWriter fileWriter = new FileWriter(cookieFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (Cookie cookie : cookies) {
				String strCookie = cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";"
						+ cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure() + ";"
						+ cookie.isHttpOnly();
				bufferedWriter.write(strCookie);
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从文件中读取cookie加载到WebDriver
	 */
	public static void readCookieFile() {
		try {
			File cookieFile = new File(Constants.COOKIE_FILE_PATH);
			FileReader fileReader = new FileReader(cookieFile);
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
					Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure, isHttpOnly);
					BrowserKeyword.addCookie(cookie);
				}
				bufferedReader.close();
				fileReader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
