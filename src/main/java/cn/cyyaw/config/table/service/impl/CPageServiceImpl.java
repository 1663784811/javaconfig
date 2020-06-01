package cn.cyyaw.config.table.service.impl;

import cn.cyyaw.config.table.service.CPageService;
import cn.cyyaw.config.table.table.dao.CPageDao;
import cn.cyyaw.config.table.table.entity.CPage;
import cn.cyyaw.jpa.BaseDao;

import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CPageServiceImpl extends BaseService<CPage, Integer> implements CPageService {

    @Autowired
    private CPageDao cPageDao;

    @Override
    public BaseDao getBaseDao() {
        return cPageDao;
    }

}

