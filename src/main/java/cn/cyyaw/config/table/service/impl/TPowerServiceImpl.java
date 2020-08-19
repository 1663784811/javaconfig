package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.TPowerService;
import cn.cyyaw.config.table.table.dao.TPowerDao;
import cn.cyyaw.config.table.table.entity.tadmin.TPower;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TPowerServiceImpl extends BaseService<TPower, Integer> implements TPowerService {

    @Autowired
    private TPowerDao tPowerDao;

    @Override
    public BaseDao getBaseDao() {
        return tPowerDao;
    }

}

