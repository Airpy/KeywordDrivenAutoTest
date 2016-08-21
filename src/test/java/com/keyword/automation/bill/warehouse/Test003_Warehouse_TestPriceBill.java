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
 * 入口:仓库-制作单据-成本调价单<br/>
 * 主要测试功能:
 * 1、新增成本调价单
 *
 * @author Amio_
 */
public class Test003_Warehouse_TestPriceBill {
    private static List<BillCell> billCellList = new ArrayList<BillCell>();
    private static BillHeader billHeader = new BillHeader();
    private static BillCell billCellA_BASE = new BillCell("商品档案A", 6.6);
    private static BillCell billCellA_PKG = new BillCell("商品档案A", 66.0);
    private static BillCell billCellB_BASE = new BillCell("商品档案B", 7.7);

    static {
        billHeader.setBillRemark("这是一个单据备注");
        billCellList.add(billCellA_BASE);
        billCellList.add(billCellA_PKG);
        billCellList.add(billCellB_BASE);
    }

    private static BillWhole billWhole = new BillWhole(billHeader, billCellList, null);

    @Before
    public void setUp() {
        LogUtils.info("--------------------测试预处理:登录系统并跳转成本调价单界面--------------------");
        String byBillFrame = ".//iframe[@id='erp/bill/price/load/add/0']";
        LoginKeyword.loginSystem();
        MenuKeyword.selectMenu("仓库", "成本调价单");
        BrowserKeyword.switchToFrame(By.xpath(byBillFrame));
    }

    @Test
    // 测试新增销售单
    public void test_AddPriceBill() {
        LogUtils.info("--------------------测试添加成本调价单--------------------");
        String billNo = BillKeyword.addBill("成本调价单", billWhole);
        BillKeyword.approveBill("成本调价单", billNo);
    }


    @After
    public void tearDown() {
        LogUtils.info("--------------------测试后处理--------------------");

        BrowserKeyword.browserQuit();
    }
}