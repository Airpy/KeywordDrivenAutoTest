package com.keyword.automation.customer;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.browser.Browsers;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.database.domain.Bill;
import com.keyword.automation.database.domain.BillGoods;
import com.keyword.automation.database.domain.FinanceBill;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public static String addConsumerPaidBill(String billNo, Bill bill, FinanceBill financeBill) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ElementKeyword.sendKeys(getPageElement("客户"), bill.getConsumer());
        PageKeyword.clickSelectOptions(getPageElement("业务员"), bill.getWorkOper());
        ElementKeyword.sendKeys(getPageElement("备注"), bill.getBillRemark());
        // 为了页面渲染数据
        PageKeyword.clickPageButton("打印");
        int row = getRowsByBillNo(billNo);
        row = row - 1;
        String billType = ElementKeyword.getText(getCellElement(row, "saleBillType"));
        String workTime = ElementKeyword.getText(getCellElement(row, "workTime"));
        double origTotalAmount = Double.valueOf(ElementKeyword.getText(getCellElement(row, "origTotalAmount")));
        double origDiscountAmount = Double.valueOf(ElementKeyword.getText(getCellElement(row, "origDiscountAmount")));
        double origPaidAmount = Double.valueOf(ElementKeyword.getText(getCellElement(row, "origPaidAmount")));
        double origLeftAmount = Double.valueOf(ElementKeyword.getText(getCellElement(row, "origLeftAmount")));
        // 因为自动化的操作数据相对写死，所以这边的判断条件就写死，减少代码复杂度
        // 当单据的数据全部都正确时，则继续编辑收款单
        if (billType.equalsIgnoreCase("销售单") && workTime.equalsIgnoreCase(dateFormat.format(date)) && origTotalAmount
                == 976.00 && origDiscountAmount == 3.3 && origPaidAmount == 9.9 && origLeftAmount == 962.80) {
            sendKeysToCells(row, "nowDiscountAmount", null, financeBill);
            sendKeysToCells(row, "nowPaidAmount", null, financeBill);
            // 为了页面渲染数据
            PageKeyword.clickPageButton("打印");
        } else {
            throw new RuntimeException("单据[" + billNo + "]数据错误，可能原因为:单据类型、开单时间、单据金额、优惠金额、已收金额或尚欠金额.");
        }
        Double leftAmount = Double.valueOf(ElementKeyword.getText(getCellElement(row, "leftAmount")));
        Double nowDiscountAmount = Double.valueOf(ElementKeyword.getAttribute(getPageElement("本次优惠金额"), "value"));
        Double cash = Double.valueOf(ElementKeyword.getAttribute(getPageElement("现金"), "value"));
        Double totalLeftAmount = Double.valueOf(ElementKeyword.getAttribute(getPageElement("剩余金额"), "value"));
        if (leftAmount == 959.50 && nowDiscountAmount == financeBill.getNowDiscountAmount() && cash == financeBill
                .getNowPaidAmount()) {
            PageKeyword.clickPageButton("保存并审核");
            PageKeyword.clickPageButton("确认");
            String billNum = ElementKeyword.getText(By.xpath(".//span[text()='单据编号：']/following-sibling::span"));
            PageKeyword.closeTag("销售单");
            LogUtils.info("添加收款单(未审核)成功.");
            return billNum;
        } else {
            throw new RuntimeException("单据[" + billNo + "]数据金额计算错误.");
        }
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
        PageKeyword.closeTag("单");
        BrowserKeyword.switchToDefaultFrameOrWindow();
        LogUtils.info("审核单据[" + billNo + "]成功.");
    }

    /**
     * 获取销售单页面数据
     *
     * @param billType 单据类型
     * @param billNo   单据编号
     * @return 全部正确则返回True，否则返回False
     */
    public static Bill getSaleBillData(String billType, String billNo) {
        Bill bill = new Bill();
//        BillGoods billGoods = new BillGoods();
        List<BillGoods> billGoodsList = new ArrayList<BillGoods>();
        openBillDetail(billType, billNo);
        bill.setConsumer(ElementKeyword.getAttribute(getPageElement("客户"), "value"));
        bill.setWarehouse(ElementKeyword.getAttribute(getPageElement("仓库"), "value"));
        bill.setWorkOper(ElementKeyword.getAttribute(getPageElement("业务员"), "value"));
        bill.setWorkTime(ElementKeyword.getAttribute(getPageElement("交易日期"), "value"));
        bill.setBillRemark(ElementKeyword.getAttribute(getPageElement("备注"), "value"));
        for (int i = 0; i < 3; i++) {
            String goodsNameData = ElementKeyword.getText(getGoodsElementForCheck(i, "goodsName")).trim();
            String barcodeData = ElementKeyword.getText(getGoodsElementForCheck(i, "barcode")).trim();
            String currUnitNameData = ElementKeyword.getText(getGoodsElementForCheck(i, "currUnitName")).trim();
            // 小包单位的商品单位换算为空，需要try防止driver失效
            String unitFactorNameData;
            try {
                WebDriver driver = Browsers.getActiveBrowser().getDriver();
                unitFactorNameData = driver.findElement(getGoodsElementForCheck(i, "unitFactorName")).getText().trim();
            } catch (Exception e) {
                unitFactorNameData = null;
            }
            String quantityData = ElementKeyword.getText(getGoodsElementForCheck(i, "quantity"));
            double realPriceData = Double.valueOf(ElementKeyword.getText(getGoodsElementForCheck(i, "realPrice")));
            double subAmountData = Double.valueOf(ElementKeyword.getText(getGoodsElementForCheck(i, "subAmount")));
//        double stockQuantityData = Double.valueOf(ElementKeyword.getText(getGoodsElementForCheck(0,"stockQuantity")));
            String goodsRemarkData = ElementKeyword.getText(getGoodsElementForCheck(i, "remark")).trim();
            BillGoods billGoods = new BillGoods(goodsNameData, barcodeData, currUnitNameData, unitFactorNameData,
                    quantityData, realPriceData, subAmountData, goodsRemarkData);
            billGoodsList.add(billGoods);
        }
        bill.setBillGoodsList(billGoodsList);
        bill.setDiscountAmount(Double.valueOf(ElementKeyword.getAttribute(getPageElement("优惠金额"), "value")));
        bill.setAfterDiscountAmount(Double.valueOf(ElementKeyword.getAttribute(getPageElement("优惠后金额"), "value")));
        bill.setCash(Double.valueOf(ElementKeyword.getAttribute(getPageElement("现金"), "value")));
        bill.setPrepay(Double.valueOf(ElementKeyword.getAttribute(getPageElement("预收款"), "value")));
        bill.setLeftAmount(Double.valueOf(ElementKeyword.getAttribute(getPageElement("欠款金额"), "value")));
        return bill;
    }

    public static boolean validBillData(Bill originBill, Bill currBill) {
        return true;
    }

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
        PageKeyword.closeTag("查看");
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
        sendKeysToCells(targetRow, "goodsName", bill, null);
        sendKeysToCells(targetRow, "currUnitName", bill, null);
        sendKeysToCells(targetRow, "quantity", bill, null);
        sendKeysToCells(targetRow, "realPrice", bill, null);
        sendKeysToCells(targetRow, "remark", bill, null);
    }

    /**
     * 在指定行、指定列传入指定操作单据数据
     *
     * @param targetRow   指定行
     * @param targetField 指定列
     * @param bill        操作数据对象
     */
    private static void sendKeysToCells(int targetRow, String targetField, Bill bill, FinanceBill financeBill) {
        By byCell = By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + targetRow +
                "]/td[@field='" + targetField + "']");
        ElementKeyword.clickElement(byCell);
        if (targetField.equalsIgnoreCase("goodsName")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), bill.getBillGoodsList().get(targetRow)
                    .getGoodsName());
            ElementKeyword.sleep(2);
        } else if (targetField.equalsIgnoreCase("currUnitName")) {
            ElementKeyword.clickElement(byCell);
            PageKeyword.clickSelectOptions(getCellElement(targetRow, targetField), bill.getBillGoodsList().get
                    (targetRow).getCurrUnitName());
        } else if (targetField.equalsIgnoreCase("quantity")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(bill.getBillGoodsList()
                    .get(targetRow).getQuantity()));
            ElementKeyword.sleep(2);
        } else if (targetField.equalsIgnoreCase("realPrice")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(bill.getBillGoodsList()
                    .get(targetRow).getRealPrice()));
        } else if (targetField.equalsIgnoreCase("remark")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(bill.getBillGoodsList()
                    .get(targetRow).getGoodsRemark()));
        } else if (targetField.equalsIgnoreCase("nowDiscountAmount")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(financeBill
                    .getNowDiscountAmount()));
        } else if (targetField.equalsIgnoreCase("nowPaidAmount")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(financeBill
                    .getNowPaidAmount()));
        } else if (targetField.equalsIgnoreCase("leftAmount")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(financeBill.getLeftAmount
                    ()));
        } else {
            throw new RuntimeException("传入的单据列标识错误");
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
     * 获取商品档案单元格定位(录入单据时使用)
     *
     * @param row         指定单元格行
     * @param targetField 单元格的Field，如goodsName/goodsId/barcode/currUnitName/unitFactorName/currUnitFactor/quantity
     *                    /origPrice/realPrice/discount/subAmount/stockQuantity/remark/opt
     * @return 指定单元格的定位
     */
    private static By getCellElement(int row, String targetField) {
        if (targetField.equalsIgnoreCase("goodsName") || targetField.equalsIgnoreCase("currUnitName") || targetField
                .equalsIgnoreCase("quantity") || targetField.equalsIgnoreCase("realPrice") || targetField
                .equalsIgnoreCase("subAmount") || targetField.equalsIgnoreCase("remark") || targetField
                .equalsIgnoreCase("nowDiscountAmount") || targetField.equalsIgnoreCase("nowPaidAmount")) {
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
     * 获取商品档案单元格定位(检查页面元素时使用)
     *
     * @param row         指定单元格行
     * @param targetField 单元格的Field，如goodsName/goodsId/barcode/currUnitName/unitFactorName/currUnitFactor/quantity
     *                    /origPrice/realPrice/discount/subAmount/stockQuantity/remark/opt
     * @return 指定单元格的定位
     */
    private static By getGoodsElementForCheck(int row, String targetField) {
        return By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + row +
                "]/td[@field='" + targetField + "']/div");
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

    /**
     * 账户类型标识，如[优惠金额/优惠后金额/现金/银行/其他/预收款]
     *
     * @param selectName 金额类型名称
     * @return 对应账户的定位方式
     */
    private static By getMoneyForCheck(String selectName) {
        if (selectName.equalsIgnoreCase("优惠金额") || selectName.equalsIgnoreCase("优惠后金额")) {
            return By.xpath(".//label[text()='" + selectName + "']/following-sibling::span/input[2]");
        } else {
            return By.xpath(".//ul[@id='amountBox']/li[text()='" + selectName + "']/span/input[2]");
        }
    }

    private static int getRowsByBillNo(String billNo) {
        By byBillNo = By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody//td[@field='saleBillNo']/div[text()='"
                + billNo + "']/parent::td/parent::tr");
        String index = ElementKeyword.getAttribute(byBillNo, "datagrid-row-index");
        return Integer.parseInt(index) + 1;
    }
}
