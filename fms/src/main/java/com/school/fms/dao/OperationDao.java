package com.school.fms.dao;

import com.school.fms.entity.FixtureEntity;
import org.springframework.stereotype.Repository;

/**
 * @Author: hujian
 * @Date: 2020/3/28 14:00
 * @Description: <描述>
 */
@Repository
public interface OperationDao {

    void add(FixtureEntity fixtureEntity, int i);
}
