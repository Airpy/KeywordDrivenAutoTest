package com.keyword.automation.bill.sale;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.customer.*;
import com.keyword.automation.database.domain.Brand;
import com.keyword.automation.database.domain.Goods;
import com.keyword.automation.database.domain.GoodsType;
import org.eclipse.jetty.io.RuntimeIOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * 入口:销售-制作单据-销售单<br/>
 * 主要测试功能:
 * 1、新增销售单
 *
 * @author Amio_
 */
public class Test002_Sale_TestSaleBill {
    // 目前测试数据先定义在这边,后面放到properties文件中维护
    private static GoodsType firstGoodsType = new GoodsType("自动化测试一级类别", -9999, "自动化测试品牌");
    private Brand brand = new Brand("自动化测试品牌", -9999);
    private static Goods goods = new Goods("自动化测试商品档案", "999999999999", "999999999998", 9, 1.6, 12.8);

    @Before
    public void setUp() {
        String bySaleBillFrame = ".//iframe[@id='erp/sale/sale/load/add/0?type=0']";
        BusinessKeyword.loginAndGoodsAndSwitchToBill(brand, firstGoodsType, goods);
        MenuKeyword.selectMenu("销售", "销售单");
        BrowserKeyword.switchToFrame(By.xpath(bySaleBillFrame));
    }

    @Test
    // 测试新增品牌
    public void testAddBrand() {

    }


    @After
    public void tearDown() {

        GoodsTypeKeyword.deleteGoodsType(firstGoodsType.getName());
        BrowserKeyword.browserQuit();
    }
}
