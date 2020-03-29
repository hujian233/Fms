package com.school.fms.entity;

import java.io.Serializable;

/**
 * @Author: hujian
 * @Date: 2020/3/26 11:09
 * @Description: 夹具实体类
 */
public class FixtureEntity implements Serializable {
    private String code;
    private String seqId;
    private String billNo;
    private String regDate;
    /**
     * 已使用次数
     */
    private int usedCount;
    /**
     * 存放库位
     */
    private String location;
    /**
     * 0:已入库（可用），1:待采购入库，2:待入库，3:待出库，4:待报修，5:待报废，6:已出库，7:已报修，8:已报废
     */
    private int status;
    /**
     * 图片路径
     */
    private String photoPath;

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

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
