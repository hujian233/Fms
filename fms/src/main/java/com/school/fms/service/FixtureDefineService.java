package com.school.fms.service;

import com.school.fms.entity.FixtureDefine;

import java.util.List;

/**
 * @Author: hujian
 * @Date: 2020/3/26 16:00
 * @Description: 夹具定义业务
 */
public interface FixtureDefineService {

    /**
     * 读取夹具定义Excel表格
     * @param file excel文件路径
     * @return list
     */
    List<FixtureDefine> getAllByExcel(String file);
}
