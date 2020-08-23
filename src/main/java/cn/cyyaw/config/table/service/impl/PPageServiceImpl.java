package cn.cyyaw.config.table.service.impl;

import cn.cyyaw.config.table.service.PPageService;
import cn.cyyaw.config.table.table.dao.page.PPageDao;
import cn.cyyaw.config.table.table.entity.page.PPage;
import cn.cyyaw.jpa.BaseDao;

import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PPageServiceImpl extends BaseService<PPage, Integer> implements PPageService {

    @Autowired
    private PPageDao pPageDao;

    @Override
    public BaseDao getBaseDao() {
        return pPageDao;
    }

}

