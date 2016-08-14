package com.keyword.automation.doc.product.goods;

import com.keyword.automation.action.BrowserKeyword;
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
 * 功能:测试新增、修改、删除商品类别
 *
 * @author Amio_
 */
public class Test001_Product_Goods_TestGoodsType {
    private final String byFrame = ".//iframe[@id='erp/doc/goods/list']";
    // 目前测试数据先定义在这边,后面放到properties文件中维护
    private static GoodsType firstGoodsType = new GoodsType();
    private static GoodsType secondGoodsType = new GoodsType();
    static {
        firstGoodsType.setName("自动化测试一级类别");
        firstGoodsType.setSeq("-9999");
        firstGoodsType.setBrandId("五味园");
        secondGoodsType.setName("自动化测试二级类别");
        secondGoodsType.setSeq("-9998");
        secondGoodsType.setBrandId("五味园");
    }

    @Before
    public void setUp() {
        // 登录系统
        if (LoginKeyword.loginSystem()) {
            LogUtils.info("登陆系统成功，当前登录用户为: [" + Constants.TEST_USERNAME + "].");
        } else {
            throw new RuntimeIOException("登录系统失败.");
        }
        // 选择档案-商品相关-商品档案菜单
        MenuKeyword.selectMenu("档案", "商品档案");
        // 切换Frame
        BrowserKeyword.switchToFrame(By.xpath(byFrame));
    }

    @Test
    // 测试新增商品类别(包含一级类别及二级类别)
    public void testAddGoodsType() {
        GoodsTypeKeyword.addSecondGoodsType(firstGoodsType, secondGoodsType);
    }

    @Test
    // 测试修改商品类别(包含一级类别及二级类别)
    public void testEditGoodsType() {
        // 先添加两级商品类别供修改
        GoodsTypeKeyword.addSecondGoodsType(firstGoodsType, secondGoodsType);
        firstGoodsType.setName("修改后的一级类别");
        GoodsTypeKeyword.editGoodsType("自动化测试一级类别", firstGoodsType);
        secondGoodsType.setName("修改后的二级类别");
        GoodsTypeKeyword.editGoodsType("自动化测试二级类别", secondGoodsType);
    }

    @Test
    // 测试删除商品类别(包含一级类别及二级类别),暂时不考虑类别下有商品的冲突情况
    public void testDeleteGoodsType() {
        // 先添加商品类别再删除,因为后处理都把原来新建的都删除了
        GoodsTypeKeyword.addSecondGoodsType(firstGoodsType, secondGoodsType);
    }

    @After
    public void tearDown() {
        // 删除新建的商品类别,还原系统数据
        GoodsTypeKeyword.deleteGoodsType(secondGoodsType.getName());
        GoodsTypeKeyword.deleteGoodsType(firstGoodsType.getName());
        // 关闭浏览器所有标签页
        BrowserKeyword.browserQuit();
    }
}
