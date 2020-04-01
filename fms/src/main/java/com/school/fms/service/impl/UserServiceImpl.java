package com.school.fms.service.impl;

import com.school.fms.dao.UserDao;
import com.school.fms.entity.User;
import com.school.fms.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: hujian
 * @Date: 2020/3/24 14:35
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public boolean checkUser(long jobNumber) {
        List<User> user = userDao.selectUser(jobNumber, null, null, null);
        return null != user;
    }

    @Override
    public List<User> selectUser(Long jobNumber, String username, Integer authority, String department) {
        return userDao.selectUser(jobNumber, username, authority, department);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(long jobNumber) {
        userDao.deleteUser(jobNumber);
    }

    @Override
    public void changePassword(String username, String newPwd) {

    }

    @Override
    public void updateTime(long jobNumber) {
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = sdf.format(time);
        userDao.updateTime(jobNumber ,current);
    }

    @Override
    public void updateAuthority(long jobNumber, int authority) {
        userDao.updateAuthority(jobNumber ,authority);
    }
}
