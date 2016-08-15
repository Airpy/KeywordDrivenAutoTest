package com.keyword.automation.database.domain;

import java.sql.Date;

/**
 * T_DOC_GOODS表对应的实体类
 *
 * @author Amio_
 */
public class Goods {
    // 商品编号
    private int id;
    // 批发商编号
    private int cid;
    // 商品名称
    private String name;
    // 商品助记名
    private String shortName;
    // 商品类别
    private String typeId;
    // 商品类别链
    private String typeChain;
    // 商品品牌
    private int brandId;
    // 允许优惠
    private int discount;
    // 状态
    private int state;
    // 基本单位
    private String baseUnitId;
    // 基本单位名称
    private String baseUnitName;
    // 基本单位条码
    private String baseBarcode;
    // 大包装单位
    private String pkgUnitId;
    // 大包装单位名称
    private String pkgUnitName;
    // 大包装单位条码
    private String pkgBarcode;
    // 单位换算
    private double unitFactor;
    // 单位换算内容
    private String unitFactorName;
    // 基本单位批发价
    private double baseWholesale;
    // 基本单位零售价
    private double baseRetail;
    // 基本单位最低售价
    private double baseCheapest;
    // 基本单位进价
    private double basePurchase;
    // 基本单位特价1
    private double baseSpecials1;
    // 基本单位特价2
    private double baseSpecials2;
    // 基本单位特价3
    private double baseSpecials3;
    // 大包装单位批发价
    private double pkgWholesale;
    // 大包装单位零售价
    private double pkgRetail;
    // 大包装单位最低售价
    private double pkgCheapest;
    // 大包装单位进价
    private double pkgPurchase;
    // 大包装单位特价1
    private double pkgSpecials1;
    // 大包装单位特价2
    private double pkgSpecials2;
    // 大包装单位特价3
    private double pkgSpecials3;
    // 成本价
    private double costPrice;
    // 货号
    private double articleNumber;
    // 规格
    private double specifications;
    // 顺序号
    private int seq;
    // 原产地
    private String origPlace;
    // 主供应商
    private int supplierId;
    // 其他条码
    private String otherBarcode;
    // 其他条码1
    private String otherBarcode1;
    // 其他条码2
    private String otherBarcode2;
    // 统计类别
    private String statisticsId;
    // 失效预警天数
    private int warnDays;
    // 保质期
    private int shelfLife;
    // 照片
    private String picture;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;

    public Goods(String name, String baseBarcode, String pkgBarcode, int unitFactor, double baseWholesale, double
            pkgWholesale) {
        this.name = name;
        this.baseBarcode = baseBarcode;
        this.pkgBarcode = pkgBarcode;
        this.unitFactor = unitFactor;
        this.baseWholesale = baseWholesale;
        this.pkgWholesale = pkgWholesale;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeChain() {
        return typeChain;
    }

    public void setTypeChain(String typeChain) {
        this.typeChain = typeChain;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getBaseUnitId() {
        return baseUnitId;
    }

    public void setBaseUnitId(String baseUnitId) {
        this.baseUnitId = baseUnitId;
    }

    public String getBaseUnitName() {
        return baseUnitName;
    }

    public void setBaseUnitName(String baseUnitName) {
        this.baseUnitName = baseUnitName;
    }

    public String getBaseBarcode() {
        return baseBarcode;
    }

    public void setBaseBarcode(String baseBarcode) {
        this.baseBarcode = baseBarcode;
    }

    public String getPkgUnitId() {
        return pkgUnitId;
    }

    public void setPkgUnitId(String pkgUnitId) {
        this.pkgUnitId = pkgUnitId;
    }

    public String getPkgUnitName() {
        return pkgUnitName;
    }

    public void setPkgUnitName(String pkgUnitName) {
        this.pkgUnitName = pkgUnitName;
    }

    public String getPkgBarcode() {
        return pkgBarcode;
    }

    public void setPkgBarcode(String pkgBarcode) {
        this.pkgBarcode = pkgBarcode;
    }

    public double getUnitFactor() {
        return unitFactor;
    }

    public void setUnitFactor(double unitFactor) {
        this.unitFactor = unitFactor;
    }

    public String getUnitFactorName() {
        return unitFactorName;
    }

    public void setUnitFactorName(String unitFactorName) {
        this.unitFactorName = unitFactorName;
    }

    public double getBaseWholesale() {
        return baseWholesale;
    }

    public void setBaseWholesale(double baseWholesale) {
        this.baseWholesale = baseWholesale;
    }

    public double getBaseRetail() {
        return baseRetail;
    }

    public void setBaseRetail(double baseRetail) {
        this.baseRetail = baseRetail;
    }

    public double getBaseCheapest() {
        return baseCheapest;
    }

    public void setBaseCheapest(double baseCheapest) {
        this.baseCheapest = baseCheapest;
    }

    public double getBasePurchase() {
        return basePurchase;
    }

    public void setBasePurchase(double basePurchase) {
        this.basePurchase = basePurchase;
    }

    public double getBaseSpecials1() {
        return baseSpecials1;
    }

    public void setBaseSpecials1(double baseSpecials1) {
        this.baseSpecials1 = baseSpecials1;
    }

    public double getBaseSpecials2() {
        return baseSpecials2;
    }

    public void setBaseSpecials2(double baseSpecials2) {
        this.baseSpecials2 = baseSpecials2;
    }

    public double getBaseSpecials3() {
        return baseSpecials3;
    }

    public void setBaseSpecials3(double baseSpecials3) {
        this.baseSpecials3 = baseSpecials3;
    }

    public double getPkgWholesale() {
        return pkgWholesale;
    }

    public void setPkgWholesale(double pkgWholesale) {
        this.pkgWholesale = pkgWholesale;
    }

    public double getPkgRetail() {
        return pkgRetail;
    }

    public void setPkgRetail(double pkgRetail) {
        this.pkgRetail = pkgRetail;
    }

    public double getPkgCheapest() {
        return pkgCheapest;
    }

    public void setPkgCheapest(double pkgCheapest) {
        this.pkgCheapest = pkgCheapest;
    }

    public double getPkgPurchase() {
        return pkgPurchase;
    }

    public void setPkgPurchase(double pkgPurchase) {
        this.pkgPurchase = pkgPurchase;
    }

    public double getPkgSpecials1() {
        return pkgSpecials1;
    }

    public void setPkgSpecials1(double pkgSpecials1) {
        this.pkgSpecials1 = pkgSpecials1;
    }

    public double getPkgSpecials2() {
        return pkgSpecials2;
    }

    public void setPkgSpecials2(double pkgSpecials2) {
        this.pkgSpecials2 = pkgSpecials2;
    }

    public double getPkgSpecials3() {
        return pkgSpecials3;
    }

    public void setPkgSpecials3(double pkgSpecials3) {
        this.pkgSpecials3 = pkgSpecials3;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(double articleNumber) {
        this.articleNumber = articleNumber;
    }

    public double getSpecifications() {
        return specifications;
    }

    public void setSpecifications(double specifications) {
        this.specifications = specifications;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getOrigPlace() {
        return origPlace;
    }

    public void setOrigPlace(String origPlace) {
        this.origPlace = origPlace;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getOtherBarcode() {
        return otherBarcode;
    }

    public void setOtherBarcode(String otherBarcode) {
        this.otherBarcode = otherBarcode;
    }

    public String getOtherBarcode1() {
        return otherBarcode1;
    }

    public void setOtherBarcode1(String otherBarcode1) {
        this.otherBarcode1 = otherBarcode1;
    }

    public String getOtherBarcode2() {
        return otherBarcode2;
    }

    public void setOtherBarcode2(String otherBarcode2) {
        this.otherBarcode2 = otherBarcode2;
    }

    public String getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(String statisticsId) {
        this.statisticsId = statisticsId;
    }

    public int getWarnDays() {
        return warnDays;
    }

    public void setWarnDays(int warnDays) {
        this.warnDays = warnDays;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "GoodsKeyword{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", typeId='" + typeId + '\'' +
                ", typeChain='" + typeChain + '\'' +
                ", brandId=" + brandId +
                ", discount=" + discount +
                ", state=" + state +
                ", baseUnitId='" + baseUnitId + '\'' +
                ", baseUnitName='" + baseUnitName + '\'' +
                ", baseBarcode='" + baseBarcode + '\'' +
                ", pkgUnitId='" + pkgUnitId + '\'' +
                ", pkgUnitName='" + pkgUnitName + '\'' +
                ", pkgBarcode='" + pkgBarcode + '\'' +
                ", unitFactor=" + unitFactor +
                ", unitFactorName='" + unitFactorName + '\'' +
                ", baseWholesale=" + baseWholesale +
                ", baseRetail=" + baseRetail +
                ", baseCheapest=" + baseCheapest +
                ", basePurchase=" + basePurchase +
                ", baseSpecials1=" + baseSpecials1 +
                ", baseSpecials2=" + baseSpecials2 +
                ", baseSpecials3=" + baseSpecials3 +
                ", pkgWholesale=" + pkgWholesale +
                ", pkgRetail=" + pkgRetail +
                ", pkgCheapest=" + pkgCheapest +
                ", pkgPurchase=" + pkgPurchase +
                ", pkgSpecials1=" + pkgSpecials1 +
                ", pkgSpecials2=" + pkgSpecials2 +
                ", pkgSpecials3=" + pkgSpecials3 +
                ", costPrice=" + costPrice +
                ", articleNumber=" + articleNumber +
                ", specifications=" + specifications +
                ", seq=" + seq +
                ", origPlace='" + origPlace + '\'' +
                ", supplierId=" + supplierId +
                ", otherBarcode='" + otherBarcode + '\'' +
                ", otherBarcode1='" + otherBarcode1 + '\'' +
                ", otherBarcode2='" + otherBarcode2 + '\'' +
                ", statisticsId='" + statisticsId + '\'' +
                ", warnDays=" + warnDays +
                ", shelfLife=" + shelfLife +
                ", picture='" + picture + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
