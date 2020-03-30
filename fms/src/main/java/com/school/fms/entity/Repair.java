package com.school.fms.entity;

import com.school.fms.vo.CodeListVo;

import java.util.List;

/**
 * @Author: hujian
 * @Date: 2020/3/29 19:27
 * @Description: 报修申请表
 */
public class Repair {
    private Integer orderId;
    private List<CodeListVo> codeListVo;
    private String codeList;
    private String failureType;
    private String failureDesc;
    private String applicant;
    private String applicantTime;
    private int status;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<CodeListVo> getCodeListVo() {
        return codeListVo;
    }

    public void setCodeListVo(List<CodeListVo> codeListVo) {
        this.codeListVo = codeListVo;
    }

    public String getCodeList() {
        return codeList;
    }

    public void setCodeList(String codeList) {
        this.codeList = codeList;
    }

    public String getFailureType() {
        return failureType;
    }

    public void setFailureType(String failureType) {
        this.failureType = failureType;
    }

    public String getFailureDesc() {
        return failureDesc;
    }

    public void setFailureDesc(String failureDesc) {
        this.failureDesc = failureDesc;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplicantTime() {
        return applicantTime;
    }

    public void setApplicantTime(String applicantTime) {
        this.applicantTime = applicantTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
