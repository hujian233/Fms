package com.school.fms.service.impl;

import com.school.fms.dao.FixtureDao;
import com.school.fms.entity.FixtureDefine;
import com.school.fms.service.FixtureService;
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
    public List<FixtureDefine> getAllByExcel(String file) {
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
    public void addFixtureDefines(List<FixtureDefine> fixtureDefineList) {
        //fixtureDao.addFixtureDefine(fixtureDefineList);
        for (FixtureDefine fixtureDefine : fixtureDefineList) {
            fixtureDao.addOneFixtureDefine(fixtureDefine);
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
}
