package com.keyword.automation.bill.finance;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.customer.BillKeyword;
import com.keyword.automation.customer.BusinessKeyword;
import com.keyword.automation.customer.MenuKeyword;
import com.keyword.automation.database.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * 入口:财务-制作单据-收款单<br/>
 * 主要测试功能:
 * 1、新增收款单
 *
 * @author Amio_
 */
public class Test01_Finance_TestConsumerPaid {
    // 目前测试数据先定义在这边,后面放到properties文件中维护
    // 构造商品类别、品牌、含大小包的商品A，只含小包的商品B（只含小包，构造方法第4个字段传0）
    private static GoodsType firstGoodsType = new GoodsType("自动化测试一级类别", -9999, "自动化测试品牌");
    private Brand brand = new Brand("自动化测试品牌", -9999);
    private static Goods goodsA = new Goods("商品档案A", "999999999999", "999999999998", 8, 8.8, 88.8);
    // 构造在单据界面选择3行商品行，第1行大包商品A，第2行小包商品A，第3行小包商品B
    private static BillGoods billGoodsA_PKG = new BillGoods(goodsA.getName(), "箱", "10", goodsA.getPkgWholesale(),
            "这是商品A大包的备注");
    private static BillGoods billGoodsA_BASE = new BillGoods(goodsA.getName(), "瓶", "10", goodsA.getBaseWholesale(),
            "这是商品A小包的备注");
    private static List<Goods> goodsList = new ArrayList<Goods>();
    private static List<BillGoods> billGoodsList = new ArrayList<BillGoods>();

    static {
        goodsList.add(goodsA);
        billGoodsList.add(billGoodsA_PKG);
        billGoodsList.add(billGoodsA_BASE);
    }

    private static Bill bill = new Bill("测试客户", "测试仓库", "刘振峰", "这是一个单据备注", billGoodsList, 3.3, 4.4, 5.5);
    private static FinanceBill financeBill = new FinanceBill(1.1, 2.2);
    private static String billNo = "";

    @Before
    public void setUp() {
        LogUtils.info("--------------------测试预处理:登录系统并添加测试品牌、测试商品类别、商品档案、销售单(已审核)--------------------");
        String bySaleBillFrame = ".//iframe[@id='erp/bill/sale/load/add/0?type=0']";
        String byConsumerPaidFrame = ".//iframe[@id='erp/bill/consumerpaid/load/add/0']";
        BusinessKeyword.loginAndGoodsAndSwitchToBill(brand, firstGoodsType, goodsList);
        MenuKeyword.selectMenu("销售", "销售单");
        BrowserKeyword.switchToFrame(By.xpath(bySaleBillFrame));
        billNo = BillKeyword.addSaleBill(bill);
        BillKeyword.approveBill("sale", billNo);
        MenuKeyword.selectMenu("财务", "收款单");
        BrowserKeyword.switchToFrame(By.xpath(byConsumerPaidFrame));
    }

    @Test
    // 测试新增销售单
    public void testAddConsumerPaidBill() {
        LogUtils.info("--------------------测试添加收款单--------------------");
        BillKeyword.addConsumerPaidBill(billNo, bill, financeBill);
    }


    @After
    public void tearDown() {
        LogUtils.info("--------------------测试后处理--------------------");
        BrowserKeyword.browserQuit();
    }
}
