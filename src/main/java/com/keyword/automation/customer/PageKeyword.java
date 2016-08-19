package com.keyword.automation.customer;

import com.keyword.automation.action.BrowserKeyword;
import com.keyword.automation.action.ElementKeyword;
import com.keyword.automation.base.utils.LogUtils;
import com.keyword.automation.bean.BillFooter;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        By byButton = By.xpath(".//span[text()='" + buttonName + "']");
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
        By byOption = By.xpath(".//div[contains(@class,'combo-p')]/div[contains(@class,'panel-body-noheader')" +
                "]/div[text()='" + optionName + "']");
        ElementKeyword.clickElement(byOption);
    }

    /**
     * 关闭标签页
     *
     * @param tagName 指定标签页名称
     */
    public static void closeTag(String tagName) {
        BrowserKeyword.switchToDefaultFrameOrWindow();
        ElementKeyword.clickElement(By.xpath(".//span[contains(text()," + tagName + ")" +
                "]/parent::a/following-sibling::a"));
    }

    /**
     * @param targetName 单据页面元素标识，如单据头部分：客户、仓库，单据尾部分：优惠后金额、剩余金额等
     * @return 该元素的By的定位方式
     */
    public static By getBillPageElement(String targetName) {
        return By.xpath(".//label[text()='" + targetName + "']/following-sibling::span/input[1]");
    }

    /**
     * 选择收款账户并返回有哪些账户类型已经被选择
     *
     * @param billFooter 单据尾传入数据对象
     * @return 账户支付类型列表
     */
    public static List<String> selectAccountType(BillFooter billFooter) {
        List<String> accountTypeList = new ArrayList<String>();
        Map<String, Double> accountMap = billFooter.getAccountMap();
        for (Map.Entry<String, Double> entry : accountMap.entrySet()) {
            if (entry.getValue() != 0.0) {
                accountTypeList.add(entry.getKey());
            }
        }
        if (accountTypeList.size() <= 2) {
            ElementKeyword.clickElement(By.xpath(".//a[text()='更改收款账户']"));
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
