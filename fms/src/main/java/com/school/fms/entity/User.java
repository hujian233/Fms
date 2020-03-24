package com.school.fms.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: hujian
 * @Date: 2020/3/24 12:24
 * @Description: 用户实体类
 */
@Data
public class User implements Serializable {
    /**
     *  主键
     */
    private int id;
    /**
     *  工号
     */
    private long jobNumber;

    private String userName;
    private String password;
    private String mailAddress;
    /**
     *  权限：初级用户、高级用户、监管员、部门经理、系统管理员
     */
    private String authority;
    /**
     *  工作部门
     */
    private String department;
    /**
     *  上次登录时间
     */
    private String loginTime;

}
