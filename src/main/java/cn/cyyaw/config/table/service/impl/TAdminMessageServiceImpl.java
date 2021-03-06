package cn.cyyaw.config.table.service.impl;

import cn.cyyaw.config.table.service.TAdminMessageService;
import cn.cyyaw.config.table.table.dao.tadmin.TAdminMessageDao;
import cn.cyyaw.config.table.table.entity.tadmin.TAdminMessage;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j
public class TAdminMessageServiceImpl extends BaseService<TAdminMessage, Integer> implements TAdminMessageService {

    @Autowired
    private TAdminMessageDao tAdminMessageDao;

    @Override
    public BaseDao getBaseDao() {
        return tAdminMessageDao;
    }

}

