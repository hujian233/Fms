package com.school.fms.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @Author: hujian
 * @Date: 2020/3/24 12:24
 * @Description: 用户实体类
 */
public class User implements Serializable {
    /**
     *  主键
     */
    private int id;
    /**
     *  工号
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private long jobNumber;

    private String userName;
    private String password;
    private String mailAddress;
    /**
     *  权限：初级用户、高级用户、监管员、经理、系统管理员
     */
    private int authority;
    /**
     *  工作部门
     */
    private String department;
    /**
     *  上次登录时间
     */
    private String loginTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(long jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", jobNumber=" + jobNumber +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                ", authority='" + authority + '\'' +
                ", department='" + department + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }
}
