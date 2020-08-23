package cn.cyyaw.config.table.service;

import cn.cyyaw.config.table.table.dao.tadmin.TPowerDao;
import cn.cyyaw.config.table.table.entity.tadmin.TPower;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Slf4j
@SpringBootTest
public class TPowerDaoTest {


    @Autowired
    private TPowerDao tPowerDao;


    @Test
    public void test01(){
        TPower t_1 = new TPower();
        t_1.setCode("001");
        t_1.setCreatetime(new Date());


        TPower save = tPowerDao.save(t_1);




    }


}
