package com.keyword.automation.database.domain;

/**
 * T_DOC_GOODS_TYPE表对应的实体类
 *
 * @author Amio_
 */
public class GoodsType {
    // 商品类别编号
    private String id;
    // 批发商编号
    private String cid;
    // 商品类别名称
    private String name;
    // 父类别编号
    private String pid;
    // 顺序号
    private String seq;
    // 统计类别
    private String statisticsId;
    // 分类方式
    private String classification;
    // 品牌
    private String brandId;
    // 状态
    private String state;
    // 类别链
    private String typeChain;

    public String getId() {
        return id;
    }

    public String getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public String getPid() {
        return pid;
    }

    public String getSeq() {
        return seq;
    }

    public String getStatisticsId() {
        return statisticsId;
    }

    public String getClassification() {
        return classification;
    }

    public String getBrandId() {
        return brandId;
    }

    public String getState() {
        return state;
    }

    public String getTypeChain() {
        return typeChain;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public void setStatisticsId(String statisticsId) {
        this.statisticsId = statisticsId;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTypeChain(String typeChain) {
        this.typeChain = typeChain;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", seq=" + seq +
                ", statisticsId='" + statisticsId + '\'' +
                ", classification=" + classification +
                ", brandId=" + brandId +
                ", state=" + state +
                ", typeChain='" + typeChain + '\'' +
                '}';
    }
}
