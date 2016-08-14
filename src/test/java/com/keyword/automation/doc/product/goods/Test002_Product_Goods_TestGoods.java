package com.keyword.automation.doc.product.goods;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.customer.GoodsKeyword;
import com.keyword.automation.customer.GoodsTypeKeyword;
import com.keyword.automation.customer.LoginKeyword;
import com.keyword.automation.customer.MenuKeyword;
import com.keyword.automation.database.domain.Goods;
import com.keyword.automation.database.domain.GoodsType;
import org.eclipse.jetty.io.RuntimeIOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * 入口:档案-商品相关-商品档案<br/>
 * 功能:测试新增、修改、删除商品
 *
 * @author Amio_
 */
public class Test002_Product_Goods_TestGoods {
    // 目前测试数据先定义在这边,后面放到properties文件中维护
    private static GoodsType firstGoodsType = new GoodsType();
    private static GoodsType secondGoodsType = new GoodsType();
    private static Goods goods = new Goods();

    static {
        firstGoodsType.setName("自动化测试一级类别");
        firstGoodsType.setSeq("-9999");
        firstGoodsType.setBrandId("五味园");
        secondGoodsType.setName("自动化测试二级类别");
        secondGoodsType.setSeq("-9998");
        secondGoodsType.setBrandId("五味园");
        goods.setName("自动化测试商品档案");
        goods.setBaseBarcode("999999999999");
        goods.setPkgBarcode("999999999998");
        goods.setUnitFactor(9);
        goods.setBaseWholesale(1.6);
        goods.setPkgWholesale(12.8);
    }

    @Before
    public void setUp() {
        String byFrame = ".//iframe[@id='erp/doc/goods/list']";
        // 登录系统
        if (LoginKeyword.loginSystem()) {
            LogUtils.info("登陆系统成功，当前登录用户为: [" + Constants.TEST_USERNAME + "].");
        } else {
            throw new RuntimeIOException("登录系统失败.");
        }
        // 选择档案-商品相关-商品档案菜单
        MenuKeyword.selectMenu("档案", "商品档案");
        // 切换Frame
        BrowserKeyword.switchToFrame(By.xpath(byFrame));
    }

    @Test
    // 添加商品档案
    public void testAddGoods() {
        GoodsTypeKeyword.addSecondGoodsType(firstGoodsType, secondGoodsType);
        GoodsKeyword.addGoods(secondGoodsType.getName(), goods);
    }

    @After
    public void tearDown() {
        BrowserKeyword.browserQuit();
    }
}
