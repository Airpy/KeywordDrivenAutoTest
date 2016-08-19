package com.keyword.automation.customer;

import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.bean.BillCell;
import com.keyword.automation.bean.BillFooter;
import com.keyword.automation.bean.BillHeader;
import com.keyword.automation.database.domain.BillWhole;
import org.openqa.selenium.By;

import java.util.List;

import static com.keyword.automation.customer.PageKeyword.getBillPageElement;

/**
 * 自定义单据关键字
 *
 * @author Amio_
 */
public class BillCommonKeyword {
    public static String addBill(String billType, BillWhole billWhole) {
        billHeaderAction(billType, billWhole.getBillHeader());
        billCellAction(billType, billWhole.getBillCellList());
        billFooterAction(billType, billWhole.getBillFooter());
        return null;
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
            PageKeyword.clickSelectOptions(getBillPageElement("出货仓库"), billHeader.getOutWarehouseName());
            PageKeyword.clickSelectOptions(getBillPageElement("入货仓库"), billHeader.getInWarehouseName());
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.equals("盘点盈亏单")) {
            PageKeyword.clickSelectOptions(getBillPageElement("仓库"), billHeader.getWarehouseName());
            PageKeyword.clickSelectOptions(getBillPageElement("经办人"), billHeader.getWorkOperName());
            if (isBaseUnit)
                ElementKeyword.clickElement(By.xpath(".//b[contains(text(),'单位')]/preceding-sibling::input"));
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.equals("成本调价单")) {
            ElementKeyword.sendKeys(getBillPageElement("备注"), billHeader.getBillRemark());
        } else if (billType.equals("破损单")) {
            PageKeyword.clickSelectOptions(getBillPageElement("仓库"), billHeader.getWarehouseName());
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
     * @param billType
     * @param billCellList
     */
    private static void billCellAction(String billType, List<BillCell> billCellList) {
    }

    /**
     * 封装各种类型单据尾录入操作
     *
     * @param billType   单据类型,如输入[销售订单、销售单、调拨单等字符]
     * @param billFooter 单据尾对象
     */
    private static void billFooterAction(String billType, BillFooter billFooter) {
        List<String> isSelectedAccount = PageKeyword.selectAccountType(billFooter);
        if (billType.equals("销售单")) {
            ElementKeyword.sendKeys(getBillPageElement("优惠金额"), String.valueOf(billFooter.getDiscountAmount()));
        }
    }
}
