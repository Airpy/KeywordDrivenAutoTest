package com.keyword.automation.action;

import java.util.List;

import com.keyword.automation.base.utils.LogUtils;
import org.openqa.selenium.*;

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
     * 获取页面Body元素
     *
     * @return 页面Body元素
     */
    public static WebElement findBody() {
        return Browsers.getActiveBrowser().findBody();
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
    public static List<WebElement> findElements(String locator, String locatorValue, String subLocator, String
            subLocatorValue) {
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

    /**
     * 切换到当前活跃的webElement或当前页面body
     */
    public static void switchToActiveElement() {
        Browsers.getActiveBrowser().switchToActiveElement();
    }

    /**
     * 点击页面元素
     *
     * @param webElement 页面元素
     */
    public static void clickElement(WebElement webElement) {
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.click();
        }
    }

    /**
     * 通过元素定位寻找元素后点击该元素
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     */
    public static void clickElement(By by) {
        WebElement webElement = ElementKeyword.findElement(by);
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.click();
        }
    }

    /**
     * 通过元素定位类型及元素定位值寻找元素
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     */
    public static void clickElement(String locator, String locatorValue) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.click();
        }
    }

    /**
     * 提交指定元素所在的Form
     *
     * @param webElement 页面元素
     */
    public static void submitForm(WebElement webElement) {
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.submit();
        }
    }

    /**
     * 提交指定元素所在的Form
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     */
    public static void submitForm(By by) {
        WebElement webElement = ElementKeyword.findElement(by);
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.submit();
        }
    }

    /**
     * 提交指定元素所在的Form
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     */
    public static void submitForm(String locator, String locatorValue) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.submit();
        }
    }

    /**
     * 在指定元素文本框中输入内容
     *
     * @param webElement 指定页面元素
     * @param inputKeys  输入的内容
     */
    public static void sendKeys(WebElement webElement, String inputKeys) {
        webElement.clear();
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.sendKeys(inputKeys);
        }
    }

    /**
     * 在指定元素文本框中输入内容
     *
     * @param by        元素定位方式By(By.id(xx)/By.name(xx)等)
     * @param inputKeys 输入的内容
     */
    public static void sendKeys(By by, String inputKeys) {
        WebElement webElement = ElementKeyword.findElement(by);
        webElement.clear();
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.sendKeys(inputKeys);
        }
    }

    /**
     * 在指定元素文本框中输入内容
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     * @param inputKeys    输入的内容
     */
    public static void sendKeys(String locator, String locatorValue, String inputKeys) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        webElement.clear();
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.sendKeys(inputKeys);
        }
    }

    /**
     * 清空指定页面元素
     *
     * @param webElement 指定页面元素
     */
    public static void clearElement(WebElement webElement) {
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.clear();
        }
    }

    /**
     * 清空指定页面元素
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     */
    public static void clearElement(By by) {
        WebElement webElement = ElementKeyword.findElement(by);
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.clear();
        }
    }

    /**
     * 清空指定页面元素
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     */
    public static void clearElement(String locator, String locatorValue) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        if (checkElementDisplayedAndEnabled(webElement)) {
            webElement.clear();
        }
    }

    /**
     * 获取指定页面元素的TagName
     *
     * @param webElement 指定页面元素
     * @return 指定页面元素的TagName
     */
    public static String getTagName(WebElement webElement) {
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getTagName();
        } else {
            LogUtils.error("该页面元素不可见，无法获取TagName.");
            throw new RuntimeException("获取TagName失败.");
        }
    }

    /**
     * 获取指定页面元素的TagName
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     * @return 指定页面元素的TagName
     */
    public static String getTagName(By by) {
        WebElement webElement = ElementKeyword.findElement(by);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getTagName();
        } else {
            LogUtils.error("该页面元素不可见，无法获取TagName.");
            throw new RuntimeException("获取TagName失败.");
        }
    }

    /**
     * 获取指定页面元素的TagName
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     * @return 指定页面元素的TagName
     */
    public static String getTagName(String locator, String locatorValue) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getTagName();
        } else {
            LogUtils.error("该页面元素不可见，无法获取TagName.");
            throw new RuntimeException("获取TagName失败.");
        }
    }

    /**
     * 根据属性名称获取指定页面元素的属性值
     *
     * @param webElement    指定页面元素
     * @param attributeName 属性名称
     * @return 指定页面元素该属性名称的属性值
     */
    public static String getAttribute(WebElement webElement, String attributeName) {
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getAttribute(attributeName);
        } else {
            LogUtils.error("该页面元素不可见，无法获取属性值.");
            throw new RuntimeException("获取属性值失败.");
        }
    }

    /**
     * 根据属性名称获取指定页面元素的属性值
     *
     * @param by            元素定位方式By(By.id(xx)/By.name(xx)等)
     * @param attributeName 属性名称
     * @return 指定页面元素该属性名称的属性值
     */
    public static String getAttribute(By by, String attributeName) {
        WebElement webElement = ElementKeyword.findElement(by);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getAttribute(attributeName);
        } else {
            LogUtils.error("该页面元素不可见，无法获取属性值.");
            throw new RuntimeException("获取属性值失败.");
        }
    }

    /**
     * 根据属性名称获取指定页面元素的属性值
     *
     * @param locator       元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue  元素定位值
     * @param attributeName 属性名称
     * @return 指定页面元素该属性名称的属性值
     */
    public static String getAttribute(String locator, String locatorValue, String attributeName) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getAttribute(attributeName);
        } else {
            LogUtils.error("该页面元素不可见，无法获取属性值.");
            throw new RuntimeException("获取属性值失败.");
        }
    }

    /**
     * 判断指定页面元素单选框、复选框等是否被选择
     *
     * @param webElement 指定页面元素
     * @return 如果被选择返回true，否则返回false
     */
    public static boolean isSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    /**
     * 判断指定页面元素单选框、复选框等是否被选择
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     * @return 如果被选择返回true，否则返回false
     */
    public static boolean isSelected(By by) {
        WebElement webElement = ElementKeyword.findElement(by);
        return webElement.isSelected();
    }

    /**
     * 判断指定页面元素单选框、复选框等是否被选择
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorVluae 元素定位值
     * @return 如果被选择返回true，否则返回false
     */
    public static boolean isSelected(String locator, String locatorVluae) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorVluae);
        return webElement.isSelected();
    }

    /**
     * 获取指定页面元素文本框的内容(被CSS隐藏的元素获取不到)
     *
     * @param webElement 指定页面元素
     * @return 文本框内容
     */
    public static String getText(WebElement webElement) {
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getText();
        } else {
            LogUtils.error("该页面元素不可见，无法获取文本框内容.");
            throw new RuntimeException("获取文本框内容失败.");
        }
    }

    /**
     * 获取指定页面元素文本框的内容(被CSS隐藏的元素获取不到)
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     * @return 文本框内容
     */
    public static String getText(By by) {
        WebElement webElement = ElementKeyword.findElement(by);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getText();
        } else {
            LogUtils.error("该页面元素不可见，无法获取文本框内容.");
            throw new RuntimeException("获取文本框内容失败.");
        }
    }

    /**
     * 获取指定页面元素文本框的内容(被CSS隐藏的元素获取不到)
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     * @return 文本框内容
     */
    public static String getText(String locator, String locatorValue) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getText();
        } else {
            LogUtils.error("该页面元素不可见，无法获取文本框内容.");
            throw new RuntimeException("获取文本框内容失败.");
        }
    }

    /**
     * 获取指定元素在整个页面的坐标点
     *
     * @param webElement 指定页面元素
     * @return 元素坐标点
     */
    public static Point getLocation(WebElement webElement) {
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getLocation();
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 获取指定元素在整个页面的坐标点
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     * @return 元素坐标点
     */
    public static Point getLocation(By by) {
        WebElement webElement = ElementKeyword.findElement(by);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getLocation();
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 获取指定元素在整个页面的坐标点
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     * @return 元素坐标点
     */
    public static Point getLocation(String locator, String locatorValue) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getLocation();
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 获取指定元素的长、宽
     *
     * @param webElement 指定页面元素
     * @return 元素长、宽
     */
    public static Dimension getSize(WebElement webElement) {
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getSize();
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 获取指定元素的长、宽
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     * @return 元素长、宽
     */
    public static Dimension getSize(By by) {
        WebElement webElement = ElementKeyword.findElement(by);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getSize();
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 获取指定元素的长、宽
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     * @return 元素长、宽
     */
    public static Dimension getSize(String locator, String locatorValue) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getSize();
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 获取指定元素的本地矩形区域
     *
     * @param webElement 指定页面元素
     * @return 本地元素矩形区域
     */
    public static Rectangle getRect(WebElement webElement) {
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getRect();
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 获取指定元素的本地矩形区域
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     * @return 本地元素矩形区域
     */
    public static Rectangle getRect(By by) {
        WebElement webElement = ElementKeyword.findElement(by);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getRect();
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 获取指定元素的本地矩形区域
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     * @return 本地元素矩形区域
     */
    public static Rectangle getRect(String locator, String locatorValue) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getRect();
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 根据CSS属性名称获取指定元素的属性值
     *
     * @param webElement   指定页面元素
     * @param propertyName CSS属性名称
     * @return CSS属性值
     */
    public static String getCssValue(WebElement webElement, String propertyName) {
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getCssValue(propertyName);
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 根据CSS属性名称获取指定元素的属性值
     *
     * @param by           元素定位方式By(By.id(xx)/By.name(xx)等)
     * @param propertyName CSS属性名称
     * @return CSS属性值
     */
    public static String getCssValue(By by, String propertyName) {
        WebElement webElement = ElementKeyword.findElement(by);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getCssValue(propertyName);
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 根据CSS属性名称获取指定元素的属性值
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     * @param propertyName CSS属性名称
     * @return CSS属性值
     */
    public static String getCssValue(String locator, String locatorValue, String propertyName) {
        WebElement webElement = ElementKeyword.findElement(locator, locatorValue);
        if (checkElementDisplayedAndEnabled(webElement)) {
            return webElement.getCssValue(propertyName);
        } else {
            LogUtils.error("该页面元素不可见，无法获取该元素页面坐标点.");
            throw new RuntimeException("获取页面坐标点失败.");
        }
    }

    /**
     * 切换到当前Alert对话框
     *
     * @return Alert对话框对象
     */
    public static Alert switchToAlert() {
        return Browsers.getActiveBrowser().switchToAlert();
    }

    /**
     * 确定指定Alert对话框
     *
     * @param alert 指定Alert对话框
     */
    public static void acceptAlert(Alert alert) {
        Browsers.getActiveBrowser().acceptAlert(alert);
    }

    /**
     * 取消指定Alert对话框
     *
     * @param alert 指定Alert对话框
     */
    public static void dismissAlert(Alert alert) {
        Browsers.getActiveBrowser().dismissAlert(alert);
    }

    /**
     * 将鼠标悬停到指定元素上
     *
     * @param webElement 指定页面元素
     */
    public static void moveToElement(WebElement webElement) {
        Browsers.getActiveBrowser().moveToElement(webElement);
    }

    /**
     * 将鼠标悬停到指定元素上
     *
     * @param by 元素定位方式By(By.id(xx)/By.name(xx)等)
     */
    public static void moveToElement(By by) {
        Browsers.getActiveBrowser().moveToElement(by);
    }

    /**
     * 将鼠标悬停到指定元素上
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     */
    public static void moveToElement(String locator, String locatorValue) {
        Browsers.getActiveBrowser().moveToElement(locator, locatorValue);
    }

    /**
     * 判断元素是否可见或可点击
     *
     * @param webElement 元素WebElement
     * @return 可可见可点击返回True, 否则返回false
     */
    private static boolean checkElementDisplayedAndEnabled(WebElement webElement) {
        if (webElement.isDisplayed() && webElement.isEnabled()) {
            return true;
        } else {
            throw new RuntimeException("元素没有找到,点击操作失败.");
        }
    }
}
