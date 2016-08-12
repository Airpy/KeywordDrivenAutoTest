package com.keyword.automation.doc.product.brand;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.customer.LoginKeyword;
import com.keyword.automation.customer.MenuKeyword;
import com.keyword.automation.customer.PageKeyword;
import org.eclipse.jetty.io.RuntimeIOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 入口:档案-商品相关-品牌档案<br/>
 * 功能:新增品牌档案
 *
 * @author Amio_
 */
public class Test001_product_brand_addBrand {
    private final String FRAME_ID = "erp/doc/brand/list";
    @Before
    public void setUp() {
        // 登录系统
        if (LoginKeyword.loginSystem()) {
            LogUtils.info("登陆系统成功，当前登录用户为: [" + Constants.TEST_USERNAME + "].");
        } else {
            throw new RuntimeIOException("登录系统失败.");
        }
    }

    @Test
    public void testAddGoodsType() {
        // 选择档案-商品相关-品牌档案菜单
        MenuKeyword.selectMenu("档案", "品牌档案");
        // 切换Frame
        BrowserKeyword.switchToFrame(FRAME_ID);
        // 点击添加按钮
        PageKeyword.clickFormButton(ElementKeyword.findBody(), "添加");
    }

    @After
    public void tearDown() {
        // 关闭浏览器所有标签页
        BrowserKeyword.browserQuit();
    }
}
