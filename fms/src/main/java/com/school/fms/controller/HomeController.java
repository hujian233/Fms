package com.school.fms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: hujian
 * @Date: 2020/3/25 17:20
 * @Description: 主页面入口
 */
@Controller
public class HomeController {
    @RequestMapping("/fms")
    public String index(){
        return "Login";
    }
}
