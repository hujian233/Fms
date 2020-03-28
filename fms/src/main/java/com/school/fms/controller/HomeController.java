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

    @RequestMapping("/common1")
    public String gotoPage1() {
        return "Common1";
    }

    @RequestMapping("/common2")
    public String gotoPage2() {
        return "Common2";
    }

    @RequestMapping("/common3")
    public String gotoPage3() {
        return "Common3";
    }

    @RequestMapping("/common4")
    public String gotoPage4() {
        return "Common4";
    }

    @RequestMapping("/common5")
    public String gotoPage5() {
        return "Common5";
    }

    @RequestMapping("/displayToolEntity")
    public String gotoPageToolEntity() {
        return "ToolEntityDisplay";
    }
}
