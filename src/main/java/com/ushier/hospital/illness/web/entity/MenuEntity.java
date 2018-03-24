package com.ushier.hospital.illness.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class MenuEntity {
    private Integer id;
    private Integer pid;
    private String title;
    private String href;
    private String icon;
    private Integer status;
    private Integer weight;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private List<MenuEntity> childrenList;
    private String pTitle;

    @Override
    public String toString() {
        return "MenuEntity{" +
                "id=" + id +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", icon='" + icon + '\'' +
                ", status=" + status +
                ", weight=" + weight +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", childrenList=" + childrenList +
                ", pTitle='" + pTitle + '\'' +
                '}';
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public List<MenuEntity> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<MenuEntity> childrenList) {
        this.childrenList = childrenList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
}
