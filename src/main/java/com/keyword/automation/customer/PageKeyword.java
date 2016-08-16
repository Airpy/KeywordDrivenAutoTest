package com.keyword.automation.customer;

import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * 自定义网页关键字
 *
 * @author Amio_
 */
public class PageKeyword {
    /**
     * 点击指定页面的指定按钮
     *
     * @param webElement 指定页面
     * @param buttonName 指定按钮
     */
    public static void clickFormButton(WebElement webElement, String buttonName) {
        WebElement element = ElementKeyword.findElement(webElement, By.xpath(".//a[contains(@class,'l-btn') and " +
                "contains(descendant::text(),'" + buttonName + "')]"));
        ElementKeyword.clickElement(element);
        LogUtils.info("点击[" + buttonName + "]按钮成功.");
    }

    /**
     * 点击指定页面的指定按钮
     *
     * @param by         元素定位方式By(By.id(xx)/By.name(xx)等)
     * @param buttonName 指定按钮
     */
    public static void clickFormButton(By by, String buttonName) {
        WebElement parentElement = ElementKeyword.findElement(by);
        WebElement subElement = ElementKeyword.findElement(parentElement, By.xpath(".//a[contains(@class,'l-btn') and" +
                " " +
                "contains(descendant::text(),'" + buttonName + "')]"));
        ElementKeyword.clickElement(subElement);
        LogUtils.info("点击[" + buttonName + "]按钮成功.");
    }

    /**
     * 点击指定页面的指定按钮
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     * @param buttonName   指定按钮
     */
    public static void clickFormButton(String locator, String locatorValue, String buttonName) {
        WebElement parentElement = ElementKeyword.findElement(locator, locatorValue);
        WebElement subElement = ElementKeyword.findElement(parentElement, By.xpath(".//a[contains(@class,'l-btn') and" +
                " contains(descendant::text(),'" + buttonName + "')]"));
        ElementKeyword.clickElement(subElement);
        LogUtils.info("点击[" + buttonName + "]按钮成功.");
    }

    /**
     * 点击指定页面的指定单元格行的指定按钮
     *
     * @param webElement 指定页面
     * @param row        指定单元格行
     * @param cellName   指定按钮
     */
    public static void clickCellButton(WebElement webElement, int row, String cellName) {
        List<WebElement> webElements = ElementKeyword.findElements(webElement, By.xpath(".//a[@title='" + cellName +
                "']"));
        row = row - 1;
        ElementKeyword.clickElement(webElements.get(row));
    }

    /**
     * 点击指定页面的指定单元格行的指定按钮
     *
     * @param by       元素定位方式By(By.id(xx)/By.name(xx)等)
     * @param row      指定单元格行
     * @param cellName 指定按钮
     */
    public static void clickCellButton(By by, int row, String cellName) {
        WebElement parentElement = ElementKeyword.findElement(by);
        List<WebElement> webElements = ElementKeyword.findElements(parentElement, By.xpath(".//a[@title='" + cellName
                + "']"));
        row = row - 1;
        ElementKeyword.clickElement(webElements.get(row));
    }

    /**
     * 点击指定页面的指定单元格行的指定按钮
     *
     * @param locator      元素定位类型(id/name/linkText/partialLinkText/tagName/xpath/className/cssSelector)
     * @param locatorValue 元素定位值
     * @param row          指定单元格行
     * @param cellName     指定按钮
     */
    public static void clickCellButton(String locator, String locatorValue, int row, String cellName) {
        WebElement parentElement = ElementKeyword.findElement(locator, locatorValue);
        List<WebElement> webElements = ElementKeyword.findElements(parentElement, By.xpath(".//a[@title='" + cellName
                + "']"));
        row = row - 1;
        ElementKeyword.clickElement(webElements.get(row));
    }

    /**
     * 点击页面按钮
     *
     * @param buttonName 指定按钮名称，如添加/批量删除/保存/保存并新增/关闭/确认/取消/导入/导出 <br/>
     *                   注意：商品分类的添加/修改/删除除外
     */
    public static void clickPageButton(String buttonName) {
        By byButton = By.xpath(".//span[text()='" + buttonName + "']");
        ElementKeyword.clickElement(byButton);
    }
}
