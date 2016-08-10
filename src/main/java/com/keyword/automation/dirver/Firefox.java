package com.keyword.automation.dirver;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.keyword.automation.base.browser.FirefoxBrowserProfile;
import com.keyword.automation.base.browser.WebBrowser;

/**
 * Firefox Webdriver生成器
 *
 * @author Amio_
 */
public class Firefox extends WebBrowser {
    /**
     * 本地初始化Firefox浏览器driver
     */
    public Firefox() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("acceptSslCerts", false);
        FirefoxBrowserProfile firefoxProfile = new FirefoxBrowserProfile();
        String sProfile = firefoxProfile.getDefaultProfile();
        if (sProfile.equals("")) {
            this.driver = new FirefoxDriver();
        } else {
            try {
                FirefoxProfile profile = new FirefoxProfile(new File(sProfile));
                FirefoxBinary firefoxBinary = new FirefoxBinary(new File(firefoxProfile.getFirefoxBinInstallPath()));
                profile.setAcceptUntrustedCertificates(false);
                this.driver = new FirefoxDriver(firefoxBinary, profile);
            } catch (Exception e) {
                throw new RuntimeException("Failed to start firefox browser,please check!", e);
            }
        }
    }

    /**
     * 使用remoteWebDriver远程初始化Firefox浏览器driver(暂未调测)
     *
     * @param remoteUrl 远程服务器的URL
     */
    public Firefox(String remoteUrl) {
        try {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
            this.driver = new RemoteWebDriver(new URL(remoteUrl), desiredCapabilities);
        } catch (Throwable e) {
            throw new RuntimeException("Failed to start firefox browser,please check!", e);
        }
    }
}
