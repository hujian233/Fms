package com.school.fms.service;

import com.school.fms.entity.FixtureDefine;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: hujian
 * @Date: 2020/3/26 16:00
 * @Description: 夹具定义业务
 */
public interface FixtureService {

    /**
     * 读取夹具定义Excel表格
     * @param file excel文件路径
     * @return list
     */
    List<FixtureDefine> getAllByExcel(String file);

    /**
     * 夹具定义列表入库
     * @param fixtureDefineList list
     */
    void  addFixtureDefines(List<FixtureDefine> fixtureDefineList);

    /**
     * 上传文件到服务器
     * @param request 请求
     * @param file 文件
     * @return 文件列表
     */
    List<String> upLoadFile(HttpServletRequest request, MultipartFile file);

}
