package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.PPageComponentsService;
import cn.cyyaw.config.table.table.dao.page.PPageComponentsDao;
import cn.cyyaw.config.table.table.entity.page.PPageComponents;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PPageComponentsServiceImpl extends BaseService<PPageComponents, Integer> implements PPageComponentsService {

    @Autowired
    private PPageComponentsDao pPageComponentsDao;

    @Override
    public BaseDao getBaseDao() {
        return pPageComponentsDao;
    }

}

