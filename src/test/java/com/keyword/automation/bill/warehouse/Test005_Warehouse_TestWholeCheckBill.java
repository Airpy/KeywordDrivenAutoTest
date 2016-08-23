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
 * 入口:销售-制作单据-销售订单<br/>
 * 主要测试功能:
 * 1、新增销售订单
 *
 * @author Amio_
 */
public class Test005_Warehouse_TestWholeCheckBill {
    private static List<BillCell> billCellList = new ArrayList<BillCell>();
    private static BillHeader billHeader = new BillHeader("测试仓库", "刘振峰");
    private static BillCell billCellA_BASE = new BillCell(0.0,5.0);
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
        LogUtils.info("--------------------测试预处理:登录系统并跳转报损单界面--------------------");
        LoginKeyword.loginSystem();
        MenuKeyword.selectMenu("仓库", "报损单");
    }

    @Test
    // 测试新增销售单
    public void test_AddSaleBill() {
        LogUtils.info("--------------------测试添加报损单--------------------");
        BillKeyword.addBill("报损单", billWhole);
    }


    @After
    public void tearDown() {
        LogUtils.info("--------------------测试后处理--------------------");

        BrowserKeyword.browserQuit();
    }
}
