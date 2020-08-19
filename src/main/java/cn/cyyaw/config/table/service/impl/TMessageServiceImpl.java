package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.TMessageService;
import cn.cyyaw.config.table.table.dao.TMessageDao;
import cn.cyyaw.config.table.table.entity.tadmin.TMessage;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TMessageServiceImpl extends BaseService<TMessage, Integer> implements TMessageService {

    @Autowired
    private TMessageDao tMessageDao;

    @Override
    public BaseDao getBaseDao() {
        return tMessageDao;
    }

}

