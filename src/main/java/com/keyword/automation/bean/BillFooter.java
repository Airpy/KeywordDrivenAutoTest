package com.keyword.automation.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试数据--单据尾信息传入数据实体
 *
 * @author Amio_
 */
public class BillFooter {
    // 优惠金额
    private double discountAmount;
    // 优惠后金额
    private double afterDiscountAmount;
    // 现金
    private double cash;
    // 银行
    private double bank;
    // 预收款
    private double prePay;
    // 预付款
    private double preCharge;
    // 其他
    private double others;
    // 欠款金额/剩余金额
    private double leftAmount;
    // 本次优惠金额
    private double nowDiscountAmount;
    // 付款方式
    private String payType;
    // 支出金额
    private double payAmount;
    // 备注
    private String remark;

    private Map<String, Double> accountMap = new HashMap<String, Double>();

    public BillFooter() {
    }

    /**
     * 构造方法<br/>
     * 用于:销售单/退货单/采购单/采购退货单/
     *
     * @param discountAmount      优惠金额
     * @param afterDiscountAmount 优惠后金额
     * @param cash                现金
     * @param bank                银行
     * @param prePay              预收款
     * @param others              其他
     * @param leftAmount          欠款金额/剩余金额
     */
    public BillFooter(double discountAmount, double afterDiscountAmount, double cash, double bank, double prePay,
                      double preCharge, double others, double leftAmount) {
        this.discountAmount = discountAmount;
        this.afterDiscountAmount = afterDiscountAmount;
        this.cash = cash;
        this.bank = bank;
        this.prePay = prePay;
        this.preCharge = preCharge;
        this.others = others;
        this.leftAmount = leftAmount;
    }

    /**
     * 构造方法<br/>
     * 用于:销售单/退货单/采购单/采购退货单/
     *
     * @param discountAmount      优惠金额
     * @param afterDiscountAmount 优惠后金额
     * @param accountMap          账户map
     * @param leftAmount          欠款金额/剩余金额
     */
    public BillFooter(double discountAmount, double afterDiscountAmount, Map<String, Double> accountMap, double
            leftAmount) {
        this.discountAmount = discountAmount;
        this.afterDiscountAmount = afterDiscountAmount;
        this.accountMap = accountMap;
        this.leftAmount = leftAmount;
    }

    /**
     * 构造方法<br/>
     * 用于:收款单/付款单
     *
     * @param nowDiscountAmount 本次优惠金额
     * @param cash              现金
     * @param bank              银行
     * @param others            其他
     * @param leftAmount        欠款金额/剩余金额
     */
    public BillFooter(double nowDiscountAmount, double cash, double bank, double others, double leftAmount) {
        this.cash = cash;
        this.bank = bank;
        this.others = others;
        this.leftAmount = leftAmount;
        this.nowDiscountAmount = nowDiscountAmount;
    }

    /**
     * 构造方法<br/>
     * 用于:费用支出
     *
     * @param payType   付款方式
     * @param payAmount 支出金额
     * @param remark    备注
     */
    public BillFooter(String payType, double payAmount, String remark) {
        this.payType = payType;
        this.payAmount = payAmount;
        this.remark = remark;
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

    public double getPrePay() {
        return prePay;
    }

    public void setPrePay(double prePay) {
        this.prePay = prePay;
    }

    public double getPreCharge() {
        return preCharge;
    }

    public void setPreCharge(double preCharge) {
        this.preCharge = preCharge;
    }

    public double getOthers() {
        return others;
    }

    public void setOthers(double others) {
        this.others = others;
    }

    public double getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(double leftAmount) {
        this.leftAmount = leftAmount;
    }

    public double getNowDiscountAmount() {
        return nowDiscountAmount;
    }

    public void setNowDiscountAmount(double nowDiscountAmount) {
        this.nowDiscountAmount = nowDiscountAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, Double> getAccountMap() {
        return accountMap;
    }

    public void setAccountMap(Map<String, Double> accountMap) {
        this.accountMap = accountMap;
    }

    @Override
    public String toString() {
        return "BillFooter{" +
                "discountAmount=" + discountAmount +
                ", afterDiscountAmount=" + afterDiscountAmount +
                ", cash=" + cash +
                ", bank=" + bank +
                ", prePay=" + prePay +
                ", preCharge=" + preCharge +
                ", others=" + others +
                ", leftAmount=" + leftAmount +
                ", nowDiscountAmount=" + nowDiscountAmount +
                ", payType='" + payType + '\'' +
                ", payAmount=" + payAmount +
                ", remark='" + remark + '\'' +
                ", accountMap=" + accountMap +
                '}';
    }

    public static void main(String[] args) {
        BillFooter billFooter = new BillFooter(1.1, 2.2, 3.3, 4.4, 5.5);
        Double aaa = billFooter.getAfterDiscountAmount();
        System.out.println(billFooter.getBank());
        System.out.println(aaa);
        System.out.println(billFooter.toString());
    }
}
