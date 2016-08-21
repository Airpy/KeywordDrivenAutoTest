package com.keyword.automation.bill.purchase;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.bean.BillCell;
import com.keyword.automation.bean.BillHeader;
import com.keyword.automation.customer.BillKeyword;
import com.keyword.automation.customer.LoginKeyword;
import com.keyword.automation.customer.MenuKeyword;
import com.keyword.automation.bean.BillWhole;
import com.keyword.automation.customer.PageKeyword;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * 入口:采购-制作单据-采购订单<br/>
 * 主要测试功能:
 * 1、新增采购订单
 *
 * @author Amio_
 */
public class Test001_Purchase_TestPurchaseOrderBill {
    private static List<BillCell> billCellList = new ArrayList<BillCell>();
    private static BillHeader billHeader = new BillHeader(null, "测试供应商", null, "刘振峰", null, false, "这是一个单据备注");
    private static BillCell billCellA_BASE = new BillCell("商品档案A", "瓶", "10", 8.80, 88.00, null);
    private static BillCell billCellA_PKG = new BillCell("商品档案A", "箱", "10", 88.80, 888.00, null);
    private static BillCell billCellB_BASE = new BillCell("商品档案B", "瓶", "10", 9.90, 99.00, null);

    static {
        billCellList.add(billCellA_BASE);
        billCellList.add(billCellA_PKG);
        billCellList.add(billCellB_BASE);
    }

    private static BillWhole billWhole = new BillWhole(billHeader, billCellList, null);

    @Before
    public void setUp() {
        LogUtils.info("--------------------测试预处理:登录系统并跳转采购订单界面--------------------");
        String byBillFrame = ".//iframe[@id='erp/bill/purchaseorder/load/add/0']";
        LoginKeyword.loginSystem();
        MenuKeyword.selectMenu("采购", "采购订单");
        BrowserKeyword.switchToFrame(By.xpath(byBillFrame));
    }

    @Test
    // 测试新增销售单
    public void test_AddPurchaseOrderBill() {
        LogUtils.info("--------------------测试添加采购订单--------------------");
        String billNo = BillKeyword.addBill("采购订单", billWhole);
        BillKeyword.approveBill("采购订单", billNo);
    }


    @After
    public void tearDown() {
        LogUtils.info("--------------------测试后处理--------------------");

        BrowserKeyword.browserQuit();
    }
}

