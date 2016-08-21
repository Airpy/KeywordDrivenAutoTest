package com.keyword.automation.bill.finance;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.bean.BillCell;
import com.keyword.automation.bean.BillFooter;
import com.keyword.automation.bean.BillHeader;
import com.keyword.automation.bean.BillWhole;
import com.keyword.automation.customer.*;
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
    private static List<BillCell> billCellList = new ArrayList<BillCell>();
    private static BillHeader billHeader = new BillHeader("测试客户", null, "刘振峰", null, false, "这是一个单据备注");
    private static BillCell billCell = new BillCell(1.1, 2.2);
    private static BillFooter billFooter = new BillFooter(2.2, 0.0, 0.0);

    static {
        billCellList.add(billCell);
    }

    private static BillWhole billWhole = new BillWhole(billHeader, billCellList, billFooter);

    @Before
    public void setUp() {
        LogUtils.info("--------------------测试预处理:登录系统并跳转收款单界面--------------------");
        String byBillFrame = ".//iframe[@id='erp/bill/consumerpaid/load/add/0']";
        LoginKeyword.loginSystem();
        MenuKeyword.selectMenu("销售", "收款单");
        BrowserKeyword.switchToFrame(By.xpath(byBillFrame));
    }

    @Test
    // 测试新增销售单
    public void testAddConsumerPaidBill() {
        LogUtils.info("--------------------测试添加收款单--------------------");
        BillKeyword.addBill("销售单", billWhole);
    }


    @After
    public void tearDown() {
        LogUtils.info("--------------------测试后处理--------------------");
        BrowserKeyword.browserQuit();
    }
}
