package com.keyword.automation.bill.sale;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.bean.BillWhole;
import com.keyword.automation.customer.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * 入口:销售-制作单据-销售单<br/>
 * 主要测试功能:
 * 1、新增销售单
 *
 * @author Amio_
 */
public class Test002_Sale_TestSaleBill {
    private static Map<String, Map<String, Object>> initData = JSONKeyword.initData("bill/销售单.json");

    @Before
    public void setUp() {
        LogUtils.info("--------------------测试预处理:登录系统并跳转销售单界面--------------------");
        LoginKeyword.loginSystem();
        MenuKeyword.selectMenu("销售", "销售单");
    }

    @Test
    // 测试新增销售单
    public void test_AddSaleBill() {
        LogUtils.info("--------------------测试添加销售单--------------------");
        BillWhole billWhole = (BillWhole) initData.get("addSaleOrder_001").get("billWhole");
        String billNo = BillKeyword.addBill("销售单", billWhole);
        BillKeyword.approveBill("销售单", billNo);
//        BillWhole billWhole = BillKeyword.getBillDetailData("销售单", billNo);
//        System.out.println(billWhole.toString());
    }

    @After
    public void tearDown() {
        LogUtils.info("--------------------测试后处理--------------------");

        BrowserKeyword.browserQuit();
    }
}
