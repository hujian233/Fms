package com.school.fms.entity;

/**
 * @Author: hujian
 * @Date: 2020/3/29 19:24
 * @Description: 出库申请表
 */
public class Outbound {
    private String orderId;
    private String codeList;
    private String employer;
    /**
     * 产线
     */
    private String proLine;
    private boolean ifCheck;
    private String note;
    private String applicant;
    private String applicantTime;
    private int status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCodeList() {
        return codeList;
    }

    public void setCodeList(String codeList) {
        this.codeList = codeList;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getProLine() {
        return proLine;
    }

    public void setProLine(String proLine) {
        this.proLine = proLine;
    }

    public boolean isIfCheck() {
        return ifCheck;
    }

    public void setIfCheck(boolean ifCheck) {
        this.ifCheck = ifCheck;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
