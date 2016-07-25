package com.keyword.automation.user;

import java.util.Set;

import org.openqa.selenium.Cookie;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.CookieKeyword;

public class Test001_User_RegisterUser {
	public static void main(String[] args) throws InterruptedException {
//		 BrowserKeyword.browserOpen("chrome", "http://www.smzdm.com/");
//		 Set<Cookie> cookies = CookieKeyword.getAllCookies();
//		 CookieKeyword.writeAllCookiesToFile(cookies);
//		 Thread.sleep(3000);
//		 BrowserKeyword.browserQuit();
		Set<Cookie> cookies = CookieKeyword.getAllCookiesFromFile();
		System.out.println(cookies);
	}
}
