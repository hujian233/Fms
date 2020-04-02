package com.school.fms.service;

import com.school.fms.entity.FixtureDefine;
import com.school.fms.entity.FixtureEntity;
import com.school.fms.vo.WaitSubmitVo;
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
     *
     * @param file excel文件路径
     * @return list
     */
    List<FixtureDefine> getAllDefineByExcel(String file);

    /**
     * 读取夹具实体Excel表格
     *
     * @param file excel文件路径
     * @return list
     */
    List<FixtureEntity> getAllEntityByExcel(String file);

    /**
     * 夹具定义列表入库
     *
     * @param fixtureDefineList list
     */
    void addFixtureDefines(List<FixtureDefine> fixtureDefineList);

    /**
     * 夹具实体列表入库
     *
     * @param fixtureEntityList list
     */
    void addFixtureEntities(List<FixtureEntity> fixtureEntityList);

    /**
     * 上传文件到服务器
     *
     * @param request 请求
     * @param file    文件
     * @return 文件路径及名称，方便getAllByExcel调用
     */
    List<String> upLoadFile(HttpServletRequest request, MultipartFile file);

    /**
     * 查询夹具定义列表
     *
     * @param code   夹具代码
     * @param name   夹具名称
     * @param family 所属大类
     * @param model  夹具模组
     * @return 夹具定义列表
     */
    List<FixtureDefine> queryDefines(String code, String name, String family, String model);

    /**
     * 查询夹具实体列表
     *
     * @param code  夹具代码
     * @param seqId 序列号
     * @param status 状态
     * @return 夹具实体list
     */
    List<FixtureEntity> queryEntities(String code, String seqId, Integer status);

    /**
     * 查询待提交申请数据
     *
     * @param status 状态
     * @return 夹具实体list
     */
    List<WaitSubmitVo> queryWaitSubmit(Integer status);
    /**
     * 查询一条夹具定义
     *
     * @param code 夹具代码
     * @return FixtureDefine
     */
    FixtureDefine queryDefineDetail(String code);

    /**
     * 添加一个夹具实体
     * @param fixtureEntity 实体信息
     */
    void addFixtureEntity(FixtureEntity fixtureEntity);
}
