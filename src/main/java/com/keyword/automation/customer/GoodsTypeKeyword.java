package com.keyword.automation.customer;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.utils.LogUtils;
import org.openqa.selenium.By;


/**
 * 自定义商品类别关键字
 *
 * @author Amio_
 */
public class GoodsTypeKeyword {
    // 新增商品类别弹出框--类名xpath
    private static final String ByGoodsName = ".//label[text()='类名']/following-sibling::div/span/input[1]";

    private static final String BySeq = ".//";
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
    public static void addFirstGoodsType(String firstGoodsTypeName) {
        selectWholeGoodsTypeTree();
        ElementKeyword.clickElement(By.xpath(".//a[@title='添加']"));
        System.out.println(1111);
//        BrowserKeyword.switchToWindow();
        ElementKeyword.findElement(By.xpath(".//div[contains(@class,'dialog-common')]"));
        System.out.println(2222);
        ElementKeyword.sendKeys(By.xpath(ByGoodsName), firstGoodsTypeName);
        System.out.println(3333);
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
