package cn.cyyaw.config.table.service.impl;

import cn.cyyaw.config.table.service.TRolePowerService;
import cn.cyyaw.config.table.table.dao.TRolePowerDao;
import cn.cyyaw.config.table.table.entity.TRolePower;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TRolePowerServiceImpl extends BaseService<TRolePower, Integer> implements TRolePowerService {

    @Autowired
    private TRolePowerDao tRolePowerDao;

    @Override
    public BaseDao getBaseDao() {
        return tRolePowerDao;
    }

}

