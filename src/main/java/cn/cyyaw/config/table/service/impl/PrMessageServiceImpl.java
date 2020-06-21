package cn.cyyaw.config.table.service.impl;

import cn.cyyaw.config.table.service.PrMessageService;
import cn.cyyaw.config.table.table.dao.PrMessageDao;
import cn.cyyaw.config.table.table.entity.PrMessage;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PrMessageServiceImpl extends BaseService<PrMessage, Integer> implements PrMessageService {

    @Autowired
    private PrMessageDao prMessageDao;

    @Override
    public BaseDao getBaseDao() {
        return prMessageDao;
    }

}

