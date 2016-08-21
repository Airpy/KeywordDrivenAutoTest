package com.keyword.automation.bean;

import java.util.List;

/**
 * 测试数据--单据总信息传入数据实体（包含Header、Cell、Footer）
 *
 * @author Amio_
 */
public class BillWhole {
    // 单据头信息
    private BillHeader billHeader;
    // 单据消息主体
    private List<BillCell> billCellList;
    // 单据尾信息
    private BillFooter billFooter;

    public BillWhole() {
    }

    /**
     * 构造方法<br/>
     * 用于:销售订单/采购订单/调拨单/盘点盈亏单/成本调价单/破损单/盘点任务（整仓）/盘点任务（部分）
     *
     * @param billHeader   单据头信息
     * @param billCellList 单据消息主体列表
     */
    public BillWhole(BillHeader billHeader, List<BillCell> billCellList) {
        this.billHeader = billHeader;
        this.billCellList = billCellList;
    }

    /**
     * 构造方法<br/>
     * 用于:销售单/退货单/采购单/采购退货单/收款单/付款单/费用支出
     *
     * @param billHeader   单据头信息
     * @param billCellList 单据消息主体列表
     * @param billFooter   单据尾信息
     */
    public BillWhole(BillHeader billHeader, List<BillCell> billCellList, BillFooter billFooter) {
        this.billHeader = billHeader;
        this.billCellList = billCellList;
        this.billFooter = billFooter;
    }

    public BillHeader getBillHeader() {
        return billHeader;
    }

    public void setBillHeader(BillHeader billHeader) {
        this.billHeader = billHeader;
    }

    public List<BillCell> getBillCellList() {
        return billCellList;
    }

    public void setBillCellList(List<BillCell> billCellList) {
        this.billCellList = billCellList;
    }

    public BillFooter getBillFooter() {
        return billFooter;
    }

    public void setBillFooter(BillFooter billFooter) {
        this.billFooter = billFooter;
    }

    @Override
    public String toString() {
        return "BillWhole{" +
                "billHeader=" + billHeader +
                ", billCellList=" + billCellList +
                ", billFooter=" + billFooter +
                '}';
    }
}
