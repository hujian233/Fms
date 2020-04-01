package com.school.fms.vo;

/**
 * @Author: hujian
 * @Date: 2020/3/31 12:15
 * @Description: 审批列表VO
 */
public class CheckListVo {
    /**
     * 订单编号
     */
    private int orderId;
    /**
     * 申请人
     */
    private String applicant;
    /**
     * 申请时间
     */
    private String applicantTime;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
}
