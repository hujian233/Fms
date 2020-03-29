package com.school.fms.service.impl;

import com.school.fms.dao.FixtureDao;
import com.school.fms.dao.OperationDao;
import com.school.fms.entity.*;
import com.school.fms.service.OperationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
        operationDao.addToInbound(inbound);
    }

    @Override
    public void addToOutbound(Outbound outbound) {
        operationDao.addToOutbound(outbound);
    }

    @Override
    public void addToRepair(Repair repair) {
        operationDao.addToRepair(repair);
    }

    @Override
    public void addToScrap(Scrap scrap) {
        operationDao.addToScrap(scrap);
    }
}
