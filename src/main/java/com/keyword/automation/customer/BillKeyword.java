package com.keyword.automation.customer;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.browser.Browsers;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.database.domain.Bill;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义单据关键字
 *
 * @author Amio_
 */
public class BillKeyword {
    private BillKeyword() {

    }

    /**
     * 添加销售单(未审核)
     *
     * @param bill 销售单数据对象
     * @return 新增的单据号
     */
    public static String addSaleBill(Bill bill) {
        ElementKeyword.sendKeys(getPageElement("客户"), bill.getConsumer());
        PageKeyword.clickSelectOptions(getPageElement("仓库"), bill.getWarehouse());
        PageKeyword.clickSelectOptions(getPageElement("业务员"), bill.getWorkOper());
        ElementKeyword.sendKeys(getPageElement("备注"), bill.getBillRemark());
        for (int i = 1; i <= bill.getBillGoodsList().size(); i++) {
            selectGoodsToCell(i, bill);
        }
        List<String> accountTypeList = new ArrayList<String>();
        accountTypeList.add("现金");
        accountTypeList.add("预收款");
        selectAccountType(accountTypeList);
        ElementKeyword.sendKeys(getPageElement("优惠金额"), String.valueOf(bill.getDiscountAmount()));
        ElementKeyword.sendKeys(getAccountType("现金"), String.valueOf(bill.getCash()));
        ElementKeyword.sendKeys(getAccountType("预收款"), String.valueOf(bill.getPrepay()));
        PageKeyword.clickPageButton("保存");
        String billNo = ElementKeyword.getText(By.xpath(".//span[text()='单据编号：']/following-sibling::span"));
        PageKeyword.closeTag("销售单");
        LogUtils.info("添加销售单(未审核)成功,操作数据为:" + bill.toString());
        return billNo;
    }

    /**
     * 审核指定单据类型的指定单据
     *
     * @param billType 单据类型
     * @param billNo   指定单据编号
     */
    public static void approveBill(String billType, String billNo) {
        openBillDetail(billType, billNo);
        PageKeyword.clickPageButton("审核");
        PageKeyword.clickPageButton("确认");
        LogUtils.info("审核单据[" + billNo + "]成功.");
    }

//    /**
//     * 验证单据数据是否正确
//     *
//     * @param billType 单据类型
//     * @param billNo   单据编号
//     * @return 全部正确则返回True，否则返回False
//     */
//    public static boolean validBillData(String billType, String billNo) {
//        Bill bill = new Bill();
//        openBillDetail(billType, billNo);
//        try {
//            WebDriver tempDriver = Browsers.getActiveBrowser().getDriver();
//            bill.setConsumer(tempDriver.findElement(getPageElement("客户")).getText());
//            bill.setWarehouse(ElementKeyword.getText(getPageElement("仓库")));
//            bill.setWorkOper(ElementKeyword.getText(getPageElement("业务员")));
//            bill.
//        }
//
//    }

    /**
     * 打开指定单据类型的制定单据编号详情
     *
     * @param billType 单据类型
     * @param billNo   单据编号
     */
    private static void openBillDetail(String billType, String billNo) {
        if (billType.equalsIgnoreCase("saleOrder")) {
            MenuKeyword.selectMenu("销售", "查看销售订单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/saleorder/list']"));
        } else if (billType.equalsIgnoreCase("sale")) {
            MenuKeyword.selectMenu("销售", "查看销售单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/sale/list?type=0']"));
        } else if (billType.equalsIgnoreCase("returnSale")) {
            MenuKeyword.selectMenu("销售", "查看退货单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/sale/list?type=1']"));
        } else if (billType.equalsIgnoreCase("purchaseOrder")) {
            MenuKeyword.selectMenu("采购", "查看采购订单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/purchaseorder/list']"));
        } else if (billType.equalsIgnoreCase("purchase")) {
            MenuKeyword.selectMenu("采购", "查看采购单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/purchase/list?type=0']"));
        } else if (billType.equalsIgnoreCase("returnPurchase")) {
            MenuKeyword.selectMenu("采购", "查看采购退货单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/purchase/list?type=1']"));
        } else if (billType.equalsIgnoreCase("move")) {
            MenuKeyword.selectMenu("仓库", "查看调拨单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/move/list']"));
        } else if (billType.equalsIgnoreCase("stockAdjust")) {
            MenuKeyword.selectMenu("仓库", "查看盘点盈亏单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/stockadjust/list']"));
        } else if (billType.equalsIgnoreCase("price")) {
            MenuKeyword.selectMenu("仓库", "查看成本调价单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/price/list']"));
        } else if (billType.equalsIgnoreCase("loss")) {
            MenuKeyword.selectMenu("仓库", "查看破损单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/loss/list']"));
        } else if (billType.equalsIgnoreCase("wholeCheck")) {
            MenuKeyword.selectMenu("仓库", "查看盘点任务（整仓）");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/check/list/1']"));
        } else if (billType.equalsIgnoreCase("partCheck")) {
            MenuKeyword.selectMenu("仓库", "查看盘点任务（部分）");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/check/list/0']"));
        } else if (billType.equalsIgnoreCase("consumerPaid")) {
            MenuKeyword.selectMenu("财务", "查看收款单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/consumerpaid/list']"));
        } else if (billType.equalsIgnoreCase("supplierPaid")) {
            MenuKeyword.selectMenu("财务", "查看付款单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/supplierpaid/list']"));
        } else if (billType.equalsIgnoreCase("consumerPrepay")) {
            MenuKeyword.selectMenu("财务", "查看预收款单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/consumerprepay/list']"));
        } else if (billType.equalsIgnoreCase("supplierPrepay")) {
            MenuKeyword.selectMenu("财务", "查看预付款单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/supplierprepay/list']"));
        } else if (billType.equalsIgnoreCase("expenditure")) {
            MenuKeyword.selectMenu("财务", "查看费用支出");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/expenditure/list']"));
        }
        if (billType.equalsIgnoreCase("saleOrder") || billType.equalsIgnoreCase("purchaseOrder")) {
            ElementKeyword.sendKeys(getPageElement("订单号:"), billNo);
        } else {
            ElementKeyword.sendKeys(getPageElement("单据号:"), billNo);
        }
        PageKeyword.clickPageButton("查询");
        ElementKeyword.clickElement(By.xpath(".//td[@field='billNo']//a[@title='修改']"));
        BrowserKeyword.switchToDefaultFrameOrWindow();
        BrowserKeyword.switchToFrame(By.xpath(".//iframe[contains(@id,'/saas/erp/bill/')]"));
    }

    /**
     * 向目标行、目标列添加商品
     *
     * @param targetRow 目标行
     * @param bill      操作数据对象
     */
    private static void selectGoodsToCell(int targetRow, Bill bill) {
        targetRow = targetRow - 1;
        sendKeysToCells(targetRow, "goodsName", bill);
        sendKeysToCells(targetRow, "currUnitName", bill);
        sendKeysToCells(targetRow, "quantity", bill);
        sendKeysToCells(targetRow, "realPrice", bill);
        sendKeysToCells(targetRow, "remark", bill);
    }

    /**
     * 在指定行、指定列传入指定操作单据数据
     *
     * @param targetRow   指定行
     * @param targetField 指定列
     * @param bill        操作数据对象
     */
    private static void sendKeysToCells(int targetRow, String targetField, Bill bill) {
        By byCell = By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + targetRow +
                "]/td[@field='" + targetField + "']");
        ElementKeyword.clickElement(byCell);
        if (targetField.equalsIgnoreCase("goodsName")) {
            ElementKeyword.sendKeys(getGoodsElement(targetRow, targetField), bill.getBillGoodsList().get(targetRow)
                    .getGoodsName());
            ElementKeyword.sleep(2);
        } else if (targetField.equalsIgnoreCase("currUnitName")) {
            ElementKeyword.clickElement(byCell);
            PageKeyword.clickSelectOptions(getGoodsElement(targetRow, targetField), bill.getBillGoodsList().get
                    (targetRow).getCurrUnitName());
        } else if (targetField.equalsIgnoreCase("quantity")) {
            ElementKeyword.sendKeys(getGoodsElement(targetRow, targetField), String.valueOf(bill.getBillGoodsList()
                    .get(targetRow).getQuantity()));
            ElementKeyword.sleep(2);
        } else if (targetField.equalsIgnoreCase("realPrice")) {
            ElementKeyword.sendKeys(getGoodsElement(targetRow, targetField), String.valueOf(bill.getBillGoodsList()
                    .get(targetRow).getRealPrice()));
        } else if (targetField.equalsIgnoreCase("remark")) {
            ElementKeyword.sendKeys(getGoodsElement(targetRow, targetField), String.valueOf(bill.getBillGoodsList()
                    .get(targetRow).getGoodsRemark()));
        } else {
            throw new RuntimeException("传入的商品列标识错误，请传入[goodsName/currUnitName/quantity/realPrice/remark.");
        }
    }

    /**
     * 选择收款账户
     *
     * @param accountTypeList 收款账户类型集合(最多两个类型[现金/银行/其他/预收款])
     */
    private static void selectAccountType(List<String> accountTypeList) {
        ElementKeyword.clickElement(By.xpath(".//a[text()='更改收款账户']"));
        ElementKeyword.findElement(By.xpath(".//div[@id='dlgAccount']/parent::div"));
        for (String accountType : accountTypeList) {
            By bySelect = By.xpath(".//div[text()='" + accountType + "']");
            ElementKeyword.clickElement(bySelect);
        }
        PageKeyword.clickPageButton("选择并关闭");
    }

    /**
     * @param selectName 页面元素标识，如客户/仓库/业务员/备注/优惠金额/优惠后金额/现金等
     * @return 该元素的By的定位方式
     */
    private static By getPageElement(String selectName) {
        return By.xpath(".//label[text()='" + selectName + "']/following-sibling::span/input[1]");
    }

    /**
     * 获取商品档案单元格定位
     *
     * @param row         指定单元格行
     * @param targetField 单元格的Field，如goodsName/goodsId/barcode/currUnitName/unitFactorName/currUnitFactor/quantity
     *                    /origPrice/realPrice/discount/subAmount/stockQuantity/remark/opt
     * @return 指定单元格的定位
     */
    private static By getGoodsElement(int row, String targetField) {
        if (targetField.equalsIgnoreCase("goodsName") || targetField.equalsIgnoreCase("currUnitName") || targetField
                .equalsIgnoreCase("quantity") || targetField.equalsIgnoreCase("realPrice") || targetField
                .equalsIgnoreCase("subAmount") || targetField.equalsIgnoreCase("remark")) {
            return By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + row +
                    "]/td[@field='" + targetField + "']//td/span/input[1]");
        } else if (targetField.equalsIgnoreCase("opt")) {
            // 单元格删除的按钮(添加行按钮目前没有维护)
            return By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + row +
                    "]/td[@field='" + targetField + "']/div/span[2]/a");
        } else {
            return By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + row +
                    "]/td[@field='" + targetField + "']/div");
        }
    }

    /**
     * 账户类型标识，如[现金/银行/其他/预收款]
     *
     * @param selectName 账户类型名称
     * @return 对应账户的定位方式
     */
    private static By getAccountType(String selectName) {
        return By.xpath(".//ul[@id='amountBox']/li[text()='" + selectName + "']/span/input[1]");
    }
}
