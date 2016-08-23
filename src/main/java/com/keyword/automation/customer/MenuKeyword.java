package com.keyword.automation.customer;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.utils.LogUtils;
import org.openqa.selenium.By;

/**
 * 自定义系统菜单关键字
 *
 * @author Amio_
 */
public class MenuKeyword {
    /**
     * 选择系统菜单,如:档案-商品档案
     *
     * @param masterMenuName 主菜单,如:档案
     * @param subMenuName    子菜单，如:商品档案
     */
    public static void selectMenu(String masterMenuName, String subMenuName) {
        By masterMenu = By.xpath(".//span[text()='" + masterMenuName + "']/parent::div/parent::div");
        By subMenu = By.xpath(".//div[text()='" + subMenuName + "']/parent::div");
        ElementKeyword.moveToElement(masterMenu);
        ElementKeyword.clickElement(subMenu);
        LogUtils.info("选择菜单成功，主菜单: [" + masterMenuName + "],子菜单为: [" + subMenuName + "].");
        switchToTargetFrame(subMenuName);
    }

    /**
     * 跳转指定菜单Frame
     *
     * @param targetMenu 指定菜单
     */
    private static void switchToTargetFrame(String targetMenu) {
        if (targetMenu.equals("销售订单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/saleorder/load/add/0']"));
        } else if (targetMenu.equals("销售单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/sale/load/add/0?type=0']"));
        } else if (targetMenu.equals("退货单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/sale/load/add/0?type=1']"));
        } else if (targetMenu.equals("采购订单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/purchaseorder/load/add/0']"));
        } else if (targetMenu.equals("采购单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/purchase/load/add/0?type=0']"));
        } else if (targetMenu.equals("采购订单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/purchase/load/add/0?type=1']"));
        } else if (targetMenu.equals("调拨单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/move/load/add/0']"));
        } else if (targetMenu.equals("盘点盈亏单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/stockadjust/load/add/0']"));
        } else if (targetMenu.equals("成本调价单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/price/load/add/0']"));
        } else if (targetMenu.equals("报损单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/loss/load/add/0']"));
        } else if (targetMenu.equals("整仓盘点任务")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/check/load/add/1?checkType=1']"));
        } else if (targetMenu.equals("部分盘点任务")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/check/load/add/1?checkType=0']"));
        } else if (targetMenu.equals("收款单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/consumerpaid/load/add/0']"));
        } else if (targetMenu.equals("付款单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/supplierpaid/load/add/0']"));
        } else if (targetMenu.equals("预收款单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/consumerprepay/load/add/0']"));
        } else if (targetMenu.equals("预付款单")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/supplierprepay/load/add/0']"));
        } else if (targetMenu.equals("费用支出")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/expenditure/load/add/0']"));
        } else if (targetMenu.equals("品牌档案")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/doc/brand/list']"));
        } else if (targetMenu.equals("商品档案")) {
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/doc/goods/list']"));
        }
    }
}
