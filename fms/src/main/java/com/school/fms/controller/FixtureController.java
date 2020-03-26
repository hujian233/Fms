package com.school.fms.controller;

import com.school.fms.service.FixtureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hujian
 * @Date: 2020/3/26 16:49
 * @Description: 夹具控制器
 */
@Controller
public class FixtureController {

    @Resource
    private FixtureService fixtureService;


    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>(5);
        fixtureService.upLoadFile(request,file);
        map.put("code",200);
        return map;
    }

}
