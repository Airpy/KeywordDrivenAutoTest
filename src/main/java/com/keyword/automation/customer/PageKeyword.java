package com.keyword.automation.customer;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.browser.Browsers;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.bean.BillCell;
import com.keyword.automation.bean.BillFooter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义网页关键字
 *
 * @author Amio_
 */
public class PageKeyword {
    /**
     * 点击页面按钮
     *
     * @param buttonName 指定按钮名称，如添加/批量删除/保存/保存并新增/关闭/确认/取消/导入/导出 <br/>
     *                   注意：商品分类的添加/修改/删除除外
     */
    public static void clickPageButton(String buttonName) {
        By byButton = By.xpath(".//span[@class='l-btn-text' and contains(text(),'" + buttonName + "')]");
        ElementKeyword.clickElement(byButton);
    }

    /**
     * 点击指定下拉选择框的指定元素
     *
     * @param bySelect   指定下拉框
     * @param optionName 指定元素
     */
    public static void clickSelectOptions(By bySelect, String optionName) {
        ElementKeyword.clickElement(bySelect);
        By byOption = By.xpath(".//div[contains(@class,'combo-p') and contains(@style,'display: block')]/div[contains" +
                "(@class,'panel-body-noheader')]/div[text()='" + optionName + "']");
        ElementKeyword.clickElement(byOption);
    }

    /**
     * 点击指定下拉选择框的指定元素(仅限仓库菜单使用)
     *
     * @param targetName 指定下拉框
     * @param optionName 指定元素
     */
    public static void clickSelectOptionsForWarehouse(String targetName, String optionName) {
        By byTarget = By.xpath(".//label[contains(text(),'" + targetName + "')]/following-sibling::span/input[1]");
        if (targetName.equals("入货仓库")) ElementKeyword.clickElement(byTarget);
        By byOption = By.xpath(".//div[contains(@class,'combo-p') and contains(@style,'display: block')]/div[contains" +
                "(@class,'panel-body-noheader')]/div[text()='" + optionName + "']");
        ElementKeyword.clickElement(byOption);
    }

    /**
     * 关闭标签页
     *
     * @param tagName 指定标签页名称
     */
    public static void closeTag(String tagName) {
        BrowserKeyword.switchToDefaultFrameOrWindow();
        ElementKeyword.clickElement(By.xpath(".//span[contains(text(),'" + tagName + "')" +
                "]/parent::a/following-sibling::a"));
    }

    /**
     * @param targetName 单据页面元素标识，如单据头部分：客户、仓库，单据尾部分：优惠金额、剩余金额等
     * @return 该元素的By的定位方式
     */
    public static By getBillPageElement(String targetName) {
        return By.xpath(".//label[contains(text(),'" + targetName + "')]/following-sibling::span/input[1]");
    }

    /**
     * 选择收款账户并返回有哪些账户类型已经被选择
     *
     * @param billFooter 单据尾传入数据对象
     * @return 账户支付类型列表
     */
    public static List<String> selectAccountType(BillFooter billFooter) {
        if (billFooter != null) {
            List<String> accountTypeList = new ArrayList<String>();
            // 判断传入值是否为0.0,如果为0.0则说明不需要选择该支付方式
            if (billFooter.getCash() != 0.0) accountTypeList.add("现金");
            if (billFooter.getBank() != 0.0) accountTypeList.add("银行");
            if (billFooter.getPrePay() != 0.0) accountTypeList.add("预收款");
            if (billFooter.getPreCharge() != 0.0) accountTypeList.add("预付款");
            if (billFooter.getOthers() != 0.0) accountTypeList.add("其他");
            if (accountTypeList.size() <= 2) {
                ElementKeyword.clickElement(By.xpath(".//a[contains(text(),'更改')]"));
                ElementKeyword.findElement(By.xpath(".//div[@id='dlgAccount']/parent::div"));
                for (String accountType : accountTypeList) {
                    By bySelect = By.xpath(".//div[text()='" + accountType + "']");
                    ElementKeyword.clickElement(bySelect);
                }
                PageKeyword.clickPageButton("选择并关闭");
            } else {
                throw new RuntimeException("最多选择两种支付方式.您已经选择了:[" + accountTypeList.size() + "].");
            }
            return accountTypeList;
        } else {
            return null;
        }
    }

    /**
     * 账户类型标识，如[现金/银行/其他/预收款]
     *
     * @param targetAccountName 账户类型名称
     * @return 对应账户的定位方式
     */
    public static By getAccountTypeElement(String targetAccountName) {
        return By.xpath(".//ul[@id='amountBox']/li[text()='" + targetAccountName + "']/span/input[1]");
    }

    /**
     * 向指定行、指定单元格传入指定数据
     *
     * @param targetRow   指定行
     * @param targetField 指定单元格
     * @param billCell    指定数据
     */
    public static void sendDataToCell(int targetRow, String targetField, BillCell billCell) {
        By byCell = By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + targetRow +
                "]/td[@field='" + targetField + "']");
        ElementKeyword.clickElement(byCell);
        if (targetField.equalsIgnoreCase("goodsName")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), billCell.getGoodsName());
            ElementKeyword.sleep(2);
        } else if (targetField.equalsIgnoreCase("currUnitName")) {
            ElementKeyword.clickElement(byCell);
            PageKeyword.clickSelectOptions(getCellElement(targetRow, targetField), billCell.getCurrUnitName());
        } else if (targetField.equalsIgnoreCase("quantity")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(billCell.getQuantity()));
            ElementKeyword.sleep(2);
        } else if (targetField.equalsIgnoreCase("realPrice")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(billCell.getRealPrice()));
        } else if (targetField.equalsIgnoreCase("subAmount")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(billCell.getSubAmount()));
        } else if (targetField.equalsIgnoreCase("remark")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(billCell.getRemark()));
        } else if (targetField.equalsIgnoreCase("newCostPrice")) {
            ElementKeyword.clickElement(byCell);
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(billCell.getNewCostPrice()));
        } else if (targetField.equalsIgnoreCase("pkgQuantity")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(billCell.getPkgQuantity()));
        } else if (targetField.equalsIgnoreCase("baseQuantity")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(billCell.getBaseQuantity()));
        } else if (targetField.equalsIgnoreCase("nowDiscountAmount")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(billCell
                    .getNowDiscountAmount()));
        } else if (targetField.equalsIgnoreCase("nowPaidAmount")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(billCell.getNowPaidAmount
                    ()));
        } else if (targetField.equalsIgnoreCase("costTypeName")) {
            ElementKeyword.clickElement(byCell);
            PageKeyword.clickSelectOptions(getCellElement(targetRow, targetField), billCell.getCostTypeName());
        } else if (targetField.equalsIgnoreCase("expenditureAmount")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(billCell
                    .getExpenditureAmount()));
        } else if (targetField.equalsIgnoreCase("consumerName")) {
            ElementKeyword.sendKeys(getCellElement(targetRow, targetField), String.valueOf(billCell
                    .getExpenditureAmount()));
        } else {
            throw new RuntimeException("传入的单据列标识错误");
        }
    }

    /**
     * 获取指定行指定列的数据
     *
     * @param targetRow       指定行
     * @param targetFieldList 指定列列表
     * @return 单据数据对象
     */
    public static BillCell getDataFromCell(int targetRow, List<String> targetFieldList) {
        WebDriver driver = Browsers.getActiveBrowser().getDriver();
        BillCell billCell = new BillCell();
        for (String targetField : targetFieldList) {
            By byField = By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + targetRow +
                    "]/td[@field='" + targetField + "']/div");
            if (targetField.equalsIgnoreCase("goodsName")) {
                billCell.setGoodsName(ElementKeyword.getText(byField));
            } else if (targetField.equalsIgnoreCase("barcode")) {
                billCell.setBarcode(ElementKeyword.getText(byField));
            } else if (targetField.equalsIgnoreCase("currUnitName")) {
                billCell.setCurrUnitName(ElementKeyword.getText(byField));
            } else if (targetField.equalsIgnoreCase("currUnitFactorName")) {
                // 小包单位这个单元格为空,必须用临时driver来找元素
                billCell.setCurrUnitFactorName(driver.findElement(byField).getText());
            } else if (targetField.equalsIgnoreCase("quantity")) {
                billCell.setQuantity(ElementKeyword.getText(byField));
            } else if (targetField.equalsIgnoreCase("realPrice")) {
                billCell.setRealPrice(Double.valueOf(ElementKeyword.getText(By.xpath("" +
                        ".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + targetRow +
                        "]/td[@field='" + targetField + "']/div/span"))));
            } else if (targetField.equalsIgnoreCase("subAmount")) {
                billCell.setSubAmount(Double.valueOf(ElementKeyword.getText(byField)));
            } else if (targetField.equalsIgnoreCase("remark")) {
                billCell.setRemark(ElementKeyword.getText(byField));
            } else {
                throw new RuntimeException("传入的单据列标识错误");
            }
        }
        return billCell;
    }

    /**
     * 获取商品档案单元格定位(录入单据时使用)
     *
     * @param targetRow   指定单元格行
     * @param targetField 单元格的Field，如goodsName/goodsId/barcode/currUnitName/unitFactorName/currUnitFactor/quantity
     *                    /origPrice/realPrice/discount/subAmount/stockQuantity/remark/opt
     * @return 指定单元格的定位
     */
    public static By getCellElement(int targetRow, String targetField) {
        if (targetField.equalsIgnoreCase("goodsName") || targetField.equalsIgnoreCase("currUnitName") || targetField
                .equalsIgnoreCase("quantity") || targetField.equalsIgnoreCase("realPrice") || targetField
                .equalsIgnoreCase("subAmount") || targetField.equalsIgnoreCase("remark") || targetField
                .equalsIgnoreCase("nowDiscountAmount") || targetField.equalsIgnoreCase("nowPaidAmount") ||
                targetField.equalsIgnoreCase("newCostPrice")) {
            return By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + targetRow +
                    "]/td[@field='" + targetField + "']//td/span/input[1]");
        } else if (targetField.equalsIgnoreCase("opt")) {
            // 单元格删除的按钮(添加行按钮目前没有维护)
            return By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + targetRow +
                    "]/td[@field='" + targetField + "']/div/span[2]/a");
        } else {
            return By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr[@datagrid-row-index=" + targetRow +
                    "]/td[@field='" + targetField + "']/div");
        }
    }

    public static int getCellRowsCount() {
        int count = 0;
        WebDriver driver = Browsers.getActiveBrowser().getDriver();
        By byCount = By.xpath(".//div[@class='datagrid-view2']/div[2]//tbody/tr/td[@field='goodsName']/div");
        List<WebElement> webElementList = driver.findElements(byCount);
        for (WebElement webElement : webElementList) {
            if ((webElement.getText() != null) && (!webElement.getText().equals(""))) {
                count++;
            }
        }
        return count;
    }

    /**
     * 验证操作是否成功(已经测试过品牌菜单、商品档案菜单)
     *
     * @param targetMsg 目标标志性成功信息
     * @return 如果存在则返回True，不存在返回False
     */
    public static boolean validIsSuccess(String targetMsg) {
        try {
            ElementKeyword.findElement(By.xpath(".//tr[contains(@id,'datagrid-row-r1')]/td[1]/div[text()='" +
                    targetMsg + "']"));
            return true;
        } catch (Exception e) {
            LogUtils.info("商品类别[" + targetMsg + "]不存在.");
            return false;
        }
    }
}
