package com.keyword.automation.dirver;

import java.net.URL;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.keyword.automation.base.browser.WebBrowser;
import com.keyword.automation.base.common.Constants;

/**
 * Internet Explorer WebDriver生成器
 *
 * @author Amio_
 */
public class InternetExplorer extends WebBrowser {
    /**
     * 本地初始化IE浏览器driver
     */
    public InternetExplorer() {
        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + Constants.IE_PATH);
        InternetExplorerDriverService service = (InternetExplorerDriverService) ((InternetExplorerDriverService.Builder) new InternetExplorerDriverService.Builder()
                .usingAnyFreePort()).build();
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);// 关闭保护模式，IE默认为开启模式
        capabilities.setCapability("unexpectedAlertBehaviour", "accept");
        this.driver = new InternetExplorerDriver(service, capabilities);
    }

    /**
     * 使用remoteWebDriver远程初始化IE浏览器driver(暂未调测)
     *
     * @param remoteUrl 远程服务器的URL
     */
    public InternetExplorer(String remoteUrl) {
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            this.driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);
        } catch (Throwable e) {
            throw new RuntimeException("Failed to start remote IE driver.", e);
        }
    }
}
