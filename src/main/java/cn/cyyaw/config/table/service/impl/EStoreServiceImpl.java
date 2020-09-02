package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.EStoreService;
import cn.cyyaw.config.table.table.dao.enterprise.EStoreDao;
import cn.cyyaw.config.table.table.entity.enterprise.EStore;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class EStoreServiceImpl extends BaseService<EStore, Integer> implements EStoreService {

    @Autowired
    private EStoreDao eStoreDao;

    @Override
    public BaseDao getBaseDao() {
        return eStoreDao;
    }

}

