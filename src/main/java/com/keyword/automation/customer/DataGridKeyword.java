package com.keyword.automation.customer;

import com.keyword.automation.action.BrowserKeyword;

/**
 * 自定义DataGrid表格关键字
 *
 * @author Amio_
 */
public class DataGridKeyword {
    /**
     * 通过输入方式选择商品
     *
     * @param iframe    单据的iframe(每种单据的iframe唯一)
     * @param goodsName 商品名称
     */
    public static void selectGoodsByInput(String iframe, String goodsName) {
        BrowserKeyword.switchToFrame(iframe);

    }
}
