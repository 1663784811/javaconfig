package cn.cyyaw.config.table.service.impl;

import cn.cyyaw.config.table.service.CTableService;
import cn.cyyaw.config.table.table.dao.CTableDao;
import cn.cyyaw.config.table.table.entity.CTable;
import cn.cyyaw.jpa.BaseDao;

import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CTableServiceImpl extends BaseService<CTable, Integer> implements CTableService {

    @Autowired
    private CTableDao cTableDao;

    @Override
    public BaseDao getBaseDao() {
        return cTableDao;
    }

}

