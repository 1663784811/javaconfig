package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.TAdminRoleService;
import cn.cyyaw.config.table.table.dao.tadmin.TAdminRoleDao;
import cn.cyyaw.config.table.table.entity.tadmin.TAdminRole;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TAdminRoleServiceImpl extends BaseService<TAdminRole, Integer> implements TAdminRoleService {

    @Autowired
    private TAdminRoleDao tAdminRoleDao;

    @Override
    public BaseDao getBaseDao() {
        return tAdminRoleDao;
    }

}

