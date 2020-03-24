package com.school.fms.service;

import com.school.fms.entity.User;

/**
 * @Author: hujian
 * @Date: 2020/3/24 14:26
 * @Description: 用户业务接口
 */
public interface UserService {

    /**
     * 检验用户是否注册
     * @param jobNumber 工号
     * @return boolean
     */
    public boolean checkUser(long jobNumber);

    /**
     * 查询用户
     * @param jobNumber 工号
     * @param username 用户名
     * @param mailAddress 邮箱地址
     * @return User
     */
    public User selectUser(Long jobNumber, String username, String mailAddress);

    /**
     * 添加用户
     * @param user User
     */
    public void addUser(User user);

    /**
     * 删除用户
     * @param jobNumber 工号
     */
    public void deleteUser(long jobNumber);
    /**
     * 修改密码
     * @param username 用户名
     * @param newPwd 新密码
     */
    public void changePassword(String username,String newPwd);
}
