package com.keyword.automation.customer;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.browser.Browsers;
import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.database.domain.Brand;
import com.keyword.automation.database.domain.Goods;
import com.keyword.automation.database.domain.GoodsType;
import org.eclipse.jetty.io.RuntimeIOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 自定义常用业务关键字
 *
 * @author Amio_
 */
public class BusinessKeyword {
    private BusinessKeyword() {

    }

    /**
     * 步骤:登录系统-添加品牌-切到商品档案界面
     *
     * @param brand 品牌对象
     */
    public static void loginAndAddBrandAndSwitchToGoods(Brand brand) {
        String byGoodsFrame = ".//iframe[@id='erp/doc/goods/list']";
        String byBrandFrame = ".//iframe[@id='erp/doc/brand/list']";
        // 登录系统
        if (LoginKeyword.loginSystem()) {
            LogUtils.info("登陆系统成功，当前登录用户为: [" + Constants.TEST_USERNAME + "].");
        } else {
            throw new RuntimeIOException("登录系统失败.");
        }
        // 先添加品牌，让商品类别绑定品牌
        MenuKeyword.selectMenu("档案", "品牌档案");
        BrowserKeyword.switchToFrame(By.xpath(byBrandFrame));
        BrandKeyword.addBrand(brand);
        BrowserKeyword.switchToDefaultFrameOrWindow();
        ElementKeyword.clickElement(By.xpath(".//span[text()='品牌档案']/parent::a/following-sibling::a"));
        // 选择档案-商品相关-商品档案菜单
        MenuKeyword.selectMenu("档案", "商品档案");
        // 切换到添加商品档案Frame
        BrowserKeyword.switchToFrame(By.xpath(byGoodsFrame));
    }


    public static void loginAndGoodsAndSwitchToBill(Brand brand, GoodsType goodsType, Goods goods) {
        String byGoodsFrame = ".//iframe[@id='erp/doc/goods/list']";
        String byBrandFrame = ".//iframe[@id='erp/doc/brand/list']";
        // 登录系统
        if (LoginKeyword.loginSystem()) {
            LogUtils.info("登陆系统成功，当前登录用户为: [" + Constants.TEST_USERNAME + "].");
        } else {
            throw new RuntimeIOException("登录系统失败.");
        }
        // 先添加品牌，让商品类别绑定品牌
        MenuKeyword.selectMenu("档案", "品牌档案");
        BrowserKeyword.switchToFrame(By.xpath(byBrandFrame));
        BrandKeyword.addBrand(brand);
        BrowserKeyword.switchToDefaultFrameOrWindow();
        ElementKeyword.clickElement(By.xpath(".//span[text()='品牌档案']/parent::a/following-sibling::a"));
        // 选择档案-商品相关-商品档案菜单
        MenuKeyword.selectMenu("档案", "商品档案");
        // 切换到添加商品档案Frame
        BrowserKeyword.switchToFrame(By.xpath(byGoodsFrame));
        // 添加商品类别
        GoodsTypeKeyword.addFirstGoodsType(goodsType);
        // 添加商品档案
        GoodsKeyword.addGoods(goodsType.getName(), goods);
        // 切回原来的Frame
        BrowserKeyword.switchToDefaultFrameOrWindow();
        ElementKeyword.clickElement(By.xpath(".//span[text()='商品档案']/parent::a/following-sibling::a"));
    }
}
