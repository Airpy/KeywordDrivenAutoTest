package com.keyword.automation.action;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.keyword.automation.base.browser.Browsers;

/**
 * 元素操作关键字
 *
 * @author airpy
 */
public class ElementKeyword {
    // 不允许被初始化
    private ElementKeyword() {

    }

    /**
     * 通过元素定位寻找元素
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     * @return WebElement
     */
    public static WebElement findElement(By by) {
        return Browsers.getActiveBrowser().findElement(by);
    }

    /**
     * 通过元素定位类型及元素定位值寻找元素
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/
     *                     className/cssSelector)
     * @param locatorValue 元素定位值
     * @return WebElement
     */
    public static WebElement findElement(String locator, String locatorValue) {
        return Browsers.getActiveBrowser().findElement(locator, locatorValue);
    }

    /**
     * 通过父元素定位类型及元素定位值及子元素定位类型及元素定位值寻找元素
     *
     * @param locator         父元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/
     *                        className/cssSelector)
     * @param locatorValue    父元素定位值
     * @param subLocator      子元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/
     *                        className/cssSelector)
     * @param subLocatorValue 子元素定位值
     * @return WebElement
     */
    public static WebElement findElement(String locator, String locatorValue, String subLocator,
                                         String subLocatorValue) {
        return Browsers.getActiveBrowser().findElement(locator, locatorValue, subLocator, subLocatorValue);
    }

    /**
     * 通过父元素定位及子元素定位寻找元素
     *
     * @param locator    父元素定位
     * @param subLocator 子元素定位
     * @return WebElement
     */
    public static WebElement findElement(By locator, By subLocator) {
        return Browsers.getActiveBrowser().findElement(locator, subLocator);
    }

    /**
     * 通过父元素WebElement寻找子元素
     *
     * @param parent 父元素
     * @param by     元素定位方式By(By.id(xx)/By.name(xx)等)
     * @return WebElement
     */
    public static WebElement findElement(WebElement parent, By by) {
        return Browsers.getActiveBrowser().findElement(parent, by);
    }

    /**
     * 通过父元素WebElement寻找子元素
     *
     * @param parent       父元素
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/
     *                     className/cssSelector)
     * @param locatorValue 子元素定位值
     * @return WebElement
     */
    public static WebElement findElement(WebElement parent, String locator, String locatorValue) {
        return Browsers.getActiveBrowser().findElement(parent, locator, locatorValue);
    }

    /**
     * 通过元素定位寻找元素
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     * @return WebElement组
     */
    public static List<WebElement> findElements(By by) {
        return Browsers.getActiveBrowser().findElements(by);
    }

    /**
     * 通过元素定位类型及元素定位值寻找元素组
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/
     *                     className/cssSelector)
     * @param locatorValue 元素定位值
     * @return WebElement组
     */
    public static List<WebElement> findElements(String locator, String locatorValue) {
        return Browsers.getActiveBrowser().findElements(locator, locatorValue);
    }

    /**
     * 通过父元素定位类型及元素定位值及子元素定位类型及元素定位值寻找元素
     *
     * @param locator         父元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue    父元素定位值
     * @param subLocator      子元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param subLocatorValue 子元素定位值
     * @return WebElement组
     */
    public static List<WebElement> findElements(String locator, String locatorValue, String subLocator, String subLocatorValue) {
        return Browsers.getActiveBrowser().findElements(locator, locatorValue, subLocator, subLocatorValue);
    }

    /**
     * 通过父元素定位及子元素定位寻找元素组
     *
     * @param locator    父元素定位
     * @param subLocator 子元素定位
     * @return WebElement组
     */
    public static List<WebElement> findElements(By locator, By subLocator) {
        return Browsers.getActiveBrowser().findElements(locator, subLocator);
    }

    /**
     * 通过父元素WebElement寻找子元素组
     *
     * @param parent 父元素
     * @param by     元素定位方式By(By.id(xx)/By.name(xx)等)
     * @return WebElement组
     */
    public static List<WebElement> findElements(WebElement parent, By by) {
        return Browsers.getActiveBrowser().findElements(parent, by);
    }

    /**
     * 通过父元素WebElement寻找子元素组
     *
     * @param parent       父元素
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/
     *                     className/cssSelector)
     * @param locatorValue 子元素定位值
     * @return WebElement组
     */
    public static List<WebElement> findElements(WebElement parent, String locator, String locatorValue) {
        return Browsers.getActiveBrowser().findElements(parent, locator, locatorValue);
    }
}
