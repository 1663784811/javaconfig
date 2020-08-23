package cn.cyyaw.config.table.service.impl;

import cn.cyyaw.config.table.service.CComponentsService;
import cn.cyyaw.config.table.table.dao.config.CComponentsDao;
import cn.cyyaw.config.table.table.entity.config.CComponents;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CComponentsServiceImpl extends BaseService<CComponents, Integer> implements CComponentsService {

    @Autowired
    private CComponentsDao cComponentsDao;

    @Override
    public BaseDao getBaseDao() {
        return cComponentsDao;
    }

}

