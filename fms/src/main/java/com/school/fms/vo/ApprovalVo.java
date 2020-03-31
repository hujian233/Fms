package com.school.fms.vo;

import java.util.List;

/**
 * @Author: hujian
 * @Date: 2020/3/31 8:49
 * @Description: 审批申请结果
 */
public class ApprovalVo {

    /**
     * 类型：2：入库，3：出库，4：报修，5：报废
     */
    private int type;
    /**
     * 订单号
     */
    private List<Integer> orderIds;
    /**
     * 审批结果，0：同意，1：驳回
     */
    private int result;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
