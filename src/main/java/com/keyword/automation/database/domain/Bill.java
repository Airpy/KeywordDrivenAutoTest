package com.keyword.automation.database.domain;

import java.util.List;

/**
 * 测试数据--单据实体类
 *
 * @author Amio_
 */
public class Bill {
    // 客户
    private String consumer;
    // 仓库
    private String warehouse;
    // 业务员
    private String workOper;
    // 交易时间
    private String workTime;
    // 按最小单位销售
    private int isBaseUnit;
    // 订单备注
    private String billRemark;
    // 优惠金额
    private double discountAmount;
    // 优惠后金额
    private double afterDiscountAmount;
    // 现金
    private double cash;
    // 银行
    private double bank;
    // 其他收款账户
    private double others;
    // 预收款
    private double prepay;
    // 欠款金额
    private double leftAmount;
    // 选择商品列表
    private List<BillGoods> billGoodsList;

    public Bill() {

    }
    public Bill(String consumer, String warehouse, String workOper, String billRemark, List<BillGoods> billGoodsList,
                double discountAmount, double cash, double prepay) {
        this.consumer = consumer;
        this.warehouse = warehouse;
        this.workOper = workOper;
        this.billRemark = billRemark;
        this.billGoodsList = billGoodsList;
        this.discountAmount = discountAmount;
        this.cash = cash;
        this.prepay = prepay;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getWorkOper() {
        return workOper;
    }

    public void setWorkOper(String workOper) {
        this.workOper = workOper;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public int getIsBaseUnit() {
        return isBaseUnit;
    }

    public void setIsBaseUnit(int isBaseUnit) {
        this.isBaseUnit = isBaseUnit;
    }

    public String getBillRemark() {
        return billRemark;
    }

    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getAfterDiscountAmount() {
        return afterDiscountAmount;
    }

    public void setAfterDiscountAmount(double afterDiscountAmount) {
        this.afterDiscountAmount = afterDiscountAmount;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getBank() {
        return bank;
    }

    public void setBank(double bank) {
        this.bank = bank;
    }

    public double getOthers() {
        return others;
    }

    public void setOthers(double others) {
        this.others = others;
    }

    public double getPrepay() {
        return prepay;
    }

    public void setPrepay(double prepay) {
        this.prepay = prepay;
    }

    public double getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(double leftAmount) {
        this.leftAmount = leftAmount;
    }

    public List<BillGoods> getBillGoodsList() {
        return billGoodsList;
    }

    public void setBillGoodsList(List<BillGoods> billGoodsList) {
        this.billGoodsList = billGoodsList;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "consumer='" + consumer + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", workOper='" + workOper + '\'' +
                ", workTime='" + workTime + '\'' +
                ", isBaseUnit=" + isBaseUnit +
                ", billRemark='" + billRemark + '\'' +
                ", discountAmount=" + discountAmount +
                ", afterDiscountAmount=" + afterDiscountAmount +
                ", cash=" + cash +
                ", bank=" + bank +
                ", others=" + others +
                ", prepay=" + prepay +
                ", leftAmount=" + leftAmount +
                ", billGoodsList=" + billGoodsList +
                '}';
    }
}
