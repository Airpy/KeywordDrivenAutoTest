package com.keyword.automation.database.domain;

/**
 * T_BILL_SALE_DETAIL表对应的实体类
 *
 * @author Amio_
 */
public class BillSaleDetail {
    // 明细编号
    private int id;
    // 批发商编号
    private int cid;
    // 销售单ID
    private int billId;
    // 销售单编号
    private String billNo;
    // 进出标志
    private int inoutFlag;
    // 单据中的行索引
    private int seq;
    // 仓库
    private int warehouseId;
    // 商品编号
    private int goodsId;
    // 商品名称
    private String goodsName;
    // 条形码
    private String barcode;
    // 批次编号
    private String batchNo;
    // 商品单位
    private String currUnitName;
    // 单位换算
    private double currUnitFactor;
    // 单位换算内容
    private String currUnitFactorName;
    // 商品数量
    private double quantity;
    // 原价
    private double origPrice;
    // 实价
    private double realPrice;
    // 折扣
    private int discount;
    // 成本价
    private double costPrice;
    // 金额
    private double subAmount;
    // 备注
    private String remark;

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

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public int getInoutFlag() {
        return inoutFlag;
    }

    public void setInoutFlag(int inoutFlag) {
        this.inoutFlag = inoutFlag;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCurrUnitName() {
        return currUnitName;
    }

    public void setCurrUnitName(String currUnitName) {
        this.currUnitName = currUnitName;
    }

    public double getCurrUnitFactor() {
        return currUnitFactor;
    }

    public void setCurrUnitFactor(double currUnitFactor) {
        this.currUnitFactor = currUnitFactor;
    }

    public String getCurrUnitFactorName() {
        return currUnitFactorName;
    }

    public void setCurrUnitFactorName(String currUnitFactorName) {
        this.currUnitFactorName = currUnitFactorName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getOrigPrice() {
        return origPrice;
    }

    public void setOrigPrice(double origPrice) {
        this.origPrice = origPrice;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(double subAmount) {
        this.subAmount = subAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BillSaleDetail{" +
                "id=" + id +
                ", cid=" + cid +
                ", billId=" + billId +
                ", billNo='" + billNo + '\'' +
                ", inoutFlag=" + inoutFlag +
                ", seq=" + seq +
                ", warehouseId=" + warehouseId +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", barcode='" + barcode + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", currUnitName='" + currUnitName + '\'' +
                ", currUnitFactor=" + currUnitFactor +
                ", currUnitFactorName='" + currUnitFactorName + '\'' +
                ", quantity=" + quantity +
                ", origPrice=" + origPrice +
                ", realPrice=" + realPrice +
                ", discount=" + discount +
                ", costPrice=" + costPrice +
                ", subAmount=" + subAmount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
