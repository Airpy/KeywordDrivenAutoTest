package com.keyword.automation.database.domain;

/**
 * T_DOC_BRAND表对应的实体类
 *
 * @author Amio_
 */
public class Brand {
    // 品牌编号
    private int id;
    // 批发商编号
    private int cid;
    // 品牌名称
    private String name;
    // 状态
    private int state;
    // 顺序号
    private int seq;

    public Brand(String name, int seq) {
        this.name = name;
        this.seq = seq;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", seq=" + seq +
                '}';
    }
}
