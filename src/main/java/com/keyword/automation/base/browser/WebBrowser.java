package com.keyword.automation.base.browser;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.keyword.automation.action.ElementKeyword;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.LogUtils;

/**
 * 浏览器操作实现类
 *
 * @author airpy
 */
public class WebBrowser {
    protected WebDriver driver;
    private static final String mouseHoverjs = "var evObj = document.createEvent('MouseEvents');" +
            "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, " +
            "null);" +
            "arguments[0].dispatchEvent(evObj);";

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String requestUrl) {
        this.driver.get(requestUrl);
        try {
            this.driver.manage().window().maximize();
        } catch (Exception e) {
            LogUtils.error("browser does not support maximize");
        }
    }

    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    public String getCurrentTitle() {
        return this.driver.getTitle();
    }

    public String getPageSource() {
        return this.driver.getPageSource();
    }

    public void browserClose() {
        this.driver.close();
    }

    public void browserQuit() {
        this.driver.quit();
    }

    public void switchToWindow() {
        String currentWindow = this.driver.getWindowHandle();
        Set<String> windowHandles = this.driver.getWindowHandles();
        Iterator<String> it = windowHandles.iterator();
        while (it.hasNext()) {
            if (currentWindow.equals(it.next()))
                continue;
            this.driver.switchTo().window(it.next());
        }
    }

    public void switchToWindow(String windowTitle) {
        long start = System.currentTimeMillis();
        long maxWaitTime = 15000L;
        do {
            try {
                Set<String> windowHandles = this.driver.getWindowHandles();
                LogUtils.debug("all window handles: " + windowHandles);
                for (String windowHandle : windowHandles) {
                    this.driver.switchTo().window(windowHandle);
                    String currentTitle = this.driver.getTitle();
                    LogUtils.info(
                            "Try to check window <title: " + currentTitle + ", expect window: " + windowHandle + ">.");
                    if (((windowTitle.equals("")) && (currentTitle.equals("")))
                            || ((!windowTitle.equals("")) && (currentTitle.contains(windowTitle)))) {
                        LogUtils.debug("switch to window: " + currentTitle + "success.");
                        return;
                    }
                }
            } catch (Exception e) {
                LogUtils.debug("switch window failed, please check: " + windowTitle + " first");
            }
        } while (System.currentTimeMillis() - start < maxWaitTime);
        throw new NoSuchWindowException("Window <Title:" + windowTitle + "> is not exist in browser.");
    }

    public void switchToDefaultFrame() {
        this.driver.switchTo().defaultContent();
    }

    public void switchToFrame(int index) {
        this.driver.switchTo().frame(index);
    }

    public void switchToFrame(Object obj) {
        if (obj instanceof String) {
            this.driver.switchTo().frame(obj.toString());
        } else if (obj instanceof Integer) {
            this.driver.switchTo().frame(Integer.parseInt(obj.toString()));
        }
    }

    public void switchToFrame(String locator, String locatorValue) {
        WebElement webElement = null;
        By ByLocator = verifyLocator(locator, locatorValue);
        try {
            webElement = this.driver.findElement(ByLocator);
        } catch (Exception e) {
        }
        checkIsElementExists(webElement, locatorValue);
        this.driver.switchTo().frame(webElement);
    }

    public void switchToFrame(WebElement webElement) {
        this.driver.switchTo().frame(webElement);
    }

    public void switchToParentFrame() {
        this.driver.switchTo().parentFrame();
    }

    public void browserBack() {
        this.driver.navigate().back();
    }

    public void browserForward() {
        this.driver.navigate().forward();
    }

    public void BrowserTo(String requestUrl) {
        this.driver.navigate().to(requestUrl);
    }

    public void BrowserTo(URL requestUrl) {
        this.driver.navigate().to(requestUrl);
    }

    public void browserRefresh() {
        this.driver.navigate().refresh();
    }

    public void browserMax() {
        this.driver.manage().window().maximize();
    }

    public void addCookie(String cookieName, String CookieValue) {
        Cookie cookie = new Cookie(cookieName, CookieValue);
        this.driver.manage().addCookie(cookie);
    }

    public void addCookie(Cookie cookie) {
        this.driver.manage().addCookie(cookie);
    }

    public void deleteCookieByName(String cookieName) {
        this.driver.manage().deleteCookieNamed(cookieName);
    }

    public void deleteCookie(Cookie cookie) {
        this.driver.manage().deleteCookie(cookie);
    }

    public void deleteAllCookies() {
        this.driver.manage().deleteAllCookies();
    }

    public Set<Cookie> getAllCookies() {
        return this.driver.manage().getCookies();
    }

    public String getCookieValueByName(String cookieName) {
        Cookie cookie = this.driver.manage().getCookieNamed(cookieName);
        if (null == cookie) {
            throw new RuntimeException("通过[" + cookieName + "]没有找到对应的cookie值");
        } else {
            return cookie.getValue();
        }
    }

    public Cookie getCookieByName(String cookieName) {
        Cookie cookie = this.driver.manage().getCookieNamed(cookieName);
        if (null == cookie) {
            throw new RuntimeException("通过[" + cookieName + "]没有找到对应的cookie值");
        } else {
            return cookie;
        }
    }

    public WebElement findBody() {
        WebElement webElement = null;
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素
            webElement = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//body")));
        } catch (Exception e) {
        }
        checkIsElementExists(webElement);
        return webElement;
    }

    public WebElement findElement(final By by) {
        WebElement webElement = null;
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素
            webElement = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
        }
        checkIsElementExists(webElement);
        return webElement;
    }

    public WebElement findElement(String locator, String locatorValue) {
        WebElement webElement = null;
        By ByLocator = verifyLocator(locator, locatorValue);
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素
            webElement = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(ByLocator));
        } catch (Exception e) {
        }
        checkIsElementExists(webElement, locatorValue);
        return webElement;
    }

    public WebElement findElement(String locator, String locatorValue, String subLocator, String subLocatorValue) {
        WebElement webElement = null;
        By ByLocator = verifyLocator(locator, locatorValue);
        By subByLocator = verifyLocator(subLocator, subLocatorValue);
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素
            webElement = (WebElement) getWebDriverWait()
                    .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(ByLocator, subByLocator));
        } catch (Exception e) {
        }
        checkIsElementExists(webElement, locatorValue);
        return webElement;
    }

    public WebElement findElement(By locator, By subLocator) {
        WebElement webElement = null;
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素
            webElement = (WebElement) getWebDriverWait()
                    .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(locator, subLocator));
        } catch (Exception e) {
        }
        checkIsElementExists(webElement);
        return webElement;
    }

    public WebElement findElement(WebElement parent, By by) {
        WebElement webElement = null;
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素
            webElement = (WebElement) getWebDriverWait()
                    .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, by));
        } catch (Exception e) {
        }
        checkIsElementExists(webElement);
        return webElement;
    }

    public WebElement findElement(WebElement parent, String locator, String locatorValue) {
        WebElement webElement = null;
        By ByLocator = verifyLocator(locator, locatorValue);
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素
            webElement = (WebElement) getWebDriverWait()
                    .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, ByLocator));
        } catch (Exception e) {
        }
        checkIsElementExists(webElement, locatorValue);
        return webElement;
    }

    public List<WebElement> findElements(By by) {
        List<WebElement> webElements = new ArrayList<WebElement>();
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素组
            webElements = getWebDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Exception e) {
        }
        checkIsElementExists(webElements);
        return webElements;
    }

    public List<WebElement> findElements(String locator, String locatorValue) {
        List<WebElement> webElements = new ArrayList<WebElement>();
        By ByLocator = verifyLocator(locator, locatorValue);
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素组
            webElements = getWebDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByLocator));
        } catch (Exception e) {
        }
        checkIsElementExists(webElements, locatorValue);
        return webElements;
    }

    public List<WebElement> findElements(String locator, String locatorValue, String subLocator, String
            subLocatorValue) {
        List<WebElement> webElements = new ArrayList<WebElement>();
        By ByLocator = verifyLocator(locator, locatorValue);
        By BySubLocator = verifyLocator(subLocator, subLocatorValue);
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素组
            webElements = getWebDriverWait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(ByLocator,
                    BySubLocator));
        } catch (Exception e) {
        }
        checkIsElementExists(webElements, locatorValue);
        return webElements;
    }

    public List<WebElement> findElements(By ByLocator, By BySubLocator) {
        List<WebElement> webElements = new ArrayList<WebElement>();
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素组
            webElements = getWebDriverWait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(ByLocator,
                    BySubLocator));
        } catch (Exception e) {
        }
        checkIsElementExists(webElements);
        return webElements;
    }

    public List<WebElement> findElements(WebElement parent, By by) {
        List<WebElement> webElements = new ArrayList<WebElement>();
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素组
            webElements = getWebDriverWait().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, by));
        } catch (Exception e) {
        }
        checkIsElementExists(webElements);
        return webElements;
    }

    public List<WebElement> findElements(WebElement parent, String locator, String locatorValue) {
        List<WebElement> webElements = new ArrayList<WebElement>();
        By ByLocator = verifyLocator(locator, locatorValue);
        try {
            // 一直等到页面元素可见及长、宽大于0即返回该元素组
            webElements = getWebDriverWait()
                    .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, ByLocator));
        } catch (Exception e) {
        }
        checkIsElementExists(webElements, locatorValue);
        return webElements;
    }

    public void switchToActiveElement() {
        this.driver.switchTo().activeElement();
    }

    // 等待30s，每0.5s检测1次，当元素存在后停止检测
    public FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<WebDriver>(this.driver)
                .withTimeout(Constants.TIMEOUT_SECONDS, TimeUnit.MILLISECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
    }

    // 等待timeoutSeconds(通过传参)，每0.5s检测1次，当元素存在后停止检测
    public FluentWait<WebDriver> getFluentWait(int timeoutSeconds) {
        return new FluentWait<WebDriver>(this.driver)
                .withTimeout(timeoutSeconds, TimeUnit.MILLISECONDS).pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
    }

    /**
     * 定义页面等待超时时间(常量定义最大超时时间)
     *
     * @return WebDriverWait
     */
    public WebDriverWait getWebDriverWait() {
        return new WebDriverWait(this.driver, Constants.TIMEOUT_SECONDS);
    }

    /**
     * 定义页面等待超时时间
     *
     * @param timeoutSeconds 自定义最大超时时间
     * @return WebDriverWait
     */
    public WebDriverWait getWebDriverWait(int timeoutSeconds) {
        return new WebDriverWait(this.driver, timeoutSeconds);
    }

    /**
     * 定义页面等待超时时间
     *
     * @param timeoutSeconds 自定义最大超时时间
     * @param sleepInMillis  循环寻找元素时的睡眠时间(毫秒)
     * @return WebDriverWait
     */
    public WebDriverWait getWebDriverWait(int timeoutSeconds, int sleepInMillis) {
        return new WebDriverWait(this.driver, timeoutSeconds, sleepInMillis);
    }

    public Alert switchToAlert() {
        return this.driver.switchTo().alert();
    }

    public void acceptAlert(Alert alert) {
        alert.accept();
    }

    public void dismissAlert(Alert alert) {
        alert.dismiss();
    }

    public void moveToElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript(mouseHoverjs, webElement);
    }

    public void moveToElement(By by) {
        WebElement webElement = ElementKeyword.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript(mouseHoverjs, webElement);
    }

    public void moveToElement(String locator, String locatorValue) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript(mouseHoverjs, webElement);
    }

    private By verifyLocator(String locator, String locatorValue) {
        if (locator.equalsIgnoreCase("id")) {
            return By.id(locatorValue);
        }
        if (locator.equalsIgnoreCase("name")) {
            return By.name(locatorValue);
        }
        if (locator.equalsIgnoreCase("linkText")) {
            return By.linkText(locatorValue);
        }
        if (locator.equalsIgnoreCase("partialLinkText")) {
            return By.partialLinkText(locatorValue);
        }
        if (locator.equalsIgnoreCase("tagName")) {
            return By.tagName(locatorValue);
        }
        if (locator.equalsIgnoreCase("xpath")) {
            return By.xpath(locatorValue);
        }
        if (locator.equalsIgnoreCase("className")) {
            return By.className(locatorValue);
        }
        if (locator.equalsIgnoreCase("cssSelector")) {
            return By.cssSelector(locatorValue);
        }
        // 如果传入元素定位类型错误，则关闭浏览器并退出执行
        this.driver.quit();
        throw new RuntimeException("当前使用过的元素定位符[" + locator + "]不正确,请检查");
    }

    // 检查元素是否存在
    private void checkIsElementExists(WebElement webElement, String locator) {
        if (null == webElement) {
            this.driver.quit();
            throw new NoSuchElementException(
                    "no such element by [" + locator + "],please change locatorType[xpath,or the others]");
        }
    }

    private void checkIsElementExists(WebElement webElement) {
        if (null == webElement) {
            this.driver.quit();
            throw new NoSuchElementException("no such element, please change locatorType[xpath,or the others]");
        }
    }

    // 检查元素组是否存在
    private void checkIsElementExists(List<WebElement> webElements, String locator) {
        if (webElements.isEmpty()) {
            this.driver.quit();
            throw new NoSuchElementException(
                    "no such element by [" + locator + "],please change locatorType[xpath,or the others]");
        }
    }

    private void checkIsElementExists(List<WebElement> webElements) {
        if (webElements.isEmpty()) {
            this.driver.quit();
            throw new NoSuchElementException("no such element, please change locatorType[xpath,or the others]");
        }
    }
}
