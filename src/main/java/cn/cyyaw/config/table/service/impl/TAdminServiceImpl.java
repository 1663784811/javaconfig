package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.TAdminService;
import cn.cyyaw.config.table.table.dao.tadmin.TAdminDao;
import cn.cyyaw.config.table.table.entity.tadmin.TAdmin;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TAdminServiceImpl extends BaseService<TAdmin, Integer> implements TAdminService {

    @Autowired
    private TAdminDao tAdminDao;

    @Override
    public BaseDao getBaseDao() {
        return tAdminDao;
    }

}

