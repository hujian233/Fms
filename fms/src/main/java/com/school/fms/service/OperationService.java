package com.school.fms.service;

import com.school.fms.entity.Inbound;
import com.school.fms.entity.Outbound;
import com.school.fms.entity.Repair;
import com.school.fms.entity.Scrap;

import java.util.List;

/**
 * @Author: hujian
 * @Date: 2020/3/28 14:02
 * @Description: 夹具操作服务
 */
public interface OperationService {

    void updateStatus(String code, String seqId,int status);


    void addToInbound(Inbound inbound);

    void addToOutbound(Outbound outbound);

    void addToRepair(Repair repair);

    void addToScrap(Scrap scrap);


    void approvalInbound(List<Integer> orderIds, int result);

    void approvalOutbound(List<Integer> orderIds, int result);

    void approvalRepair(List<Integer> orderIds, int result);

    void approvalScrap(List<Integer> orderIds, int result);


    Inbound queryInbound(int orderId);

    Outbound queryOutbound(int orderId);

    Repair queryRepair(int orderId);

    Scrap queryScrap(int orderId);
}
