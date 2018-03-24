package com.ushier.hospital.illness.web.dto;

import com.ushier.hospital.illness.web.entity.CaseEntity;

/**
 * Created by Administrator on 2018/3/24.
 */
public class CasePrintDto extends CaseEntity{
    private String dName;//医生名
    private String hName;//医院名
    private String sName;//病人名
    private String sex;//性别

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
