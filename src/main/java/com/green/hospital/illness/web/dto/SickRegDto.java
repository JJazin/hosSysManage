package com.green.hospital.illness.web.dto;

/**
 * Created by Administrator on 2018/3/22.
 */
public class SickRegDto {
    private Integer id;
    private Integer roleId;
    private String roleName;
    private String name;
    private String password;
    private String realName;
    private String idcard;
    private String phone;
    private String email;
    private Integer status;

    public SickRegDto(Integer id, Integer roleId, String roleName, String name, String password, String realName, String idcard, String phone, String email, Integer status) {
        this.id = id;
        this.roleId = roleId;
        this.roleName = roleName;
        this.name = name;
        this.password = password;
        this.realName = realName;
        this.idcard = idcard;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SickRegDto{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", idcard='" + idcard + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
