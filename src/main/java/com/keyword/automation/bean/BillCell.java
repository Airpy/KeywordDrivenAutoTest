package com.keyword.automation.bean;

/**
 * 测试数据--单据表格信息传入数据实体
 *
 * @author Amio_
 */
public class BillCell {
    // 商品名称
    private String goodsName;
    // 条形码
    private String barcode;
    // 单位
    private String currUnitName;
    // 单位换算
    private String currUnitFactorName;
    // 商品数量(因为开单时输入为数字,检查数据时,页面加上了单位,故改为String类型)
    private String quantity;
    // 商品价格(实价)
    private double realPrice;
    // 商品金额(实价*数量)
    private double subAmount;
    // 库存数量(因为开单时输入为数字,检查数据时,页面加上了单位,故改为String类型)
    private String stockQuantity;
    // 备注
    private String remark;
    // 单价
    private double currWholesale;
    // 出库库存数量(因为开单时输入为数字,检查数据时,页面加上了单位,故改为String类型)
    private String outStockQuantity;
    // 入库库存数量(因为开单时输入为数字,检查数据时,页面加上了单位,故改为String类型)
    private String inStockQuantity;
    // 成本价
    private double costPrice;
    // 成本金额
    private double costAmount;
    // 调整前价格
    private double origCostPrice;
    // 调整后价格
    private double newCostPrice;
    // 当前库存数量
    private double origQuantity;
    // 大包单位数量
    private double pkgQuantity;
    // 基本单位数量
    private double baseQuantity;
    // 盘盈数量
    private double profitQuantity;
    // 盘亏数量
    private double lossQuantity;
    // 本次优惠金额
    private double nowDiscountAmount;
    // 本次收款金额/本次付款金额
    private double nowPaidAmount;
    // 费用类别
    private String costTypeName;
    // 费用支出金额
    private double expenditureAmount;
    // 费用支出客户
    private String consumerName;
    // 操作
    private String opt;

    public BillCell() {
    }

    /**
     * 构造方法<br/>
     * 用于:销售单/销售订单/退货单/采购订单/采购单/采购退货单
     *
     * @param goodsName    商品名称
     * @param currUnitName 单位
     * @param quantity     商品数量
     * @param realPrice    商品价格
     * @param subAmount    商品金额(实价*数量)
     * @param remark       库存数量
     */
    public BillCell(String goodsName, String currUnitName, String quantity, double realPrice, double subAmount, String
            remark) {
        this.goodsName = goodsName;
        this.currUnitName = currUnitName;
        this.quantity = quantity;
        this.realPrice = realPrice;
        this.subAmount = subAmount;
        this.remark = remark;
    }

    /**
     * 构造方法<br/>
     * 用于:调拨单/盘点盈亏单/报损单
     *
     * @param goodsName    商品名称
     * @param currUnitName 单位
     * @param quantity     商品数量
     */
    public BillCell(String goodsName, String currUnitName, String quantity) {
        this.goodsName = goodsName;
        this.currUnitName = currUnitName;
        this.quantity = quantity;
    }

    /**
     * 构造方法<br/>
     * 用于:成本条件单
     *
     * @param goodsName    商品名称
     * @param newCostPrice 调整后价格
     */
    public BillCell(String goodsName, double newCostPrice) {
        this.goodsName = goodsName;
        this.newCostPrice = newCostPrice;
    }

    /**
     * 构造方法<br/>
     * 用于:盘点任务（整仓）
     *
     * @param pkgQuantity  大包单位数量
     * @param baseQuantity 基本单位数量
     */
    public BillCell(int pkgQuantity, double baseQuantity) {
        this.pkgQuantity = pkgQuantity;
        this.baseQuantity = baseQuantity;
    }

    /**
     * 构造方法<br/>
     * 用于:收款单
     *
     * @param nowDiscountAmount 本次优惠金额
     * @param nowPaidAmount     本次收款金额/付款金额
     */
    public BillCell(double nowDiscountAmount, double nowPaidAmount) {
        this.nowDiscountAmount = nowDiscountAmount;
        this.nowPaidAmount = nowPaidAmount;
    }

    /**
     * 构造方法<br/>
     * 用于:盘点任务（部分）
     *
     * @param goodsName    商品名称
     * @param pkgQuantity  大包单位数量
     * @param baseQuantity 基本单位数量
     */
    public BillCell(String goodsName, double pkgQuantity, double baseQuantity) {
        this.goodsName = goodsName;
        this.pkgQuantity = pkgQuantity;
        this.baseQuantity = baseQuantity;
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

    public String getCurrUnitFactorName() {
        return currUnitFactorName;
    }

    public void setCurrUnitFactorName(String currUnitFactorName) {
        this.currUnitFactorName = currUnitFactorName;
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

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getCurrWholesale() {
        return currWholesale;
    }

    public void setCurrWholesale(double currWholesale) {
        this.currWholesale = currWholesale;
    }

    public String getOutStockQuantity() {
        return outStockQuantity;
    }

    public void setOutStockQuantity(String outStockQuantity) {
        this.outStockQuantity = outStockQuantity;
    }

    public String getInStockQuantity() {
        return inStockQuantity;
    }

    public void setInStockQuantity(String inStockQuantity) {
        this.inStockQuantity = inStockQuantity;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(double costAmount) {
        this.costAmount = costAmount;
    }

    public double getOrigCostPrice() {
        return origCostPrice;
    }

    public void setOrigCostPrice(double origCostPrice) {
        this.origCostPrice = origCostPrice;
    }

    public double getNewCostPrice() {
        return newCostPrice;
    }

    public void setNewCostPrice(double newCostPrice) {
        this.newCostPrice = newCostPrice;
    }

    public double getOrigQuantity() {
        return origQuantity;
    }

    public void setOrigQuantity(double origQuantity) {
        this.origQuantity = origQuantity;
    }

    public double getPkgQuantity() {
        return pkgQuantity;
    }

    public void setPkgQuantity(double pkgQuantity) {
        this.pkgQuantity = pkgQuantity;
    }

    public double getBaseQuantity() {
        return baseQuantity;
    }

    public void setBaseQuantity(double baseQuantity) {
        this.baseQuantity = baseQuantity;
    }

    public double getProfitQuantity() {
        return profitQuantity;
    }

    public void setProfitQuantity(double profitQuantity) {
        this.profitQuantity = profitQuantity;
    }

    public double getLossQuantity() {
        return lossQuantity;
    }

    public void setLossQuantity(double lossQuantity) {
        this.lossQuantity = lossQuantity;
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

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName;
    }

    public double getExpenditureAmount() {
        return expenditureAmount;
    }

    public void setExpenditureAmount(double expenditureAmount) {
        this.expenditureAmount = expenditureAmount;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    @Override
    public String toString() {
        return "BillCell{" +
                "goodsName='" + goodsName + '\'' +
                ", barcode='" + barcode + '\'' +
                ", currUnitName='" + currUnitName + '\'' +
                ", currUnitFactorName='" + currUnitFactorName + '\'' +
                ", quantity=" + quantity +
                ", realPrice=" + realPrice +
                ", subAmount=" + subAmount +
                ", stockQuantity=" + stockQuantity +
                ", goodsRemark='" + remark + '\'' +
                ", currWholesale=" + currWholesale +
                ", outStockQuantity=" + outStockQuantity +
                ", inStockQuantity=" + inStockQuantity +
                ", costPrice=" + costPrice +
                ", costAmount=" + costAmount +
                ", origCostPrice=" + origCostPrice +
                ", newCostPrice=" + newCostPrice +
                ", origQuantity=" + origQuantity +
                ", pkgQuantity=" + pkgQuantity +
                ", baseQuantity=" + baseQuantity +
                ", profitQuantity=" + profitQuantity +
                ", lossQuantity=" + lossQuantity +
                ", nowDiscountAmount=" + nowDiscountAmount +
                ", nowPaidAmount=" + nowPaidAmount +
                ", costTypeName=" + costTypeName +
                ", expenditureAmount=" + expenditureAmount +
                ", consumerName=" + consumerName +
                ", opt='" + opt + '\'' +
                '}';
    }
}
