package cn.cyyaw.config.table.service.impl;

import cn.cyyaw.config.table.service.CFieldService;
import cn.cyyaw.config.table.table.dao.CFieldDao;
import cn.cyyaw.config.table.table.entity.CField;
import cn.cyyaw.jpa.BaseDao;

import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CFieldServiceImpl extends BaseService<CField, Integer> implements CFieldService {

    @Autowired
    private CFieldDao cFieldDao;

    @Override
    public BaseDao getBaseDao() {
        return cFieldDao;
    }

}

