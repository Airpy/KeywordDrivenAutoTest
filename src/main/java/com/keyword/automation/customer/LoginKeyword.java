package com.keyword.automation.customer;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.LogUtils;
import org.openqa.selenium.By;

/**
 * 自定义系统登录关键字
 *
 * @author Amio_
 */
public class LoginKeyword {
    /**
     * 使用默认用户名、密码登录系统
     *
     * @return 登录成功返回True，否则返回False
     */
    public static boolean loginSystem() {
        BrowserKeyword.browserOpen(Constants.DEFAULT_BROWSER, Constants.TEST_URL);
        ElementKeyword.findElement(By.id("pwd"));
        ElementKeyword.sendKeys(By.id("userName"), Constants.TEST_USERNAME);
        ElementKeyword.sendKeys(By.id("pwd"), Constants.TEST_PASSWORD);
        ElementKeyword.clickElement(By.id("login_id"));
        return isLoginSuccess();
    }

    /**
     * 使用默认用户名、密码登录系统
     *
     * @param browserType 浏览器类型(Chrome/Firefox/IE/Opera/Safari)
     * @param requestUrl  请求url
     * @return 登录成功返回True，否则返回False
     */
    public static boolean loginSystem(String browserType, String requestUrl) {
        BrowserKeyword.browserOpen(browserType, requestUrl);
        ElementKeyword.findElement(By.id("pwd"));
        ElementKeyword.sendKeys(By.id("userName"), Constants.TEST_USERNAME);
        ElementKeyword.sendKeys(By.id("pwd"), Constants.TEST_PASSWORD);
        ElementKeyword.clickElement(By.id("login_id"));
        return isLoginSuccess();
    }

    /**
     * 使用传入用户名、密码登录系统
     *
     * @param browserType 浏览器类型(Chrome/Firefox/IE/Opera/Safari)
     * @param requestUrl  请求url
     * @param username    登录用户名
     * @param password    密码
     * @return 登录成功返回True，否则返回False
     */
    public static boolean loginSystem(String browserType, String requestUrl, String username, String password) {
        BrowserKeyword.browserOpen(browserType, requestUrl);
        ElementKeyword.findElement(By.id("pwd"));
        ElementKeyword.sendKeys(By.id("userName"), username);
        ElementKeyword.sendKeys(By.id("pwd"), password);
        ElementKeyword.clickElement(By.id("login_id"));
        return isLoginSuccess();
    }

    private static boolean isLoginSuccess() {
        String titleQQ = ElementKeyword.getAttribute(By.className("icon-qq"), "title");
        if (titleQQ.equals("请加为好友")) {
            LogUtils.info("登录系统成功.");
            return true;
        } else {
            LogUtils.error("登录系统失败.");
            return false;
        }
    }
}
