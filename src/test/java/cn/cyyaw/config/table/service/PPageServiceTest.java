package cn.cyyaw.config.table.service;


import cn.cyyaw.common.util.WhyStringUtil;
import cn.cyyaw.config.home.dao.CommonDao;
import cn.cyyaw.config.table.table.dao.PPageDao;
import cn.cyyaw.config.table.table.entity.PPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.UUID;

@Slf4j
@SpringBootTest
public class PPageServiceTest {



    @Autowired
    private PPageDao pPageDao;


    @Autowired
    private CommonDao commonDao;


    @Test
    void test01() {

        commonDao.update(null);



    }





}
