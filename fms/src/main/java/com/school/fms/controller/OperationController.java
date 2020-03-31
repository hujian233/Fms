package com.school.fms.controller;

import com.school.fms.common.Response;
import com.school.fms.entity.Inbound;
import com.school.fms.entity.Outbound;
import com.school.fms.entity.Repair;
import com.school.fms.entity.Scrap;
import com.school.fms.service.OperationService;
import com.school.fms.utils.JsonUtils;
import com.school.fms.vo.ApprovalVo;
import com.school.fms.vo.CheckListVo;
import com.school.fms.vo.CodeListVo;
import com.school.fms.vo.WaitSubmitVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            Date time = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String current = sdf.format(time);
            List<CodeListVo> codeListVos = inbound.getCodeListVo();
            inbound.setApplicantTime(current);
            for (CodeListVo vo : codeListVos) {
                operationService.updateStatus(vo.getCode(), vo.getSeqId(), 11);
            }
            operationService.addToInbound(inbound);
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
            Date time = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String current = sdf.format(time);
            outbound.setApplicantTime(current);
            List<CodeListVo> codeListVos = outbound.getCodeListVo();
            for (CodeListVo vo : codeListVos) {
                operationService.updateStatus(vo.getCode(), vo.getSeqId(), 12);
            }
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
            Date time = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String current = sdf.format(time);
            repair.setApplicantTime(current);
            List<CodeListVo> codeListVos = repair.getCodeListVo();
            for (CodeListVo vo : codeListVos) {
                operationService.updateStatus(vo.getCode(), vo.getSeqId(), 13);
            }
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
            Date time = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String current = sdf.format(time);
            scrap.setApplicantTime(current);
            List<CodeListVo> codeListVos = scrap.getCodeListVo();
            for (CodeListVo vo : codeListVos) {
                operationService.updateStatus(vo.getCode(), vo.getSeqId(), 14);
            }
            operationService.addToScrap(scrap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.objectToJson(Response.ok("报废申请成功"));
    }

    /**
     * 查询待审批申请
     *
     * @param status 类型 1:待采购入库，2:待入库，3:待出库，4:待报修，5:待报废
     * @return list
     */
    @RequestMapping(value = "/querysubmit", method = {RequestMethod.GET})
    @ResponseBody
    public String getSubmit(@RequestParam(value = "type", required = false) Integer status) {
        List<CheckListVo> checkListVos = new ArrayList<>();
        switch (status) {
            case 2:
                checkListVos = operationService.queryInboundList();
                break;
            case 3:
                checkListVos = operationService.queryOutboundList();
                break;
            case 4:
                checkListVos = operationService.queryRepairList();
                break;
            case 5:
                checkListVos = operationService.queryScrapList();
                break;
            default:
                return JsonUtils.objectToJson(Response.error("操作失败，无此类型"));
        }
        return JsonUtils.objectToJson(new Response(checkListVos));
    }

    /**
     * 对用户操作进行审批
     *
     * @param approvalVo 审批请求
     * @return response
     */
    @PostMapping(value = "/approval")
    @ResponseBody
    public String approval(@RequestBody ApprovalVo approvalVo) {
        int type = approvalVo.getType();
        List<Integer> orderIds = approvalVo.getOrderIds();
        int result = approvalVo.getResult();
        switch (type) {
            case 2:
                operationService.approvalInbound(orderIds, result);
                break;
            case 3:
                operationService.approvalOutbound(orderIds, result);
                break;
            case 4:
                operationService.approvalRepair(orderIds, result);
                break;
            case 5:
                operationService.approvalScrap(orderIds, result);
                break;
            default:
                return JsonUtils.objectToJson(Response.error("操作失败，无此类型"));
        }
        return JsonUtils.objectToJson(Response.ok("操作成功"));
    }

    @GetMapping(value = "/seeDetail")
    @ResponseBody
    public String seeDetail(@RequestParam int type, @RequestParam int orderId) {
        Object response;
        switch (type) {
            case 2:
                response = operationService.queryInbound(orderId);
                break;
            case 3:
                response = operationService.queryOutbound(orderId);
                break;
            case 4:
                response = operationService.queryRepair(orderId);
                break;
            case 5:
                response = operationService.queryScrap(orderId);
                break;
            default:
                return JsonUtils.objectToJson(Response.error("操作失败，无此类型"));
        }
        return JsonUtils.objectToJson(new Response(response));
    }
}
