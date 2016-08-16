package com.keyword.automation.doc.product;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.customer.*;
import com.keyword.automation.database.domain.Brand;
import com.keyword.automation.database.domain.Goods;
import com.keyword.automation.database.domain.GoodsType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * 入口:档案-商品相关-商品档案<br/>
 * 主要测试功能:
 * 1、新增商品档案
 * 2、修改商品档案
 * 3、删除商品档案
 *
 * @author Amio_
 */
public class Test001_Product_TestGoods {
    // 目前测试数据先定义在这边,后面放到properties文件中维护
    private static GoodsType firstGoodsType = new GoodsType("自动化测试一级类别", -9999, "自动化测试品牌");
    private static GoodsType secondGoodsType = new GoodsType("自动化测试二级类别", -9998, "自动化测试品牌");
    private Brand brand = new Brand("自动化测试品牌", -9999);
    private static Goods goods = new Goods("自动化测试商品档案", "999999999999", "999999999998", 9, 1.6, 12.8);

    @Before
    public void setUp() {
        LogUtils.info("--------------------测试预处理:登录系统并添加测试品牌、测试商品类别--------------------");
        BusinessKeyword.loginAndAddBrandAndSwitchToGoods(brand);
        GoodsTypeKeyword.addSecondGoodsType(firstGoodsType, secondGoodsType);
    }

    @Test
    // 添加商品档案
    public void testAddGoods() {
        LogUtils.info("--------------------测试添加商品档案--------------------");
        GoodsKeyword.addGoods(secondGoodsType.getName(), goods);
    }

    @After
    public void tearDown() {
        LogUtils.info("--------------------测试后处理:删除商品类别及品牌--------------------");
        GoodsKeyword.deleteGoods(secondGoodsType.getName(), goods.getName());
        GoodsTypeKeyword.deleteGoodsType(secondGoodsType.getName());
        GoodsTypeKeyword.deleteGoodsType(firstGoodsType.getName());
        BrowserKeyword.switchToDefaultFrameOrWindow();
        MenuKeyword.selectMenu("档案", "品牌档案");
        BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/doc/brand/list']"));
        BrandKeyword.deleteBrand(brand.getName());
        BrowserKeyword.browserQuit();
    }
}
