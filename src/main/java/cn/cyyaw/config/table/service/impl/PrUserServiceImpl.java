package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.PrUserService;
import cn.cyyaw.config.table.table.dao.PrUserDao;
import cn.cyyaw.config.table.table.entity.PrUser;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PrUserServiceImpl extends BaseService<PrUser, Integer> implements PrUserService {

    @Autowired
    private PrUserDao prUserDao;

    @Override
    public BaseDao getBaseDao() {
        return prUserDao;
    }

}

