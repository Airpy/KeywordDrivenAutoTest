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
 * 入口:仓库-制作单据-调拨单<br/>
 * 主要测试功能:
 * 1、新增调拨单
 *
 * @author Amio_
 */
public class Test001_Warehouse_TestMoveBill {
    private static List<BillCell> billCellList = new ArrayList<BillCell>();
    private static BillHeader billHeader = new BillHeader("测试仓库", "仓库B", null, "这是个单据备注");
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
        LogUtils.info("--------------------测试预处理:登录系统并跳转调拨单界面--------------------");
        LoginKeyword.loginSystem();
        MenuKeyword.selectMenu("仓库", "调拨单");
    }

    @Test
    // 测试新增销售单
    public void test_AddMoveBill() {
        LogUtils.info("--------------------测试添加调拨单--------------------");
        String billNo = BillKeyword.addBill("调拨单", billWhole);
        BillKeyword.approveBill("调拨单", billNo);
    }


    @After
    public void tearDown() {
        LogUtils.info("--------------------测试后处理--------------------");

        BrowserKeyword.browserQuit();
    }
}

