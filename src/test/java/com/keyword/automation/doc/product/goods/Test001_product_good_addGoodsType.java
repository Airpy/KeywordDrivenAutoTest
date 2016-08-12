package com.keyword.automation.doc.product.goods;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.customer.GoodsTypeKeyword;
import com.keyword.automation.customer.LoginKeyword;
import com.keyword.automation.customer.MenuKeyword;
import com.keyword.automation.database.domain.GoodsType;
import org.eclipse.jetty.io.RuntimeIOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * 入口:档案-商品相关-商品档案<br/>
 * 功能:新增商品类别
 *
 * @author Amio_
 */
public class Test001_product_good_addGoodsType {
    private final String firstGoodsType = "自动化一级类别";
    private final String secondGoodsType = "自动化二级类别";

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
    public void testAddGoodsType() throws InterruptedException {
        // 选择档案-商品相关-商品档案菜单
        MenuKeyword.selectMenu("档案", "商品档案");
        // 切换Frame
        BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/doc/goods/list']"));
//        // 点击商品档案树状图的"全部"菜单
//        GoodsTypeKeyword.selectWholeGoodsTypeTree();
//        // 点击添加按钮
//        PageKeyword.clickFormButton(ElementKeyword.findBody(), "添加");
        GoodsType goodsType = new GoodsType();
        goodsType.setName("sdsdfs284d");
        goodsType.setSeq("-11111");
        goodsType.setBrandId("五味园");
        GoodsTypeKeyword.addFirstGoodsType(goodsType);

    }

    @After
    public void tearDown() {
        // 关闭浏览器所有标签页
        BrowserKeyword.browserQuit();
    }
}
