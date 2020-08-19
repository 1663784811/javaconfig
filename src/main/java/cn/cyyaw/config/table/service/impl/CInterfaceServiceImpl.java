package cn.cyyaw.config.table.service.impl;

import cn.cyyaw.config.table.service.CInterfaceService;
import cn.cyyaw.config.table.table.dao.CInterfaceDao;
import cn.cyyaw.config.table.table.entity.config.CInterface;
import cn.cyyaw.jpa.BaseDao;

import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CInterfaceServiceImpl extends BaseService<CInterface, Integer> implements CInterfaceService {

    @Autowired
    private CInterfaceDao cInterfaceDao;

    @Override
    public BaseDao getBaseDao() {
        return cInterfaceDao;
    }

}

