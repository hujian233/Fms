package com.school.fms.vo;

import lombok.Data;

/**
 * @Author: hujian
 * @Date: 2020/3/24 22:43
 * @Description: 用户登录携带的参数
 */
@Data
public class UserLoginVo {
    private long jobNumber;
    private String password;
    private Boolean rememberme;
}
