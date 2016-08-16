package com.keyword.automation.doc.product;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.customer.BrandKeyword;
import com.keyword.automation.customer.LoginKeyword;
import com.keyword.automation.customer.MenuKeyword;
import com.keyword.automation.database.domain.Brand;
import org.eclipse.jetty.io.RuntimeIOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * 入口:档案-商品相关-品牌档案<br/>
 * 主要测试功能:
 * 1、新增品牌
 * 2、修改品牌
 * 3、删除品牌
 * 4、批量删除品牌
 * 5、搜索品牌
 * 6、是否展示停用品牌
 *
 * @author Amio_
 */
public class Test002_Product_TestBrand {
    // 目前测试数据先定义在这边,后面放到properties文件中维护
    private Brand brand = new Brand("自动化测试品牌", -9999);

    @Before
    public void setUp() {
        LogUtils.info("--------------------测试预处理:登录系统--------------------");
        String byFrame = ".//iframe[@id='erp/doc/brand/list']";
        // 登录系统
        if (LoginKeyword.loginSystem()) {
            LogUtils.info("登陆系统成功，当前登录用户为: [" + Constants.TEST_USERNAME + "].");
        } else {
            throw new RuntimeIOException("登录系统失败.");
        }
        // 选择档案-商品相关-商品档案菜单
        MenuKeyword.selectMenu("档案", "品牌档案");
        // 切换Frame
        BrowserKeyword.switchToFrame(By.xpath(byFrame));
    }

    @Test
    // 测试新增品牌
    public void testAddBrand() {
        LogUtils.info("--------------------测试新增品牌--------------------");
        BrandKeyword.addBrand(brand);
    }

    @Test
    // 测试修改品牌
    public void testEditBrand() {
        LogUtils.info("--------------------测试修改品牌--------------------");
        BrandKeyword.addBrand(brand);
        brand.setName("修改后的测试品牌");
        BrandKeyword.editBrand("自动化测试品牌", brand);
    }

    @Test
    // 测试删除品牌
    public void testDeleteBrand() {
        LogUtils.info("--------------------测试删除品牌--------------------");
        BrandKeyword.addBrand(brand);
        BrandKeyword.deleteBrand(brand.getName());
    }
//
//    @Test
//    // 测试批量修改品牌
//    public void testBatchDeleteBrand() {
//
//    }
//
//    @Test
//    // 测试搜索品牌
//    public void testQueryBrand() {
//
//    }
//
//    @Test
//    // 测试是否展示停用品牌
//    public void testIsDisplayDisableBrand() {
//
//    }

    @After
    public void tearDown() {
        LogUtils.info("--------------------测试后处理:删除品牌--------------------");
        BrandKeyword.deleteBrand(brand.getName());
        // 关闭浏览器所有标签页
        BrowserKeyword.browserQuit();
    }
}
