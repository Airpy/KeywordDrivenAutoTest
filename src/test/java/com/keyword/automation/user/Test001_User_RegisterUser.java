package com.keyword.automation.user;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;

public class Test001_User_RegisterUser {
	public static void main(String[] args) throws InterruptedException {
		BrowserKeyword.browserOpen("chrome", "www.baidu.com");
		ElementKeyword.findElement("id", "333").sendKeys("2222");
		Thread.sleep(3000);
		BrowserKeyword.browserQuit();
	}
}
