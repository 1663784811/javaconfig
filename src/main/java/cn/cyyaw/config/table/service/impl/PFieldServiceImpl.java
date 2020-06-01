package cn.cyyaw.config.table.service.impl;


import cn.cyyaw.config.table.service.PFieldService;
import cn.cyyaw.config.table.table.dao.PFieldDao;
import cn.cyyaw.config.table.table.entity.PField;
import cn.cyyaw.jpa.BaseDao;
import cn.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PFieldServiceImpl extends BaseService<PField, Integer> implements PFieldService {

    @Autowired
    private PFieldDao pFieldDao;

    @Override
    public BaseDao getBaseDao() {
        return pFieldDao;
    }

}

