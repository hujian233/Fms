package com.school.fms.controller;

import com.school.fms.entity.FixtureDefine;
import com.school.fms.entity.FixtureEntity;
import com.school.fms.service.FixtureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: hujian
 * @Date: 2020/3/26 16:49
 * @Description: 夹具控制器
 */
@Controller
@RequestMapping(value = "/fixture")
public class FixtureController {

    @Resource
    private FixtureService fixtureService;

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>(5);
        fixtureService.upLoadFile(request, file);
        map.put("code", 200);
        return map;
    }

    @RequestMapping(value = "/queryDefine", method = {RequestMethod.GET})
    @ResponseBody
    public List<FixtureDefine> getFixtureDefines(@RequestParam(value = "code", required = false) String code,
                                                 @RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "family", required = false) String family,
                                                 @RequestParam(value = "model", required = false) String model) {
        List<FixtureDefine> fixtureDefines = new ArrayList<>();
        try {
            fixtureDefines = fixtureService.queryDefines(code, name, family, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fixtureDefines;
    }

    @RequestMapping(value = "/queryEntity", method = {RequestMethod.GET})
    @ResponseBody
    public List<FixtureEntity> getFixtureEntities(@RequestParam(value = "code", required = false) String code,
                                                  @RequestParam(value = "seqId", required = false) String seqId) {
        List<FixtureEntity> fixtureEntities = new ArrayList<>();
        try {
            fixtureEntities = fixtureService.queryEntities(code, seqId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fixtureEntities;
    }
}
