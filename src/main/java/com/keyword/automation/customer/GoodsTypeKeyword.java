package com.keyword.automation.customer;

import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.browser.Browsers;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.database.domain.GoodsType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * 自定义商品类别关键字
 *
 * @author Amio_
 */
public class GoodsTypeKeyword {
    private GoodsTypeKeyword() {

    }

//    // 新增商品类别按钮
//    private static final String byAddButton = ".//a[@title='添加']";
//    // 修改商品类别按钮
//    private static final String byEditButton = ".//a[@title='修改']";
//    // 删除商品类别按钮
//    private static final String byDeleteButton = ".//a[@title='删除']";
    // 新增商品类别弹出窗口
    private static final String byAddWindow = ".//div[contains(@class,'dialog-common')]";
    // 新增商品类别弹出框--类名xpath
    private static final String byGoodsName = ".//label[text()='类名']/following-sibling::div/span/input[1]";
    // 新增商品类别弹出框--顺序号xpath
    private static final String bySeq = ".//label[text()='顺序号']/following-sibling::div/span/input[1]";
    // 新增商品类别弹出框--统计类别xpath
    private static final String byStatistics = ".//label[text()" +
            "='统计类别']/following-sibling::div/span/span/following-sibling::input[1]";
    // 新增商品类别弹出框--对应品牌xpath
    private static final String byBrand = ".//label[text()" +
            "='对应品牌']/following-sibling::div/span/span/following-sibling::input[1]";
    // 新增商品类别弹出框--对应状态（正常状态）xpath
    private static final String byNormalState = ".//label[text()='状态']/following-sibling::div/div/input[1]";
    // 新增商品类别弹出框--对应状态（停用状态）xpath
    private static final String byDisableState = ".//label[text()='状态']/following-sibling::div/div/input[2]";
//    // 新增商品类别弹出框--保存按钮xpath
//    private static final String bySubmitButton = ".//span[text()='保存']";
    // 删除商品类别弹出窗口
    private static final String byDeleteWindow = ".//div[text()='确认删除']/parent::div/parent::div";
//    // 确认删除商品类别按钮
//    private static final String byConfirmDeleteButton = ".//span[text()='确认']";

    /**
     * 点击商品档案树状图的"全部"菜单
     */
    public static void selectWholeGoodsTypeTree() {
        ElementKeyword.clickElement(By.xpath(".//div[@id='_easyui_tree_1']"));
        LogUtils.info("选择商品类别(全部)成功.");
    }

    /**
     * 选择指定商品类别
     *
     * @param goodsTypeName 商品类别名称
     */
    public static void selectSpecifiedGoodsType(String goodsTypeName) {
        ElementKeyword.clickElement(By.xpath(".//span[text()='" + goodsTypeName + "']"));
        LogUtils.info("选择[" + goodsTypeName + "]类别成功.");
    }

    /**
     * 新增或修改商品类别
     *
     * @param actionType 操作类型(add/edit)
     * @param goodsType  商品类别对象
     */
    public static void addOrEditGoodsType(String actionType, GoodsType goodsType) {
        if (actionType.equalsIgnoreCase("add")) {
//            ElementKeyword.clickElement(By.xpath(byAddButton));
            clickPageButton("添加");
        } else if (actionType.equalsIgnoreCase("edit")) {
//            ElementKeyword.clickElement(By.xpath(byEditButton));
            clickPageButton("修改");
        } else {
            throw new RuntimeException("操作类型[" + actionType + "]错误.");
        }
        ElementKeyword.findElement(By.xpath(byAddWindow));
        ElementKeyword.sendKeys(By.xpath(byGoodsName), goodsType.getName());
        ElementKeyword.sendKeys(By.xpath(bySeq), String.valueOf(goodsType.getSeq()));
//        ElementKeyword.sendKeys(By.xpath(byStatistics), String.valueOf(GoodsType.getStatisticsId()));
        ElementKeyword.sendKeys(By.xpath(byBrand), goodsType.getBrandId());
        ElementKeyword.clickElement(By.xpath(byNormalState));
//        ElementKeyword.clickElement("xpath", bySubmitButton);
        PageKeyword.clickPageButton("保存");
        LogUtils.info("操作[" + actionType + "]商品类别[" + goodsType.getName() + "]成功.");
    }

    /**
     * 在全部商品类别下添加一级商品类别
     *
     * @param firstGoodsType 一级商品类别对象
     */
    public static void addFirstGoodsType(GoodsType firstGoodsType) {
        if (!checkIsGoodsTypeExists(firstGoodsType.getName())) {
            selectWholeGoodsTypeTree();
            addOrEditGoodsType("add", firstGoodsType);
        }
    }

    /**
     * 在指定一级类别下新增二级商品类别
     *
     * @param firstGoodsType  一级商品类别名称
     * @param secondGoodsType 二级商品类别对象
     */
    public static void addSecondGoodsType(GoodsType firstGoodsType, GoodsType secondGoodsType) {
        // 如果一级分类不存在,则先新增一级分类;如果存在则直接新增二级分类
        if (!checkIsGoodsTypeExists(firstGoodsType.getName())) {
            addFirstGoodsType(firstGoodsType);
        }
        // 如果二级分类不存在,则新增二级分类;如果存在直接跳过新增商品类别
        if (!checkIsGoodsTypeExists(secondGoodsType.getName())) {
            selectSpecifiedGoodsType(firstGoodsType.getName());
            addOrEditGoodsType("add", secondGoodsType);
        }
        LogUtils.info("新增一级分类[" + firstGoodsType.getName() + "],二级分类[" + secondGoodsType.getName() + "]成功.");
    }

    /**
     * 修改制定商品类别
     *
     * @param targetGoodsType 指定商品类别
     * @param afterGoodsType  修改后的商品类别对象
     */
    public static void editGoodsType(String targetGoodsType, GoodsType afterGoodsType) {
        // 如果该类别存在,则选中它并修改;如果不存在直接新增该商品类别(一级类别)
        if (checkIsGoodsTypeExists(targetGoodsType)) {
            selectSpecifiedGoodsType(targetGoodsType);
            addOrEditGoodsType("edit", afterGoodsType);
        } else {
            addFirstGoodsType(afterGoodsType);
        }
    }

    /**
     * 删除指定商品类别
     *
     * @param targetGoodsType 指定商品类别
     */
    public static void deleteGoodsType(String targetGoodsType) {
        if (checkIsGoodsTypeExists(targetGoodsType)) {
            selectSpecifiedGoodsType(targetGoodsType);
//            ElementKeyword.clickElement(By.xpath(byDeleteButton));
            clickPageButton("删除");
            ElementKeyword.findElement(By.xpath(byDeleteWindow));
//            ElementKeyword.clickElement(By.xpath(byConfirmDeleteButton));
            PageKeyword.clickPageButton("确认");
            LogUtils.info("删除[" + targetGoodsType + "]成功.");
        }
    }

    /**
     * 从数据库中捞出数据与操作数据比对
     *
     * @param goodsType 操作数据--商品类别对象
     * @param cid       批发商编号
     * @return 如果相同则返回True, 否则返回False
     */
    public static boolean checkDataFromDB(GoodsType goodsType, String cid) {
        // 后面维护
        return true;
    }

    /**
     * 检查商品类别名称是否存在
     *
     * @param goodsTypeName 商品类别名称
     * @return 不区分商品类别为一级类别还是二级类别，只要存在相同名称的，则返回True，否则返回False
     */
    public static boolean checkIsGoodsTypeExists(String goodsTypeName) {
        try {
            // 拷贝当前生效的driver到临时driver
            WebDriver tempDriver = Browsers.getActiveBrowser().getDriver();
            // 如果寻找元素没有报错,则说明该商品类别存在,返回True,否则False
            tempDriver.findElement(By.xpath(".//span[text()='" + goodsTypeName + "']"));
            return true;
        } catch (Exception e) {
            LogUtils.info("商品类别[" + goodsTypeName + "]不存在.");
            return false;
        }
    }

    /**
     * 点击页面按钮
     *
     * @param buttonName 指定按钮名称，如添加/修改/删除除外
     */
    private static void clickPageButton(String buttonName) {
        By byButton = By.xpath(".//a[@title='" + buttonName + "']");
        ElementKeyword.clickElement(byButton);
    }
}
