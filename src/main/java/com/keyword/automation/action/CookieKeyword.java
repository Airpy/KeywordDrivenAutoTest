package com.keyword.automation.action;

import java.util.Set;

import org.openqa.selenium.Cookie;

import com.keyword.automation.base.browser.Browsers;
import com.keyword.automation.base.browser.WebCookies;

/**
 * 封装Cookie操作关键字
 * 
 * @author airpy
 *
 */
public class CookieKeyword {
	/**
	 * 通过cookieName、cookieValue添加Cookie
	 * 
	 * @param cookieName
	 *            Cookie名称
	 * @param CookieValue
	 *            Cookie值
	 */
	public static void addCookie(String cookieName, String CookieValue) {
		Browsers.getActiveBrowser().addCookie(cookieName, CookieValue);
	}

	/**
	 * 添加指定cookie
	 * 
	 * @param cookie
	 */
	public static void addCookie(Cookie cookie) {
		Browsers.getActiveBrowser().addCookie(cookie);
	}

	/**
	 * 获取所有Cookie
	 * 
	 * @return Cookie对象集合
	 */
	public static Set<Cookie> getAllCookies() {
		return Browsers.getActiveBrowser().getAllCookies();
	}

	/**
	 * 通过cookie名称获取cookie值
	 * 
	 * @param cookieName
	 *            cookie名称
	 * @return cookie值
	 */
	public static String getCookieValueByName(String cookieName) {
		return Browsers.getActiveBrowser().getCookieValueByName(cookieName);
	}

	/**
	 * 通过cookie名称获取cookie
	 * 
	 * @param cookieName
	 *            cookie名称
	 * @return cookie对象
	 */
	public static Cookie getCookieByName(String cookieName) {
		return Browsers.getActiveBrowser().getCookieByName(cookieName);
	}

	/**
	 * 通过Cookie名称删除对应Cookie
	 * 
	 * @param cookieName
	 *            Cookie名称
	 */
	public static void deleteCookieByName(String cookieName) {
		Browsers.getActiveBrowser().deleteCookieByName(cookieName);
	}

	/**
	 * 删除指定cookie
	 * 
	 * @param cookie
	 *            Cookie对象
	 */
	public static void deleteCookie(Cookie cookie) {
		Browsers.getActiveBrowser().deleteCookie(cookie);
	}

	/**
	 * 删除所有Cookie
	 */
	public static void deleteAllCookies() {
		Browsers.getActiveBrowser().deleteAllCookies();
	}

	/**
	 * 将制定cookie写入cookie文件
	 * 
	 * @param cookie
	 *            cookie文件路径
	 * @param isDeleteFirst
	 *            是否先删除cookie文件
	 */
	public static void writeCookieToFile(Cookie cookie, boolean isDeleteFirst) {
		WebCookies.writeCookieToFile(cookie, isDeleteFirst);
	}

	/**
	 * 将所有cookie写入cookie文件
	 * 
	 * @param cookies
	 *            cookie集合
	 */
	public static void writeAllCookiesToFile(Set<Cookie> cookies) {
		WebCookies.writeCookiesToFile(cookies);
	}

	/**
	 * 从Cookie文件中获取所有Cookie
	 * 
	 * @param cookieFile
	 *            Cookie文件名称
	 * @return
	 */
	public static Set<Cookie> getAllCookiesFromFile() {
		return WebCookies.getAllCookiesFromFile();
	}

	/**
	 * 根据Cookie名称获取Cookie值
	 * 
	 * @param cookieName
	 *            Cookie名称
	 * @return
	 */
	public static String getCookieByNameFromFile(String cookieName) {
		return null;
	}

	/**
	 * 从Cookie文件中通过Cookie名称获取Cookie值
	 * 
	 * @param cookieFile
	 *            Cookie文件名称
	 * @param cookieName
	 *            Cookie名称
	 * @return
	 */
	public static String getCookieByNameFromFile(String cookieFile, String cookieName) {
		return null;
	}

}
