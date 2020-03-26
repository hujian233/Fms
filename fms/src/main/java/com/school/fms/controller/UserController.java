package com.school.fms.controller;

import com.school.fms.common.Response;
import com.school.fms.entity.User;
import com.school.fms.service.UserService;
import com.school.fms.utils.CookieUtil;
import com.school.fms.utils.JsonUtils;
import com.school.fms.vo.UserLoginVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: hujian
 * @Date: 2020/3/24 14:34
 * @Description: 用户控制器
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 检查该员工是否已注册
     *
     * @param jobNumber 前端请求数据
     * @return String
     */
    @GetMapping(value = "/checkUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkUser(@RequestParam long jobNumber) {
        String response;
        if (userService.checkUser(jobNumber)) {
            // 员工已注册,返回响应码-1
            response = JsonUtils.objectToJson(Response.error("exist"));
        } else {
            // 可以注册，返回响应码0
            response = JsonUtils.objectToJson(Response.ok("ok"));
        }
        return response;
    }

    /**
     * 注册,要设置produces,否则回调函数会出现中文乱码
     *
     * @param userVo  user
     * @param session session
     * @return String
     */
    @PostMapping(value = "/doRegister", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doRegister(@RequestBody User userVo, HttpSession session) {
        String pwd = userVo.getPassword().trim();
        //Sha1加密入库
        pwd = DigestUtils.sha1Hex(pwd);
        User user = new User();
        user.setUserName(userVo.getUserName());
        user.setPassword(pwd);
        user.setJobNumber(userVo.getJobNumber());
        user.setAuthority(userVo.getAuthority());
        user.setDepartment(userVo.getDepartment());
        user.setMailAddress(userVo.getMailAddress());
        try {
            userService.addUser(user);
            return JsonUtils.objectToJson(Response.ok("success"));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.objectToJson(Response.error("注册失败，后台异常"));
        }
    }

    /**
     * 登录
     *
     * @param userLoginVo 用户名，密码
     * @param session     session
     * @return json
     */
    @PostMapping(value = "/doLogin", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doLogin(@RequestBody UserLoginVo userLoginVo, HttpSession session,
                          HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse) {

        long jobNumber = userLoginVo.getJobNumber();
        String password = userLoginVo.getPassword();
        //给一个默认值false
        boolean rememberme = userLoginVo.getRememberme() != null ? userLoginVo.getRememberme() : false;

        String shapwd = DigestUtils.sha1Hex(password);
        User user1 = userService.selectUser(jobNumber, null, null);
        if (user1 != null) {
            if (shapwd.equals(user1.getPassword())) {
                session.setAttribute("loginUser", user1);
                if (rememberme) {
                    CookieUtil.setCookie(httpServletResponse, "jobNumber", String.valueOf(jobNumber), CookieUtil.COOKIE_MAX_AGE);
                    CookieUtil.setCookie(httpServletResponse, "password", password, CookieUtil.COOKIE_MAX_AGE);
                } else {
                    CookieUtil.removeCookie(httpServletRequest, httpServletResponse, "username");
                    CookieUtil.removeCookie(httpServletRequest, httpServletResponse, "password");
                }
                //登录成功
                return JsonUtils.objectToJson(new Response(user1));
            } else {
                return JsonUtils.objectToJson(Response.error("登录失败，用户名或密码错误"));
            }
        } else {
            return JsonUtils.objectToJson(Response.error("登录失败，用户不存在"));
        }
    }

    @GetMapping(value = "/deleteUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doLogin(@RequestParam long jobNumber) {
        String response;
        try {
            userService.deleteUser(jobNumber);
            response = JsonUtils.objectToJson(Response.ok("删除成功"));
        } catch (Exception e) {
            e.printStackTrace();
            response = JsonUtils.objectToJson(Response.error("删除失败"));
        }
        return response;
    }
}
