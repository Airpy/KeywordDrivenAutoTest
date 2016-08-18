package com.keyword.automation.database.domain;

/**
 * 测试数据--商品单据实体类
 *
 * @author Amio_
 */
public class BillGoods {
    // 商品名称
    private String goodsName;
    // 条形码
    private String barcode;
    // 单位
    private String currUnitName;
    // 单位换算
    private String unitFactorName;
    // 商品数量
    private String quantity;
    // 实价
    private double realPrice;
    // 金额
    private double subAmount;
    // 库存数量
    private double stockQuantity;
    // 商品备注
    private String goodsRemark;

    public BillGoods() {

    }

    public BillGoods(String goodsName, String currUnitName, String quantity, double realPrice, String goodsRemark) {
        this.goodsName = goodsName;
        this.currUnitName = currUnitName;
        this.quantity = quantity;
        this.realPrice = realPrice;
        this.goodsRemark = goodsRemark;
    }

    public BillGoods(String goodsName, String barcode, String currUnitName, String unitFactorName, String quantity,
                     double realPrice, double subAmount, String goodsRemark) {
        this.goodsName = goodsName;
        this.barcode = barcode;
        this.currUnitName = currUnitName;
        this.unitFactorName = unitFactorName;
        this.quantity = quantity;
        this.realPrice = realPrice;
        this.subAmount = subAmount;
//        this.stockQuantity = stockQuantity;
        this.goodsRemark = goodsRemark;
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

    public String getCurrUnitName() {
        return currUnitName;
    }

    public void setCurrUnitName(String currUnitName) {
        this.currUnitName = currUnitName;
    }

    public String getUnitFactorName() {
        return unitFactorName;
    }

    public void setUnitFactorName(String unitFactorName) {
        this.unitFactorName = unitFactorName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public double getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(double subAmount) {
        this.subAmount = subAmount;
    }

    public double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark;
    }

    @Override
    public String toString() {
        return "BillGoods{" +
                "goodsName='" + goodsName + '\'' +
                ", barcode='" + barcode + '\'' +
                ", currUnitName='" + currUnitName + '\'' +
                ", unitFactorName='" + unitFactorName + '\'' +
                ", quantity=" + quantity +
                ", realPrice=" + realPrice +
                ", subAmount=" + subAmount +
                ", stockQuantity=" + stockQuantity +
                ", goodsRemark='" + goodsRemark + '\'' +
                '}';
    }
}
