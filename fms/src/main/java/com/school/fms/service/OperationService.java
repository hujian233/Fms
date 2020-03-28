package com.school.fms.service;

/**
 * @Author: hujian
 * @Date: 2020/3/28 14:02
 * @Description: 夹具操作服务
 */
public interface OperationService {

    void updateStatus(String code, String seqId,int status);
}
