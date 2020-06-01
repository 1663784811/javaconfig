package cn.cyyaw.config.table.service.impl;

import cn.cyyaw.config.table.service.CPageComponentsService;
import cn.cyyaw.config.table.table.dao.CPageComponentsDao;
import cn.cyyaw.config.table.table.entity.CPageComponents;
import cn.cyyaw.jpa.BaseDao;

import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CPageComponentsServiceImpl extends BaseService<CPageComponents, Integer> implements CPageComponentsService {

    @Autowired
    private CPageComponentsDao cPageComponentsDao;

    @Override
    public BaseDao getBaseDao() {
        return cPageComponentsDao;
    }

}

