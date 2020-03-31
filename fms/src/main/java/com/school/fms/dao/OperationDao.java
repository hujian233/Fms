package com.school.fms.dao;

import com.school.fms.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: hujian
 * @Date: 2020/3/28 14:00
 * @Description: 四张操作表的处理
 */
@Repository
public interface OperationDao {

    //添加数据

    void addToInbound(Inbound inbound);

    void addToOutbound(Outbound outbound);

    void addToRepair(Repair repair);

    void addToScrap(Scrap scrap);

    //修改状态

    void updateInbound(@Param("id") int id, @Param("result") int result);

    void updateOutbound(@Param("id")int id, @Param("result")int result);

    void updateRepair(@Param("id")int id, @Param("result")int result);

    void updateScrap(@Param("id")int id, @Param("result")int result);

    //查询数据

    Inbound queryInbound(@Param("orderId")int orderId);

    Outbound queryOutbound(@Param("orderId")int orderId);

    Repair queryRepair(@Param("orderId")int orderId);

    Scrap queryScrap(@Param("orderId")int orderId);

}
