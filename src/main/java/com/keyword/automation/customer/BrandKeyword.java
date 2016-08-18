package com.keyword.automation.customer;

import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.browser.Browsers;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.database.domain.Brand;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * 自定义品牌关键字
 *
 * @author Amio_
 */
public class BrandKeyword {
    private BrandKeyword() {

    }

    // 品牌搜索框
    private static final String byQueryTextBox = ".//input[@placeholder='请输入品牌名称']";
    // 展示停用品牌复选框
    private static final String byIsDisplayDisableBrandCheckBox = ".//label/input[@name='docstate']";
    // 添加品牌按钮
//    private static final String byAddButton = ".//span[text()='添加']";
    // 批量删除品牌按钮
    private static final String byBatchDeleteButton = ".//span[text()='批量删除']";
    // 添加品牌弹出窗口
    private static final String byAddWindow = ".//div[@id='docDialog']/parent::div";
    // 新增品牌弹出框--品牌名称xpath
    private static final String byBrandName = ".//label[text()='品牌名称:']/following-sibling::div/span/input[1]";
    // 新增品牌弹出框--顺序号xpath
    private static final String bySeq = ".//label[text()='顺序号:']/following-sibling::div/span/input[1]";
    // 新增品牌弹出框--正常状态xpath
    private static final String byNormalState = ".//label[text()='状态：']/following-sibling::div/div/input[1]";
    // 新增品牌弹出框--停用状态xpath
    private static final String byDisableState = ".//label[text()='状态：']/following-sibling::div/div/input[2]";
    // 新增品牌弹出框--保存按钮xpath
//    private static final String bySaveButton = ".//span[text()='保存']";
    // 新增品牌弹出框--保存并新增按钮xpath
    private static final String bySaveAndAddButton = ".//span[text()='保存并新增']";
    // 新增品牌弹出框--关闭按钮xpath
    private static final String byCloseButton = ".//span[text()='关闭']";
    // 删除品牌弹出窗口
    private static final String byDeleteWindow = ".//div[text()='确认删除？']/parent::div/parent::div";
    // 确认删除品牌按钮
    private static final String byConfirmDeleteButton = ".//span[text()='确认']";

    /**
     * 添加品牌(正常状态)
     *
     * @param brand 品牌对象
     */
    public static void addBrand(Brand brand) {
        if (!checkIsBrandExists(brand.getName())) {
//            ElementKeyword.clickElement(By.xpath(byAddButton));
            PageKeyword.clickPageButton("添加");
            ElementKeyword.findElement(By.xpath(byAddWindow));
            ElementKeyword.sendKeys(By.xpath(byBrandName), brand.getName());
            ElementKeyword.sendKeys(By.xpath(bySeq), String.valueOf(brand.getSeq()));
            ElementKeyword.clickElement(By.xpath(byNormalState));
//            ElementKeyword.clickElement(By.xpath(bySaveButton));
            PageKeyword.clickPageButton("保存");
            LogUtils.info("添加品牌[" + brand.getName() + "]成功.");
        } else {
            LogUtils.warn("品牌[" + brand.getName() + "]已经存在.");
        }
    }

    /**
     * 修改指定品牌
     *
     * @param brandName   指定品牌名称
     * @param targetBrand 修改后的品牌
     */
    public static void editBrand(String brandName, Brand targetBrand) {
        // 如果品牌存在，则直接修改;如果不存在则添加该品牌
        if (checkIsBrandExists(brandName)) {
            final String byEditButton = ".//tr[contains(@id,'datagrid-row-r1')]/td[1]/div[text()='" + brandName +
                    "']/parent::td/following-sibling::td[3]/div/span[1]/a";
            ElementKeyword.clickElement(By.xpath(byEditButton));
            ElementKeyword.sendKeys(By.xpath(byBrandName), targetBrand.getName());
            ElementKeyword.sendKeys(By.xpath(bySeq), String.valueOf(targetBrand.getSeq()));
//            ElementKeyword.clickElement(By.xpath(bySaveButton));
            PageKeyword.clickPageButton("保存");
            LogUtils.info("修改品牌成功,原品牌名称为[" + brandName + "]，修改为[" + targetBrand.toString() + "].");
        } else {
            addBrand(targetBrand);
        }
    }

    /**
     * 删除指定品牌
     *
     * @param brandName 指定品牌名称
     */
    public static void deleteBrand(String brandName) {
        // 如果品牌存在，则直接删除；如果不存在，不做任何操作(不考虑任何业务冲突情况)
        if (checkIsBrandExists(brandName)) {
            final String byDeleteButton = ".//tr[contains(@id,'datagrid-row-r1')]/td[1]/div[text()='" + brandName +
                    "']/parent::td/following-sibling::td[3]/div/span[2]/a";
            ElementKeyword.clickElement(By.xpath(byDeleteButton));
            ElementKeyword.findElement(By.xpath(byDeleteWindow));
//            ElementKeyword.clickElement(By.xpath(byConfirmDeleteButton));
            PageKeyword.clickPageButton("确认");
            LogUtils.info("删除品牌[" + brandName + "]成功.");
        }
    }

    /**
     * 批量删除指定品牌
     *
     * @param brandNameList 指定品牌名称列表
     */
    public static void batchDeleteBrand(ArrayList<String> brandNameList) {

    }

    /**
     * 搜索品牌
     *
     * @param brand 品牌对象
     */
    public static void queryBrand(Brand brand) {

    }

    /**
     * 从数据库中捞出数据与操作数据比对
     *
     * @param brand 操作数据--品牌对象
     * @param cid   批发商编号
     * @return 如果相同则返回True, 否则返回False
     */
    public static boolean checkDataFromDB(Brand brand, String cid) {
        // 后面维护
        return true;
    }

    /**
     * 检查品牌名称是否存在
     *
     * @param brandName 品牌名称
     * @return 存在相同名称的品牌，则返回True，否则返回False
     */
    public static boolean checkIsBrandExists(String brandName) {
        try {
            // 拷贝当前生效的driver到临时driver
            WebDriver tempDriver = Browsers.getActiveBrowser().getDriver();
            // 如果寻找元素没有报错,则说明该商品类别存在,返回True,否则False
            tempDriver.findElement(By.xpath(".//tr[contains(@id,'datagrid-row-r1')]/td[1]/div[text()='" + brandName +
                    "']"));
            return true;
        } catch (Exception e) {
            LogUtils.info("品牌[" + brandName + "]不存在.");
            return false;
        }
    }
}
