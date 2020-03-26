package com.school.fms.controller;

import com.school.fms.service.FixtureDefineService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @Author: hujian
 * @Date: 2020/3/26 16:49
 * @Description: <描述>
 */
@Controller
public class FixtureDefineController {

    @Resource
    private FixtureDefineService fixtureDefineService;

}
