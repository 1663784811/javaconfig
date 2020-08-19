package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.TRoleService;
import cn.cyyaw.config.table.table.dao.TRoleDao;
import cn.cyyaw.config.table.table.entity.tadmin.TRole;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TRoleServiceImpl extends BaseService<TRole, Integer> implements TRoleService {

    @Autowired
    private TRoleDao tRoleDao;

    @Override
    public BaseDao getBaseDao() {
        return tRoleDao;
    }

}

