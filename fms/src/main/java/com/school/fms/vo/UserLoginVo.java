package com.school.fms.vo;

import lombok.Data;

/**
 * @Author: hujian
 * @Date: 2020/3/24 22:43
 * @Description: 用户登录携带的参数
 */
public class UserLoginVo {
    private long jobNumber;
    private String password;
    private Boolean rememberme;

    public long getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(long jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberme() {
        return rememberme;
    }

    public void setRememberme(Boolean rememberme) {
        this.rememberme = rememberme;
    }
}
