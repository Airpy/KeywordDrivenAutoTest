package com.keyword.automation.database.domain;

/**
 * T_DOC_GOODS_TYPE表对应的实体类
 *
 * @author Amio_
 */
public class GoodsType {
    // 商品类别编号
    private int id;
    // 批发商编号
    private int cid;
    // 商品类别名称
    private String name;
    // 父类别编号
    private int pid;
    // 顺序号
    private int seq;
    // 统计类别
    private String statisticsId;
    // 分类方式
    private int classification;
    // 品牌
    private String brandId;
    // 状态
    private int state;
    // 类别链
    private String typeChain;

    public GoodsType(String name, int seq, String brandId) {
        this.name = name;
        this.seq = seq;
        this.brandId = brandId;
    }

    public int getId() {
        return id;
    }

    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public int getPid() {
        return pid;
    }

    public int getSeq() {
        return seq;
    }

    public String getStatisticsId() {
        return statisticsId;
    }

    public int getClassification() {
        return classification;
    }

    public String getBrandId() {
        return brandId;
    }

    public int getState() {
        return state;
    }

    public String getTypeChain() {
        return typeChain;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public void setStatisticsId(String statisticsId) {
        this.statisticsId = statisticsId;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setState(int state) {
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
