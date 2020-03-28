package com.school.fms.vo;

/**
 * @Author: hujian
 * @Date: 2020/3/28 15:01
 * @Description: 待提交请求的VO
 */
public class WaitSubmitVo {

    private String code;
    private String seqId;
    private String name;

    public WaitSubmitVo() {
    }

    public WaitSubmitVo(String code, String seqId, String name) {
        this.code = code;
        this.seqId = seqId;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
