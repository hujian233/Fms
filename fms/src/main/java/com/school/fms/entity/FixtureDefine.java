package com.school.fms.entity;

import java.io.Serializable;

/**
 * @Author: hujian
 * @Date: 2020/3/26 10:55
 * @Description: 夹具定义信息表
 */
public class FixtureDefine implements Serializable {
    private int id;
    /**
     * 夹具所属工作部
     */
    private String workCell;
    /**
     * 所属大类
     */
    private String family;
    /**
     * 夹具代码
     */
    private String code;
    private String name;
    /**
     * 夹具模组
     */
    private String model;
    /**
     * 夹具料号
     */
    private String partNo;
    private String usedFor;
    /**
     * 该夹具在每条产线上需要配备的数量
     */
    private int upl;
    private String owner;
    /**
     * 保养点检周期(天）
     */
    private int pmPeriod;
    private String recOn;
    private String recBy;
    private String editOn;
    private String editBy;

    public void setWorkCell(String workCell) {
        this.workCell = workCell;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public void setUsedFor(String usedFor) {
        this.usedFor = usedFor;
    }

    public void setUpl(int upl) {
        this.upl = upl;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPmPeriod(int pmPeriod) {
        this.pmPeriod = pmPeriod;
    }

    public void setRecOn(String recOn) {
        this.recOn = recOn;
    }

    public void setRecBy(String recBy) {
        this.recBy = recBy;
    }

    public void setEditOn(String editOn) {
        this.editOn = editOn;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getWorkCell() {
        return workCell;
    }

    public String getFamily() {
        return family;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getPartNo() {
        return partNo;
    }

    public String getUsedFor() {
        return usedFor;
    }

    public int getUpl() {
        return upl;
    }

    public String getOwner() {
        return owner;
    }

    public int getPmPeriod() {
        return pmPeriod;
    }

    public String getRecOn() {
        return recOn;
    }

    public String getRecBy() {
        return recBy;
    }

    public String getEditOn() {
        return editOn;
    }

    public String getEditBy() {
        return editBy;
    }
}
