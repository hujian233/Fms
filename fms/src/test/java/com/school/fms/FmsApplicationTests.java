package com.school.fms;

import com.school.fms.entity.FixtureDefine;
import com.school.fms.entity.FixtureEntity;
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
        List<FixtureEntity> allByExcel = fixtureService.getAllEntityByExcel("D:\\夹具实体.xls");
        fixtureService.addFixtureEntities(allByExcel);
    }

}
