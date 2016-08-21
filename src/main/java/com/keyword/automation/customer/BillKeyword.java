package com.keyword.automation.customer;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.bean.BillCell;
import com.keyword.automation.bean.BillFooter;
import com.keyword.automation.bean.BillHeader;
import com.keyword.automation.bean.BillWhole;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.keyword.automation.customer.PageKeyword.getBillPageElement;

/**
 * 自定义单据关键字
 *
 * @author Amio_
 */
public class BillKeyword {
    private BillKeyword() {
    }

    /**
     * 添加单据入口
     *
     * @param billType  单据类型,如输入[销售订单、销售单、调拨单等字符]
     * @param billWhole 操作数据对象(包括单据头、单据Cell、单据尾)
     */
    public static String addBill(String billType, BillWhole billWhole) {
        billHeaderAction(billType, billWhole.getBillHeader());
        billCellAction(billType, billWhole.getBillCellList());
        billFooterAction(billType, billWhole.getBillFooter());
        PageKeyword.clickPageButton("保存");
        String billNo = ElementKeyword.getText(By.xpath(".//span[text()='单据编号：']/following-sibling::span"));
        PageKeyword.closeTag(billType);
        LogUtils.info("添加[" + billType + "](未审核)成功,操作数据为: " + billWhole.toString());
        return billNo;
    }

    /**
     * 审核指定单据类型的指定单据
     *
     * @param billType 单据类型,如输入[销售订单、销售单、调拨单等字符]
     * @param billNo   指定单据编号
     */
    public static void approveBill(String billType, String billNo) {
        openBillDetail(billType, billNo);
        PageKeyword.clickPageButton("审核");
        PageKeyword.clickPageButton("确认");
        PageKeyword.closeTag("单");
        BrowserKeyword.switchToDefaultFrameOrWindow();
        LogUtils.info("审核" + billType + "单据[" + billNo + "]成功.");
    }

    /**
     * 红冲指定单据类型的指定单据
     *
     * @param billType 单据类型,如输入[销售订单、销售单、调拨单等字符]
     * @param billNo   指定单据编号
     */
    public static void redBill(String billType, String billNo) {
        openBillDetail(billType, billNo);
        ElementKeyword.clickElement(By.xpath(".//a[text()='红冲']"));
        PageKeyword.clickPageButton("确认");
        PageKeyword.closeTag("单");
        BrowserKeyword.switchToDefaultFrameOrWindow();
        LogUtils.info("红冲" + billType + "单据[" + billNo + "]成功.");
    }

    public static BillWhole getBillDetailData(String billType, String billNo) {
        openBillDetail(billType, billNo);
        BillHeader billHeader = getBillHeaderDetailData(billType);
        List<BillCell> billCellList = getBillCellDetailData(billType);
        return new BillWhole(billHeader, billCellList, null);
    }

    /**
     * 封装各种类型单据头录入操作
     *
     * @param billType   单据类型,如输入[销售订单、销售单、调拨单等字符]
     * @param billHeader 单据头对象
     */
    private static void billHeaderAction(String billType, BillHeader billHeader) {
        boolean isBaseUnit = billHeader.getIsBaseUnit();
        if (billType.equals("销售订单")) {
            ElementKeyword.sendKeys(getBillPageElement("客户"), billHeader.getConsumerName());
            PageKeyword.clickSelectOptions(getBillPageElement("业务员"), billHeader.getWorkOperName());
            if (isBaseUnit)
                ElementKeyword.clickElement(By.xpath(".//b[contains(text(),'单位')]/preceding-sibling::input"));
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.equals("销售单") || billType.equals("退货单")) {
            ElementKeyword.sendKeys(getBillPageElement("客户"), billHeader.getConsumerName());
            PageKeyword.clickSelectOptions(getBillPageElement("仓库"), billHeader.getWarehouseName());
            PageKeyword.clickSelectOptions(getBillPageElement("业务员"), billHeader.getWorkOperName());
            if (isBaseUnit)
                ElementKeyword.clickElement(By.xpath(".//b[contains(text(),'单位')]/preceding-sibling::input"));
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.equals("采购订单")) {
            ElementKeyword.sendKeys(getBillPageElement("供应商"), billHeader.getSupplierName());
            PageKeyword.clickSelectOptions(getBillPageElement("业务员"), billHeader.getWorkOperName());
            if (isBaseUnit)
                ElementKeyword.clickElement(By.xpath(".//b[contains(text(),'单位')]/preceding-sibling::input"));
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.equals("采购单") || billType.equals("采购退货单")) {
            ElementKeyword.sendKeys(getBillPageElement("供应商"), billHeader.getSupplierName());
            PageKeyword.clickSelectOptions(getBillPageElement("仓库"), billHeader.getWarehouseName());
            PageKeyword.clickSelectOptions(getBillPageElement("业务员"), billHeader.getWorkOperName());
            if (isBaseUnit)
                ElementKeyword.clickElement(By.xpath(".//b[contains(text(),'单位')]/preceding-sibling::input"));
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.equals("调拨单")) {
            PageKeyword.clickSelectOptionsForWarehouse("出货仓库", billHeader.getOutWarehouseName());
            PageKeyword.clickSelectOptionsForWarehouse("入货仓库", billHeader.getInWarehouseName());
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.equals("盘点盈亏单")) {
            PageKeyword.clickSelectOptionsForWarehouse("仓库", billHeader.getWarehouseName());
            PageKeyword.clickSelectOptions(getBillPageElement("经办人"), billHeader.getWorkOperName());
            if (isBaseUnit)
                ElementKeyword.clickElement(By.xpath(".//b[contains(text(),'单位')]/preceding-sibling::input"));
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.equals("成本调价单")) {
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.equals("报损单")) {
            PageKeyword.clickSelectOptionsForWarehouse("仓库", billHeader.getWarehouseName());
            PageKeyword.clickSelectOptions(getBillPageElement("经办人"), billHeader.getWorkOperName());
            if (isBaseUnit)
                ElementKeyword.clickElement(By.xpath(".//b[contains(text(),'单位')]/preceding-sibling::input"));
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.contains("盘点任务")) {
            PageKeyword.clickSelectOptions(getBillPageElement("仓库"), billHeader.getWarehouseName());
            PageKeyword.clickSelectOptions(getBillPageElement("盘点人员"), billHeader.getWorkOperName());
        } else if (billType.contains("收款单")) {
            PageKeyword.clickSelectOptions(getBillPageElement("客户"), billHeader.getWarehouseName());
            PageKeyword.clickSelectOptions(getBillPageElement("业务员"), billHeader.getWorkOperName());
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.contains("付款单")) {
            PageKeyword.clickSelectOptions(getBillPageElement("供应商"), billHeader.getWarehouseName());
            PageKeyword.clickSelectOptions(getBillPageElement("业务员"), billHeader.getWorkOperName());
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.contains("费用支出")) {
            PageKeyword.clickSelectOptions(getBillPageElement("业务员"), billHeader.getWorkOperName());
        }
    }

    /**
     * 封装各种类型单据Cell录入操作
     *
     * @param billType     单据类型,如输入[销售订单、销售单、调拨单等字符]
     * @param billCellList 需传入Cell数据列表
     */
    private static void billCellAction(String billType, List<BillCell> billCellList) {
        int currRow = 0;
        for (BillCell billCell : billCellList) {
            if (billType.equals("销售订单") || billType.equals("销售单") || billType.equals("退货单") || billType.equals("采购单")
                    || billType.equals("采购退货单")) {
                PageKeyword.sendDataToCell(currRow, "goodsName", billCell);
                ElementKeyword.sleep(1);
                PageKeyword.sendDataToCell(currRow, "currUnitName", billCell);
                PageKeyword.sendDataToCell(currRow, "quantity", billCell);
                PageKeyword.sendDataToCell(currRow, "realPrice", billCell);
                ElementKeyword.sleep(2);
                PageKeyword.sendDataToCell(currRow, "subAmount", billCell);
                PageKeyword.sendDataToCell(currRow, "remark", billCell);
            } else if (billType.equals("采购订单")) {
                PageKeyword.sendDataToCell(currRow, "goodsName", billCell);
                ElementKeyword.sleep(1);
                PageKeyword.sendDataToCell(currRow, "currUnitName", billCell);
                PageKeyword.sendDataToCell(currRow, "quantity", billCell);
                PageKeyword.sendDataToCell(currRow, "realPrice", billCell);
                ElementKeyword.sleep(2);
                PageKeyword.sendDataToCell(currRow, "subAmount", billCell);
            } else if (billType.equals("调拨单") || billType.equals("盘点盈亏单") || billType.equals("报损单")) {
                PageKeyword.sendDataToCell(currRow, "goodsName", billCell);
                PageKeyword.sendDataToCell(currRow, "currUnitName", billCell);
                PageKeyword.sendDataToCell(currRow, "quantity", billCell);
            } else if (billType.equals("成本调价单")) {
                PageKeyword.sendDataToCell(currRow, "goodsName", billCell);
                ElementKeyword.sleep(1);
                PageKeyword.sendDataToCell(currRow, "newCostPrice", billCell);
            }
            currRow++;
        }
    }

    /**
     * 封装各种类型单据尾录入操作
     *
     * @param billType   单据类型,如输入[销售订单、销售单、调拨单等字符]
     * @param billFooter 单据尾对象
     */
    private static void billFooterAction(String billType, BillFooter billFooter) {
        List<String> selectedAccount = PageKeyword.selectAccountType(billFooter);
        if (selectedAccount != null) {
            for (String accountName : selectedAccount) {
                if (accountName.equals("现金")) {
                    ElementKeyword.sendKeys(PageKeyword.getAccountTypeElement("现金"), String.valueOf(billFooter
                            .getCash()));
                } else if (accountName.equals("银行")) {
                    ElementKeyword.sendKeys(PageKeyword.getAccountTypeElement("银行"), String.valueOf(billFooter
                            .getBank()));
                } else if (accountName.equals("预收款")) {
                    ElementKeyword.sendKeys(PageKeyword.getAccountTypeElement("预收款"), String.valueOf(billFooter
                            .getPrePay
                                    ()));
                } else if (accountName.equals("预付款")) {
                    ElementKeyword.sendKeys(PageKeyword.getAccountTypeElement("预付款"), String.valueOf(billFooter
                            .getPreCharge()));
                } else if (accountName.equals("其他")) {
                    ElementKeyword.sendKeys(PageKeyword.getAccountTypeElement("其他"), String.valueOf(billFooter.getOthers
                            ()));
                }
            }
        }
        if (billType.equals("销售单") || billType.equals("退货单") || billType.equals("采购单") || billType.equals("采购退货单") ||
                billType.equals("收款单")) {
            ElementKeyword.sendKeys(getBillPageElement("优惠金额"), String.valueOf(billFooter.getDiscountAmount()));
        } else if (billType.equals("费用支出")) {
            PageKeyword.clickSelectOptions(getBillPageElement("付款方式"), billFooter.getPayType());
            ElementKeyword.sendKeys(getBillPageElement("支出金额"), String.valueOf(billFooter.getPayAmount()));
        }
    }

    /**
     * 获取指定单据类型的单据头数据详情
     *
     * @param billType 单据类型,如输入[销售订单、销售单、调拨单等字符]
     * @return 单据头数据对象
     */
    private static BillHeader getBillHeaderDetailData(String billType) {
        BillHeader billHeader = new BillHeader();
        if (billType.equals("销售订单")) {
            billHeader.setConsumerName(ElementKeyword.getAttribute(getBillPageElement("客户"), "value"));
            billHeader.setWorkOperName(ElementKeyword.getAttribute(getBillPageElement("业务员"), "value"));
            billHeader.setWorkTime(ElementKeyword.getAttribute(getBillPageElement("交易日期"), "value"));
            billHeader.setIsBaseUnit(ElementKeyword.isSelected(By.xpath(".//b[contains(text(),'单位')" +
                    "]/preceding-sibling::input")));
            billHeader.setBillRemark(ElementKeyword.getAttribute(getBillPageElement("备注"), "value"));
        } else if (billType.equals("销售单") || billType.equals("退货单")) {
            billHeader.setConsumerName(ElementKeyword.getAttribute(getBillPageElement("客户"), "value"));
            billHeader.setWarehouseName(ElementKeyword.getAttribute(getBillPageElement("仓库"), "value"));
            billHeader.setWorkOperName(ElementKeyword.getAttribute(getBillPageElement("业务员"), "value"));
            billHeader.setWorkTime(ElementKeyword.getAttribute(getBillPageElement("交易日期"), "value"));
            billHeader.setIsBaseUnit(ElementKeyword.isSelected(By.xpath(".//b[contains(text(),'单位')" +
                    "]/preceding-sibling::input")));
            billHeader.setBillRemark(ElementKeyword.getAttribute(getBillPageElement("备注"), "value"));
        } else if (billType.equals("采购订单")) {
            billHeader.setSupplierName(ElementKeyword.getAttribute(getBillPageElement("供应商"), "value"));
            billHeader.setWorkOperName(ElementKeyword.getAttribute(getBillPageElement("业务员"), "value"));
            billHeader.setWorkTime(ElementKeyword.getAttribute(getBillPageElement("交易日期"), "value"));
            billHeader.setIsBaseUnit(ElementKeyword.isSelected(By.xpath(".//b[contains(text(),'单位')" +
                    "]/preceding-sibling::input")));
            billHeader.setBillRemark(ElementKeyword.getAttribute(getBillPageElement("备注"), "value"));
        } else if (billType.equals("采购单") || billType.equals("采购退货单")) {
            billHeader.setSupplierName(ElementKeyword.getAttribute(getBillPageElement("供应商"), "value"));
            billHeader.setWarehouseName(ElementKeyword.getAttribute(getBillPageElement("仓库"), "value"));
            billHeader.setWorkOperName(ElementKeyword.getAttribute(getBillPageElement("业务员"), "value"));
            billHeader.setWorkTime(ElementKeyword.getAttribute(getBillPageElement("交易日期"), "value"));
            billHeader.setIsBaseUnit(ElementKeyword.isSelected(By.xpath(".//b[contains(text(),'单位')" +
                    "]/preceding-sibling::input")));
            billHeader.setBillRemark(ElementKeyword.getAttribute(getBillPageElement("备注"), "value"));
        } else if (billType.equals("调拨单")) {
            ElementKeyword.clickElement(By.xpath(".//label[text()='出货仓库']"));
            billHeader.setOutWarehouseName(ElementKeyword.getAttribute(getBillPageElement("出货仓库"), "value"));
            billHeader.setInWarehouseName(ElementKeyword.getAttribute(getBillPageElement("入货仓库"), "value"));
            billHeader.setWorkTime(ElementKeyword.getAttribute(getBillPageElement("交易日期"), "value"));
            billHeader.setBillRemark(ElementKeyword.getAttribute(getBillPageElement("备注"), "value"));
        } else if (billType.equals("盘点盈亏单")) {
            ElementKeyword.clickElement(By.xpath(".//label[text()='仓库']"));
            billHeader.setWarehouseName(ElementKeyword.getAttribute(getBillPageElement("仓库"), "value"));
            billHeader.setWorkOperName(ElementKeyword.getAttribute(getBillPageElement("经办人"), "value"));
            billHeader.setIsBaseUnit(ElementKeyword.isSelected(By.xpath(".//b[contains(text(),'单位')" +
                    "]/preceding-sibling::input")));
            billHeader.setBillRemark(ElementKeyword.getAttribute(getBillPageElement("备注"), "value"));
        } else if (billType.equals("成本调价单")) {
            billHeader.setBillRemark(ElementKeyword.getAttribute(getBillPageElement("备注"), "value"));
        } else if (billType.equals("报损单")) {
            ElementKeyword.clickElement(By.xpath(".//label[text()='仓库']"));
            billHeader.setWorkOperName(ElementKeyword.getAttribute(getBillPageElement("经办人"), "value"));
            billHeader.setIsBaseUnit(ElementKeyword.isSelected(By.xpath(".//b[contains(text(),'单位')" +
                    "]/preceding-sibling::input")));
            billHeader.setBillRemark(ElementKeyword.getAttribute(getBillPageElement("备注"), "value"));
        }
        return billHeader;
    }

    /**
     * 获取指定单据类型的单据Cell数据详情
     *
     * @param billType 单据类型,如输入[销售订单、销售单、调拨单等字符]
     * @return 单据Cell列表
     */
    private static List<BillCell> getBillCellDetailData(String billType) {
        List<BillCell> billCellList = new ArrayList<BillCell>();
        int count = PageKeyword.getCellRowsCount();
        for (int i = 0; i < count; i++) {
            BillCell billCell = new BillCell();
            if (billType.equals("销售订单")) {
                List<String> targetFieldList = new ArrayList<String>();
                targetFieldList.add("goodsName");
                targetFieldList.add("barcode");
                targetFieldList.add("currUnitName");
                targetFieldList.add("currUnitFactorName");
                targetFieldList.add("quantity");
                targetFieldList.add("realPrice");
                targetFieldList.add("subAmount");
                targetFieldList.add("remark");
                billCell = PageKeyword.getDataFromCell(i, targetFieldList);
            }
            billCellList.add(billCell);
        }
        return billCellList;
    }

    public static BillFooter getBillFooterDetailData(String billType, String billNo) {
        return null;
    }


//    public static String addConsumerPaidBill(String billNo, Bill bill, FinanceBill financeBill) {
//        Date date = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        ElementKeyword.sendKeys(getPageElement("客户"), bill.getConsumer());
//        PageKeyword.clickSelectOptions(getPageElement("业务员"), bill.getWorkOper());
//        ElementKeyword.sendKeys(getPageElement("备注"), bill.getBillRemark());
//        // 为了页面渲染数据
//        PageKeyword.clickPageButton("打印");
//        int row = getRowsByBillNo(billNo);
//        row = row - 1;
//        String billType = ElementKeyword.getText(getCellElement(row, "saleBillType"));
//        String workTime = ElementKeyword.getText(getCellElement(row, "workTime"));
//        double origTotalAmount = Double.valueOf(ElementKeyword.getText(getCellElement(row, "origTotalAmount")));
//        double origDiscountAmount = Double.valueOf(ElementKeyword.getText(getCellElement(row, "origDiscountAmount")));
//        double origPaidAmount = Double.valueOf(ElementKeyword.getText(getCellElement(row, "origPaidAmount")));
//        double origLeftAmount = Double.valueOf(ElementKeyword.getText(getCellElement(row, "origLeftAmount")));
//        // 因为自动化的操作数据相对写死，所以这边的判断条件就写死，减少代码复杂度
//        // 当单据的数据全部都正确时，则继续编辑收款单
//        if (billType.equalsIgnoreCase("销售单") && workTime.equalsIgnoreCase(dateFormat.format(date)) && origTotalAmount
//                == 976.00 && origDiscountAmount == 3.3 && origPaidAmount == 9.9 && origLeftAmount == 962.80) {
//            sendKeysToCells(row, "nowDiscountAmount", null, financeBill);
//            sendKeysToCells(row, "nowPaidAmount", null, financeBill);
//            // 为了页面渲染数据
//            PageKeyword.clickPageButton("打印");
//        } else {
//            throw new RuntimeException("单据[" + billNo + "]数据错误，可能原因为:单据类型、开单时间、单据金额、优惠金额、已收金额或尚欠金额.");
//        }
//        Double leftAmount = Double.valueOf(ElementKeyword.getText(getCellElement(row, "leftAmount")));
//        Double nowDiscountAmount = Double.valueOf(ElementKeyword.getAttribute(getPageElement("本次优惠金额"), "value"));
//        Double cash = Double.valueOf(ElementKeyword.getAttribute(getPageElement("现金"), "value"));
//        Double totalLeftAmount = Double.valueOf(ElementKeyword.getAttribute(getPageElement("剩余金额"), "value"));
//        if (leftAmount == 959.50 && nowDiscountAmount == financeBill.getNowDiscountAmount() && cash == financeBill
//                .getNowPaidAmount()) {
//            PageKeyword.clickPageButton("保存并审核");
//            PageKeyword.clickPageButton("确认");
//            String billNum = ElementKeyword.getText(By.xpath(".//span[text()='单据编号：']/following-sibling::span"));
//            PageKeyword.closeTag("销售单");
//            LogUtils.info("添加收款单(未审核)成功.");
//            return billNum;
//        } else {
//            throw new RuntimeException("单据[" + billNo + "]数据金额计算错误.");
//        }
//    }
//


    /**
     * 打开指定单据类型的指定单据编号详情
     *
     * @param billType 单据类型
     * @param billNo   单据编号
     */
    private static void openBillDetail(String billType, String billNo) {
        if (billType.equals("销售订单")) {
            MenuKeyword.selectMenu("销售", "查看销售订单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/saleorder/list']"));
        } else if (billType.equals("销售单")) {
            MenuKeyword.selectMenu("销售", "查看销售单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/sale/list?type=0']"));
        } else if (billType.equals("退货单")) {
            MenuKeyword.selectMenu("销售", "查看退货单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/sale/list?type=1']"));
        } else if (billType.equals("采购订单")) {
            MenuKeyword.selectMenu("采购", "查看采购订单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/purchaseorder/list']"));
        } else if (billType.equals("采购单")) {
            MenuKeyword.selectMenu("采购", "查看采购单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/purchase/list?type=0']"));
        } else if (billType.equals("采购退货单")) {
            MenuKeyword.selectMenu("采购", "查看采购退货单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/purchase/list?type=1']"));
        } else if (billType.equals("调拨单")) {
            MenuKeyword.selectMenu("仓库", "查看调拨单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/move/list']"));
        } else if (billType.equals("盘点盈亏单")) {
            MenuKeyword.selectMenu("仓库", "查看盘点盈亏单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/stockadjust/list']"));
        } else if (billType.equals("成本调价单")) {
            MenuKeyword.selectMenu("仓库", "查看成本调价单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/price/list']"));
        } else if (billType.equals("报损单")) {
            MenuKeyword.selectMenu("仓库", "查看报损单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/loss/list']"));
        } else if (billType.equals("整仓盘点任务")) {
            MenuKeyword.selectMenu("仓库", "查看盘点任务（整仓）");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/check/list/1']"));
        } else if (billType.equals("部分盘点任务")) {
            MenuKeyword.selectMenu("仓库", "查看盘点任务（部分）");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/check/list/0']"));
        } else if (billType.equals("收款单")) {
            MenuKeyword.selectMenu("财务", "查看收款单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/consumerpaid/list']"));
        } else if (billType.equals("付款单")) {
            MenuKeyword.selectMenu("财务", "查看付款单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/supplierpaid/list']"));
        } else if (billType.equals("预收款单")) {
            MenuKeyword.selectMenu("财务", "查看预收款单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/consumerprepay/list']"));
        } else if (billType.equals("预付款单")) {
            MenuKeyword.selectMenu("财务", "查看预付款单");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/supplierprepay/list']"));
        } else if (billType.equals("费用支出")) {
            MenuKeyword.selectMenu("财务", "查看费用支出");
            BrowserKeyword.switchToFrame(By.xpath(".//iframe[@id='erp/bill/expenditure/list']"));
        }
        if (billType.equals("销售订单") || billType.equals("采购订单")) {
            ElementKeyword.sendKeys(getBillPageElement("订单号:"), billNo);
        } else {
            ElementKeyword.sendKeys(getBillPageElement("单据号:"), billNo);
        }
        PageKeyword.clickPageButton("查询");
        ElementKeyword.clickElement(By.xpath(".//td[@field='billNo']//a[@title='修改']"));
        PageKeyword.closeTag("查看");
        BrowserKeyword.switchToDefaultFrameOrWindow();
        BrowserKeyword.switchToFrame(By.xpath(".//iframe[contains(@id,'/saas/erp/bill/')]"));
    }

//    /**
//     * 获取商品档案单元格定位(检查页面元素时使用)
//     *
//     * @param row         指定单元格行
//     * @param targetField 单元格的Field，如goodsName/goodsId/barcode/currUnitName/unitFactorName/currUnitFactor/quantity
//     *                    /origPrice/realPrice/discount/subAmount/stockQuantity/remark/opt
//     * @return 指定单元格的定位
//     */
//    private static By getGoodsElementForCheck(int row, String targetField) {
//        return By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + row +
//                "]/td[@field='" + targetField + "']/div");
//    }
//
//    /**
//     * 账户类型标识，如[优惠金额/优惠后金额/现金/银行/其他/预收款]
//     *
//     * @param selectName 金额类型名称
//     * @return 对应账户的定位方式
//     */
//    private static By getMoneyForCheck(String selectName) {
//        if (selectName.equalsIgnoreCase("优惠金额") || selectName.equalsIgnoreCase("优惠后金额")) {
//            return By.xpath(".//label[text()='" + selectName + "']/following-sibling::span/input[2]");
//        } else {
//            return By.xpath(".//ul[@id='amountBox']/li[text()='" + selectName + "']/span/input[2]");
//        }
//    }
//
//    private static int getRowsByBillNo(String billNo) {
//        By byBillNo = By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody//td[@field='saleBillNo']/div[text()='"
//                + billNo + "']/parent::td/parent::tr");
//        String index = ElementKeyword.getAttribute(byBillNo, "datagrid-row-index");
//        return Integer.parseInt(index) + 1;
//    }
}
