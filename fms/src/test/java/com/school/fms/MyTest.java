package com.school.fms;

import com.school.fms.entity.FixtureDefine;
import jxl.Sheet;
import jxl.Workbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hujian
 * @Date: 2020/3/26 17:28
 * @Description: Test
 */
public class MyTest {

    @Test
    public void testExcel() {
        List<FixtureDefine> list = new ArrayList<>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File("D:\\夹具定义.xls"));
            // 或者rwb.getSheet(0)
            Sheet rs = rwb.getSheet("夹具定义表");
            // 得到所有的列
            int clos = rs.getColumns();
            // 得到所有的行
            int rows = rs.getRows();
            System.out.println("clos:" + clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    /*String userId = rs.getCell(j++, i).getContents();
                    if (userId == null || "".equals(userId)) {
                        userId = "0";
                    }*/
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
    }
}
