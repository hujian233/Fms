package com.school.fms.dao;

import com.school.fms.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: hujian
 * @Date: 2020/3/24 12:36
 * @Description: 用户dao层
 */
@Repository
public interface UserDao {
    /**
     * 查询用户
     * @param id 主键
     * @param username 用户名
     * @param mailaddress 邮箱
     * @return User
     */
    public User selectUser(@Param("id") int id, @Param("username") String username,
                           @Param("mailaddress") String mailaddress);

    /**
     * 添加用户
     * @param user User
     */
    public void addUser(User user);

    /**
     * 修改密码
     * @param username 用户名
     * @param newPwd 新密码
     */
    public void changePassword(@Param("username") String username,@Param("newPwd") String newPwd);

}
