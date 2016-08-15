package com.keyword.automation.customer;

import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.browser.Browsers;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.database.domain.Goods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 自定义商品档案关键字
 *
 * @author Amio_
 */
public class GoodsKeyword {
    private GoodsKeyword() {

    }

    // 添加商品档案按钮
    private static final String byAddButton = ".//span[text()='添加']";
    // 批量删除商品档案按钮
    private static final String byBatchDeleteButton = ".//span[text()='批量删除']";
    // 导入商品档案按钮
    private static final String byImportButton = ".//span[text()='导入']";
    // 导出商品档案按钮
    private static final String byExportButton = ".//span[text()='导出']";
    // 添加商品档案弹出窗口
    private static final String byAddWindow = ".//div[@id='docDialog']/parent::div";
    // 新增商品档案弹出框--商品名称xpath
    private static final String byGoodsName = ".//label[text()='商品名称']/following-sibling::div/span/input[1]";
    // 新增商品档案弹出框--商品助记名xpath
    private static final String byGoodsShortName = ".//label[text()='商品助记名']/following-sibling::div/span/input[1]";
    // 新增商品档案弹出框--类别xpath
    private static final String byGoodsType = ".//label[text()='类别']/following-sibling::div/span/input[1]";
    // 新增商品档案弹出框--品牌xpath
    private static final String byBrand = ".//label[text()='品牌']/following-sibling::div/span/input[1]";
    // 新增商品档案弹出框--允许优惠xpath
    private static final String byDiscount = ".//label[text()='允许优惠']/following-sibling::div/div/input[1]";
    // 新增商品档案弹出框--正常状态xpath
    private static final String byNormalState = ".//label[text()='状态']/following-sibling::div/div/input[1]";
    // 新增商品档案弹出框--停用状态xpath
    private static final String byDisableState = ".//label[text()='状态']/following-sibling::div/div/input[2]";
    // 新增商品档案弹出框--基本单位xpath
    private static final String byBaseUint = ".//label[text()='基本单位']/following-sibling::div/span/input[1]";
    // 新增商品档案弹出框--基本单位条码xpath
    private static final String byBaseUnitBarcode = ".//label[text()" +
            "='条码']/following-sibling::div/input[@id='baseBarcode']/following-sibling::span/input[1]";
    // 新增商品档案弹出框--大包单位xpath
    private static final String byPkgUnit = ".//label[text()='大包单位']/following-sibling::div/span/input[1]";
    // 新增商品档案弹出框--大包单位条码xpath
    private static final String byPkgUnitBarcode = ".//label[text()" +
            "='条码']/following-sibling::div/input[@id='pkgBarcode']/following-sibling::span/input[1]";
    // 新增商品档案弹出框--单位换算xpath
    private static final String byUnitFactor = ".//label[text()='单位换算']/following-sibling::div/span[2]/input[1]";
    // 新增商品档案弹出框--基本单位批发价xpath
    private static final String byBaseWholeSale = ".//input[@id='baseWholesale']/following-sibling::span/input[1]";
    // 新增商品档案弹出框--大包单位批发价xpath
    private static final String byPkgWholeSale = ".//input[@id='pkgWholesale']/following-sibling::span/input[1]";
    // 新增商品档案弹出框--保存按钮xpath
    private static final String bySaveButton = ".//span[text()='保存']";
    // 新增商品档案弹出框--保存并新增按钮xpath
    private static final String bySaveAndAddButton = ".//span[text()='保存并新增']";
    // 新增商品档案弹出框--关闭按钮xpath
    private static final String byCloseButton = ".//span[text()='关闭']";

    /**
     * 在指定商品下添加商品档案(正常状态)
     *
     * @param targetGoodsType 指定商品类别
     * @param goods           商品档案对象
     */
    public static void addGoods(String targetGoodsType, Goods goods) {
        GoodsTypeKeyword.selectSpecifiedGoodsType(targetGoodsType);
        ElementKeyword.clickElement(By.xpath(byAddButton));
        ElementKeyword.findElement(By.xpath(byAddWindow));
        ElementKeyword.sendKeys(By.xpath(byGoodsName), goods.getName());
//        ElementKeyword.clickComboBox(ElementKeyword.findElement(By.xpath(byBaseUint)), "瓶", false);
        // 选择"瓶"基本单位
        ElementKeyword.clickElement(By.xpath(byBaseUint));
        ElementKeyword.clickElement(By.xpath(".//div[contains(@class,'combo-p')]/div[contains(@class," +
                "'panel-body-noheader')]/div[text()='瓶']"));
        ElementKeyword.sendKeys(By.xpath(byBaseUnitBarcode), goods.getBaseBarcode());
//        ElementKeyword.clickComboBox(ElementKeyword.findElement(By.xpath(byPkgUnit)), "包", false);
        // 选择"箱"大包单位(因为初始化数据小包、大包均有"箱"单位，删除小包单位"箱")
        ElementKeyword.clickElement(By.xpath(byPkgUnit));
        ElementKeyword.clickElement(By.xpath(".//div[contains(@class,'combo-p')]/div[contains(@class," +
                "'panel-body-noheader')]/div[text()='箱']"));
        ElementKeyword.sendKeys(By.xpath(byPkgUnitBarcode), goods.getPkgBarcode());
        ElementKeyword.sendKeys(By.xpath(byBaseWholeSale), String.valueOf(goods.getBaseWholesale()));
        ElementKeyword.sendKeys(By.xpath(byUnitFactor), String.valueOf(goods.getUnitFactor()));
        ElementKeyword.sendKeys(By.xpath(byPkgWholeSale), String.valueOf(goods.getPkgWholesale()));
        ElementKeyword.clickElement(By.xpath(bySaveButton));
        LogUtils.info("新增商品档案[" + goods.getName() + "]成功.");
    }

    public static void editGoods(String targetGoodsType, String goodsName, Goods afterGoods) {
        GoodsTypeKeyword.selectSpecifiedGoodsType(targetGoodsType);
    }

    public static boolean checkIsGoodsExists(String goodsName) {
        try {
            // 拷贝当前生效的driver到临时driver
            WebDriver tempDriver = Browsers.getActiveBrowser().getDriver();
            tempDriver.findElement(By.xpath(""));
            // 如果寻找元素没有报错,则说明该商品档案存在,返回True,否则False
            tempDriver.findElement(By.xpath(".//span[text()='" + goodsName + "']"));
            return true;
        } catch (Exception e) {
            LogUtils.info("商品档案[" + goodsName + "]不存在.");
            return false;
        }
    }

}
