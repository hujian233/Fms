package com.school.fms.dao;

import com.school.fms.entity.*;
import org.springframework.stereotype.Repository;

/**
 * @Author: hujian
 * @Date: 2020/3/28 14:00
 * @Description: <描述>
 */
@Repository
public interface OperationDao {

    void addToInbound(Inbound inbound);

    void addToOutbound(Outbound outbound);

    void addToRepair(Repair repair);

    void addToScrap(Scrap scrap);
}
