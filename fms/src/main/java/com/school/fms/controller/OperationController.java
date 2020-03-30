package com.school.fms.controller;

import com.school.fms.common.Response;
import com.school.fms.entity.Inbound;
import com.school.fms.entity.Outbound;
import com.school.fms.entity.Repair;
import com.school.fms.entity.Scrap;
import com.school.fms.service.OperationService;
import com.school.fms.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: hujian
 * @Date: 2020/3/28 11:20
 * @Description: 用户操作入口：采购入库，入库，出库，报修，报废
 */
@Controller
@RequestMapping(value = "/do")
public class OperationController {

    @Resource
    private OperationService operationService;

    /**
     * 用户操作 type 1:采购入库操作，2:入库操作，3:出库操作，4:报修操作，5:报废操作，修改夹具的状态
     *
     * @param code  夹具代码
     * @param seqId 夹具序列号
     * @return list
     */
    @RequestMapping(value = "/operation/{type}/update", method = {RequestMethod.GET})
    @ResponseBody
    public String operation(@RequestParam(value = "code", required = false) String code,
                            @RequestParam(value = "seqId", required = false) String seqId, @PathVariable int type) {
        try {
            //修改夹具状态
            operationService.updateStatus(code, seqId, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.objectToJson(Response.ok("操作成功"));
    }

    /**
     * 采购入库
     *
     * @param code  夹具代码
     * @param seqId 夹具序列号
     * @return list
     * TODO 这个先放着最后做，看在入库的基础上怎么修改
     */
    @RequestMapping(value = "/purchase  ", method = {RequestMethod.POST})
    @ResponseBody
    public String purchase(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "seqId", required = false) String seqId) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 入库
     *
     * @return response
     */
    @RequestMapping(value = "/inbound", method = {RequestMethod.POST})
    @ResponseBody
    public String inbound(@RequestBody Inbound inbound) {
        try {
            operationService.addToInbound(inbound);
            //TODO 原来的夹具实体的状态需要更改
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.objectToJson(Response.ok("入库申请成功"));
    }

    /**
     * 出库
     *
     * @return response
     */
    @RequestMapping(value = "/outbound", method = {RequestMethod.POST})
    @ResponseBody
    public String outbound(@RequestBody Outbound outbound) {
        try {
            operationService.addToOutbound(outbound);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.objectToJson(Response.ok("出库申请成功"));
    }

    /**
     * 报修
     *
     * @return list
     */
    @RequestMapping(value = "/repair", method = {RequestMethod.POST})
    @ResponseBody
    public String repair(@RequestBody Repair repair) {
        try {
            operationService.addToRepair(repair);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.objectToJson(Response.ok("报修申请成功"));
    }

    /**
     * 报废
     *
     * @return list
     */
    @RequestMapping(value = "/scrapped", method = {RequestMethod.POST})
    @ResponseBody
    public String scrapped(@RequestBody Scrap scrap) {
        try {
            operationService.addToScrap(scrap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.objectToJson(Response.ok("报废申请成功"));
    }
}
