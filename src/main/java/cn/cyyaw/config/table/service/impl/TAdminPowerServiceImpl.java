package cn.cyyaw.config.table.service.impl;

import cn.cyyaw.config.table.service.TAdminPowerService;
import cn.cyyaw.config.table.table.dao.TAdminPowerDao;
import cn.cyyaw.config.table.table.entity.TAdminPower;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TAdminPowerServiceImpl extends BaseService<TAdminPower, Integer> implements TAdminPowerService {

    @Autowired
    private TAdminPowerDao tAdminPowerDao;

    @Override
    public BaseDao getBaseDao() {
        return tAdminPowerDao;
    }

}

