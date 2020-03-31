package com.school.fms.service.impl;

import com.school.fms.dao.FixtureDao;
import com.school.fms.dao.OperationDao;
import com.school.fms.entity.*;
import com.school.fms.service.OperationService;
import com.school.fms.utils.JsonUtils;
import com.school.fms.vo.CheckListVo;
import com.school.fms.vo.CodeListVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: hujian
 * @Date: 2020/3/28 14:05
 * @Description: impl
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OperationServiceImpl implements OperationService {

    @Resource
    private FixtureDao fixtureDao;

    @Resource
    private OperationDao operationDao;

    @Override
    public void updateStatus(String code, String seqId, int status) {
        fixtureDao.updateStatus(code, seqId, status);
    }

    @Override
    public void addToInbound(Inbound inbound) {
        inbound.setCodeList(JsonUtils.objectToJson(inbound.getCodeListVo()));
        operationDao.addToInbound(inbound);
    }

    @Override
    public void addToOutbound(Outbound outbound) {
        outbound.setCodeList(JsonUtils.objectToJson(outbound.getCodeListVo()));
        operationDao.addToOutbound(outbound);
    }

    @Override
    public void addToRepair(Repair repair) {
        repair.setCodeList(JsonUtils.objectToJson(repair.getCodeListVo()));
        operationDao.addToRepair(repair);
    }

    @Override
    public void addToScrap(Scrap scrap) {
        scrap.setCodeList(JsonUtils.objectToJson(scrap.getCodeListVo()));
        operationDao.addToScrap(scrap);
    }

    @Override
    public void approvalInbound(List<Integer> orderIds, int result) {
        for (int id : orderIds) {
            operationDao.updateInbound(id, result);
            Inbound inbound = operationDao.queryInbound(id);
            String codeList = inbound.getCodeList();
            List<CodeListVo> codeListVos = JsonUtils.jsonToList(codeList, CodeListVo.class);
            if (result == 1) {
                //同意入库申请
                if (codeListVos != null) {
                    for (CodeListVo vo : codeListVos) {
                        fixtureDao.updateStatus(vo.getCode(), vo.getSeqId(), 0);
                    }
                }
            } else if (result == 2) {
                //驳回入库申请
                if (codeListVos != null) {
                    for (CodeListVo vo : codeListVos) {
                        fixtureDao.updateStatus(vo.getCode(), vo.getSeqId(), 9);
                    }
                }
            }
        }
    }

    @Override
    public void approvalOutbound(List<Integer> orderIds, int result) {
        for (int id : orderIds) {
            operationDao.updateOutbound(id, result);
            Outbound outbound = operationDao.queryOutbound(id);
            String codeList = outbound.getCodeList();
            List<CodeListVo> codeListVos = JsonUtils.jsonToList(codeList, CodeListVo.class);
            if (result == 1) {
                //同意出库申请
                if (codeListVos != null) {
                    for (CodeListVo vo : codeListVos) {
                        fixtureDao.updateStatus(vo.getCode(), vo.getSeqId(), 6);
                    }
                }
            } else if (result == 2) {
                //驳回出库申请
                if (codeListVos != null) {
                    for (CodeListVo vo : codeListVos) {
                        fixtureDao.updateStatus(vo.getCode(), vo.getSeqId(), 0);
                    }
                }
            }
        }
    }

    @Override
    public void approvalRepair(List<Integer> orderIds, int result) {
        for (int id : orderIds) {
            operationDao.updateRepair(id, result);
            Repair repair = operationDao.queryRepair(id);
            String codeList = repair.getCodeList();
            List<CodeListVo> codeListVos = JsonUtils.jsonToList(codeList, CodeListVo.class);
            if (result == 1) {
                //同意报修申请
                if (codeListVos != null) {
                    for (CodeListVo vo : codeListVos) {
                        fixtureDao.updateStatus(vo.getCode(), vo.getSeqId(), 7);
                    }
                }
            } else if (result == 2) {
                //驳回报修申请
                if (codeListVos != null) {
                    for (CodeListVo vo : codeListVos) {
                        fixtureDao.updateStatus(vo.getCode(), vo.getSeqId(), 0);
                    }
                }
            }
        }
    }

    @Override
    public void approvalScrap(List<Integer> orderIds, int result) {
        for (int id : orderIds) {
            operationDao.updateScrap(id, result);
            Scrap scrap = operationDao.queryScrap(id);
            String codeList = scrap.getCodeList();
            List<CodeListVo> codeListVos = JsonUtils.jsonToList(codeList, CodeListVo.class);
            if (result == 1) {
                //同意报废申请
                if (codeListVos != null) {
                    for (CodeListVo vo : codeListVos) {
                        fixtureDao.updateStatus(vo.getCode(), vo.getSeqId(), 8);
                    }
                }
            } else if (result == 2) {
                //驳回报废申请
                if (codeListVos != null) {
                    for (CodeListVo vo : codeListVos) {
                        fixtureDao.updateStatus(vo.getCode(), vo.getSeqId(), 0);
                    }
                }
            }
        }
    }

    @Override
    public Inbound queryInbound(int orderId) {
        return operationDao.queryInbound(orderId);
    }

    @Override
    public Outbound queryOutbound(int orderId) {
        return operationDao.queryOutbound(orderId);
    }

    @Override
    public Repair queryRepair(int orderId) {
        return operationDao.queryRepair(orderId);
    }

    @Override
    public Scrap queryScrap(int orderId) {
        return operationDao.queryScrap(orderId);
    }

    @Override
    public List<CheckListVo> queryInboundList() {
        return operationDao.queryInboundList();
    }

    @Override
    public List<CheckListVo> queryOutboundList() {
        return operationDao.queryOutboundList();
    }

    @Override
    public List<CheckListVo> queryRepairList() {
        return operationDao.queryRepairList();
    }

    @Override
    public List<CheckListVo> queryScrapList() {
        return operationDao.queryScrapList();
    }
}
