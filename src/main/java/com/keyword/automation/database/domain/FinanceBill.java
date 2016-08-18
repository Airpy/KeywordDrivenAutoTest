package com.keyword.automation.database.domain;

/**
 * 测试数据--财务单据实体类
 *
 * @author Amio_
 */
public class FinanceBill {
    private String saleBillNo;
    private String saleBillType;
    private String workTime;
    private double origTotalAmount;
    private double origDiscountAmount;
    private double origPaidAmount;
    private double origLeftAmount;
    private double nowDiscountAmount;
    private double nowPaidAmount;
    private double leftAmount;

    public FinanceBill() {

    }

    public FinanceBill(double nowDiscountAmount, double nowPaidAmount) {
        this.nowDiscountAmount = nowDiscountAmount;
        this.nowPaidAmount = nowPaidAmount;
    }

    public FinanceBill(String saleBillNo, String saleBillType, String workTime, double origTotalAmount, double
            origDiscountAmount, double origPaidAmount, double origLeftAmount, double nowDiscountAmount, double
                               nowPaidAmount, double leftAmount) {
        this.saleBillNo = saleBillNo;
        this.saleBillType = saleBillType;
        this.workTime = workTime;
        this.origTotalAmount = origTotalAmount;
        this.origDiscountAmount = origDiscountAmount;
        this.origPaidAmount = origPaidAmount;
        this.origLeftAmount = origLeftAmount;
        this.nowDiscountAmount = nowDiscountAmount;
        this.nowPaidAmount = nowPaidAmount;
        this.leftAmount = leftAmount;
    }

    public String getSaleBillNo() {
        return saleBillNo;
    }

    public void setSaleBillNo(String saleBillNo) {
        this.saleBillNo = saleBillNo;
    }

    public String getSaleBillType() {
        return saleBillType;
    }

    public void setSaleBillType(String saleBillType) {
        this.saleBillType = saleBillType;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public double getOrigTotalAmount() {
        return origTotalAmount;
    }

    public void setOrigTotalAmount(double origTotalAmount) {
        this.origTotalAmount = origTotalAmount;
    }

    public double getOrigDiscountAmount() {
        return origDiscountAmount;
    }

    public void setOrigDiscountAmount(double origDiscountAmount) {
        this.origDiscountAmount = origDiscountAmount;
    }

    public double getOrigPaidAmount() {
        return origPaidAmount;
    }

    public void setOrigPaidAmount(double origPaidAmount) {
        this.origPaidAmount = origPaidAmount;
    }

    public double getOrigLeftAmount() {
        return origLeftAmount;
    }

    public void setOrigLeftAmount(double origLeftAmount) {
        this.origLeftAmount = origLeftAmount;
    }

    public double getNowDiscountAmount() {
        return nowDiscountAmount;
    }

    public void setNowDiscountAmount(double nowDiscountAmount) {
        this.nowDiscountAmount = nowDiscountAmount;
    }

    public double getNowPaidAmount() {
        return nowPaidAmount;
    }

    public void setNowPaidAmount(double nowPaidAmount) {
        this.nowPaidAmount = nowPaidAmount;
    }

    public double getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(double leftAmount) {
        this.leftAmount = leftAmount;
    }

    @Override
    public String toString() {
        return "FinanceBill{" +
                "saleBillNo='" + saleBillNo + '\'' +
                ", saleBillType='" + saleBillType + '\'' +
                ", workTime='" + workTime + '\'' +
                ", origTotalAmount=" + origTotalAmount +
                ", origDiscountAmount=" + origDiscountAmount +
                ", origPaidAmount=" + origPaidAmount +
                ", origLeftAmount=" + origLeftAmount +
                ", nowDiscountAmount=" + nowDiscountAmount +
                ", nowPaidAmount=" + nowPaidAmount +
                ", leftAmount=" + leftAmount +
                '}';
    }
}
