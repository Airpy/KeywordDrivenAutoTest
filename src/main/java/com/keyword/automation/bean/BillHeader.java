package com.keyword.automation.bean;

/**
 * 测试数据--单据头信息传入数据实体
 *
 * @author Amio_
 */
public class BillHeader {
    // 客户名称
    private String consumerName;
    // 供应商名称
    private String supplierName;
    // 仓库名称
    private String warehouseName;
    // 业务员名称/经办人名称/盘点人员名称
    private String workOperName;
    // 交易日期/调拨日期/开单日期/入款日期
    private String workTime;
    // 是否按最小单位销售/采购/盘点/报损
    private boolean isBaseUnit;
    // 单据备注
    private String billRemark;
    // 出货仓库名称
    private String outWarehouseName;
    // 入货仓库名称
    private String inWarehouseName;

    public BillHeader() {

    }

    /**
     * 构造方法<br/>
     * 用于:销售订单/采购订单/收款单
     *
     * @param consumerName 客户名称
     * @param supplierName 供应商名称
     * @param workOperName 业务员名称/经办人名称/盘点人员名称
     * @param workTime     交易日期/调拨日期/开单日期/入款日期
     * @param isBaseUnit   是否按最小单位采购
     * @param billRemark   单据备注
     */
    public BillHeader(String consumerName, String supplierName, String workOperName, String workTime, boolean
            isBaseUnit, String billRemark) {
        this.consumerName = consumerName;
        this.supplierName = supplierName;
        this.workOperName = workOperName;
        this.workTime = workTime;
        this.isBaseUnit = isBaseUnit;
        this.billRemark = billRemark;
    }

    /**
     * 构造方法<br/>
     * 用于:销售单/退货单/采购单/采购退货单
     *
     * @param consumerName  客户名称
     * @param supplierName  供应商名称
     * @param warehouseName 仓库名称
     * @param workOperName  业务员名称/经办人名称/盘点人员名称
     * @param workTime      交易日期/调拨日期/开单日期/入款日期
     * @param isBaseUnit    是否按最小单位销售
     * @param billRemark    单据备注
     */
    public BillHeader(String consumerName, String supplierName, String warehouseName, String workOperName, String
            workTime, boolean isBaseUnit, String billRemark) {
        this.consumerName = consumerName;
        this.supplierName = supplierName;
        this.warehouseName = warehouseName;
        this.workOperName = workOperName;
        this.workTime = workTime;
        this.isBaseUnit = isBaseUnit;
        this.billRemark = billRemark;
    }

    /**
     * 构造方法<br/>
     * 用于:调拨单
     *
     * @param outWarehouseName 出货仓库
     * @param inWarehouseName  入货仓库
     * @param workTime         交易日期/调拨日期/开单日期/入款日期
     * @param billRemark       单据备注
     */
    public BillHeader(String outWarehouseName, String inWarehouseName, String workTime, String billRemark) {
        this.outWarehouseName = outWarehouseName;
        this.inWarehouseName = inWarehouseName;
        this.workTime = workTime;
        this.billRemark = billRemark;
    }

    /**
     * 构造方法<br/>
     * 用于:盘点盈亏单/报损单
     *
     * @param warehouseName 仓库名称
     * @param workOperName  业务员名称/经办人名称/盘点人员名称
     * @param isBaseUnit    是否按最小单位销售
     * @param billRemark    单据备注
     */
    public BillHeader(String warehouseName, String workOperName, boolean isBaseUnit, String billRemark) {
        this.warehouseName = warehouseName;
        this.workOperName = workOperName;
        this.isBaseUnit = isBaseUnit;
        this.billRemark = billRemark;
    }

    /**
     * 构造方法<br/>
     * 用于:盘点任务（整仓）/盘点任务（部分）
     *
     * @param warehouseName 仓库名称
     * @param workOperName  业务员名称/经办人名称/盘点人员名称
     */
    public BillHeader(String warehouseName, String workOperName) {
        this.warehouseName = warehouseName;
        this.workOperName = workOperName;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWorkOperName() {
        return workOperName;
    }

    public void setWorkOperName(String workOperName) {
        this.workOperName = workOperName;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public boolean getIsBaseUnit() {
        return isBaseUnit;
    }

    public void setIsBaseUnit(boolean baseUnit) {
        isBaseUnit = baseUnit;
    }

    public String getBillRemark() {
        return billRemark;
    }

    public void setBillRemark(String billRemark) {
        this.billRemark = billRemark;
    }

    public String getOutWarehouseName() {
        return outWarehouseName;
    }

    public void setOutWarehouseName(String outWarehouseName) {
        this.outWarehouseName = outWarehouseName;
    }

    public String getInWarehouseName() {
        return inWarehouseName;
    }

    public void setInWarehouseName(String inWarehouseName) {
        this.inWarehouseName = inWarehouseName;
    }

    @Override
    public String toString() {
        return "BillHeader{" +
                "consumerName='" + consumerName + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", workOperName='" + workOperName + '\'' +
                ", workTime='" + workTime + '\'' +
                ", isBaseUnit=" + isBaseUnit +
                ", billRemark='" + billRemark + '\'' +
                ", outWarehouseName='" + outWarehouseName + '\'' +
                ", inWarehouseName='" + inWarehouseName + '\'' +
                '}';
    }
}
