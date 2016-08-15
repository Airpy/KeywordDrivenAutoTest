package com.keyword.automation.bill.sale;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.customer.LoginKeyword;
import com.keyword.automation.customer.MenuKeyword;
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
    @Before
    public void setUp() {
        String bySaleBillFrame = ".//iframe[@id='erp/sale/sale/load/add/0?type=0']";
        // 登录系统
        if (LoginKeyword.loginSystem()) {
            LogUtils.info("登陆系统成功，当前登录用户为: [" + Constants.TEST_USERNAME + "].");
        } else {
            throw new RuntimeIOException("登录系统失败.");
        }
        MenuKeyword.selectMenu("销售", "销售单");
        BrowserKeyword.switchToFrame(By.xpath(bySaleBillFrame));
    }

    @Test
    // 测试新增品牌
    public void testAddBrand() {

    }


    @After
    public void tearDown() {

    }
}
