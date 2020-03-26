package com.school.fms.common;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: hujian
 * @Date: 2020/3/26 8:45
 * @Description: 用户权限枚举，好像暂时没有用到这个类
 */
public enum Authority {
    /**
     * 收件箱
     */
    NONUSER("初级用户", 1),
    HIGUSER("高级用户", 2),
    SUPERVISOR("监管员", 3),
    MANAGER("经理", 4),
    SYSMANAGER("系统管理员", 5);

    private String key;
    private int value;

    Authority(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getKey() {
        return key;
    }
}
