package com.school.fms.vo;

import java.io.Serializable;

/**
 * @Author: hujian
 * @Date: 2020/3/30 15:59
 * @Description: code seqId
 */
public class CodeListVo implements Serializable {

    private String code;
    private String seqId;

    public CodeListVo() {
    }

    public CodeListVo(String code, String seqId) {
        this.code = code;
        this.seqId = seqId;
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
}
