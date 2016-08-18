package com.keyword.automation.database.domain;

import java.util.Date;

/**
 * T_BILL_SALE表对应的实体类
 *
 * @author Amio_
 */
public class BillSale {
    // 销售单ID
    private int id;
    // 批发商编号
    private int cid;
    // 销售单编号
    private String billNo;
    // 单据类型
    private int type;
    // 销售单订单ID
    private int orderBillId;
    // 销售单订单编号
    private String orderBillNo;
    // 仓库编号
    private int warehouseId;
    // 是否红冲
    private int redFlag;
    // 客户编号
    private int consumerId;
    // 合计金额
    private double totalAmount;
    // 优惠金额
    private double discountAmount;
    // 优惠后金额
    private double afterDiscountAmount;
    // 已收金额
    private double paidAmount;
    // 剩余金额
    private double leftAmount;
    // 本次优惠金额
    private double nowDiscountAmount;
    // 本次收款金额
    private double nowPaidAmount;
    // 本次欠款金额
    private double nowLeftAmount;
    // 现金账户
    private int cashAccountId;
    // 现金金额
    private double cashAmount;
    // 银行账户
    private int bankAccountId;
    // 银行金额
    private double bankAmount;
    // 其他支付账户
    private int otherAccountId;
    // 其他支付金额
    private double otherAmount;
    // 预收款账户
    private int prepayAccountId;
    // 预收款金额
    private double prepayAmount;
    // 预收款剩余金额
    private double prepayLeftAmount;
    // 制单人
    private double operId;
    // 制单时间
    private Date operTime;
    // 业务员
    private int workOperId;
    // 交易时间
    private Date workTime;
    // 是否被审核
    private int approveFlag;
    // 审核人员
    private int approveOperId;
    // 审核时间
    private Date approveTime;
    // 备注
    private String remark;
    // 从手机端提交时间
    private Date submitTime;
    // 打印时间
    private Date printTime;
    // 打印次数
    private int printCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrderBillId() {
        return orderBillId;
    }

    public void setOrderBillId(int orderBillId) {
        this.orderBillId = orderBillId;
    }

    public String getOrderBillNo() {
        return orderBillNo;
    }

    public void setOrderBillNo(String orderBillNo) {
        this.orderBillNo = orderBillNo;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getRedFlag() {
        return redFlag;
    }

    public void setRedFlag(int redFlag) {
        this.redFlag = redFlag;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
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

    public double getNowPaidAmount() {
        return nowPaidAmount;
    }

    public void setNowPaidAmount(double nowPaidAmount) {
        this.nowPaidAmount = nowPaidAmount;
    }

    public double getNowLeftAmount() {
        return nowLeftAmount;
    }

    public void setNowLeftAmount(double nowLeftAmount) {
        this.nowLeftAmount = nowLeftAmount;
    }

    public int getCashAccountId() {
        return cashAccountId;
    }

    public void setCashAccountId(int cashAccountId) {
        this.cashAccountId = cashAccountId;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public double getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(double bankAmount) {
        this.bankAmount = bankAmount;
    }

    public int getOtherAccountId() {
        return otherAccountId;
    }

    public void setOtherAccountId(int otherAccountId) {
        this.otherAccountId = otherAccountId;
    }

    public double getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(double otherAmount) {
        this.otherAmount = otherAmount;
    }

    public int getPrepayAccountId() {
        return prepayAccountId;
    }

    public void setPrepayAccountId(int prepayAccountId) {
        this.prepayAccountId = prepayAccountId;
    }

    public double getPrepayAmount() {
        return prepayAmount;
    }

    public void setPrepayAmount(double prepayAmount) {
        this.prepayAmount = prepayAmount;
    }

    public double getPrepayLeftAmount() {
        return prepayLeftAmount;
    }

    public void setPrepayLeftAmount(double prepayLeftAmount) {
        this.prepayLeftAmount = prepayLeftAmount;
    }

    public double getOperId() {
        return operId;
    }

    public void setOperId(double operId) {
        this.operId = operId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public int getWorkOperId() {
        return workOperId;
    }

    public void setWorkOperId(int workOperId) {
        this.workOperId = workOperId;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public int getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(int approveFlag) {
        this.approveFlag = approveFlag;
    }

    public int getApproveOperId() {
        return approveOperId;
    }

    public void setApproveOperId(int approveOperId) {
        this.approveOperId = approveOperId;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    public int getPrintCount() {
        return printCount;
    }

    public void setPrintCount(int printCount) {
        this.printCount = printCount;
    }

    @Override
    public String toString() {
        return "BillSale{" +
                "id=" + id +
                ", cid=" + cid +
                ", billNo='" + billNo + '\'' +
                ", type=" + type +
                ", orderBillId=" + orderBillId +
                ", orderBillNo='" + orderBillNo + '\'' +
                ", warehouseId=" + warehouseId +
                ", redFlag=" + redFlag +
                ", consumerId=" + consumerId +
                ", totalAmount=" + totalAmount +
                ", discountAmount=" + discountAmount +
                ", afterDiscountAmount=" + afterDiscountAmount +
                ", paidAmount=" + paidAmount +
                ", leftAmount=" + leftAmount +
                ", nowDiscountAmount=" + nowDiscountAmount +
                ", nowPaidAmount=" + nowPaidAmount +
                ", nowLeftAmount=" + nowLeftAmount +
                ", cashAccountId=" + cashAccountId +
                ", cashAmount=" + cashAmount +
                ", bankAccountId=" + bankAccountId +
                ", bankAmount=" + bankAmount +
                ", otherAccountId=" + otherAccountId +
                ", otherAmount=" + otherAmount +
                ", prepayAccountId=" + prepayAccountId +
                ", prepayAmount=" + prepayAmount +
                ", prepayLeftAmount=" + prepayLeftAmount +
                ", operId=" + operId +
                ", operTime=" + operTime +
                ", workOperId=" + workOperId +
                ", workTime=" + workTime +
                ", approveFlag=" + approveFlag +
                ", approveOperId=" + approveOperId +
                ", approveTime=" + approveTime +
                ", remark='" + remark + '\'' +
                ", submitTime=" + submitTime +
                ", printTime=" + printTime +
                ", printCount=" + printCount +
                '}';
    }
}
