package com.school.fms.service.impl;

import com.school.fms.dao.FixtureDao;
import com.school.fms.entity.FixtureDefine;
import com.school.fms.entity.FixtureEntity;
import com.school.fms.service.FixtureService;
import com.school.fms.vo.WaitSubmitVo;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: hujian
 * @Date: 2020/3/26 16:20
 * @Description: 夹具定义业务
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FixtureServiceImpl implements FixtureService {

    @Resource
    FixtureDao fixtureDao;

    @Override
    public List<FixtureDefine> getAllDefineByExcel(String file) {
        List<FixtureDefine> list = new ArrayList<>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(file));
            // 或者rwb.getSheet(0)
            Sheet rs = rwb.getSheet("夹具定义表");
            // 得到所有的列
            int clos = rs.getColumns();
            // 得到所有的行
            int rows = rs.getRows();
            System.out.println("clos:" + clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //默认最左边编号也算一列，所以用j++
                    FixtureDefine fixtureDefine = new FixtureDefine();
                    fixtureDefine.setWorkCell(rs.getCell(j++, i).getContents());
                    fixtureDefine.setFamily(rs.getCell(j++, i).getContents());
                    fixtureDefine.setCode(rs.getCell(j++, i).getContents());
                    fixtureDefine.setName(rs.getCell(j++, i).getContents());
                    fixtureDefine.setModel(rs.getCell(j++, i).getContents());
                    fixtureDefine.setPartNo(rs.getCell(j++, i).getContents());
                    fixtureDefine.setUsedFor(rs.getCell(j++, i).getContents());
                    fixtureDefine.setUpl(Integer.parseInt(rs.getCell(j++, i).getContents()));
                    fixtureDefine.setOwner(rs.getCell(j++, i).getContents());
                    fixtureDefine.setPmPeriod(Integer.parseInt(rs.getCell(j++, i).getContents()));
                    fixtureDefine.setRecOn(rs.getCell(j++, i).getContents());
                    fixtureDefine.setRecBy(rs.getCell(j++, i).getContents());
                    fixtureDefine.setEditOn(rs.getCell(j++, i).getContents());
                    fixtureDefine.setEditBy(rs.getCell(j++, i).getContents());
                    list.add(fixtureDefine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<FixtureEntity> getAllEntityByExcel(String file) {
        List<FixtureEntity> list = new ArrayList<>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(file));
            // 或者rwb.getSheet(0)
            Sheet rs = rwb.getSheet(0);
            // 得到所有的列
            int clos = rs.getColumns();
            // 得到所有的行
            int rows = rs.getRows();
            System.out.println("clos:" + clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //默认最左边编号也算一列，所以用j++
                    FixtureEntity fixtureEntity = new FixtureEntity();
                    fixtureEntity.setCode(rs.getCell(j++, i).getContents());
                    fixtureEntity.setSeqId(rs.getCell(j++, i).getContents());
                    fixtureEntity.setBillNo(rs.getCell(j++, i).getContents());
                    fixtureEntity.setRegDate(rs.getCell(j++, i).getContents());
                    fixtureEntity.setUsedCount(Integer.parseInt(rs.getCell(j++, i).getContents()));
                    fixtureEntity.setLocation(rs.getCell(j++, i).getContents());
                    list.add(fixtureEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addFixtureDefines(List<FixtureDefine> fixtureDefineList) {
        //fixtureDao.addFixtureDefines(fixtureDefineList);
        for (FixtureDefine fixtureDefine : fixtureDefineList) {
            fixtureDao.addOneFixtureDefine(fixtureDefine);
        }
    }

    @Override
    public void addFixtureEntities(List<FixtureEntity> fixtureEntityList) {
        for (FixtureEntity fixtureEntity : fixtureEntityList) {
            fixtureDao.addOneFixtureEntity(fixtureEntity);
        }
    }

    @Override
    public List<String> upLoadFile(HttpServletRequest request, MultipartFile file) {
        List<String> lst = new ArrayList<>();
        String fileName = file.getOriginalFilename();
        String[] sArray = fileName.split("\\.");

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        String dt = ft.format(dNow);
        String uploadFileName = sArray[0] + "_" + dt + "." + sArray[1];
        String filePath = request.getServletContext().getRealPath("poiUploadedFile/") + uploadFileName;

        File dest = new File(filePath);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lst.add(filePath);
        lst.add(dt);
        return lst;
    }

    @Override
    public List<FixtureDefine> queryDefines(String code, String name, String family, String model) {
        return fixtureDao.queryDefines(code, name, family, model);
    }

    @Override
    public List<FixtureEntity> queryEntities(String code, String seqId, Integer status) {
        return fixtureDao.queryEntities(code, seqId, status);
    }

    @Override
    public List<WaitSubmitVo> queryWaitSubmit(Integer status) {
        return fixtureDao.queryWaitSubmit(status);
    }

    @Override
    public FixtureDefine queryDefineDetail(String code) {
        return fixtureDao.queryDefineDetail(code);
    }
}
