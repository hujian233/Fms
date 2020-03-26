package com.school.fms.entity;

/**
 * @Author: hujian
 * @Date: 2020/3/26 11:09
 * @Description: 夹具实体类
 */
public class FixtureEntity {
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
     * 0:已入库，1:已出库，2:已报修，3:已报废
     */
    private int status;
}
