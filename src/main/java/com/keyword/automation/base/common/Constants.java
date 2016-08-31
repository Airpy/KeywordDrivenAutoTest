package com.keyword.automation.base.common;

/**
 * 系统常量类
 *
 * @author Amio_
 */
public class Constants {
    /**
     * Chrome浏览器driver的存放路径(MAC)
     */
    public static final String CHROME_PATH_MAC = "/src/main/resources/static/driver/chromedriver";

    /**
     * Chrome浏览器driver的存放路径(WIN)
     */
    public static final String CHROME_PATH_WIN = "/src/main/resources/static/driver/chromedriver.exe";

    /**
     * IE浏览器driver的存放路径
     */
    public static final String IE_PATH = "/src/main/resources/static/driver/IEDriverServer.exe";

    /**
     * 默认浏览器类型
     */
    public static final String DEFAULT_BROWSER = "Chrome";

    /**
     * Cookie存放路径
     */
    public static final String COOKIE_FILE_PATH = "/src/main/resources/static/cookie/cookie.txt";

    /**
     * 加载页面元素超时时间(秒)
     */
    public static final int TIMEOUT_SECONDS = 30;

    /**
     * 测试环境地址
     */
    public static final String TEST_URL = "https://www.zhoupu123.com/saas/tologin";

    /**
     * 测试环境登录用户名
     */
    public static final String TEST_USERNAME = "13951684793";

    /**
     * 测试环境登录用户密码
     */
    public static final String TEST_PASSWORD = "123456qq";
}
