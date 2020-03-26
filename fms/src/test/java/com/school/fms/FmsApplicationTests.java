package com.school.fms;

import com.school.fms.entity.FixtureDefine;
import com.school.fms.service.FixtureService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class FmsApplicationTests {

    @Resource
    FixtureService fixtureService;

    @Test
    void contextLoads() {
        List<FixtureDefine> allByExcel = fixtureService.getAllByExcel("D:\\夹具定义.xls");
        fixtureService.addFixtureDefines(allByExcel);
    }

}
