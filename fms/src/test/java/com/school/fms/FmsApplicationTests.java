package com.school.fms;

import com.school.fms.service.FixtureDefineService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class FmsApplicationTests {

    @Resource
    FixtureDefineService fixtureDefineService;

    @Test
    void contextLoads() {
        fixtureDefineService.getAllByExcel("D:\\夹具定义.xls");
    }

}
