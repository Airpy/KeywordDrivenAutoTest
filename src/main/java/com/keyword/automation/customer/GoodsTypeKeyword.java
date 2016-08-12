package com.keyword.automation.customer;

import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.database.domain.GoodsType;
import org.openqa.selenium.By;


/**
 * 自定义商品类别关键字
 *
 * @author Amio_
 */
public class GoodsTypeKeyword {
    // 新增商品类别按钮
    private static final String ByAddButton = ".//a[@title='添加']";
    // 新增商品类别按钮
    private static final String ByAddWindow = ".//div[contains(@class,'dialog-common')]";
    // 新增商品类别弹出框--类名xpath
    private static final String ByGoodsName = ".//label[text()='类名']/following-sibling::div/span/input[1]";
    // 新增商品类别弹出框--顺序号xpath
    private static final String BySeq = ".//label[text()='顺序号']/following-sibling::div/span/input[1]";
    // 新增商品类别弹出框--统计类别xpath
    private static final String ByStatistics = ".//label[text()" +
            "='统计类别']/following-sibling::div/span/span/following-sibling::input[1]";
    // 新增商品类别弹出框--对应品牌xpath
    private static final String ByBrand = ".//label[text()" +
            "='对应品牌']/following-sibling::div/span/span/following-sibling::input[1]";
    // 新增商品类别弹出框--对应状态（正常状态）xpath
    private static final String ByNormalState = ".//label[text()='状态']/following-sibling::div/div/input[1]";
    // 新增商品类别弹出框--对应状态（停用状态）xpath
    private static final String ByDisableState = ".//label[text()='状态']/following-sibling::div/div/input[2]";
    // 新增商品类别弹出框--保存按钮xpath
    private static final String BySubmitButton = ".//span[text()='保存']";

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
     * 在指定父类别下添加子商品类别
     *
     * @param parentGoodsTypeName 父商品类别
     * @param subGoodsTypeName    子商品类别
     */
    public static void addGoodsType(String parentGoodsTypeName, String subGoodsTypeName) {

    }

    /**
     * 在全部类别下新增一级商品类别
     *
     * @param firstGoodsTypeName 一级商品类别名称
     */
    public static void addFirstGoodsType(GoodsType goodsType) {
        selectWholeGoodsTypeTree();
        ElementKeyword.clickElement(By.xpath(ByAddButton));
        ElementKeyword.findElement(By.xpath(ByAddWindow));
        ElementKeyword.sendKeys(By.xpath(ByGoodsName), goodsType.getName());
        ElementKeyword.sendKeys(By.xpath(BySeq), goodsType.getSeq());
        ElementKeyword.sendKeys(By.xpath(ByBrand), goodsType.getBrandId());
        ElementKeyword.clickElement(By.xpath(ByNormalState));
        ElementKeyword.clickElement("xpath", BySubmitButton);
    }

    /**
     * 在指定一级类别下新增二级商品类别
     *
     * @param firstGoodsTypeName  一级商品类别名称
     * @param secondGoodsTypeName 二级商品类别名称
     */
    public static void addSecondGoodsType(String firstGoodsTypeName, String secondGoodsTypeName) {

    }

    /**
     * 删除指定商品类别
     *
     * @param goodsTypeName 商品类别名称
     */
    public static void deleteGoodsType(String goodsTypeName) {

    }

    /**
     * 检查商品列别名称是否存在
     *
     * @param goodsTypeName 商品类别名称
     * @return 不区分商品类别为一级类别还是二级类别，只要存在相同名称的，则返回True，否则返回False
     */
    public static boolean checkIsGoodsTypeExists(String goodsTypeName) {
        return true;
    }

}
