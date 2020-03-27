package com.school.fms.dao;

import com.school.fms.entity.FixtureDefine;
import com.school.fms.entity.FixtureEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: hujian
 * @Date: 2020/3/26 20:47
 * @Description: 夹具dao层
 */
@Repository
public interface FixtureDao {

    /**
     * 批量添加夹具定义到数据库
     * @param fixtureDefineList 夹具定义列表
     */
    void addFixtureDefine(@Param("fixtureDefineList") List<FixtureDefine> fixtureDefineList);

    /**
     * 添加一条夹具定义到数据库
     * @param fixtureDefine 夹具定义
     */
    void addOneFixtureDefine(@Param("fixtureDefine") FixtureDefine fixtureDefine);

    /**
     * 添加一条夹具实体到数据库
     * @param fixtureEntity 夹具实体
     */
    void addOneFixtureEntity(@Param("fixtureEntity") FixtureEntity fixtureEntity);

}
