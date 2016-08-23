package com.keyword.automation.doc.product;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.customer.*;
import com.keyword.automation.database.domain.Brand;
import com.keyword.automation.database.domain.GoodsType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * 入口:档案-商品相关-商品档案<br/>
 * 主要测试功能:
 * 1、新增商品类别
 * 2、修改商品类别
 * 3、删除商品类别
 *
 * @author Amio_
 */
public class Test003_Product_TestGoodsType {
    // 目前测试数据先定义在这边,后面放到properties文件中维护
    private static GoodsType firstGoodsType = new GoodsType("自动化测试一级类别", -9999, "自动化测试品牌");
    private static GoodsType secondGoodsType = new GoodsType("自动化测试二级类别", -9998, "自动化测试品牌");
    private Brand brand = new Brand("自动化测试品牌", -9999);

    @Before
    public void setUp() {
        LogUtils.info("--------------------测试预处理:登录系统并添加测试品牌--------------------");
        BusinessKeyword.loginAndAddBrandAndSwitchToGoods(brand);
    }

    @Test
    // 测试新增商品类别(包含一级类别及二级类别)
    public void testAddGoodsType() {
        LogUtils.info("--------------------测试新增商品类别--------------------");
        GoodsTypeKeyword.addSecondGoodsType(firstGoodsType, secondGoodsType);
    }

    @Test
    // 测试修改商品类别(包含一级类别及二级类别)
    public void testEditGoodsType() {
        LogUtils.info("--------------------测试修改商品类别--------------------");
        // 先添加两级商品类别供修改
        GoodsTypeKeyword.addSecondGoodsType(firstGoodsType, secondGoodsType);
        firstGoodsType.setName("修改后的一级类别");
        GoodsTypeKeyword.editGoodsType("自动化测试一级类别", firstGoodsType);
        secondGoodsType.setName("修改后的二级类别");
        GoodsTypeKeyword.editGoodsType("自动化测试二级类别", secondGoodsType);
    }

    @Test
    // 测试删除商品类别(包含一级类别及二级类别),暂时不考虑类别下有商品的冲突情况
    public void testDeleteGoodsType() {
        LogUtils.info("--------------------测试删除商品类别--------------------");
        // 先添加商品类别再删除,因为后处理都把原来新建的都删除了
        GoodsTypeKeyword.addSecondGoodsType(firstGoodsType, secondGoodsType);
        GoodsTypeKeyword.deleteGoodsType(secondGoodsType.getName());
        GoodsTypeKeyword.deleteGoodsType(firstGoodsType.getName());
    }

    @After
    public void tearDown() {
        LogUtils.info("--------------------测试后处理:删除商品类别及品牌--------------------");
        GoodsTypeKeyword.deleteGoodsType(secondGoodsType.getName());
        GoodsTypeKeyword.deleteGoodsType(firstGoodsType.getName());
        BrowserKeyword.switchToDefaultFrameOrWindow();
        MenuKeyword.selectMenu("档案", "品牌档案");
        BrandKeyword.deleteBrand(brand.getName());
        // 关闭浏览器所有标签页
        BrowserKeyword.browserQuit();
    }
}
