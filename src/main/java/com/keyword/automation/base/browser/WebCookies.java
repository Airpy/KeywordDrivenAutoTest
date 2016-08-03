package com.keyword.automation.base.browser;

import java.util.Set;

import org.openqa.selenium.Cookie;

import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.FileUtils;

/**
 * Cookie关键字实现类
 *
 * @author airpy
 */
public class WebCookies {
    // 拼装cookie文件绝对路径
    public static final String cookiePath = System.getProperty("user.dir") + Constants.COOKIE_FILE_PATH;

    /**
     * 把cookie追加写入cookie文件
     *
     * @param isDeleteFirst 是否先删除cookie文件
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
     * @param cookies cookie集合
     */
    public static void writeCookiesToFile(Set<Cookie> cookies) {
        FileUtils.deleteFile(cookiePath);
        FileUtils.createFile(cookiePath);
        FileUtils.writeFile(cookiePath, cookies);
    }

    /**
     * 从文件中读取cookie加载到WebDriver
     */
    public static Set<Cookie> getAllCookiesFromFile() {
        return FileUtils.getAllCookiesFromFile(cookiePath);
    }

    /**
     * 从Cookie文件中根据Cookie名称获取Cookie值
     *
     * @param cookieName Cookie名称
     * @return Cookie值
     */
    public static String getCookieValueByNameFromFile(String cookieName) {
        Set<Cookie> cookies = FileUtils.getAllCookiesFromFile(cookiePath);
        String cookieValue = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(cookieName)) {
                cookieValue = cookie.getValue();
                break;
            }
        }
        if (null != cookieValue) {
            return cookieValue;
        } else {
            throw new RuntimeException("cookie文件中不存在cookie名为[" + cookieName + "]的cookie!");
        }
    }

    /**
     * 从Cookie文件中根据Cookie名称获取Cookie
     *
     * @param cookieName Cookie名称
     * @return Cookie对象
     */
    public static Cookie getCookieByNameFromFile(String cookieName) {
        Set<Cookie> cookies = FileUtils.getAllCookiesFromFile(cookiePath);
        Cookie tempCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(cookieName)) {
                tempCookie = cookie;
                break;
            }
        }
        if (null != tempCookie) {
            return tempCookie;
        } else {
            throw new RuntimeException("cookie文件中不存在cookie名为[" + cookieName + "]的cookie!");
        }
    }
}
