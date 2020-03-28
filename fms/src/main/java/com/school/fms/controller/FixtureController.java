package com.school.fms.controller;

import com.school.fms.common.Response;
import com.school.fms.entity.FixtureDefine;
import com.school.fms.entity.FixtureEntity;
import com.school.fms.service.FixtureService;
import com.school.fms.utils.JsonUtils;
import com.school.fms.vo.WaitSubmitVo;
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

    /**
     * 上传夹具定义表
     *
     * @return map
     */
    @PostMapping("/upload/define")
    @ResponseBody
    public String upload1(@RequestBody List<FixtureDefine> fixtureDefines) {
        Map<String, Object> map = new HashMap<>(5);
        fixtureService.addFixtureDefines(fixtureDefines);
        return JsonUtils.objectToJson(Response.ok("上传成功"));
    }

    @PostMapping("/upload/entity")
    @ResponseBody
    public String upload2(@RequestBody List<FixtureEntity> fixtureEntities) {
        Map<String, Object> map = new HashMap<>(5);
        fixtureService.addFixtureEntities(fixtureEntities);
        return JsonUtils.objectToJson(Response.ok("上传成功"));
    }

    /**
     * 查看夹具定义列表
     *
     * @param code   代码
     * @param name   名称
     * @param family 大类
     * @param model  模组
     * @return list<FixtureDefine>
     */
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

    /**
     * 查看夹具定义详情,获取一条记录信息
     *
     * @param code 夹具代码，在定义表中具有唯一性
     * @return define
     */
    @RequestMapping(value = "/queryDefineDetail", method = {RequestMethod.GET})
    @ResponseBody
    public FixtureDefine getFixtureDefineDetail(@RequestParam(value = "code") String code) {
        FixtureDefine fixtureDefine = new FixtureDefine();
        try {
            fixtureDefine = fixtureService.queryDefineDetail(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fixtureDefine;
    }

    /**
     * 查看夹具实体列表或者详情,查看列表不带参数，查看详情带两个参数，查看夹具定义下的实体只带一个参数code
     *
     * @param code  夹具代码
     * @param seqId 夹具序列号
     * @return list
     */
    @RequestMapping(value = "/queryEntity", method = {RequestMethod.GET})
    @ResponseBody
    public List<FixtureEntity> getFixtureEntities(@RequestParam(value = "code", required = false) String code,
                                                  @RequestParam(value = "seqId", required = false) String seqId) {
        List<FixtureEntity> fixtureEntities = new ArrayList<>();
        try {
            fixtureEntities = fixtureService.queryEntities(code, seqId, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fixtureEntities;
    }

    /**
     * 查询待提交申请
     *
     * @param status 类型 1:待采购入库，2:待入库，3:待出库，4:待报修，5:待报废
     * @return list
     */
    @RequestMapping(value = "/querywaitsubmit", method = {RequestMethod.GET})
    @ResponseBody
    public List<WaitSubmitVo> getWaitSubmit(@RequestParam(value = "type", required = false) int status) {
        List<WaitSubmitVo> waitSubmitVos = new ArrayList<>();
        try {
            waitSubmitVos = fixtureService.queryWaitSubmit(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return waitSubmitVos;
    }
}
