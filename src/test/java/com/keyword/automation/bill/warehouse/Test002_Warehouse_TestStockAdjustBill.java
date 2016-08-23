package com.keyword.automation.bill.warehouse;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.bean.BillCell;
import com.keyword.automation.bean.BillHeader;
import com.keyword.automation.customer.BillKeyword;
import com.keyword.automation.customer.LoginKeyword;
import com.keyword.automation.customer.MenuKeyword;
import com.keyword.automation.bean.BillWhole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * 入口:仓库-制作单据-盘点盈亏单<br/>
 * 主要测试功能:
 * 1、新增盘点盈亏单
 *
 * @author Amio_
 */
public class Test002_Warehouse_TestStockAdjustBill {
    private static List<BillCell> billCellList = new ArrayList<BillCell>();
    private static BillHeader billHeader = new BillHeader("测试仓库", "刘振峰", false, "这是个单据备注");
    private static BillCell billCellA_BASE = new BillCell("商品档案A", "瓶", "10");
    private static BillCell billCellA_PKG = new BillCell("商品档案A", "箱", "10");
    private static BillCell billCellB_BASE = new BillCell("商品档案B", "瓶", "10");

    static {
        billCellList.add(billCellA_BASE);
        billCellList.add(billCellA_PKG);
        billCellList.add(billCellB_BASE);
    }

    private static BillWhole billWhole = new BillWhole(billHeader, billCellList, null);

    @Before
    public void setUp() {
        LogUtils.info("--------------------测试预处理:登录系统并跳转盘点盈亏单界面--------------------");
        LoginKeyword.loginSystem();
        MenuKeyword.selectMenu("仓库", "盘点盈亏单");
    }

    @Test
    // 测试新增销售单
    public void test_AddStockAdjustBill() {
        LogUtils.info("--------------------测试添加盘点盈亏单--------------------");
        String billNo = BillKeyword.addBill("盘点盈亏单", billWhole);
        BillKeyword.approveBill("盘点盈亏单", billNo);
    }


    @After
    public void tearDown() {
        LogUtils.info("--------------------测试后处理--------------------");

        BrowserKeyword.browserQuit();
    }
}