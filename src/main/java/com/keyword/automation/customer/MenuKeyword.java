package com.keyword.automation.customer;

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
    }
}
