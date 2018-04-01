package com.green.hospital.illness.web.entity;

public class DepartmentEntity {
    private Integer id;
    private Integer hosId;
    private String name;
    private String desc;
    private Integer normalNum;
    private Integer expertNum;
    private Integer normalMoney;
    private Integer expertMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHosId() {
        return hosId;
    }

    public void setHosId(Integer hosId) {
        this.hosId = hosId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getNormalNum() {
        return normalNum;
    }

    public void setNormalNum(Integer normalNum) {
        this.normalNum = normalNum;
    }

    public Integer getExpertNum() {
        return expertNum;
    }

    public void setExpertNum(Integer expertNum) {
        this.expertNum = expertNum;
    }

    public Integer getNormalMoney() {
        return normalMoney;
    }

    public void setNormalMoney(Integer normalMoney) {
        this.normalMoney = normalMoney;
    }

    public Integer getExpertMoney() {
        return expertMoney;
    }

    public void setExpertMoney(Integer expertMoney) {
        this.expertMoney = expertMoney;
    }
}
