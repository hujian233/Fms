package com.school.fms.controller;

import com.school.fms.common.Response;
import com.school.fms.entity.FixtureDefine;
import com.school.fms.entity.FixtureEntity;
import com.school.fms.entity.Inbound;
import com.school.fms.service.FixtureService;
import com.school.fms.service.OperationService;
import com.school.fms.utils.JsonUtils;
import com.school.fms.vo.CodeListVo;
import com.school.fms.vo.WaitSubmitVo;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Resource
    private OperationService operationService;

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
            if (null == code) {
                code = "";
            }
            if (null == seqId) {
                seqId = "";
            }
            fixtureEntities = fixtureService.queryEntities(code, seqId, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fixtureEntities;
    }

    /**
     * 查询待提交申请
     *
     * @param status 类型 1:待采购入库，2:待入库，3:待出库，4:待报修，5:待报废，不传值就是查询所有的状态
     * @return list
     */
    @RequestMapping(value = "/querywaitsubmit", method = {RequestMethod.GET})
    @ResponseBody
    public List<WaitSubmitVo> getWaitSubmit(@RequestParam(value = "type", required = false) Integer status) {
        List<WaitSubmitVo> waitSubmitVos = new ArrayList<>();
        try {
            waitSubmitVos = fixtureService.queryWaitSubmit(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return waitSubmitVos;
    }

    /**
     * 删除待提交申请，删除该申请就是将夹具实体状态改成0：已入库，可用
     *
     * @param code  夹具代码
     * @param seqId 夹具序列号
     * @return response
     */
    @RequestMapping(value = "/deletewaitsubmit", method = {RequestMethod.GET})
    @ResponseBody
    public String deleteWaitSubmit(@RequestParam(value = "code", required = false) String code,
                                   @RequestParam(value = "seqId", required = false) String seqId) {
        try {
            operationService.updateStatus(code, seqId, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.objectToJson(Response.error("操作失败"));
        }
        return JsonUtils.objectToJson(Response.ok("删除成功"));
    }

    /**
     * 增加一个夹具实体申请，入库后状态为 2:待入库， 同时增加一条入库申请到inbound表
     *
     * @param fixtureEntity 实体信息
     * @return response
     */
    @PostMapping(value = "/addFixtureEntity/{jobNumber}", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addFixtureEntity(@RequestBody FixtureEntity fixtureEntity, @PathVariable String jobNumber) {
        String code = fixtureEntity.getCode();
        FixtureDefine fixtureDefine = fixtureService.queryDefineDetail(code);
        //判断夹具定义是否存在
        if (null == fixtureDefine){
            return JsonUtils.objectToJson(Response.error("夹具定义不存在"));
        }
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = sdf.format(time);
        //添加入库时间
        fixtureEntity.setRegDate(current);
        //添加一个夹具实体,夹具代码和序列id具有唯一性，添加了唯一索引，前段需要控制一下序列id不能是已经存在的
        fixtureService.addFixtureEntity(fixtureEntity);
        Inbound inbound = new Inbound();
        CodeListVo vo  = new CodeListVo(fixtureEntity.getCode(),fixtureEntity.getSeqId());
        List<CodeListVo> listVos = new ArrayList<>();
        listVos.add(vo);
        inbound.setCodeListVo(listVos);
        //添加申请时间
        inbound.setApplicantTime(current);
        inbound.setApplicant(jobNumber);
        operationService.addToInbound(inbound);
        return JsonUtils.objectToJson(Response.ok("操作成功"));
    }
}
